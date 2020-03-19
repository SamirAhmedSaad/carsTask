package com.g22solutions.carsapp.screens.carsActivityPackage;

import com.g22solutions.carsapp.retrofitServiceDataModel.CarsDataModel;

public interface CarsNetwork {

    interface CarsNetworkCommunicator{

        void carsLoaded(CarsDataModel carDataModel);

        void showAlert(String msg);

    }

    void getCarsList(int page , CarsNetworkCommunicator carsNetworkCommunicator);

    void onClear();

}
