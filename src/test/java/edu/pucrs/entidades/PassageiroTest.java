package edu.pucrs.entidades;

import org.junit.jupiter.api.Test;

import static edu.pucrs.entidades.Passageiro.AVALIACOES_INICIAL;
import static edu.pucrs.entidades.Passageiro.PONTUACAO_INICIAL;
import static org.junit.jupiter.api.Assertions.*;

class PassageiroTest {

    @Test
    void deveCirarNovoPassageiro() {
        Passageiro resultado = Passageiro.novoPassageiro("12345678910", "Fulano");
        assertEquals("12345678910", resultado.getCPF());
        assertEquals("Fulano", resultado.getNome());
        assertEquals(PONTUACAO_INICIAL, resultado.getPontuacaoAcumulada());
        assertEquals(AVALIACOES_INICIAL, resultado.getQtdadeAvaliacoes());
    }

    @Test
    void deveCriarPassageiroExistente() {
        Passageiro resultado = Passageiro.passageiroExistente("12345678910", "Fulano", PONTUACAO_INICIAL + 2, AVALIACOES_INICIAL + 3);
        assertEquals("12345678910", resultado.getCPF());
        assertEquals("Fulano", resultado.getNome());
        assertEquals(PONTUACAO_INICIAL + 2, resultado.getPontuacaoAcumulada());
        assertEquals(AVALIACOES_INICIAL + 3, resultado.getQtdadeAvaliacoes());
    }

    @Test
    void getPontuacaoMediaParaPassageiroInicial() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Fulano");
        assertEquals(8, passageiro.getPontuacaoMedia());
    }

    @Test
    void getPontuacaoMediaParaPassageiroExistente() {
        Passageiro passageiro = Passageiro.passageiroExistente("12345678910", "Fulano", 50, 10);
        assertEquals(5, passageiro.getPontuacaoMedia());
    }

    @Test
    void infoPontuacao() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Fulano");
        passageiro.infoPontuacao(10);
        assertEquals(PONTUACAO_INICIAL + 10, passageiro.getPontuacaoAcumulada());
        assertEquals(AVALIACOES_INICIAL + 1, passageiro.getQtdadeAvaliacoes());
        assertEquals(9, passageiro.getPontuacaoMedia());
    }

    @Test
    void infoPontuacaoDeveDarErro() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Fulano");
        assertThrows(IllegalArgumentException.class, () -> passageiro.infoPontuacao(-1));
    }

    @Test
    void testToString() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Fulano");
        String esperado = "Passageiro [cpf=12345678910, nome=Fulano, pontuacaoAcumulada=" + PONTUACAO_INICIAL
                + ", qtdadeAvaliacoes=" + AVALIACOES_INICIAL + "]";
        assertEquals(esperado, passageiro.toString());
    }
}