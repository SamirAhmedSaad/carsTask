package com.g22solutions.carsapp.screens.carsActivityPackage;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.g22solutions.carsapp.retrofitServiceDataModel.CarsDataModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CarsViewModelImp extends ViewModel implements CarsViewModel, CarsNetwork.CarsNetworkCommunicator {

    MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    MutableLiveData<String> showAlertLiveData = new MutableLiveData<>();
    MutableLiveData<List<CarsDataModel.DataBean>> carsLoaded = new MutableLiveData();
    MutableLiveData<Boolean> stopPaging = new MutableLiveData<>();

    List<CarsDataModel.DataBean> carList = new ArrayList<>();

    @Inject
    CarsNetworkImp carsNetworkImp;

    @Inject
    public CarsViewModelImp() {
    }

    @Override
    public void getCars(int page) {
        progressLiveData.setValue(true);
        carsNetworkImp.getCarsList(page,this);
    }

    @Override
    public void carsLoaded(CarsDataModel carDataModel) {
        progressLiveData.setValue(false);

        //if i know size check will based on page size
//            if(carDataModel.getData().size() < PAGE_SIZE){
//                stopPaging.postValue(true);
//            }

        if(carDataModel.getData() != null) {
            carList.addAll(carDataModel.getData());
            carsLoaded.setValue(carList);
        }else {
            stopPaging.setValue(true);
        }
    }

    @Override
    public void showAlert(String msg) {
        progressLiveData.setValue(false);
        stopPaging.setValue(true);
        if(!TextUtils.isEmpty(msg))
            showAlertLiveData.setValue(msg);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        carsNetworkImp.onClear();
    }
}
