package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "images"
)
public class Image extends File{
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
