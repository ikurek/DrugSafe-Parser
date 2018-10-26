package models;

public class Substance {

    String name; // nazwa

    public Substance() {
    }

    public Substance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
