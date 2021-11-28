package edu.pucrs.casosdeuso.servicos;

import edu.pucrs.casosdeuso.politicas.CalculoCustoViagemBasico;
import edu.pucrs.casosdeuso.politicas.CustoViagem;
import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Roteiro;
import edu.pucrs.entidades.Viagem;
import edu.pucrs.interfaces.persistencia.RepositorioBairrosImplMem;
import edu.pucrs.interfaces.persistencia.RepositorioPassageirosImplMem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ServicosPassageiroTest {

    ServicosPassageiro servico;

    @BeforeEach
    void setup() {
        this.servico = new ServicosPassageiro(new RepositorioBairrosImplMem(), new RepositorioPassageirosImplMem(), new CustoViagem(new CalculoCustoViagemBasico()));
    }

    @Test
    void getListaBairros() {
        List<String> listaBairros = this.servico.getListaBairros();
        assertEquals(8, listaBairros.size());
        assertTrue(listaBairros.contains("Ipiranga"));
    }

    @Test
    void getPassageirosCadastrados() {
        List<String> listaPassageiros = this.servico.getPassageirosCadastrados();
        assertEquals(4, listaPassageiros.size());
        assertTrue(listaPassageiros.contains("Ze"));
    }

    @Test
    void criaRoteiro() {
        Roteiro roteiro = this.servico.criaRoteiro("Ipiranga", "Bom Fim");
        assertEquals("Ipiranga", roteiro.getBairroOrigem().getNome());
        assertEquals("Bom Fim", roteiro.getBairroDestino().getNome());
        List<Bairro> bairros = roteiro.bairrosPercoridos();
        assertEquals(2, bairros.size());
        assertEquals("Ipiranga", bairros.get(0).getNome());
        assertEquals("Bom Fim", bairros.get(1).getNome());
    }

    @Test
    void criaViagem() {
        Roteiro roteiro = this.servico.criaRoteiro("Ipiranga", "Bom Fim");
        Viagem viagem = this.servico.criaViagem(1, roteiro, "123456789");
        assertEquals("123456789", viagem.getPassageiro().getCPF());
        assertNotNull(viagem.getDataHora());
        assertEquals(roteiro, viagem.getRoteiro());
        assertEquals(1, viagem.getId());
        assertEquals(16, viagem.getValorCobrado());
    }
}