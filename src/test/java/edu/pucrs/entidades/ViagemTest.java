package edu.pucrs.entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ViagemTest {

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        Roteiro roteiro = mock(Roteiro.class);
        Passageiro passageiro = mock(Passageiro.class);
        Viagem viagem = new Viagem(1, dateTime, roteiro, passageiro, 10);
        assertEquals("Viagem [valor cobrado=10.0, dataHora=" + dateTime + ", id=1, passageiro=" + passageiro + ", roteiro=" + roteiro + "]", viagem.toString());
    }
}