package com.luiz.gallery.api.model;

import java.util.ArrayList;

/**
 * Classe modelo de galeria
 */
public class Gallery {
    private String id;
    private String title;
    private String description = null;
    private ArrayList<Images> images;

    public Gallery() {
    }

    public Gallery(String id, String title, String description, ArrayList<Images> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.images = images;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
