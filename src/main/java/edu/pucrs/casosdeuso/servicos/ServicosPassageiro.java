package edu.pucrs.casosdeuso.servicos;

import edu.pucrs.casosdeuso.politicas.CustoViagem;
import edu.pucrs.casosdeuso.repositorios.IRepositorioBairros;
import edu.pucrs.casosdeuso.repositorios.IRepositorioPassageiros;
import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;
import edu.pucrs.entidades.Viagem;
import edu.pucrs.entidades.geometria.Reta;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ServicosPassageiro {
    private IRepositorioBairros repBairros;
    private IRepositorioPassageiros repPassageiros;
    private CustoViagem custoViagem;

    public ServicosPassageiro(IRepositorioBairros repBairros, IRepositorioPassageiros repPassageiros,
                              CustoViagem custoViagem) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = custoViagem;
    }

    public List<String> getListaBairros() {
        return repBairros.recuperaListaBairros()
                .stream()
                .map(Bairro::getNome)
                .collect(Collectors.toList());
    }

    public List<String> getPassageirosCadastrados() {
        return repPassageiros.listaPassageiros()
                .stream()
                .map(Passageiro::getNome)
                .collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem, String bairroDestino) {
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        Bairro bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        Bairro bDestino = repBairros.recuperaPorNome(bairroDestino);
        Reta rota = new Reta(bOrigem.getPontoCentral(), bDestino.getPontoCentral());
        return new Roteiro(bOrigem, bDestino, todosBairros, rota);
    }

    public Viagem criaViagem(int id, Roteiro roteiro, String cpfPassageiro) {
        LocalDateTime data = LocalDateTime.now();
        Passageiro passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id, data, roteiro, passageiro, valorCobrado);
    }
}