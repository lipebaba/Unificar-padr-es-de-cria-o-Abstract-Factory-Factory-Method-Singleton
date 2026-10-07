package padroescriacao.unificacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CriadorEntregaTest {
    @Test
    void deveDelegarParaMetodoSobrescritoPelaSubclasse() {
        CriadorEntrega criador = new CriadorEntrega() {
            @Override
            protected FabricaAbstrata criarFabrica() {
                return new FabricaAbstrata() {
                    public Etiqueta createEtiqueta() { return () -> "Outra etiqueta"; }
                    public Comprovante createComprovante() { return () -> "Outro comprovante"; }
                };
            }
        };
        Entrega entrega = criador.criarEntrega();
        assertEquals("Outra etiqueta", entrega.emitirEtiqueta());
        assertEquals("Outro comprovante", entrega.emitirComprovante());
    }
}
