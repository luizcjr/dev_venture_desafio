package com.luiz.gallery.api.responses;

import com.luiz.gallery.api.model.Gallery;

import java.util.ArrayList;


/**
 * Classe de response da requisição
 */
public class GalleryResponse {
    private ArrayList<Gallery> data;

    public GalleryResponse(ArrayList<Gallery> data) {
        this.data = data;
    }

    public ArrayList<Gallery> getData() {
        return data;
    }

    public void setData(ArrayList<Gallery> data) {
        this.data = data;
    }
}
