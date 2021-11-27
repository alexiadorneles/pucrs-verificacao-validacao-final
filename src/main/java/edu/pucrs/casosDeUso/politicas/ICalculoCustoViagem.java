package edu.pucrs.casosDeUso.politicas;

import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;

public interface ICalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);

    void definePassageiro(Passageiro passageiro);

    Roteiro getRoteiro();

    Passageiro getPassageiro();

    double calculoCustoBasico();

    double descontoPontuacao();

    double descontoPromocaoSazonal();

    double custoViagem();

}
