import edu.pucrs.entidades.geometria.Area;
import edu.pucrs.entidades.geometria.Ponto;
import edu.pucrs.entidades.geometria.Reta;
import edu.pucrs.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    @Test
    void classificaDeveRetornarTodaDentro() {
        Ponto pontoSupEsq = new Ponto(0, 5);
        Ponto pontoInfDir = new Ponto(5, 0);
        Area area = new Area(pontoSupEsq, pontoInfDir);
        Reta reta = new Reta(new Ponto(1, 2), new Ponto(2, 1));
        assertEquals(SituacaoReta.TODA_DENTRO, area.classifica(reta));
    }
}