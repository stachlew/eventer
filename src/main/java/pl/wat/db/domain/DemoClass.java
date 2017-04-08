package pl.wat.db.domain;


//KLASA DEMO, NIE W BAZIE, NA POTRZEBY PREZENTACJI DemoRestController
public class DemoClass {
    public int nr;
    public String napis;

    public DemoClass(){}

    public DemoClass(int nr, String napis) {
        this.nr = nr;
        this.napis = napis;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getNapis() {
        return napis;
    }

    public void setNapis(String napis) {
        this.napis = napis;
    }
}
