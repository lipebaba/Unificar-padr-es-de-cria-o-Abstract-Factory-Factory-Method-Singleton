package padroescriacao.unificacao;

public class CriadorEntregaExpressa extends CriadorEntrega {
    @Override
    protected FabricaAbstrata criarFabrica() {
        return new FabricaEntregaExpressa();
    }
}
