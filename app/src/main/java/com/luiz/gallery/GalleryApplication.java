package com.luiz.gallery;

import android.app.Application;
import android.content.Context;

public class GalleryApplication extends Application {
    /**
     * Instance
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        /* Set context */
        context = getApplicationContext();
    }

    public static Context getInstance() {
        return context;
    }
}
