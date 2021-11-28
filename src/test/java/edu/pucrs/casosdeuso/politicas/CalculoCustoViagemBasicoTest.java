package edu.pucrs.casosdeuso.politicas;

import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculoCustoViagemBasicoTest {

    CalculoCustoViagemBasico calculoCustoViagemBasico;

    @BeforeEach
    void setup() {
        this.calculoCustoViagemBasico = new CalculoCustoViagemBasico();
    }

    @Test
    void defineRoteiro() {
        Roteiro roteiro = mock(Roteiro.class);
        this.calculoCustoViagemBasico.defineRoteiro(roteiro);
        assertEquals(roteiro, this.calculoCustoViagemBasico.getRoteiro());
    }

    @Test
    void definePassageiro() {
        Passageiro passageiro = mock(Passageiro.class);
        this.calculoCustoViagemBasico.definePassageiro(passageiro);
        assertEquals(passageiro, this.calculoCustoViagemBasico.getPassageiro());
    }

    @Test
    void calculoCustoBasico() {
        Roteiro roteiro = mock(Roteiro.class);
        Bairro bairroUm = mock(Bairro.class);
        when(bairroUm.getCustoTransporte()).thenReturn(10d);
        Bairro bairroDois = mock(Bairro.class);
        when(bairroDois.getCustoTransporte()).thenReturn(25d);
        when(roteiro.bairrosPercoridos()).thenReturn(List.of(bairroUm, bairroDois));
        this.calculoCustoViagemBasico.defineRoteiro(roteiro);

        assertEquals(35d, this.calculoCustoViagemBasico.calculoCustoBasico());
    }

    @Test
    void descontoPontuacao() {
        assertEquals(0, this.calculoCustoViagemBasico.descontoPontuacao());
    }

    @Test
    void descontoPromocaoSazonal() {
        assertEquals(0, this.calculoCustoViagemBasico.descontoPromocaoSazonal());
    }

    @Test
    void custoViagem() {
        CalculoCustoViagemBasico spy = spy(this.calculoCustoViagemBasico);
        doReturn(100d).when(spy).calculoCustoBasico();
        assertEquals(100d, spy.custoViagem());
        verify(spy, times(1)).descontoPromocaoSazonal();
        verify(spy, times(1)).descontoPontuacao();
    }
}