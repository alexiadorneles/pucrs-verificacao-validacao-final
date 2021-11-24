package edu.pucrs.casosDeUso.repositorios;

import edu.pucrs.entidades.Bairro;

import java.util.List;

public interface IRepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);

    List<Bairro> recuperaListaBairros();

}
