package padroescriacao.unificacao;

public class Aplicacao {
    public static void main(String[] args) {
        String modalidade = args.length == 0 ? "Padrao" : args[0];
        Entrega entrega = FactoryMethod.getInstance().criarEntrega(modalidade);
        System.out.println(entrega.emitirEtiqueta());
        System.out.println(entrega.emitirComprovante());
    }
}
