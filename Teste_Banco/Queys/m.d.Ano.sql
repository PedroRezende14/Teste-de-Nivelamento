-- referente ao ano de 2007 
SELECT 
    o.razao_social,
    o.registro_ans,
    o.cnpj,
    o.modalidade,
    o.ddd,
    o.telefone,
    o.fax,
    o.endereco_eletronico,
    o.representante,
    o.cargo_representante,
    o.regiao_de_comercializacao,
    o.data_registro_ans,
    SUM(d.VL_SALDO_FINAL) AS total_despesa
FROM 
    DEMONSTRACOES_CONTABEIS d
JOIN 
    OPERADORAS o ON d.REG_ANS = o.registro_ans
WHERE 
    d.DATA >= '2007-01-01'  
    AND d.DATA <= '2007-12-31'  
GROUP BY 
    o.razao_social, 
    o.registro_ans, 
    o.cnpj, 
    o.modalidade, 
    o.ddd, 
    o.telefone, 
    o.fax, 
    o.endereco_eletronico, 
    o.representante, 
    o.cargo_representante, 
    o.regiao_de_comercializacao, 
    o.data_registro_ans
ORDER BY 
    total_despesa DESC
LIMIT 10;


-- referente ao ano de 2008 
SELECT 
  SELECT 
    o.razao_social,
    o.registro_ans,
    o.cnpj,
    o.modalidade,
    o.ddd,
    o.telefone,
    o.fax,
    o.endereco_eletronico,
    o.representante,
    o.cargo_representante,
    o.regiao_de_comercializacao,
    o.data_registro_ans,
    SUM(d.VL_SALDO_FINAL) AS total_despesa
FROM 
    DEMONSTRACOES_CONTABEIS d
JOIN 
    OPERADORAS o ON d.REG_ANS = o.registro_ans
WHERE 
    d.DATA >= '2008-01-01'  
    AND d.DATA <= '2008-12-31'  
GROUP BY 
    o.razao_social, 
    o.registro_ans, 
    o.cnpj, 
    o.modalidade, 
    o.ddd, 
    o.telefone, 
    o.fax, 
    o.endereco_eletronico, 
    o.representante, 
    o.cargo_representante, 
    o.regiao_de_comercializacao, 
    o.data_registro_ans
ORDER BY 
    total_despesa DESC
LIMIT 10;



