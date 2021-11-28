package edu.pucrs.casosDeUso.politicas;

import factories.BairroFactory;
import edu.pucrs.entidades.Bairro;
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

import static helpers.CalculoCustoViagemEspecificoHelper.descontoPontuacaoHelper;
import static helpers.CalculoCustoViagemEspecificoHelper.descontoSazonalHelper;
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
        descontoPontuacaoHelper(avaliacoesPassageiro, mediaPassageiro, custoBase, esperado, this.calculoCustoViagemRelampago);
    }

    @ParameterizedTest
    @MethodSource("sazonalProvider")
    void descontoPromocaoSazonal(int quantidadeBairros, List<Integer> custos, int esperado) {
        descontoSazonalHelper(quantidadeBairros, custos, esperado, this.calculoCustoViagemRelampago);
    }

    private static Stream<Arguments> sazonalProvider() {
        return Stream.of(
                arguments(3, Arrays.asList(10, 15, 20), 0),
                arguments(2, Arrays.asList(10, 15), 0),
                arguments(4, Arrays.asList(10, 15, 35, 20), 4)
        );
    }

}