package helpers;

import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Roteiro;
import factories.BairroFactory;
import factories.PassageiroFactory;
import edu.pucrs.casosdeuso.politicas.CalculoCustoViagemBasico;
import edu.pucrs.entidades.Passageiro;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CalculoCustoViagemEspecificoHelper {
    public static void descontoPontuacaoHelper(int avaliacoesPassageiro,
                                               int mediaPassageiro,
                                               double custoBase,
                                               double esperado,
                                               CalculoCustoViagemBasico calculadora) {
        Passageiro passageiro = PassageiroFactory.comAvaliacoesEMedia(avaliacoesPassageiro, mediaPassageiro);
        calculadora.definePassageiro(passageiro);
        CalculoCustoViagemBasico spy = spy(calculadora);
        doReturn(custoBase).when(spy).calculoCustoBasico();
        assertEquals(esperado, spy.descontoPontuacao());
    }

    public static void descontoSazonalHelper(int quantidadeBairros, List<Integer> custos, int esperado, CalculoCustoViagemBasico calculadora) {
        List<Bairro> bairros = IntStream.range(0, quantidadeBairros)
                .mapToObj(i -> BairroFactory.novoBairro(custos.get(i)))
                .collect(Collectors.toList());

        Roteiro roteiro = mock(Roteiro.class);
        when(roteiro.bairrosPercoridos()).thenReturn(bairros);
        calculadora.defineRoteiro(roteiro);

        assertEquals(esperado, calculadora.descontoPromocaoSazonal());

    }
}
