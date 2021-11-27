package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ViagemTest {

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.now();
        Bairro bairroUm = Bairro.novoBairroQuadrado("1", new Ponto(0, 5), 10, 20);
        Bairro bairroDois = Bairro.novoBairroQuadrado("2", new Ponto(0, 15), 10, 25);
        Reta reta = new Reta(bairroUm.getPontoCentral(), bairroDois.getPontoCentral());
        Roteiro roteiro = new Roteiro(bairroUm, bairroDois, Arrays.asList(bairroUm, bairroDois), reta);
        Viagem viagem = new Viagem(1, dateTime, roteiro, Passageiro.novoPassageiro("", ""), 10);
        assertEquals("Viagem [valor cobrado=10.0, dataHora=" + dateTime + ", id=1, passageiro=Passageiro [cpf=, nome=, pontuacaoAcumulada=8, qtdadeAvaliacoes=1], roteiro=Roteiro [bairroDestino=Bairro [area=Area [pInfDir=Ponto [x=10, y=5], pSupEsq=Ponto [x=0, y=15]], nome=2], bairroOrigem=Bairro [area=Area [pInfDir=Ponto [x=10, y=-5], pSupEsq=Ponto [x=0, y=5]], nome=1]]]", viagem.toString());
    }
}