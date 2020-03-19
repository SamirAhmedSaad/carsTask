package com.g22solutions.carsapp.screens.carsActivityPackage;

import com.g22solutions.carsapp.retrofitApi.RetrofitApi;
import com.g22solutions.carsapp.retrofitServiceDataModel.CarsDataModel;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import javax.inject.Inject;

public class CarsNetworkImp implements CarsNetwork {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    RetrofitApi retrofitApi;

    @Inject
    public CarsNetworkImp() {
    }


    @Override
    public void getCarsList(int page, CarsNetworkCommunicator carsNetworkCommunicator) {
        retrofitApi.getCars(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(response -> {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() != 1) {
                            carsNetworkCommunicator.showAlert(response.body().getError().getMessage());
                            return false;
                        }
                    }
                    return true;
                }).subscribe(new MaybeObserver<Response<CarsDataModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(Response<CarsDataModel> carDataModelResponse) {
                carsNetworkCommunicator.carsLoaded(carDataModelResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                carsNetworkCommunicator.showAlert("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onClear() {
        compositeDisposable.clear();
    }
}
