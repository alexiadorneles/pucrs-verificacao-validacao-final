package edu.pucrs.casosDeUso.politicas;

import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;

public class CustoViagem {
    private ICalculoCustoViagem ccv;

    public CustoViagem(ICalculoCustoViagem ccv) {
        this.ccv = ccv;
    }

    public double custoViagem(Roteiro roteiro, Passageiro passageiro) {
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}