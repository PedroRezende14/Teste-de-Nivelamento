package app;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ModificadorTabela {
	private final boolean[] opcoesAtivas;
	private final String[] legendas;
	private final int[] colunasAlvo;
	private final String caminhoTabela;

	// Valores permitidos para substituição
	private static final Set<String> VALORES_VALIDOS = new HashSet<>(Arrays.asList("OD", "AMB", "HCO", "HSO", "REF", "PAC"));

	public ModificadorTabela(String caminhoTabela, boolean OD, boolean AMB, boolean HCO, boolean HSO, boolean REF, boolean PAC) {
		this.caminhoTabela = caminhoTabela;

		this.opcoesAtivas = new boolean[]{OD, AMB, HCO, HSO, REF, PAC};

		this.legendas = new String[]{
				"Seg. Odontológica",
				"Seg. Ambulatorial",
				"Seg. Hospitalar Com Obstetrícia",
				"Seg. Hospitalar Sem Obstetrícia",
				"Plano Referência",
				"Procedimento de Alta Complexidade"
		};

		this.colunasAlvo = new int[]{3, 4, 5, 6, 7, 8}; // OD, AMB, HCO, HSO, REF, PAC
	}

	public void processarTabelaCompleta() throws IOException {
		// Lê as linhas do arquivo CSV
		List<String> linhas = Files.readAllLines(Paths.get(caminhoTabela));

		// Lista para armazenar as linhas processadas
		List<String> linhasProcessadas = new ArrayList<>();
		// Itera sobre cada linha do CSV
		for (String linha : linhas) {
			String[] campos = linha.split(",", -1); 
			// Verifica as colunas alvo
			for (int i = 0; i < colunasAlvo.length; i++) {
				int coluna = colunasAlvo[i];

				if (coluna < campos.length) {
					// Remove aspas e espaços extras
					String valorOriginal = campos[coluna].trim().replace("\"", "");
					// Verifica se o valor da coluna corresponde a um dos valores válidos
					if (opcoesAtivas[i] && VALORES_VALIDOS.contains(valorOriginal)) {
						campos[coluna] = legendas[i]; 
					}
				}
			}

			String linhaProcessada = String.join(",", campos);
			linhasProcessadas.add(linhaProcessada);
		}

		System.out.println("Tabela modificada com sucesso");
		Files.write(Paths.get(caminhoTabela), linhasProcessadas);
	}
	
	
	public void compactarZip(String Anexo1,String arquivoSaida) throws IOException { // compacta os arquivos em formato zip
		String[] arquivosParaZipar = {Anexo1};

		try (FileOutputStream fos = new FileOutputStream(arquivoSaida);
				ZipOutputStream zipOut = new ZipOutputStream(fos)) {

			for (String arquivo : arquivosParaZipar) {
				if (arquivo == null) continue;

				try (FileInputStream fis = new FileInputStream(arquivo)) {
					ZipEntry zipEntry = new ZipEntry(new File(arquivo).getName());
					zipOut.putNextEntry(zipEntry);
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = fis.read(buffer)) != -1) {
						zipOut.write(buffer, 0, bytesRead);
					}
					zipOut.closeEntry();
				}
			}
		}
		System.out.println("Arquivo compactado com sucesso");
	}
	
}
