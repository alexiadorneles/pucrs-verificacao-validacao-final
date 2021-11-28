package edu.pucrs;

import edu.pucrs.entidades.Passageiro;

public class PassageiroFactory {
    public static Passageiro comAvaliacoesEMedia(int qtdadeAvaliacoes, int media) {
        int pontuacaoAcumulada = media * qtdadeAvaliacoes;
        return Passageiro.passageiroExistente("12345678910", "Fulano", pontuacaoAcumulada, qtdadeAvaliacoes);
    }
}
