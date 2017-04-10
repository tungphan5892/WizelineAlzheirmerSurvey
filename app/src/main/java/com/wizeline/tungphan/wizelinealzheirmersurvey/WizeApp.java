package com.wizeline.tungphan.wizelinealzheirmersurvey;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.DaggerAppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.AppModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.EventBusModule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * Created by tungphan on 4/8/17.
 */

public class WizeApp extends Application {
    private static final String TAG = WizeApp.class.getSimpleName();
    private static WizeApp wizeApp;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        wizeApp = this;
        initInjector();
    }

    public static WizeApp getInstance() {
        return wizeApp;
    }

    private void initInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .eventBusModule(new EventBusModule())
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((WizeApp) context.getApplicationContext()).appComponent;
    }

    //copy all assets file to external directory
    public Callable<Boolean> copyAssets() {
        return () -> {
            AssetManager assetManager = getAssets();
            String[] files = null;
            try {
                files = assetManager.list("");
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
            if (files != null) for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);
                    File outFile = new File(getExternalFilesDir(null), filename);
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                    return false;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
            return true;
        };
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
