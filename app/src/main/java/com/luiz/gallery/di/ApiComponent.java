package com.luiz.gallery.di;

import com.luiz.gallery.api.services.ApiRepository;

import dagger.Component;

/**
 * Interface para injeção da api na classe ApiRepository
 */
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(ApiRepository repository);
}