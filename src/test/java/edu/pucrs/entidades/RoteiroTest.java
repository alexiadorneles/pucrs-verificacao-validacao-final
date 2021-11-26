package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoteiroTest {

    @Test
    void bairrosPercoridos() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 5, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(5, 5), 5, 25);
        Bairro bairroTres = Bairro.novoBairroQuadrado("3", new Ponto(10, 5), 5, 25);
        Bairro bairroQuatro = Bairro.novoBairroQuadrado("4", new Ponto(15, 5), 5, 25);
        Roteiro roteiro = new Roteiro(bairroDois, bairroQuatro, Arrays.asList(bairroUm, bairroDois, bairroTres, bairroQuatro));
        assertEquals(Arrays.asList(bairroDois, bairroTres, bairroQuatro), roteiro.bairrosPercoridos());
    }

    @Test
    void testToString() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 10, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(0, 15), 10, 25);
        Roteiro roteiro = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois));
        assertEquals("Roteiro [bairroDestino=Bairro [area=Area [pInfDir=Ponto [x=10, y=5], pSupEsq=Ponto [x=0, y=15]], nome=2], bairroOrigem=Bairro [area=Area [pInfDir=Ponto [x=10, y=-5], pSupEsq=Ponto [x=0, y=5]], nome=1]]", roteiro.toString());
    }

    @Test
    void testEquals() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 10, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(0, 15), 10, 25);
        Roteiro roteiroUm = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois));
        Roteiro roteiroDois = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois));
        assertEquals(roteiroUm, roteiroDois);
    }

    @Test
    void testEqualsDeveRetornarFalsoParaOutrosObjetos() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 10, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(0, 15), 10, 25);
        Roteiro roteiroUm = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois));
        assertNotEquals(roteiroUm, new String());
    }
}