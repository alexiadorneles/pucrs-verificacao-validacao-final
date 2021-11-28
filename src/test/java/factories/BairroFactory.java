package factories;

import edu.pucrs.entidades.Bairro;
import edu.pucrs.entidades.geometria.Area;

import static org.mockito.Mockito.mock;

public class BairroFactory {
    public static Bairro novoBairro(int custo) {
        return new Bairro("Bairro " + custo, mock(Area.class), custo);
    }
}
