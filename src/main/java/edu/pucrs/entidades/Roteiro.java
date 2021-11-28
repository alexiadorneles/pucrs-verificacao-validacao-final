package edu.pucrs.entidades;

import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import edu.pucrs.entidades.geometria.SituacaoReta;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Roteiro {
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private List<Bairro> bairrosPercorridos;

    private void determinaBairrosPercorridos(Reta rota, Collection<Bairro> todosBairros) {
        for (Bairro bairro : todosBairros) {
            SituacaoReta sr = bairro.getClassificacao(rota);
            if (sr != SituacaoReta.TODA_FORA) {
                bairrosPercorridos.add(bairro);
            }
        }
    }

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino, Collection<Bairro> todosBairros, Reta rota) {
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        bairrosPercorridos = new LinkedList<>();
        determinaBairrosPercorridos(rota, todosBairros);
    }

//    public Reta getRota() {
//        return rota;
//    }

    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }

    public List<Bairro> bairrosPercoridos() {
        return bairrosPercorridos;
    }

    @Override
    public String toString() {
        return "Roteiro [bairroDestino=" + bairroDestino + ", bairroOrigem=" + bairroOrigem + "]";
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Roteiro) {
            Roteiro outroRoteiro = (Roteiro) outro;
            return this.getBairroOrigem().equals(outroRoteiro.getBairroOrigem()) &&
                    this.getBairroDestino().equals(outroRoteiro.getBairroDestino());
        } else {
            return false;
        }
    }
}