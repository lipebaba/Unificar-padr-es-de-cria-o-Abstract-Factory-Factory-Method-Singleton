package padroescriacao.unificacao;

import java.util.Objects;

public class Entrega {
    private final Etiqueta etiqueta;
    private final Comprovante comprovante;

    public Entrega(FabricaAbstrata fabrica) {
        Objects.requireNonNull(fabrica, "Fábrica obrigatória");
        this.etiqueta = Objects.requireNonNull(fabrica.createEtiqueta(), "Etiqueta obrigatória");
        this.comprovante = Objects.requireNonNull(fabrica.createComprovante(), "Comprovante obrigatório");
    }

    public String emitirEtiqueta() {
        return this.etiqueta.emitir();
    }

    public String emitirComprovante() {
        return this.comprovante.emitir();
    }
}
