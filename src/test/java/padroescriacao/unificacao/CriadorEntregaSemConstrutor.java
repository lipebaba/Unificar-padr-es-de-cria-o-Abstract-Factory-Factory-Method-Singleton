package padroescriacao.unificacao;

public class CriadorEntregaSemConstrutor extends CriadorEntrega {
    public CriadorEntregaSemConstrutor(String argumento) { }
    @Override
    protected FabricaAbstrata criarFabrica() { return new FabricaEntregaPadrao(); }
}
