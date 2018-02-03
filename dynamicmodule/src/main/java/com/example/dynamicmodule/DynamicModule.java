package com.example.dynamicmodule;

import android.os.Build;

public class DynamicModule implements IDynamicModule {

    @Override
    public String getText() {
        return Build.DEVICE;
    }
}
