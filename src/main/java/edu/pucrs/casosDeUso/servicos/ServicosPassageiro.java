package edu.pucrs.casosDeUso.servicos;

import edu.pucrs.casosDeUso.politicas.CustoViagem;
import edu.pucrs.casosDeUso.politicas.ICalculoCustoViagem;
import edu.pucrs.casosDeUso.repositorios.IRepositorioBairros;
import edu.pucrs.casosDeUso.repositorios.IRepositorioPassageiros;
import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;
import edu.pucrs.entidades.Viagem;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ServicosPassageiro {
    private IRepositorioBairros repBairros;
    private IRepositorioPassageiros repPassageiros;
    private CustoViagem custoViagem;

    public ServicosPassageiro(IRepositorioBairros repBairros, IRepositorioPassageiros repPassageiros,
                              ICalculoCustoViagem ccv) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = new CustoViagem(ccv);
    }

    public List<String> getListaBairros() {
        return repBairros.recuperaListaBairros()
                .stream()
                .map(b -> b.getNome())
                .collect(Collectors.toList());
    }

    public List<String> getPassageirosCadastrados() {
        return repPassageiros.listaPassageiros()
                .stream()
                .map(p -> p.getNome())
                .collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem, String bairroDestino) {
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        Bairro bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        Bairro bDestino = repBairros.recuperaPorNome(bairroDestino);
        return new Roteiro(bOrigem, bDestino, todosBairros);
    }

    public Viagem criaViagem(int id, Roteiro roteiro, String cpfPassageiro) {
        LocalDateTime data = LocalDateTime.now();
        Passageiro passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id, data, roteiro, passageiro, valorCobrado);
    }
}