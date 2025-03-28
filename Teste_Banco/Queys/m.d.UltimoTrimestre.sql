-- Referente ao ultimo trimeste de 2007
SELECT 
    REG_ANS, 
    DATA, 
    SUM(VL_SALDO_FINAL) AS total_despesas
FROM 
    DEMONSTRACOES_CONTABEIS
WHERE 
    DESCRICAO = 'Outras Despesas com Eventos/Sinistros Conhecidos de Assistência Médico-Hospitalar'
    AND (
        (DATA BETWEEN '2007-10-01' AND '2007-12-31') 
    )
GROUP BY 
    REG_ANS, DATA
ORDER BY 
    EXTRACT(YEAR FROM DATA), total_despesas DESC
LIMIT 10;

-- Referente ao ultimo trimeste de 2008
SELECT 
    REG_ANS, 
    DATA, 
    SUM(VL_SALDO_FINAL) AS total_despesas
FROM 
    DEMONSTRACOES_CONTABEIS
WHERE 
    DESCRICAO = 'Outras Despesas com Eventos/Sinistros Conhecidos de Assistência Médico-Hospitalar'
    AND (
        (DATA BETWEEN '2008-10-01' AND '2008-12-31') 
    )
GROUP BY 
    REG_ANS, DATA
ORDER BY 
    EXTRACT(YEAR FROM DATA), total_despesas DESC
LIMIT 10;
