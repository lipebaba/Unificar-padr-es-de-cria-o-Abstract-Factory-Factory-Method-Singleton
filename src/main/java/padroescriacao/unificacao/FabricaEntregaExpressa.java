package padroescriacao.unificacao;

public class FabricaEntregaExpressa implements FabricaAbstrata {
    @Override
    public Etiqueta createEtiqueta() {
        return new EtiquetaExpressa();
    }

    @Override
    public Comprovante createComprovante() {
        return new ComprovanteExpressa();
    }
}
