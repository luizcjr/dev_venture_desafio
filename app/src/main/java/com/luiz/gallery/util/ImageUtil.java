package com.luiz.gallery.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luiz.gallery.R;

/** Classe auxiliar para images.
 * Ela possui uma função para criar um circular progress enquanto a imagem carrega.
 * Também possui uma função onde foi criado request options do glide.
 * Ese request options possui algumas funções como criar oo cache das imagens,
 * mostrar o circular progress no placeholder, exibir as imagens center crop
 * e mostrar uma imagem caso não consiga carregar as images.
 * E por último tem um binding adapter para carregar a imagem direto no xml utilizando o databinding. */

public class ImageUtil {

    static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    static void loadImage(ImageView imageView, String uri, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(progressDrawable)
                .fitCenter()
                .error(R.drawable.image_not_found);

        GlideApp.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(uri)
                .thumbnail(0.5f)
                .into(imageView);
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view, String url) {
        loadImage(view, url, getProgressDrawable(view.getContext()));
    }
}
