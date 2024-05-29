package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "folders"
)
public class Folder extends StorableItem {
    @Column(nullable = false)
    private boolean shared;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "folder"
    )
    @JsonManagedReference
    private List<File> files;

    public Folder() {
        this.shared = false;
        this.files = new ArrayList<>();
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void addFile(File file) {
        this.files.add(file);
        file.setFolder(this);
    }

    public void removeFile(File file) {
        this.files.remove(file);
        file.setFolder(null);
    }
}
