package padroescriacao.unificacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FabricaAbstrataTest {
    @Test
    void deveCriarFamiliaPadraoCompativel() {
        FabricaAbstrata fabrica = new FabricaEntregaPadrao();
        assertInstanceOf(EtiquetaPadrao.class, fabrica.createEtiqueta());
        assertInstanceOf(ComprovantePadrao.class, fabrica.createComprovante());
    }

    @Test
    void deveCriarFamiliaExpressaCompativel() {
        FabricaAbstrata fabrica = new FabricaEntregaExpressa();
        assertInstanceOf(EtiquetaExpressa.class, fabrica.createEtiqueta());
        assertInstanceOf(ComprovanteExpressa.class, fabrica.createComprovante());
    }
}
