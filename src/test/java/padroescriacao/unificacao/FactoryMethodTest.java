package padroescriacao.unificacao;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTest {
    @Test
    void deveRetornarSempreMesmaInstancia() {
        assertSame(FactoryMethod.getInstance(), FactoryMethod.getInstance());
        assertTrue(Modifier.isPrivate(FactoryMethod.class.getDeclaredConstructors()[0].getModifiers()));
    }

    @Test
    void deveUnificarOsTresPadroesParaEntregaPadrao() {
        Entrega entrega = FactoryMethod.getInstance().criarEntrega("Padrao");
        assertEquals("Etiqueta de entrega padrão", entrega.emitirEtiqueta());
        assertEquals("Comprovante de entrega padrão", entrega.emitirComprovante());
    }

    @Test
    void deveUnificarOsTresPadroesParaEntregaExpressa() {
        Entrega entrega = FactoryMethod.getInstance().criarEntrega("Expressa");
        assertEquals("Etiqueta de entrega expressa", entrega.emitirEtiqueta());
        assertEquals("Comprovante de entrega expressa", entrega.emitirComprovante());
    }

    @Test
    void deveSelecionarCriadorPelaConvencaoDeNomes() {
        assertInstanceOf(CriadorEntregaPadrao.class, FactoryMethod.getInstance().obterCriador("Padrao"));
        assertInstanceOf(CriadorEntregaExpressa.class, FactoryMethod.getInstance().obterCriador("Expressa"));
    }

    @Test
    void deveCriarEntregasDistintasApesarDoSingleton() {
        FactoryMethod factory = FactoryMethod.getInstance();
        assertNotSame(factory.criarEntrega("Padrao"), factory.criarEntrega("Padrao"));
    }

    @Test
    void deveRejeitarModalidadeInexistente() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FactoryMethod.getInstance().criarEntrega("Internacional"));
        assertEquals("Modalidade inexistente", ex.getMessage());
    }

    @Test
    void deveRejeitarEntradasInvalidas() {
        for (String valor : new String[] {null, "", " ", "padrao", "Padrao ", "../Expressa", "a.b"}) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> FactoryMethod.getInstance().criarEntrega(valor));
            assertEquals("Modalidade inválida", ex.getMessage());
        }
    }

    @Test
    void deveRejeitarClasseQueNaoEhCriador() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FactoryMethod.getInstance().criarEntrega("Invalida"));
        assertEquals("Criador inválido", ex.getMessage());
    }

    @Test
    void deveRejeitarCriadorSemConstrutorSemArgumentos() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FactoryMethod.getInstance().criarEntrega("SemConstrutor"));
        assertEquals("Não foi possível criar o criador", ex.getMessage());
        assertInstanceOf(NoSuchMethodException.class, ex.getCause());
    }

    @Test
    void deveCompartilharSingletonSemMisturarFamiliasEmMultiplasThreads() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try {
            List<Callable<FactoryMethod>> tarefas = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                final boolean expressa = i % 2 == 0;
                tarefas.add(() -> {
                    FactoryMethod factory = FactoryMethod.getInstance();
                    Entrega entrega = factory.criarEntrega(expressa ? "Expressa" : "Padrao");
                    String modalidade = expressa ? "expressa" : "padrão";
                    assertEquals("Etiqueta de entrega " + modalidade, entrega.emitirEtiqueta());
                    assertEquals("Comprovante de entrega " + modalidade, entrega.emitirComprovante());
                    return factory;
                });
            }
            for (Future<FactoryMethod> resultado : executor.invokeAll(tarefas, 10, TimeUnit.SECONDS)) {
                assertSame(FactoryMethod.getInstance(), resultado.get());
            }
        } finally {
            executor.shutdownNow();
        }
    }
}
