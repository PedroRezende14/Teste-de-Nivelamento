package app;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		Download_Anexo1 da = new Download_Anexo1();
		da.DownloadAnexo("Anexo1"); // Deve passar o nome desejado ao anexo
	
		ExtrairTabela et = new ExtrairTabela();		
		et.extrair("tabela_extraida", "Anexo1.pdf"); //Setar um nome para o arquivo csv, e informar o anexo.pdf
		
													//Informar o CSV,    é possivel selecionar as legendas desejadas no arquivo csv
//																Legendas  OD  AMB    HCO   HSO     REF   PAC
		ModificadorTabela mt= new ModificadorTabela("tabela_extraida.csv", true, true, false, false, false,false);
		mt.processarTabelaCompleta();
		
		mt.compactarZip("tabela_extraida.csv", "Teste_PedroRezende.zip");

	}

}
