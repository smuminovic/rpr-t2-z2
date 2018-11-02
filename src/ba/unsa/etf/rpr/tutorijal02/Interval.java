package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {

    //atributi
    private double pocetnaTacka, krajnjaTacka;
    private boolean pocetnaPripada, krajnjaPripada;

    //konstruktor sa četiri parametra: početna tačka, krajnja tačka (obje tipa double), boolean vrijednost koja označava
    // da li početna tačka pripada intervalu ili ne, te isto i za krajnju tačku; u slučaju da je početna tačka veća od
    // krajnje treba baciti izuzetak tipa IllegalArgumentException;
    public Interval(double pocetna, double krajnja, boolean pocPripada, boolean krajPripada) throws IllegalArgumentException {
        if (pocetna > krajnja) throw new IllegalArgumentException("Neispravni parametri");

        this.pocetnaTacka = pocetna;
        this.krajnjaTacka = krajnja;
        this.pocetnaPripada = pocPripada;
        this.krajnjaPripada = krajPripada;
    }

    //konstruktor bez parametara koji kreira "null interval" kod kojeg su početna i krajnja tačka 0 i niti jedna od njih ne pripada intervalu;
    public Interval() {
        this.pocetnaTacka = 0;
        this.krajnjaTacka = 0;
        this.pocetnaPripada = false;
        this.krajnjaPripada = false;
    }

    //getteri
    public double getPocetnaTacka() {
        return pocetnaTacka;
    }

    public double getKrajnjaTacka() {
        return krajnjaTacka;
    }

    public boolean getPocetnaPripada() {
        return pocetnaPripada;
    }

    public boolean getKrajnjaPripada() {
        return krajnjaPripada;
    }

    //isNull koja vraća boolean vrijednost da li je interval null interval ili nije;
    public boolean isNull() {
        if (this.getPocetnaTacka() == 0 && this.getKrajnjaTacka() == 0 && this.getPocetnaPripada() == false && this.getKrajnjaPripada() == false) {
            return true;
        } else return false;
    }

    //isIn metoda koja prima vrijednost tačke (tipa double) i vraća da li tačka pripada intervalu;
    public boolean isIn(double tacka) {
        if (this.getPocetnaPripada() && tacka >= this.getPocetnaTacka() && this.getKrajnjaPripada() && tacka <= this.getKrajnjaTacka())
            return true;
        if (this.getPocetnaPripada() && tacka >= this.getPocetnaTacka() && !this.getKrajnjaPripada() && tacka < this.getKrajnjaTacka())
            return true;
        if (!this.getPocetnaPripada() && tacka > this.getPocetnaTacka() && this.getKrajnjaPripada() && tacka <= this.getKrajnjaTacka())
            return true;
        else if (tacka > this.getPocetnaTacka() && tacka < this.getKrajnjaTacka())
            return true;
        else
            return false;
    }

    //setteri
    public void setPocetnaTacka(double tacka) {
        this.pocetnaTacka = tacka;
    }

    public void setKrajnjaTacka(double tacka) {
        this.krajnjaTacka = tacka;
    }

    public void setPocetnaPripada(boolean pripada) {
        this.pocetnaPripada = pripada;
    }

    public void setKrajnjaPripada(boolean pripada) {
        this.krajnjaPripada = pripada;
    }

    //metodu intersect koja vraća presjek dva intervala: metoda treba biti urađena kao klasična public metoda prima jedan interval)
    // i kao statička metoda (prima dva intervala);
    public Interval intersect(Interval i) {
        Interval presjek = new Interval();
        //pocetna tacka intervala presjek
        if (this.getPocetnaPripada() && i.getPocetnaPripada() && this.getPocetnaTacka() >= i.getPocetnaTacka()) {
            presjek.setPocetnaTacka(this.getPocetnaTacka());
            presjek.setPocetnaPripada(this.getPocetnaPripada());
        } else if (this.getPocetnaTacka() > i.getPocetnaTacka()) {
            presjek.setPocetnaTacka(this.getPocetnaTacka());
            presjek.setPocetnaPripada(this.getPocetnaPripada());
        } else if (this.getPocetnaPripada() && i.getPocetnaPripada() && this.getPocetnaTacka() <= i.getPocetnaTacka()) {
            presjek.setPocetnaTacka(i.getPocetnaTacka());
            presjek.setPocetnaPripada(i.getPocetnaPripada());
        } else if (this.getPocetnaTacka() < i.getPocetnaTacka()) {
            presjek.setPocetnaTacka(i.getPocetnaTacka());
            presjek.setPocetnaPripada(i.getPocetnaPripada());
        }
        //krajnja tacka intervala presjek
        if (this.getKrajnjaPripada() && i.getKrajnjaPripada() && this.getKrajnjaTacka() >= i.getKrajnjaTacka()) {
            presjek.setKrajnjaTacka(i.getKrajnjaTacka());
            presjek.setKrajnjaPripada(i.getKrajnjaPripada());
        } else if (this.getKrajnjaTacka() > i.getKrajnjaTacka()) {
            presjek.setKrajnjaTacka(i.getKrajnjaTacka());
            presjek.setKrajnjaPripada(i.getKrajnjaPripada());
        } else if (this.getKrajnjaPripada() && i.getKrajnjaPripada() && this.getKrajnjaTacka() <= i.getKrajnjaTacka()) {
            presjek.setKrajnjaTacka(this.getKrajnjaTacka());
            presjek.setKrajnjaPripada(this.getKrajnjaPripada());
        } else if (this.getKrajnjaTacka() < i.getKrajnjaTacka()) {
            presjek.setKrajnjaTacka(this.getKrajnjaTacka());
            presjek.setKrajnjaPripada(this.getKrajnjaPripada());
        }
        return presjek;
    }

    public static Interval intersect(Interval i1, Interval i2) {
        return i1.intersect(i2);
    }

    @Override
    public String toString() {
        String interval = new String("");
        if (this.getPocetnaPripada()) interval = "[";
        else interval = "(";
        interval += this.getPocetnaTacka();
        interval += ",";
        interval += this.getKrajnjaTacka();
        if (this.getKrajnjaPripada()) interval += "]";
        else interval += ")";
        if (this.getPocetnaTacka() == 0 && this.getKrajnjaTacka() == 0) {
            interval = "()";
        }
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                pocetnaPripada == interval.pocetnaPripada &&
                krajnjaPripada == interval.krajnjaPripada;
    }

}


