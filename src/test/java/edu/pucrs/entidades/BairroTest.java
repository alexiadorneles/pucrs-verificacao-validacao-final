package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BairroTest {

    @Test
    void deveCriarNovoBairroQuadrado() {
        Ponto pSupEsq = new Ponto(0, 6);
        Bairro bairro = Bairro.novoBairroQuadrado("Bairro A", pSupEsq, 10, 20);
        assertEquals("Bairro A", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte());
        assertEquals(pSupEsq, bairro.getArea().getPSupEsq());
        assertEquals(new Ponto(pSupEsq.getX() + 10, pSupEsq.getY() - 10), bairro.getArea().getPInfDir());
    }

    @Test
    void deveCriarNovoBairroRetangular() {
        Ponto pSupEsq = new Ponto(0, 6);
        Bairro bairro = Bairro.novoBairroRetangular("Bairro A", pSupEsq, 10, 8, 20);
        assertEquals("Bairro A", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte());
        assertEquals(pSupEsq, bairro.getArea().getPSupEsq());
        assertEquals(new Ponto(pSupEsq.getX() + 10, pSupEsq.getY() - 8), bairro.getArea().getPInfDir());
    }

    @Test
    void alteraCustoTransporteDeveAlterarPropriedade() {
        Bairro bairro = Bairro.novoBairroQuadrado("Bairro A", new Ponto(0, 6), 10, 20);
        bairro.alteraCustoTransporte(100);
        assertEquals(100, bairro.getCustoTransporte());
    }

    @Test
    void alteraCustoTransporteDeveDarErro() {
        Bairro bairro = Bairro.novoBairroQuadrado("Bairro A", new Ponto(0, 6), 10, 20);
        assertThrows(IllegalArgumentException.class, () -> bairro.alteraCustoTransporte(-1));
    }

    @Test
    void testToString() {
        Bairro bairro = Bairro.novoBairroQuadrado("Bairro A", new Ponto(0, 6), 10, 20);
        assertEquals("Bairro [area=Area [pInfDir=Ponto [x=10, y=-4], pSupEsq=Ponto [x=0, y=6]], nome=Bairro A]", bairro.toString());
    }

    @Test
    void testEquals() {
        Bairro bairroUm = Bairro.novoBairroQuadrado("Bairro A", new Ponto(0, 6), 10, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("Bairro A", new Ponto(0, 6), 10, 20);
        assertEquals(bairroUm, bairroDois);
    }

    @Test
    void getPontoCentral() {
        // TODO: implementar com mock
    }

    @Test
    void getClassificacao() {
        // TODO: implementar com mock
    }
}