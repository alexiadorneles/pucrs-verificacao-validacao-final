package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BairroTest {
    Ponto pSupEsq;
    int ladoH;
    int ladoV;
    int custoTransporte;
    Bairro bairro;

    @BeforeEach
    void setup() {
        this.pSupEsq = new Ponto(0, 6);
        this.ladoH = 10;
        this.ladoV = 8;
        this.custoTransporte = 20;
        this.bairro = Bairro.novoBairroQuadrado("Bairro A", pSupEsq, ladoH, custoTransporte);
    }

    @Test
    void deveCriarNovoBairroQuadrado() {
        assertEquals("Bairro A", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte());
        assertEquals(pSupEsq, bairro.getArea().getPSupEsq());
        assertEquals(new Ponto(pSupEsq.getX() + 10, pSupEsq.getY() - 10), bairro.getArea().getPInfDir());
    }

    @Test
    void deveCriarNovoBairroRetangular() {
        Bairro bairro = Bairro.novoBairroRetangular("Bairro A", pSupEsq, ladoH, ladoV, custoTransporte);
        assertEquals("Bairro A", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte());
        assertEquals(pSupEsq, bairro.getArea().getPSupEsq());
        assertEquals(new Ponto(pSupEsq.getX() + 10, pSupEsq.getY() - 8), bairro.getArea().getPInfDir());
    }

    @Test
    void alteraCustoTransporteDeveAlterarPropriedade() {
        bairro.alteraCustoTransporte(100);
        assertEquals(100, bairro.getCustoTransporte());
    }

    @Test
    void alteraCustoTransporteDeveDarErro() {
        assertThrows(IllegalArgumentException.class, () -> bairro.alteraCustoTransporte(-1));
    }

    @Test
    void testToString() {
        assertEquals("Bairro [area=Area [pInfDir=Ponto [x=10, y=-4], pSupEsq=Ponto [x=0, y=6]], nome=Bairro A]", bairro.toString());
    }

    @Test
    void testEquals() {
        Bairro bairroDois = Bairro.novoBairroQuadrado("Bairro A", pSupEsq, ladoH, custoTransporte);
        assertEquals(bairro, bairroDois);
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