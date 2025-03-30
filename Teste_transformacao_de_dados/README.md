### TESTE DE TRANSFORMAÇÃO DE DADOS

**Tecnologias utilizadas:** 

- **Linguagem:** Java
- **IDE:** Eclipse
- **Bibliotecas:** java.io, java.util.zip, java.nio, java.util.Scanner, org.apache.pdfbox (para manipulação de PDF), technology.tabula (para extração de tabelas de PDF)
<br>

**Funcionalidades solicitadas:**
- Download de PDF: Baixar um arquivo PDF de URL pública da ANS (Agência Nacional de Saúde Suplementar);
- Salvar localmente com o nome especificado (padrão: Anexo1.pdf);
- Extração de tabelas:
    - Extrair tabelas do PDF para formato CSV;
    - Processar e formatar os dados da tabela;
    - Mantém estrutura original com cabeçalhos adequados.
- Modificação de dados:
    - Substituir códigos por legendas descritivas (OD → Seg. Odontológica, etc.); 
- Compactação:
    - Compactar o arquivo CSV resultante em formato ZIP;

**[EXTRA] Funcionalidades adicionais:**
- Customização, permitindo especificar:
    - URL do PDF que será baixado;
    - Nome do arquivo de saída;
    - Colunas a serem modificadas;
    - Nome do arquivo compactado.


**Estrutura do Projeto:**
| **Classe** | **Responsabilidade** | 
| --- | --- |
|Main	| Ponto de entrada, orquestra o fluxo do programa |
|Download_Anexo1 |	Gerencia o download do PDF usando java.nio |
|ExtrairTabela	| Extrai tabelas do PDF usando Tabula/PDFBox e gera CSV |
|ModificadorTabela |	Processa e modifica os dados do CSV conforme seleção do usuário|

**Fluxo do Programa:**
- Baixa o PDF automaticamente da URL especificada.
- Extrai as tabelas para formato CSV.
- Processa o CSV substituindo códigos por legendas (conforme selecionado).
- Compacta o arquivo CSV resultante.
- Gera arquivo ZIP no diretório do projeto.

**Diagrama de Classes:**
<img src="img/Diagrama.png">

### Executando o projeto:

Após a execução do método de compactação, o programa oferece ao usuário a opção de selecionar o formato desejado para o arquivo compactado, e depois compactar a tabela em .zip 

**Etapa 1 - Download do anexo e criação da tabela**

**1.1** Baixando o anexo 1:

<img src="img/teste_tranformacao_de_dados_1.1.png">

**1.2** Salvando os dados na tabela em formato .csv:

<img src="img/teste_tranformacao_de_dados_1.2.png">

**1.3** Verificando se a tabela está funcional:

<img src="img/teste_tranformacao_de_dados_1.3.png">

**Etapa 2 - Modificando a tabela**

[EXTRA] É possível selecionar as legendas desejadas no arquivo CSV. Para modificar, basta definir como true ou false.
Legendas disponíveis: OD, AMB, HCO, HSO, REF, PAC.

**2.1** Método ModificadorTabela:

<img src="img/teste_tranformacao_de_dados_2.1.png">

**2.2** Modificando as colunas OD e AMB:

<img src="img/teste_tranformacao_de_dados_2.2.png">

**2.2.1** Tabela modificada:

<img src="img/teste_tranformacao_de_dados_2.2.1.png">

Link da tabela: [OD e AMB.csv](https://drive.google.com/file/d/1V825qkPV6B-iyJcZB97xQK9_vDwkfznD/view?usp=sharing)

**2.3** Modificando as colunas OD, AMB e HCO:

<img src="img/teste_tranformacao_de_dados_2.3.png">

**2.3.1** Tabela modificada:

<img src="img/teste_tranformacao_de_dados_2.3.1.png">

Link da tabela: [OD, AMB e HCO.csv](https://drive.google.com/file/d/1ucd27KLM9UqB82QVJOBB2JU08KU95QDk/view?usp=sharing)

**2.4** Modificando as colunas OD, AMB,HCO e HSO:

<img src="img/teste_tranformacao_de_dados_2.4.png">

**2.4.1** Tabela modificada:

<img src="img/teste_tranformacao_de_dados_2.4.1.png">

Link da tabela: [OD, AMB, HCO e HSO.csv](https://drive.google.com/file/d/19lqTkq6WlPVZsKquDxdfkuhmQltqAG9l/view?usp=sharing)

**2.5** Modificando as colunas OS, AMB,HCO, HSO, REF, PAC:

<img src="img/teste_tranformacao_de_dados_2.5.png">

**2.5.1** Tabela modificada:

<img src="img/teste_tranformacao_de_dados_2.5.1.png">

Link da tabela: [OD, AMB, HCO, HSO, REF, PAC.csv](https://drive.google.com/file/d/1HCAZnqJmGfwlFpEgNSjLtzKOz1Y06mhx/view?usp=sharing)

**2.6** Modificando as colunas HCO, HSO, REF, PAC::

<img src="img/teste_tranformacao_de_dados_2.6.png">

**2.6.1** Tabela modificada:

<img src="img/teste_tranformacao_de_dados_2.6.1.png">

Link da tabela: [HCO, HSO, REF, PAC.csv](https://drive.google.com/file/d/1MUgUEBZkIewckLyXXlJU55qM6ubwMfKg/view?usp=sharing)

**Etapa 3 - Compactando a tabela**

**3.1** Tabela compactada no arquivo Teste_PedroRezende.zip::

<img src="img/teste_tranformacao_de_dados_3.1.png">

