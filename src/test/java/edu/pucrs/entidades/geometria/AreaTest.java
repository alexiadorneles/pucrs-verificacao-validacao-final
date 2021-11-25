package edu.pucrs.entidades.geometria;

import edu.pucrs.entidades.geometria.Area;
import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import edu.pucrs.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    @Test
    void classificaDeveRetornarTodaDentro() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Reta reta = new Reta(new Ponto(1, 2), new Ponto(2, 1));
        assertEquals(SituacaoReta.TODA_DENTRO, area.classifica(reta));
    }

    @Test
    void classificaDeveRetornarTodaForaAcima() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Reta reta = new Reta(new Ponto(6, 8), new Ponto(8, 10));
        assertEquals(SituacaoReta.TODA_FORA, area.classifica(reta));
    }

    @Test
    void classificaDeveRetornarTodaForaAbaixo() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Reta reta = new Reta(new Ponto(-6, -8), new Ponto(-8, -10));
        assertEquals(SituacaoReta.TODA_FORA, area.classifica(reta));
    }

    @Test
    void classificaDeveRetornarIntersecta() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Reta reta = new Reta(new Ponto(3, 3), new Ponto(10, 10));
        assertEquals(SituacaoReta.INTERSECTA, area.classifica(reta));
    }

    @Test
    void pontoCentralDeveRetornarCorretamente() {
        Ponto pontoSupEsq = new Ponto(0, 6);
        Ponto pontoInfDir = new Ponto(6, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Ponto resultado = area.pontoCentral();
        assertEquals(3, resultado.getX());
        assertEquals(3, resultado.getY());
    }

    @Test
    void construtorDeveDarErro() {
        Ponto pontoSupEsq = new Ponto(6, 0);
        Ponto pontoInfDir = new Ponto(0, 6);
        assertThrows(IllegalArgumentException.class, () -> new Area(pontoSupEsq, pontoInfDir));
    }

    @Test
    void equalsDeveRetornarTrue() {
        Ponto pontoSupEsq = new Ponto(0, 6);
        Ponto pontoInfDir = new Ponto(6, 0);
        Area areaUm = new Area(pontoSupEsq, pontoInfDir);
        Area areaDois = new Area(pontoSupEsq, pontoInfDir);
        assertEquals(areaUm, areaDois);
        assertEquals(areaDois, areaUm);
    }

    @Test
    void equalsDeveRetornarFalse() {
        Ponto pontoSupEsq = new Ponto(0, 6);
        Ponto pontoInfDir = new Ponto(6, 0);
        Area areaUm = new Area(pontoSupEsq, pontoInfDir);
        Area areaDois = new Area(new Ponto(0, 3), new Ponto(3, 0));
        assertNotEquals(areaUm, areaDois);
        assertNotEquals(areaDois, areaUm);
    }

    @Test
    void toStringDeveFormatarArea() {
        Ponto pontoSupEsq = new Ponto(0, 6);
        Ponto pontoInfDir = new Ponto(6, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        assertEquals("Area [pInfDir=Ponto [x=6, y=0], pSupEsq=Ponto [x=0, y=6]]", area.toString());
    }

}