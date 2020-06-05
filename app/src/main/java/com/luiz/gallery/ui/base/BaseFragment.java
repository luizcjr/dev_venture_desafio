package com.luiz.gallery.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.luiz.gallery.util.Utils;

/**
 * Classe abstrata para inicializar o databinding de uma maneira genérica e também a viewModel.
 * Essa classe vai ser a pai de todas as classes fragment criada para que no fragment não seja necessário inicializar
 * o databinding e nem a view model. Essa classe também possui duas váriaveis que observam um livedata, afim de que
 * mostre um progressbar quando está carregando dados ou mostre uma mensagem de erro ao usuário, caso a api retorne
 * algum erro.
 */
public abstract class BaseFragment<T extends ViewDataBinding, D extends BaseViewModel> extends Fragment {

    private void initializeDatabinding(LayoutInflater inflater, ViewGroup container) {
        vDatabinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        vModel = viewModel();
        vDatabinding.setVariable(binding(), vModel);
        vDatabinding.executePendingBindings();
        mRootView = vDatabinding.getRoot();
    }

    public T viewDataBinding() {
        return vDatabinding;
    }

    @LayoutRes
    public abstract int layoutId();

    public abstract int binding();

    public abstract D viewModel();

    private T vDatabinding;
    private D vModel;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        initializeDatabinding(inflater, container);

        return mRootView;
    }

    protected Observer<Boolean> loadingLiveDataObserver = isLoading -> {
        if (isLoading) {
            Utils.onStartLoading(getContext());
        } else {
            Utils.onStopLoading();
        }
    };

    protected Observer<String> errorLiveDataObserver = error -> {
        if (error != null) {
            Utils.alertError(getContext(), error);
        }
    };
}
