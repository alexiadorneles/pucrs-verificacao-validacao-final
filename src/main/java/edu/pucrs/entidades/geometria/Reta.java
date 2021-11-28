package edu.pucrs.entidades.geometria;

public class Reta {
    private Ponto p1;
    private Ponto p2;

    public Reta(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "Reta [p1=" + p1 + ", p2=" + p2 + "]";
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Reta) {
            Reta outraReta = (Reta) outro;
            return
                    this.getP1().equals(outraReta.getP1()) &&
                            this.getP2().equals(outraReta.getP2());
        } else {
            return false;
        }
    }
}