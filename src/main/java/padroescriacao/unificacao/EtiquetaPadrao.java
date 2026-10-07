package padroescriacao.unificacao;

public class EtiquetaPadrao implements Etiqueta {
    @Override
    public String emitir() {
        return "Etiqueta de entrega padrão";
    }
}
