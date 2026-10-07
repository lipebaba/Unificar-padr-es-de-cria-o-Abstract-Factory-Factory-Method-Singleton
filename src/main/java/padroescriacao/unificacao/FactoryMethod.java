package padroescriacao.unificacao;

import java.lang.reflect.InvocationTargetException;

/** Ponto único de seleção dos criadores; Singleton sem estado mutável. */
public final class FactoryMethod {
    private static final FactoryMethod instance = new FactoryMethod();

    private FactoryMethod() {
    }

    public static FactoryMethod getInstance() {
        return instance;
    }

    public Entrega criarEntrega(String modalidade) {
        return obterCriador(modalidade).criarEntrega();
    }

    public CriadorEntrega obterCriador(String modalidade) {
        if (modalidade == null || !modalidade.matches("[A-Z][A-Za-z0-9]*")) {
            throw new IllegalArgumentException("Modalidade inválida");
        }
        try {
            Class<?> classe = Class.forName(
                    FactoryMethod.class.getPackageName() + ".CriadorEntrega" + modalidade);
            if (!CriadorEntrega.class.isAssignableFrom(classe)) {
                throw new IllegalArgumentException("Criador inválido");
            }
            return classe.asSubclass(CriadorEntrega.class).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("Modalidade inexistente", ex);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ex) {
            throw new IllegalArgumentException("Não foi possível criar o criador", ex);
        }
    }
}
