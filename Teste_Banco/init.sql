CREATE TABLE IF NOT EXISTS DEMONSTRACOES_CONTABEIS (
    CD_CONTA_CONTABIL INT,
    DATA DATE,
    REG_ANS VARCHAR(10),
    DESCRICAO TEXT,
    VL_SALDO_FINAL NUMERIC(18,2),
    PRIMARY KEY (CD_CONTA_CONTABIL, DATA, REG_ANS)
);

CREATE TABLE IF NOT EXISTS OPERADORAS (
    id SERIAL PRIMARY KEY,
    registro_ans VARCHAR(20)  UNIQUE, -- Adicionando UNIQUE
    cnpj VARCHAR(20) ,
    razao_social VARCHAR(255) ,
    nome_fantasia VARCHAR(255) ,
    modalidade VARCHAR(255) ,
    logradouro VARCHAR(255) ,
    numero VARCHAR(50) ,
    complemento VARCHAR(255) NULL,
    bairro VARCHAR(255) ,
    cidade VARCHAR(255) ,
    uf CHAR(2) ,
    cep VARCHAR(10) ,
    ddd VARCHAR(3) ,
    telefone VARCHAR(50) ,
    fax VARCHAR(50) NULL,
    endereco_eletronico VARCHAR(255) NULL,
    representante VARCHAR(255) ,
    cargo_representante VARCHAR(255) ,
    regiao_de_comercializacao VARCHAR(255) ,
    data_registro_ans DATE ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
