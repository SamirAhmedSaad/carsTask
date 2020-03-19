package com.g22solutions.carsapp.di.scope;


import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface MyMapKey {
    Class<? extends ViewModel> value();
}