package com.luiz.gallery.di;

import com.luiz.gallery.ui.base.BaseViewModel;

import dagger.Component;

/**
 * Interface para injeção da api na classe BaseViewModel
 */
@Component(modules = {ApiModule.class})
public interface ViewModelComponent {
    void inject(BaseViewModel viewModel);
}
