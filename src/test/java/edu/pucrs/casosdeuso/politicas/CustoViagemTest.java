package edu.pucrs.casosdeuso.politicas;

import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustoViagemTest {

    @Test
    void custoViagem() {
        ICalculoCustoViagem calculadora = mock(ICalculoCustoViagem.class);
        double esperado = 99d;
        when(calculadora.custoViagem()).thenReturn(esperado);
        CustoViagem custoViagem = new CustoViagem(calculadora);
        Roteiro roteiro = mock(Roteiro.class);
        Passageiro passageiro = mock(Passageiro.class);

        double resultado = custoViagem.custoViagem(roteiro, passageiro);
        assertEquals(esperado, resultado);
        verify(calculadora, times(1)).custoViagem();
        verify(calculadora, times(1)).definePassageiro(passageiro);
        verify(calculadora, times(1)).defineRoteiro(roteiro);
    }
}