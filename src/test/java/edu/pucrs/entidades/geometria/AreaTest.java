package edu.pucrs.entidades.geometria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    Area area;

    @BeforeEach
    void setup() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        this.area = new Area(pontoSupEsq, pontoInfDir);
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,2,1,TODA_DENTRO",
            "6,8,8,10,TODA_FORA",
            "-6,-8,-8,-10,TODA_FORA",
            "3,3,10,10,INTERSECTA",
    })
    void something(int supEsqX, int supEsqY, int infDirX, int infDirY, String situacao) {
        Reta reta = new Reta(new Ponto(supEsqX, supEsqY), new Ponto(infDirX, infDirY));
        SituacaoReta esperado = this.getSituacaoPorString(situacao);
        assertEquals(esperado, area.classifica(reta));
    }

    private SituacaoReta getSituacaoPorString(String situacao) {
        return switch (situacao) {
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_FORA;
        };
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
        assertNotEquals(areaDois, new String());
    }

    @Test
    void toStringDeveFormatarArea() {
        Ponto pontoSupEsq = new Ponto(0, 6);
        Ponto pontoInfDir = new Ponto(6, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        assertEquals("Area [pInfDir=Ponto [x=6, y=0], pSupEsq=Ponto [x=0, y=6]]", area.toString());
    }

}