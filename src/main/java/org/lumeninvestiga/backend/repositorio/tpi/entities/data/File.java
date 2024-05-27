package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "files"
)
public class File extends StorableItem {
    private byte[] data;
    private MIME_TYPE mimeType;
    @ManyToOne
    @JoinColumn(
            name = "folder_id",
            nullable = false
    )
    private Folder folder;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public MIME_TYPE getMimeType() {
        return mimeType;
    }

    public void setMimeType(MIME_TYPE mimeType) {
        this.mimeType = mimeType;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
