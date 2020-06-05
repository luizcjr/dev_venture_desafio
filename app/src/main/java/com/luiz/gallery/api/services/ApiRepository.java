package com.luiz.gallery.api.services;

import com.luiz.gallery.api.responses.GalleryResponse;
import com.luiz.gallery.di.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Classe de repository da api que contém as funções de chamada da interface
 */
public class ApiRepository {

    @Inject
    public ApiDataSource api;

    public ApiRepository() {
        DaggerApiComponent.create().inject(this);
    }

    public Single<GalleryResponse> getGallery(int page, String query) {
        return api.getGallery(page, query);
    }
}
