package com.g22solutions.carsapp.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.g22solutions.carsapp.R;
import com.g22solutions.carsapp.di.MyViewModelProviders;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

public abstract class BaseActivityJ<T extends ViewDataBinding, V extends ViewModel> extends DaggerAppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    MyViewModelProviders myViewModelProviders;

    protected T dataBinding;

    protected V viewModel;


    protected abstract int resourceId();

    protected abstract Class<V> getViewModelClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, resourceId());
        dataBinding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this, myViewModelProviders).get(getViewModelClass());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.exit_slide_right);
    }

}