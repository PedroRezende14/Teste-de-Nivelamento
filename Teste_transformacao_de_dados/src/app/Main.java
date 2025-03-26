package app;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
//		Download_Anexo1 da = new Download_Anexo1();
//		da.DownloadAnexo();
//		
//		ExtrairTabela et = new ExtrairTabela();
//		et.Extrair();
//		
		Modificador_Tabela mt= new Modificador_Tabela("tabela_extraida.csv", true, true, true, true, true);
		try {
			mt.processarTabelaCompleta();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
