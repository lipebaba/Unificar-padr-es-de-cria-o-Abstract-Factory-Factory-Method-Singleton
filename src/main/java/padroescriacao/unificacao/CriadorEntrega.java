package padroescriacao.unificacao;

public abstract class CriadorEntrega {
    // Factory Method: a subclasse decide qual família será criada.
    protected abstract FabricaAbstrata criarFabrica();

    public final Entrega criarEntrega() {
        return new Entrega(criarFabrica());
    }
}
