from flask import Flask, jsonify
from flask_cors import CORS  
import psycopg2
from psycopg2 import OperationalError, ProgrammingError

app = Flask(__name__)
CORS(app)  

DB_HOST = "localhost"
DB_PORT = "5432"
DB_NAME = "admin"
DB_USER = "admin"
DB_PASSWORD = "admin"

def get_db_connection():
    """Estabelece conexão com o banco de dados"""
    try:
        conn = psycopg2.connect(
            host=DB_HOST,
            port=DB_PORT,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD
        )
        return conn
    except OperationalError as e:
        print(f"Erro ao conectar ao banco de dados: {e}")
        raise

@app.route('/operadoras', methods=['GET'])
def listar_operadoras():
    """Retorna todas as operadoras com os campos necessários"""
    try:
        conn = get_db_connection()
        cur = conn.cursor()
        
        query = """
        SELECT registro_ans, cnpj, razao_social, modalidade, cep, telefone, 
               endereco_eletronico, representante, cargo_representante
        FROM operadoras;
        """
        cur.execute(query)
        colunas = [desc[0] for desc in cur.description]  
        

        operadoras = [
            dict(zip(colunas, row))
            for row in resultados
        ]
        
        cur.close()
        conn.close()
        
        return jsonify(operadoras)
    
    except OperationalError as e:
        return jsonify({"erro": f"Erro de conexão com o banco: {str(e)}"}), 500
    except ProgrammingError as e:
        return jsonify({"erro": f"Erro SQL: {str(e)}"}), 500
    except Exception as e:
        return jsonify({"erro": f"Erro inesperado: {str(e)}"}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)