package com.luiz.gallery.api.callback;

/**
 * Interface do callback onde chamo as funções de onSuccess e onError.
 * */
public interface CallbackListener<R, T> {
    void onSuccess(R response);
    void onError(T throwable);
}
