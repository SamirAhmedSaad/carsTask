package com.g22solutions.carsapp.screens.carsActivityPackage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.g22solutions.carsapp.databinding.CarsRowBinding;
import com.g22solutions.carsapp.retrofitServiceDataModel.CarsDataModel;

import java.util.List;

public class CarsRecyclerAdapter extends RecyclerView.Adapter<CarsRecyclerAdapter.MyHolder> {

    List<CarsDataModel.DataBean> list;

    public CarsRecyclerAdapter(List<CarsDataModel.DataBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(CarsRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.carsRowBinding.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        CarsRowBinding carsRowBinding;

        public MyHolder(@NonNull CarsRowBinding carsRowBinding) {
            super(carsRowBinding.getRoot());
            this.carsRowBinding = carsRowBinding;
        }

    }

}
