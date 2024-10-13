package models;

public class StatusTanques {
    private String tanque1;
    private String tanque2;
    private String tanque3;

    public StatusTanques(String tanque1, String tanque2, String tanque3) {
        this.tanque1 = tanque1;
        this.tanque2 = tanque2;
        this.tanque3 = tanque3;
    }

    public String getTanque1() {
        return tanque1;
    }

    public void setTanque1(String tanque1) {
        this.tanque1 = tanque1;
    }

    public String getTanque2() {
        return tanque2;
    }

    public void setTanque2(String tanque2) {
        this.tanque2 = tanque2;
    }

    public String getTanque3() {
        return tanque3;
    }

    public void setTanque3(String tanque3) {
        this.tanque3 = tanque3;
    }
}
