package padroescriacao.unificacao;

public class FabricaEntregaPadrao implements FabricaAbstrata {
    @Override
    public Etiqueta createEtiqueta() {
        return new EtiquetaPadrao();
    }

    @Override
    public Comprovante createComprovante() {
        return new ComprovantePadrao();
    }
}
