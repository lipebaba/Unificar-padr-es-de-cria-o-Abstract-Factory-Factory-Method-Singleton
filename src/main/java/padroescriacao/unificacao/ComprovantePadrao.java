package padroescriacao.unificacao;

public class ComprovantePadrao implements Comprovante {
    @Override
    public String emitir() {
        return "Comprovante de entrega padrão";
    }
}
