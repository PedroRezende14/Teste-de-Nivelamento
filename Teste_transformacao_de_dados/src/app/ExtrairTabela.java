package app;

import java.io.*;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

public class ExtrairTabela {
	
    public void Extrair() {
        String caminhoPdf = "Anexo1.pdf";  // Caminho do PDF
        String caminhoCsv = "tabela_extraida.csv"; // Caminho do CSV

        try (PDDocument document = PDDocument.load(new File(caminhoPdf));
             BufferedWriter csvWriter = new BufferedWriter(new FileWriter(caminhoCsv))) {
            
            ObjectExtractor extractor = new ObjectExtractor(document);
            SpreadsheetExtractionAlgorithm algoritmo = new SpreadsheetExtractionAlgorithm();

            csvWriter.write("Procedimento,RN(alteração),Vigência,OD,AMB,HCO,HSO,REF,PAC,DUT,SUBGRUPO,GRUPO,CAPÍTULO");
            csvWriter.newLine();
            
            boolean cabecalhoEncontrado = false;

            for (int i = 1; i <= document.getNumberOfPages(); i++) {
                Page pagina = extractor.extract(i);
                List<Table> tabelas = algoritmo.extract(pagina);

                for (Table tabela : tabelas) {
                    for (List<RectangularTextContainer> linha : tabela.getRows()) {
                        if (!cabecalhoEncontrado) {
                            if (linha.toString().contains("Procedimento") || 
                                linha.toString().contains("RN(alteração)")) {
                                cabecalhoEncontrado = true;
                                continue; 
                            }
                        }
                        StringBuilder linhaCsv = new StringBuilder();
                        for (int j = 0; j < linha.size(); j++) {
                            String celula = linha.get(j).getText().trim();
                            celula = celula.replace(",", " ");
                            celula = celula.replace("\n", " ");
                            linhaCsv.append("\"").append(celula).append("\"");
                            if (j < linha.size() - 1) {
                                linhaCsv.append(",");
                            }
                        }
                        csvWriter.write(linhaCsv.toString());
                        csvWriter.newLine();
                    }
                }
            }


            System.out.println("Tabela extraída e salva em: " + caminhoCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
