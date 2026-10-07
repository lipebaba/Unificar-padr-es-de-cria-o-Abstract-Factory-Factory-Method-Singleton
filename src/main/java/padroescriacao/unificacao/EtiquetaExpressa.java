package padroescriacao.unificacao;

public class EtiquetaExpressa implements Etiqueta {
    @Override
    public String emitir() {
        return "Etiqueta de entrega expressa";
    }
}
