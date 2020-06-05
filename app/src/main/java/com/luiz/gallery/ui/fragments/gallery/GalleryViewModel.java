package com.luiz.gallery.ui.fragments.gallery;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.luiz.gallery.api.model.Gallery;
import com.luiz.gallery.api.paging.GalleryDataSourceFactory;
import com.luiz.gallery.ui.base.BaseViewModel;

/**
 * Classe de view model onde é criado os live datas para o fragment observar
 */
public class GalleryViewModel extends BaseViewModel {

    private GalleryRepository galleryRepository;
    private LiveData<PagedList<Gallery>> galleryPaged;

    /* Construtor onde instancio o repository e chamo a função para fazer a request das imagens passando a query cats */
    public GalleryViewModel() {
        galleryRepository = new GalleryRepository();
        getGalleryPaged("cats");
    }

    /*
     * Função onde instancio o GalleryDataSourceFactory e faço a config da paginação.
     * Na config da paginação passa o tanto de itens que quer que retorna por página (20),
     * uma dica (hint) do tanto que pode carregar inicialmente,
     * a distância que pode se carregar quando os itens não estão sendo exibidos na tela,
     * e se ativa o place holder. Por último é atribuido o valor a variável de live data.
     * */
    private void getGalleryPaged(String query) {
        GalleryDataSourceFactory galleryDataSourceFactory = new GalleryDataSourceFactory(disposable, galleryRepository, loading, loadError, query);

        int pageSize = 20;
        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(true)
                .build();

        galleryPaged = new LivePagedListBuilder<>(galleryDataSourceFactory, config).build();
    }

    /* Função criado para se acessar no fragment, para no fragment observar e retornar o valor que a variável galleryPaged está recebendo. */
    public LiveData<PagedList<Gallery>> getGallery() {
        return galleryPaged;
    }
}
