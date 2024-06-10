package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "files"
)
public class File extends StorableItem {

    @JsonIgnore
    @Column(nullable = false, updatable = false, columnDefinition = "LONGBLOB")
    private byte[] data;
    @Column(name = "mime_type", nullable = false)
    private String mimeType;
    @ManyToOne(targetEntity = Folder.class)
    @JsonBackReference
    private Folder folder;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
