package com.luiz.gallery.di;

import com.luiz.gallery.ui.base.BaseRepository;

import dagger.Component;

/**
 * Interface para injeção da api na classe BaseRepository
 */
@Component(modules = {ApiModule.class})
public interface RepositoryComponent {
    void inject(BaseRepository baseRepository);
}
