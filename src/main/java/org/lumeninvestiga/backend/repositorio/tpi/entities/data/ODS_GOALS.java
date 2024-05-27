package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

public enum ODS_GOALS {
    FIRST(1, "Fin de la pobreza"),
    SECOND(2, "Hambre cero"),
    THIRD(3, "Salud y bienestar"),
    FOURTH(4, "Educación de calidad"),
    FIFTH(5, "Igualdad de género"),
    SIXTH(6, "Agua limpia y saneamiento"),
    SEVENTH(7, "Energía asequible y no contaminante"),
    EIGHTH(8, "Trabajo decente y crecimiento económico"),
    NINTH(9, "Industria, innovación e infraestructura"),
    TENTH(10, "Reducción de las desigualdades"),
    ELEVENTH(11, "Ciudades y comunidades sostenibles"),
    TWELFTH(12, "Producción y consumo sostenibles"),
    THIRTEENTH(13, "Acción por el clima"),
    FOURTEENTH(14, "Vida submarina"),
    FIFTEENTH(15, "Vida de ecosistemas terrestres"),
    SIXTEENTH(16, "Paz, justicia e instituciones sólidas"),
    SEVENTEENTH(17, "Alianzas para lograr los objetivos");

    private final int position;
    private final String title;

    ODS_GOALS(int position, String title) {
        this.position = position;
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public static ODS_GOALS fromPosition(int position) {
        for (ODS_GOALS goal : values()) {
            if (goal.getPosition() == position) {
                return goal;
            }
        }
        return null;
    }
}
