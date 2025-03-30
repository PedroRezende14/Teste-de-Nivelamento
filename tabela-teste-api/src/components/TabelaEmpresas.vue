<template>
  <div class="container">
    <h2>Lista de Empresas</h2>
    
    <input v-model="filtro" @input="filtrarEmpresas" placeholder="Pesquisar por texto:" class="search-box" />
    
    <div v-if="carregando" class="loading">
      Carregando dados...
    </div>
    
    <div v-if="erro" class="error">
      {{ erro }}
    </div>
    
    <div v-if="!carregando && !erro">
      <table>
        <thead>
          <tr>
            <th v-for="(coluna, index) in colunas" :key="index">{{ coluna }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(empresa, index) in empresasFiltradas" :key="index">
            <td>{{ empresa.Registro_ANS }}</td>
            <td>{{ empresa.CNPJ }}</td>
            <td>{{ empresa.Razao_social }}</td>
            <td>{{ empresa.modalidade }}</td>
            <td>{{ empresa.cep }}</td>
            <td>{{ empresa.telefone }}</td>
            <td>{{ empresa.endereco_eletronico }}</td>
            <td>{{ empresa.representante }}</td>
            <td>{{ empresa.cargo_representante }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      colunas: [
        "Registro ANS", "CNPJ", "Razão Social", "Modalidade", "CEP",
        "Telefone", "Endereço Eletrônico", "Representante", "Cargo Representante"
      ],
      empresas: [],
      empresasFiltradas: [],
      filtro: "",
      erro: null,
      carregando: true
    };
  },
  methods: {
    async carregarEmpresas() {
      this.carregando = true;
      this.erro = null;
      
      try {
        const response = await fetch("http://localhost:5000/operadoras");
        if (!response.ok) {
          throw new Error(`Erro HTTP: ${response.status}`);
        }
        
        const data = await response.json();
        if (!Array.isArray(data)) {
          throw new Error("Os dados recebidos não são um array");
        }
        
        this.empresas = data.map(item => ({
          Registro_ANS: item.Registro_ANS || item.registro_ans || 'N/A',
          CNPJ: item.CNPJ || item.cnpj || 'N/A',
          Razao_social: item.Razao_social || item.razao_social || 'N/A',
          modalidade: item.modalidade || 'N/A',
          cep: item.cep || 'N/A',
          telefone: item.telefone || 'N/A',
          endereco_eletronico: item.endereco_eletronico || 'N/A',
          representante: item.representante || 'N/A',
          cargo_representante: item.cargo_representante || 'N/A'
        }));
        
        this.empresasFiltradas = this.empresas;
      } catch (error) {
        this.erro = `Falha ao carregar dados: ${error.message}`;
      } finally {
        this.carregando = false;
      }
    },
    filtrarEmpresas() {
      const termo = this.filtro.toLowerCase();
      this.empresasFiltradas = this.empresas.filter(empresa =>
        Object.values(empresa).some(valor =>
          String(valor).toLowerCase().includes(termo)
        )
      );
    }
  },
  mounted() {
    this.carregarEmpresas();
  }
};
</script>

<style scoped>

.container {
  width: 100vw; 
  max-width: 100vw;
  display: block;
}


.search-box {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

h2 {
  text-align: center;
}

.loading, .error {
  text-align: center;
  color: red;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #4CAF50;
  color: white;
}


</style>