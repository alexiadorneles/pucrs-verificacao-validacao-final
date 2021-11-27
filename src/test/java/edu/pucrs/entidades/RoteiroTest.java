package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoteiroTest {

    Roteiro roteiro;

    @BeforeEach
    void setup() {
        Bairro bairroOrigem = mock(Bairro.class);
        Bairro bairroDestino = mock(Bairro.class);
        Reta rota = mock(Reta.class);
        this.roteiro = new Roteiro(bairroOrigem, bairroDestino, Arrays.asList(bairroOrigem, bairroDestino), rota);
    }

    @Test
    void bairrosPercoridos() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 5, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(5, 5), 5, 25);
        Bairro bairroTres = Bairro.novoBairroQuadrado("3", new Ponto(10, 5), 5, 25);
        Bairro bairroQuatro = Bairro.novoBairroQuadrado("4", new Ponto(15, 5), 5, 25);
        assertEquals(Arrays.asList(bairroDois, bairroTres, bairroQuatro), roteiro.bairrosPercoridos());
    }

    @Test
    void testToString() {
        assertEquals("Roteiro [bairroDestino=" + this.roteiro.getBairroDestino() + ", bairroOrigem=" + this.roteiro.getBairroOrigem().toString() + "]", roteiro.toString());
    }

    @Test
    void testEquals() {
        Bairro bairroUm = mock(Bairro.class);
        Bairro bairroDois = mock(Bairro.class);
        Reta rota = mock(Reta.class);
        Roteiro roteiroUm = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois), rota);
        Roteiro roteiroDois = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois), rota);
        assertEquals(roteiroUm, roteiroDois);
    }

    @Test
    void testEqualsDeveRetornarFalsoParaOutrosObjetos() {
        assertNotEquals(roteiro, new String());
    }
}