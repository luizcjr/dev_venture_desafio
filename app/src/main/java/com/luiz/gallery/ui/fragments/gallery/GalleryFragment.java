package com.luiz.gallery.ui.fragments.gallery;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.luiz.gallery.BR;
import com.luiz.gallery.R;
import com.luiz.gallery.api.model.Gallery;
import com.luiz.gallery.databinding.GalleryBinding;
import com.luiz.gallery.ui.fragments.gallery.adapter.GalleryPagingAdapter;
import com.luiz.gallery.ui.base.BaseFragment;
import com.luiz.gallery.util.Utils;

/**
 * Fragment onde é exibido as imagens na recyclerview
 */
public class GalleryFragment extends BaseFragment<GalleryBinding, GalleryViewModel> {

    private GalleryPagingAdapter galleryPagingAdapter;

    /* Variável para observar o live data para enviar os dados para o adapter */
    private Observer<PagedList<Gallery>> galleryLiveDataObserver = gallery -> {
        if (gallery != null && gallery.size() > 0) {
            galleryPagingAdapter.submitList(gallery);
        } else {
            this.viewDataBinding().rvGallery.setLayoutManager(new LinearLayoutManager(getContext()));
            this.viewDataBinding().rvGallery.setAdapter(Utils.noResultAdapter(getContext(), getContext().getString(R.string.gallery_empty), R.drawable.ic_sad));
        }
    };

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    public int binding() {
        return BR.galleryViewModel;
    }

    @Override
    public GalleryViewModel viewModel() {
        return new ViewModelProvider(this).get(GalleryViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeRecycler();
        observers();
    }

    /* Função criada para observar os live data da view model*/
    private void observers() {
        this.viewModel().getGallery().observe(getViewLifecycleOwner(), galleryLiveDataObserver);
        this.viewModel().loading.observe(getViewLifecycleOwner(), loadingLiveDataObserver);
        this.viewModel().loadError.observe(getViewLifecycleOwner(), errorLiveDataObserver);
        this.viewModel().context = getContext();
    }

    /* Função criada para inicializar o recycler view */
    private void initializeRecycler() {
        galleryPagingAdapter = new GalleryPagingAdapter();

        this.viewDataBinding().rvGallery.setLayoutManager(new GridLayoutManager(getContext(), 4));
        this.viewDataBinding().rvGallery.setAdapter(galleryPagingAdapter);
    }
}
