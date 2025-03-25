package app;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Download_Anexos {

	InfoAnexos_DTO infoAnexos = new InfoAnexos_DTO(); // chamando o objeto que esta com as informações de url, e nome do arquivo

	public void setInfoAnexos(InfoAnexos_DTO infoAnexos) {
		this.infoAnexos = infoAnexos;	
	}

	// essa classe é responsável por chamar o metodo para realizar download
	public void DownloadAnexos(){
		try {

			Requisicao(infoAnexos.getUrlAnexo1(), infoAnexos.getNomeAnexo1()); // chamando o metodo de dowload, e passando no contrutor o a url e diretorio/nome do arquivo
			Requisicao(infoAnexos.getUrlAnexo2(), infoAnexos.getNomeAnexo2());

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
