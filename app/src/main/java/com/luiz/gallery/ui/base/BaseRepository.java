package com.luiz.gallery.ui.base;

import com.luiz.gallery.api.services.ApiRepository;
import com.luiz.gallery.di.DaggerRepositoryComponent;

import javax.inject.Inject;

/**
 * Classe abstrata para injetar a variável apiRepository para poder fazer as chamadas na api.
 * Essa classe vai ser a pai de todas as classes de repository criada para não ter que injetar
 * a apiRepository em todos os repository criados.
 */
public abstract class BaseRepository {

    @Inject
    protected ApiRepository apiRepository;

    public BaseRepository() {
        DaggerRepositoryComponent.create().inject(this);
    }
}
