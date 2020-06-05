package com.luiz.gallery.ui.fragments.gallery;

import com.luiz.gallery.api.callback.Callback;
import com.luiz.gallery.api.responses.GalleryResponse;
import com.luiz.gallery.ui.base.BaseRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Classe repository, onde serão feitas todas as requisições para a api
 */
public class GalleryRepository extends BaseRepository {

    /* Função que retorna um disposable fazendo a requisição para a api.
    Foi criado um callback para chamar no onError e no onSuccess para fazer as tratativas necessárias. */
    public Disposable getGallery(int requestedPage, String query, Callback<GalleryResponse, Throwable> callback) {
        return apiRepository.getGallery(requestedPage, query)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(callback::onError)
                .subscribe(callback::onSuccess, callback::onError);
    }
}
