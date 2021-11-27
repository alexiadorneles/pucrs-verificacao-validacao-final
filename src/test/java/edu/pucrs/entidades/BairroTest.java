package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Area;
import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import edu.pucrs.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BairroTest {
    Ponto pSupEsq;
    int custoTransporte;
    Bairro bairro;
    Area area;

    @BeforeEach
    void setup() {
        this.pSupEsq = new Ponto(0, 6);
        this.custoTransporte = 20;
        this.area = mock(Area.class);
        this.bairro = new Bairro("Bairro A", this.area, this.custoTransporte);
    }

    @Test
    void deveCriarNovoBairroQuadrado() {
        Bairro bairro = Bairro.novoBairroQuadrado("Bairro A", pSupEsq, 10, custoTransporte);
        assertEquals("Bairro A", bairro.getNome());
        assertEquals(20, bairro.getCustoTransporte());
        assertEquals(pSupEsq, bairro.getArea().getPSupEsq());
        assertEquals(new Ponto(pSupEsq.getX() + 10, pSupEsq.getY() - 10), bairro.getArea().getPInfDir());
    }

    @Test
    void deveCriarNovoBairroRetangular() {
        Bairro bairro = Bairro.novoBairroRetangular("Bairro A", pSupEsq, 10, 8, custoTransporte);
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
        assertEquals("Bairro [area=" + area.toString() + ", nome=Bairro A]", bairro.toString());
    }

    @Test
    void testEquals() {
        assertEquals(bairro, new Bairro("Bairro A", this.area, this.custoTransporte));
    }

    @Test
    void testEqualsDeveRetornarFalso() {
        assertNotEquals(bairro, new String());
    }

    @Test
    void getPontoCentral() {
        Ponto pontoEsperado = new Ponto(5, 8);
        when(area.pontoCentral()).thenReturn(pontoEsperado);
        assertEquals(pontoEsperado, bairro.getPontoCentral());
    }

    @Test
    void getClassificacao() {
        Reta reta = new Reta(new Ponto(5, 8), new Ponto(10, 10));
        SituacaoReta esperado = SituacaoReta.TODA_DENTRO;
        when(area.classifica(reta)).thenReturn(esperado);
        SituacaoReta resultado = bairro.getClassificacao(reta);
        assertEquals(esperado, resultado);
        verify(area, times(1)).classifica(reta);
    }
}