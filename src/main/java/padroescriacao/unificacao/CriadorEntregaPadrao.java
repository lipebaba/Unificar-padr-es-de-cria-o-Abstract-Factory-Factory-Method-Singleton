package padroescriacao.unificacao;

public class CriadorEntregaPadrao extends CriadorEntrega {
    @Override
    protected FabricaAbstrata criarFabrica() {
        return new FabricaEntregaPadrao();
    }
}
