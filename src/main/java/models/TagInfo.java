package models;

public class TagInfo {
    private String uid;
    private String bebida;

    public TagInfo(String uid, String bebida) {
        this.uid = uid;
        this.bebida = bebida;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }
}
