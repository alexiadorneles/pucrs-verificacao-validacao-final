package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Reta;
import edu.pucrs.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoteiroTest {

    Roteiro roteiro;
    Reta rota;

    @BeforeEach
    void setup() {
        Bairro bairroOrigem = mock(Bairro.class);
        Bairro bairroDestino = mock(Bairro.class);
        this.rota = mock(Reta.class);
        this.roteiro = new Roteiro(bairroOrigem, bairroDestino, Arrays.asList(bairroOrigem, bairroDestino), rota);
    }

    @Test
    void bairrosPercoridosDeveRetornarBairroUnico() {
        Bairro bairroUm = mock(Bairro.class);
        when(bairroUm.getClassificacao(rota)).thenReturn(SituacaoReta.TODA_DENTRO);
        Bairro bairroDois = mock(Bairro.class);
        when(bairroDois.getClassificacao(rota)).thenReturn(SituacaoReta.TODA_FORA);
        Bairro bairroTres = mock(Bairro.class);
        when(bairroTres.getClassificacao(rota)).thenReturn(SituacaoReta.TODA_FORA);
        Roteiro roteiro = new Roteiro(bairroUm, bairroUm, Arrays.asList(bairroUm, bairroDois, bairroTres), rota);
        assertEquals(List.of(bairroUm), roteiro.bairrosPercoridos());
    }

    @Test
    void bairrosPercoridosDeveRetornarInterseccoes() {
        Bairro bairroUm = mock(Bairro.class);
        when(bairroUm.getClassificacao(rota)).thenReturn(SituacaoReta.TODA_DENTRO);
        Bairro bairroDois = mock(Bairro.class);
        when(bairroDois.getClassificacao(rota)).thenReturn(SituacaoReta.INTERSECTA);
        Bairro bairroTres = mock(Bairro.class);
        when(bairroTres.getClassificacao(rota)).thenReturn(SituacaoReta.TODA_FORA);
        Roteiro roteiro = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois, bairroTres), rota);
        assertEquals(List.of(bairroUm, bairroDois), roteiro.bairrosPercoridos());
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