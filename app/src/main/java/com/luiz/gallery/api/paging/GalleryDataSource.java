package com.luiz.gallery.api.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.luiz.gallery.api.callback.Callback;
import com.luiz.gallery.api.model.Gallery;
import com.luiz.gallery.api.responses.GalleryResponse;
import com.luiz.gallery.ui.fragments.gallery.GalleryRepository;
import com.luiz.gallery.util.Utils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Classe responsável por fazer as requests da paginação.
 */
public class GalleryDataSource extends PageKeyedDataSource<Integer, Gallery> {

    private CompositeDisposable disposable;
    private GalleryRepository galleryRepository;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> loadError;
    private String query;

    public GalleryDataSource(CompositeDisposable disposable, GalleryRepository galleryRepository, MutableLiveData<Boolean> loading, MutableLiveData<String> loadError, String query) {
        this.disposable = disposable;
        this.galleryRepository = galleryRepository;
        this.loading = loading;
        this.loadError = loadError;
        this.query = query;
    }

    /**
     * Função que faz o carregamento das primeiras informções da requisição. Com isso a página inicial sempre será a 0, e a página adjacente a 1.
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Gallery> callback) {
        createInitialLoad(0, 1, callback, query);
    }

    /**
     * Função que faz o caregamento da página anterior a atual. Ela pega a referência a página atual e diminui em 1 para trazer a página anterior.
     * @param params
     * @param callback
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Gallery> callback) {
        int page = params.key;
        createLoads(page, page - 1, callback, query);
    }

    /**
     * Função que faz o carregamento da página posterior da atual. Ela pega a referência da página atual e acrescenta 1 para trazer as informações da próxima página.
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Gallery> callback) {
        int page = params.key;
        createLoads(page, page + 1, callback, query);
    }

    /**
     * Função criada para fazer a primeira requisição. Eu passo a referência da página que quer acessar, a referência a próxima página, o callback necessário
     * da função do loadInitial e a query, que no caso é "cats".
     * @param requestedPage
     * @param adjacentPage
     * @param callback
     * @param query
     */
    private void createInitialLoad(int requestedPage, int adjacentPage, LoadInitialCallback<Integer, Gallery> callback, String query) {
        Callback<GalleryResponse, Throwable> callback1 = new Callback<GalleryResponse, Throwable>() {
            @Override
            public void onSuccess(GalleryResponse response) {
                afterRequest();
                callback.onResult(response.getData(), null, adjacentPage);
            }

            @Override
            public void onError(Throwable throwable) {
                afterRequest(throwable);
            }
        };

        beforeRequest();

        disposable.add(
                galleryRepository.getGallery(requestedPage, query, callback1)
        );
    }

    /**
     * Função criada para fazer as requisições de página anterior e posterior. Eu passo a referência da página que quer acessar, a referência a próxima página ou página anterior,
     * o callback necessário das outras funções e a query, que no caso é "cats".
     * @param requestedPage
     * @param adjacentPage
     * @param callback
     * @param query
     */
    private void createLoads(int requestedPage, int adjacentPage, LoadCallback<Integer, Gallery> callback, String query) {
        Callback<GalleryResponse, Throwable> callback1 = new Callback<GalleryResponse, Throwable>() {
            @Override
            public void onSuccess(GalleryResponse response) {
                afterRequest();
                callback.onResult(response.getData(), adjacentPage);
            }

            @Override
            public void onError(Throwable throwable) {
                afterRequest(throwable);
            }
        };

        beforeRequest();
        disposable.add(
                galleryRepository.getGallery(requestedPage, query, callback1)
        );
    }

    /**
     * Função em que atualizo o valor do live data loading, que significa que é para exibir ao usuário o progress bar.
     */
    private void beforeRequest() {
        this.loading.postValue(true);
    }

    /**
     * Função em que atualizo o valor do live data loading, que significa que é para não exibir ao usuário o progress bar.
     */
    private void afterRequest() {
        this.loading.postValue(false);
    }

    /**
     * Função em que atualizo o valor do live data loading, que significa que é para não exibir ao usuário o progress bar.
     * E também nessa função passo um Throwable para verificar qual o erro está retornando e exibo para o usuário.
     * @param e
     */
    private void afterRequest(Throwable e) {
        this.loading.postValue(false);
        this.loadError.postValue(Utils.getMessageErrorObject(e));
    }

    /**
     * Função criada para retornar o valor de loading (true ou false).
     * @return
     */
    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    /**
     * Função criada para retornar a string de erro, retornada pelo servidor.
     * @return
     */
    public MutableLiveData<String> getLoadError() {
        return loadError;
    }
}
