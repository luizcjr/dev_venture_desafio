package com.luiz.gallery.ui.fragments.gallery.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.luiz.gallery.R;
import com.luiz.gallery.api.model.Gallery;
import com.luiz.gallery.databinding.ItemsGalleryBinding;

/**
 * Adapter para a exibição das imagens de forma paginada.
 */
public class GalleryPagingAdapter extends PagedListAdapter<Gallery, GalleryPagingAdapter.GalleryViewHolder> {

    public GalleryPagingAdapter() {
        super(callback);
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsGalleryBinding itemsGalleryBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.items_gallery, parent, false);
        return new GalleryViewHolder(itemsGalleryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Gallery itemAtual = getItem(position);
        holder.view.setGallery(itemAtual);
        if (itemAtual.getImages() != null) {
            holder.view.setImages(itemAtual.getImages().get(0));
        }
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {

        public ItemsGalleryBinding view;

        public GalleryViewHolder(@NonNull ItemsGalleryBinding itemView) {
            super(itemView.getRoot());

            this.view = itemView;
        }
    }

    static final DiffUtil.ItemCallback<Gallery> callback = new DiffUtil.ItemCallback<Gallery>() {
        @Override
        public boolean areItemsTheSame(@NonNull Gallery oldItem, @NonNull Gallery newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Gallery oldItem, @NonNull Gallery newItem) {
            return oldItem.equals(newItem);
        }
    };
}
