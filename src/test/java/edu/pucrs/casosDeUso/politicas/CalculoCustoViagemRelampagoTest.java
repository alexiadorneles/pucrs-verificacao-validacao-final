package edu.pucrs.casosDeUso.politicas;

import edu.pucrs.BairroFactory;
import edu.pucrs.PassageiroFactory;
import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.Passageiro;
import edu.pucrs.entidades.Roteiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class CalculoCustoViagemRelampagoTest {

    CalculoCustoViagemRelampago calculoCustoViagemRelampago;

    @BeforeEach
    void setup() {
        this.calculoCustoViagemRelampago = new CalculoCustoViagemRelampago();
    }


    @ParameterizedTest
    @CsvSource({
//          deve retornar 0
            "5, 200, 100, 0",
            "4, 30, 100, 0",
            "8, 30, 100, 0",
            "8, 29, 100, 0",
//          deve aplicar 5% de desconto
            "6, 31, 100, 5",
            "10, 100, 200, 10",
            "10, 100, 200, 10",
    })
    void descontoPontuacao(int mediaPassageiro, int avaliacoesPassageiro, double custoBase, double esperado) {
        Passageiro passageiro = PassageiroFactory.comAvaliacoesEMedia(avaliacoesPassageiro, mediaPassageiro);
        this.calculoCustoViagemRelampago.definePassageiro(passageiro);
        CalculoCustoViagemRelampago spy = spy(this.calculoCustoViagemRelampago);
        doReturn(custoBase).when(spy).calculoCustoBasico();
        assertEquals(esperado, spy.descontoPontuacao());
    }

    @ParameterizedTest
    @MethodSource("sazonalProvider")
    void descontoPromocaoSazonal(int quantidadeBairros, List<Integer> custos, int esperado) {
        List<Bairro> bairros = IntStream.range(0, quantidadeBairros)
                .mapToObj(i -> BairroFactory.novoBairro(custos.get(i)))
                .collect(Collectors.toList());

        Roteiro roteiro = mock(Roteiro.class);
        when(roteiro.bairrosPercoridos()).thenReturn(bairros);
        this.calculoCustoViagemRelampago.defineRoteiro(roteiro);

        assertEquals(esperado, this.calculoCustoViagemRelampago.descontoPromocaoSazonal());
    }

    private static Stream<Arguments> sazonalProvider() {
        return Stream.of(
                arguments(3, Arrays.asList(10, 15, 20), 0),
                arguments(2, Arrays.asList(10, 15), 0),
                arguments(4, Arrays.asList(10, 15, 35, 20), 4)
        );
    }

}