package edu.pucrs.casosdeuso.politicas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static helpers.CalculoCustoViagemEspecificoHelper.descontoPontuacaoHelper;
import static helpers.CalculoCustoViagemEspecificoHelper.descontoSazonalHelper;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class CalculoCustoViagemVeraoTest {

    CalculoCustoViagemVerao calculoCustoViagemVerao;

    @BeforeEach
    void setup() {
        this.calculoCustoViagemVerao = new CalculoCustoViagemVerao();
    }

    @ParameterizedTest
    @CsvSource({
//          deve retornar 0
            "9, 200, 100, 0",
            "8, 29, 100, 0",
//          deve aplicar 9% de desconto
            "10, 1, 100, 9",
            "10, 100, 200, 18",
    })
    void descontoPontuacao(int mediaPassageiro, int avaliacoesPassageiro, double custoBase, double esperado) {
        descontoPontuacaoHelper(avaliacoesPassageiro, mediaPassageiro, custoBase, esperado, this.calculoCustoViagemVerao);
    }

    @ParameterizedTest
    @MethodSource("sazonalProvider")
    void descontoPromocaoSazonal(int quantidadeBairros, List<Integer> custos, int esperado) {
        descontoSazonalHelper(quantidadeBairros, custos, esperado, this.calculoCustoViagemVerao);
    }

    private static Stream<Arguments> sazonalProvider() {
        return Stream.of(
                arguments(2, Arrays.asList(10, 15), 0),
                arguments(1, List.of(15), 0),
                arguments(3, Arrays.asList(10, 10, 10), 3),
                arguments(4, Arrays.asList(10, 15, 35, 20), 8)
        );
    }

}