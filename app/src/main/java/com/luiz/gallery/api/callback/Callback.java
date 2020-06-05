package com.luiz.gallery.api.callback;

/**
 * Classe de Callback genérico onde é passado como parâmetro a response da requisição e o Throwable para fazer tratativas de erro.
 */
public abstract class Callback<R, T> implements CallbackListener<R, T> {
}
