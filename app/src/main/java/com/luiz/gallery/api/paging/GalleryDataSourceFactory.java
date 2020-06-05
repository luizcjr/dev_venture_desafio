package com.luiz.gallery.api.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.luiz.gallery.ui.fragments.gallery.GalleryRepository;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Classe factory da classe GalleryDataSource
 */
public class GalleryDataSourceFactory extends DataSource.Factory {

    private CompositeDisposable disposable;
    private GalleryRepository galleryRepository;
    public MutableLiveData<Boolean> loading;
    public MutableLiveData<String> loadError;
    private String query;

    public GalleryDataSourceFactory(CompositeDisposable disposable, GalleryRepository galleryRepository, MutableLiveData<Boolean> loading, MutableLiveData<String> loadError, String query) {
        this.disposable = disposable;
        this.galleryRepository = galleryRepository;
        this.loading = loading;
        this.loadError = loadError;
        this.query = query;
    }

    @NonNull
    @Override
    public DataSource create() {
        GalleryDataSource galleryDataSource = new GalleryDataSource(disposable, galleryRepository, loading, loadError, query);

        loadError = galleryDataSource.getLoadError();
        loading = galleryDataSource.getLoading();

        return galleryDataSource;
    }
}
