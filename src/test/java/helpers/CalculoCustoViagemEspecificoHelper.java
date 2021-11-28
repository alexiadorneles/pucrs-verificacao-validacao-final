package helpers;

import edu.pucrs.PassageiroFactory;
import edu.pucrs.casosDeUso.politicas.CalculoCustoViagemBasico;
import edu.pucrs.entidades.Passageiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

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
}
