package app;

public class Main {

	public static void main(String[] args) {

		Download_Anexos da = new Download_Anexos();
		
		// caso queria modificar a url, diretorio/nome do arquivo
		//    	InfoAnexos_DTO infoAnexos = new InfoAnexos_DTO(); 
		//    	infoAnexos.setNomeAnexo1("./teste1.pdf"); // caso queria muda o nome do arquivo;
		//    	infoAnexos.setNomeAnexo2("./teste2.pdf");// caso queria muda o nome do arquivo;
		//    	infoAnexos.setUrlAnexo1("http:..."); // caso queria mudar a url
		//    	infoAnexos.setUrlAnexo1("http:..."); // caso queria mudar a url
		//    	
		//    	da.setInfoAnexos(infoAnexos); // para setar e realizar as informações 

		da.DownloadAnexos();
		
		
		Compactador cp = new Compactador();
		cp.acoes();

	}
}