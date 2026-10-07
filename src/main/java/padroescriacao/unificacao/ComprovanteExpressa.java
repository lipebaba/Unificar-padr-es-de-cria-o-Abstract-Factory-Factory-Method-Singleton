package padroescriacao.unificacao;

public class ComprovanteExpressa implements Comprovante {
    @Override
    public String emitir() {
        return "Comprovante de entrega expressa";
    }
}
