package com.luiz.gallery.api.services;

import com.luiz.gallery.api.constants.Constants;
import com.luiz.gallery.api.responses.GalleryResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface utilizada para fazer os métodos de request
 */
public interface ApiDataSource {

    @GET(Constants.GALLERY)
    Single<GalleryResponse> getGallery(@Query("page") int page, @Query("q") String query);
}
