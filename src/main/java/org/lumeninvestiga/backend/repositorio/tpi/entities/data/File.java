package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(
        name = "files"
)
public class File extends StorableItem {

    @JsonIgnore
    @Column(updatable = false, columnDefinition = "LONGBLOB")
    private byte[] data;
    @Column(name = "mime_type")
    private String mimeType;
    @ManyToOne(targetEntity = Folder.class)
    @JsonBackReference
    private Folder folder;

    public File() {
        super();
    }

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        File file = (File) object;
        return Arrays.equals(data, file.data) && Objects.equals(mimeType, file.mimeType) && Objects.equals(folder, file.folder);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mimeType, folder);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
