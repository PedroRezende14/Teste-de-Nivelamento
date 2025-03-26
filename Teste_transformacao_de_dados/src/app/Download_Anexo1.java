package app;

import java.io.IOException;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Download_Anexo1 {

	// essa classe é responsável por chamar o metodo para realizar download
	public void DownloadAnexo(String nomeAnexo){
		try {
			Requisicao("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"
					, "./"+nomeAnexo+".pdf"); 
			
			System.out.println("Download efetuado com sucesso");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void Requisicao(String urlStr, String file) throws IOException {
		@SuppressWarnings("deprecation")
		URL url = new URL(urlStr);
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		rbc.close();
	}

}
