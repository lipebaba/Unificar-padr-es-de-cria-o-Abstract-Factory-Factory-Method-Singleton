package padroescriacao.unificacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntregaTest {
    @Test
    void deveEmitirEtiquetaPadrao() {
        Entrega entrega = new Entrega(new FabricaEntregaPadrao());
        assertEquals("Etiqueta de entrega padrão", entrega.emitirEtiqueta());
    }

    @Test
    void deveEmitirComprovantePadrao() {
        Entrega entrega = new Entrega(new FabricaEntregaPadrao());
        assertEquals("Comprovante de entrega padrão", entrega.emitirComprovante());
    }

    @Test
    void deveEmitirEtiquetaExpressa() {
        Entrega entrega = new Entrega(new FabricaEntregaExpressa());
        assertEquals("Etiqueta de entrega expressa", entrega.emitirEtiqueta());
    }

    @Test
    void deveEmitirComprovanteExpressa() {
        Entrega entrega = new Entrega(new FabricaEntregaExpressa());
        assertEquals("Comprovante de entrega expressa", entrega.emitirComprovante());
    }

    @Test
    void deveConsumirProdutosDeUmaFabricaInjetada() {
        FabricaAbstrata fabrica = new FabricaAbstrata() {
            public Etiqueta createEtiqueta() { return () -> "Etiqueta de teste"; }
            public Comprovante createComprovante() { return () -> "Comprovante de teste"; }
        };
        Entrega entrega = new Entrega(fabrica);
        assertEquals("Etiqueta de teste", entrega.emitirEtiqueta());
        assertEquals("Comprovante de teste", entrega.emitirComprovante());
    }

    @Test
    void deveRejeitarFabricaNula() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> new Entrega(null));
        assertEquals("Fábrica obrigatória", ex.getMessage());
    }
}
