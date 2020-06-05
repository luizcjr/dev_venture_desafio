package com.luiz.gallery.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;

import com.luiz.gallery.R;
import com.luiz.gallery.ui.adapter.NoResultAdapter;
import com.luiz.gallery.ui.custom_view.AlertDefault;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Classe auxiliar onde mantenho funções que se usa com maior frequência no projeto.
 */
public class Utils {
    private static Dialog dialog;

    public static void onStopLoading() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void onStartLoading(Context context) {
        dialog = loadingDialog(context);
        dialog.show();
    }

    @SuppressLint("ObsoleteSdkInt")
    public static Dialog loadingDialog(final Context ctx) {
        Dialog loading = new Dialog(ctx);
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading.setContentView(R.layout.dialog_loading);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(loading.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        loading.setCanceledOnTouchOutside(false);
        loading.setCancelable(false);
        return loading;
    }

    public static void alertError(Context context, String message) {
        AlertDefault alertDefault = new AlertDefault(context, "Erro!", message, true);
        alertDefault.show();
    }

    public static String getMessageErrorObject(Throwable e) {
        String loadError = "";

        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            try {
                JSONObject jsonObject = new JSONObject(responseBody.string());
                loadError = jsonObject.getString("error");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (e instanceof SocketTimeoutException) {
            loadError = e.getMessage();
        } else if (e instanceof IOException) {
            loadError = e.getMessage();
        } else {
            loadError = e.getMessage();
        }

        return loadError;
    }

    public static NoResultAdapter noResultAdapter(Context context, String message, int image) {
        return new NoResultAdapter(context, message, R.color.colorGreyAdapter, image, 0);
    }
}
