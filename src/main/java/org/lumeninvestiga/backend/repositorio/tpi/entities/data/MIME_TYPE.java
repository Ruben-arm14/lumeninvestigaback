package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

public enum MIME_TYPE {
    PDF(1, "application/pdf"),
    DOCX(2, "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    JPG(3, "image/jpeg"),
    PNG(4, "image/png"),
    TEXT(5, "text/plain");

    private final int id;
    private final String mimeType;

    MIME_TYPE(int id, String mimeType) {
        this.id = id;
        this.mimeType = mimeType;
    }

    public int getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static MIME_TYPE fromMimeType(String mimeType) {
        for (MIME_TYPE type : values()) {
            if (type.mimeType.equals(mimeType)) {
                return type;
            }
        }
        return null;
    }
}
