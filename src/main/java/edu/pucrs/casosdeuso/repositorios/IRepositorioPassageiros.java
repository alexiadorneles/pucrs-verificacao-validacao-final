package edu.pucrs.casosdeuso.repositorios;

import edu.pucrs.entidades.Passageiro;

import java.util.List;

public interface IRepositorioPassageiros {
    List<Passageiro> listaPassageiros();

    Passageiro recuperaPorCPF(String cpf);

    void atualizaPassageiro(Passageiro passageiro);

}
