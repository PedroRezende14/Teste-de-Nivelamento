package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Modificador_Tabela {
    private final List<Boolean> opcoesSelecionadas;
    private final List<String> legendas;
    private final String caminhoTabela;

    public Modificador_Tabela(String caminhoTabela, boolean OD, boolean AMB, boolean HCO, boolean HSO, boolean REF) {
    	
        this.opcoesSelecionadas = List.of(OD, AMB, HCO, HSO, REF );
        this.legendas = List.of(
            "Seg. Odontológica",
            "Seg. Ambulatorial",
            "Seg. Hospitalar Com Obstetrícia",
            "Seg. Hospitalar Sem Obstetrícia",
            "Plano Referência"
        );
        this.caminhoTabela = caminhoTabela;
    }

    public void processarTabelaCompleta() throws IOException {
        Path path = Paths.get(caminhoTabela);
        
        try (Stream<String> linhas = Files.lines(path)) {
            List<String> linhasProcessadas = linhas
                .map(linha -> linha.split(","))
                .map(this::modificarLinha)
                .collect(Collectors.toList());
            Files.write(path, linhasProcessadas);
        }
    }

    private String modificarLinha(String[] linhaOriginal) {
        List<Integer> indicesColunas = List.of(3, 4, 5, 6, 7, 8, 9);
        
        return IntStream.range(0, linhaOriginal.length)
            .mapToObj(i -> {
                int indexOpcao = indicesColunas.indexOf(i);
                if (indexOpcao != -1 && opcoesSelecionadas.get(indexOpcao)) {
                    return legendas.get(indexOpcao);
                }
                return linhaOriginal[i];
            })
            .collect(Collectors.joining(","));
    }
}