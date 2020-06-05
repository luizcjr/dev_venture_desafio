package com.luiz.gallery.ui.base;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luiz.gallery.api.services.ApiRepository;
import com.luiz.gallery.di.DaggerViewModelComponent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Classe abstrata para injetar a variável apiRepository para poder fazer as chamadas na api.
 * Essa classe vai ser a pai de todas as classes de viewmodel criada para não ter que injetar
 * a apiRepository em todas as viewModel criadas.
 * Essa classe também possui uma váriavel de disposable onde é inicializada e limpo quando ele não
 * é mais necessário. Também tem 2 funções onde se atualiza o livedata para quando é necessário exibir
 * e esconder a progressbar e caso seja necessário mostrar uma mensagem de erro.
 */
public abstract class BaseViewModel extends ViewModel {

    public Context context;
    public CompositeDisposable disposable;
    public MutableLiveData<Boolean> loading;
    public MutableLiveData<String> loadError;

    @Inject
    protected ApiRepository apiRepository;

    public BaseViewModel() {
        disposable = new CompositeDisposable();
        loading = new MutableLiveData<>();
        loadError = new MutableLiveData<>();
        DaggerViewModelComponent.create().inject(this);
    }

    @Override
    protected void onCleared() {
        disposable.clear();

        super.onCleared();
    }
}
