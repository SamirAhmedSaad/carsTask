package com.g22solutions.carsapp.NoConnectionPackage;


import android.content.Context;
import android.widget.Toast;
import com.g22solutions.carsapp.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ConnectivityInterceptor implements Interceptor {

    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        if (NetworkUtil.isOnline(context)) {

            builder.header("Cache-Control", "public, max-age=" + 5);
        }else {

            Observable.just(context.getString(R.string.noConnection))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(msg -> Toast.makeText(context,msg,Toast.LENGTH_SHORT).show());

            builder.header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                    .removeHeader("Pragma")
                    .build();
        }

        return chain.proceed(builder.build());
    }


}
