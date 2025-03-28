

Acessa o container PostgreSQL
docker exec -i postgres psql -U admin -d admin <<EOF

CREATE TEMP TABLE temp_import (
    DATA TEXT,
    REG_ANS TEXT,
    CD_CONTA_CONTABIL TEXT,
    DESCRICAO TEXT,
    VL_SALDO_FINAL TEXT
);

\copy temp_import FROM '/arquivos/1T2007.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/2T2007.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/3T2007.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/4T2007.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/1T2008.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/2T2008.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/3T2008.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');
\copy temp_import FROM '/arquivos/4T2008.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');

INSERT INTO DEMONSTRACOES_CONTABEIS
SELECT 
    CD_CONTA_CONTABIL::INT,
    TO_DATE(DATA, 'DD/MM/YYYY'),
    REG_ANS,
    DESCRICAO,
    REPLACE(REPLACE(VL_SALDO_FINAL, '.', ''), ',', '.')::NUMERIC(18,2)
FROM temp_import
ON CONFLICT (CD_CONTA_CONTABIL, DATA, REG_ANS) DO NOTHING;

DROP TABLE temp_import;
EOF


docker exec -i postgres psql -U admin -d admin <<EOF

CREATE TEMP TABLE temp_import (
    registro_ans TEXT,
    cnpj TEXT,
    razao_social TEXT,
    nome_fantasia TEXT,
    modalidade TEXT,
    logradouro TEXT,
    numero TEXT,
    complemento TEXT,
    bairro TEXT,
    cidade TEXT,
    uf TEXT,
    cep TEXT,
    ddd TEXT,
    telefone TEXT,
    fax TEXT,
    endereco_eletronico TEXT,
    representante TEXT,
    cargo_representante TEXT,
    regiao_de_comercializacao TEXT,
    data_registro_ans TEXT
);

\copy temp_import FROM '/arquivos/Relatorio_cadop.csv' WITH (FORMAT csv, DELIMITER ';', HEADER true, ENCODING 'LATIN1');

INSERT INTO OPERADORAS (
    registro_ans,
    cnpj,
    razao_social,
    nome_fantasia,
    modalidade,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    uf,
    cep,
    ddd,
    telefone,
    fax,
    endereco_eletronico,
    representante,
    cargo_representante,
    regiao_de_comercializacao,
    data_registro_ans
) 
SELECT 
    registro_ans,
    cnpj,
    razao_social,
    nome_fantasia,
    modalidade,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    uf,
    cep,
    ddd,
    telefone,
    fax,
    endereco_eletronico,
    representante,
    cargo_representante,
    regiao_de_comercializacao,
    TO_DATE(data_registro_ans, 'YYYY-MM-DD')  -- Alteração para o formato correto de data
FROM temp_import
ON CONFLICT (registro_ans) DO NOTHING;

DROP TABLE temp_import;
EOF
