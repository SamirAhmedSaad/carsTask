package com.g22solutions.carsapp.screens.carsActivityPackage;

import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import com.g22solutions.carsapp.R;
import com.g22solutions.carsapp.base.BaseActivityJ;
import com.g22solutions.carsapp.base.PagingRecyclerListner;
import com.g22solutions.carsapp.databinding.ActivityCarsBinding;

import static android.view.View.VISIBLE;
import static com.g22solutions.carsapp.base.MyUtilsKt.myToast;

public class CarsActivity extends BaseActivityJ<ActivityCarsBinding, CarsViewModelImp> {

    int page = 1;

    boolean lastPage = false;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected int resourceId() {
        return R.layout.activity_cars;
    }

    @Override
    protected Class<CarsViewModelImp> getViewModelClass() {
        return CarsViewModelImp.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding.setToolTitle(getString(R.string.cars));

        linearLayoutManager = (LinearLayoutManager) dataBinding.carsRecycler.getLayoutManager();

        viewModel.getCars(page++);

        dataBinding.carsRecycler.addOnScrollListener(new PagingRecyclerListner(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                viewModel.getCars(page++);
            }

            @Override
            public boolean isLastPage() {
                return lastPage;
            }

            @Override
            public boolean isLoading() {
                return dataBinding.progress.getVisibility() == VISIBLE;
            }
        });

        observe();

        clicks();

    }

    private void clicks() {

        dataBinding.refreshLayout.setOnRefreshListener(() -> {
            dataBinding.refreshLayout.setRefreshing(false);

            page = 1;
            lastPage = false;
            viewModel.carList.clear();
            dataBinding.carsRecycler.setAdapter(null);
            viewModel.getCars(page++);

        });

    }

    private void observe() {

        viewModel.progressLiveData.observe(this, visible -> {
            if (visible) {
                dataBinding.progress.setVisibility(VISIBLE);
            } else {
                dataBinding.progress.setVisibility(View.GONE);
            }
        });

        viewModel.showAlertLiveData.observe(this, msg -> {
            myToast(this, msg);
        });

        viewModel.stopPaging.observe(this, it -> {
            lastPage = it;
        });

        viewModel.carsLoaded.observe(this, carsList -> {

                    if (dataBinding.carsRecycler.getAdapter() != null) {
                        dataBinding.carsRecycler.getAdapter().notifyItemInserted(linearLayoutManager.getItemCount());
                    } else {
                        dataBinding.carsRecycler.setAdapter(new CarsRecyclerAdapter(carsList));
                        dataBinding.carsRecycler.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.item_recycler_anim));
                        dataBinding.carsRecycler.scheduleLayoutAnimation();
                    }

                }
        );

    }

}
