package edu.pucrs.casosdeuso.repositorios;

import edu.pucrs.entidades.Bairro;

import java.util.List;

public interface IRepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);

    List<Bairro> recuperaListaBairros();

}
