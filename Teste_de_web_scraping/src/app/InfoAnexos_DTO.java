package app;

public class InfoAnexos_DTO {

	//Url para localizar o pdf
	private String urlAnexo1 = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
	private String urlAnexo2 = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf";

	// declarar o nome dos anexos e definir em qual diretorio ele vai ficar localizado
	private String nomeAnexo1 = "./Anexo1.pdf";
	private String nomeAnexo2 = "./Anexo2.pdf";

	public String getUrlAnexo1() {
		return urlAnexo1;
	}
	public void setUrlAnexo1(String urlAnexo1) {
		this.urlAnexo1 = urlAnexo1;
	}
	public String getUrlAnexo2() {
		return urlAnexo2;
	}
	public void setUrlAnexo2(String urlAnexo2) {
		this.urlAnexo2 = urlAnexo2;
	}
	public String getNomeAnexo1() {
		return nomeAnexo1;
	}
	public void setNomeAnexo1(String nomeAnexo1) {
		this.nomeAnexo1 = nomeAnexo1;
	}
	public String getNomeAnexo2() {
		return nomeAnexo2;
	}
	public void setNomeAnexo2(String nomeAnexo2) {
		this.nomeAnexo2 = nomeAnexo2;
	}




}
