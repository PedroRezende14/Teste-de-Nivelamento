package app;

import java.io.*;
import java.util.zip.*;
import java.util.Scanner;
import java.io.File;

public class Compactador  {
	InfoAnexos_DTO infoAnexos = new InfoAnexos_DTO();

	public void setInfoAnexos(InfoAnexos_DTO infoAnexos) {
		this.infoAnexos = infoAnexos;
	}


	Scanner ent = new Scanner(System.in);


	// console onde permite o usuario selecionar o formado no qual o arquivo sera compactado 
	public void acoes() {

		if (verificarArquivos()) {
			System.out.println("Selecione o Formato desejado: \n 1-zip \n 2-rar Requisitos:('é necessario ter o winrar instalado na máquina \n 3-Tar Requisitos:(Windows >= 10 ou Linux) \n 4-Tar.gz Requisitos:(Windows >= 10 ou Linux) \n 5-todos os formatos");
			int opc = ent.nextInt();
			try {
				switch (opc) {
				case 1:
					compactarZip(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.zip");
					System.out.println("Arquivo Compactado com sucesso");
					break;

				case 2:
					compactarRar(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.rar");
					System.out.println("Arquivo Compactado com sucesso");
					break;

				case 3:
					compactarTar(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.tar");
					System.out.println("Arquivo Compactado com sucesso");
					break;

				case 4:
					compactarTarGz(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.tar.gz");
					System.out.println("Arquivo Compactado com sucesso");
					break;

				case 5:
					compactarZip(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.zip");
					compactarRar(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.rar");
					compactarTar(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.tar");
					compactarTarGz(infoAnexos.getNomeAnexo1(), infoAnexos.getNomeAnexo2(), "Anexos_1e2.tar.gz");
					break;
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}


	// compactar em zip, passando no contrutor, o nome/diretorio nos anexos, e o nome do arquivo de saida 
	private void compactarZip(String Anexo1, String Anexo2, String arquivoSaida) throws IOException {
		String[] arquivosParaZipar = {Anexo1, Anexo2};

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
	}


	private void compactarRar(String anexo1, String anexo2, String arquivoSaida) throws IOException {
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder pb;

		if (os.contains("win")) { //vamos detectar o systema operacional 
			String winRarPath = "C:\\Program Files\\WinRAR\\WinRAR.exe"; 
			pb = new ProcessBuilder(winRarPath, "a", "-r", arquivoSaida, anexo1, anexo2);//comando para anexar os arquivos usando linux
		} else {
			pb = new ProcessBuilder("rar", "a", arquivoSaida, anexo1, anexo2); // comando para anexar os arquvivos usando linux
		}

		pb.directory(new File(".")); 
		Process process = pb.start();

	}

	private void compactarTar(String anexo1, String anexo2, String arquivoSaida) throws IOException {
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder pb;

		if (os.contains("win")) {//vamos detectar o sistema operacional 
			pb = new ProcessBuilder("powershell", "-Command", 
					"tar -cf " + arquivoSaida + " " + anexo1 + " " + anexo2);
		} else { 
			pb = new ProcessBuilder("tar", "-cf", arquivoSaida, anexo1, anexo2);
		}
		pb.directory(new File(".")); 
		Process process = pb.start();


	}

	private void compactarTarGz(String anexo1, String anexo2, String arquivoSaida) throws IOException {
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder pb;

		if (os.contains("win")) {//vamos detectar o sistema operacional 
			pb = new ProcessBuilder("powershell", "-Command", 
					"tar -czf " + arquivoSaida + " " + anexo1 + " " + anexo2);
		} else { 
			pb = new ProcessBuilder("tar", "-czf", arquivoSaida, anexo1, anexo2);
		}
		pb.directory(new File(".")); 
		Process process = pb.start();

	}

	private boolean verificarArquivos() { // classe responsavel por verificar a existencia dos 2 arquivos 
		File anexo1 = new File(infoAnexos.getNomeAnexo1());
		File anexo2 = new File(infoAnexos.getNomeAnexo2());

		if (anexo1.exists() && anexo2.exists()) {
			return true;
		}
		else { 
			System.out.println("arquivos não encontrados");
			return false;
		}
	}


}
