<template>
  <div>
    <h3 class="d-flex justify-content-center">Springboot e Vue 2</h3>
    <h6 class="d-flex justify-content-center">Formulário para inclusão de novo documento.</h6>

    <div class="d-flex align-items-center justify-content-center bg-light">
      <form id="doc-form" method="POST" @submit="createDoc">
        <br />
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Nome</span>
          <input type="text" class="form-control" v-model="nome" />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Tipo</span>
          <select class="form-select" v-model="tipo">
            <option v-for="(tipo, i) in tipos" :key="i">
              {{ tipo.nome }}
            </option>
          </select>
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Classificação</span>
          <select class="form-select" v-model="classificacao">
            <option v-for="(classificacao, i) in classificacoes" :key="i">
              {{ classificacao.nome }}
            </option>
          </select>
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Conteúdo</span>
          <textarea
            cols="30"
            rows="3"
            class="form-control"
            v-model="conteudo"
          />
        </div>

        <div class="container bg-light">
          <div class="col-md-12 text-center">
            <button type="submit" class="btn btn-primary btn-sm me-1">
              Incluir
            </button>
            <button
              type="button"
              class="btn btn-outline-secondary btn-sm"
              @click="$router.push({ path: '/' })"
            >
              Cancelar
            </button>
          </div>
        </div>
        <br />
      </form>
    </div>
  </div>
</template>

<script>

const todoUrl = "http://localhost:8180/documentos";

export default {
  name: "DocumentosInclusao",
  data() {
    return {
      nome: null,
      tipo: null,
      classificacao: null,
      conteudo: null,
      tipos: [],
      classificacoes: [],
    };
  },
  methods: {
    async createDoc(e) {

      e.preventDefault()

      if (!confirm("Confirma a inclusão do registro?")) {
        return;
      }

      const data = {
        nome: this.nome,
        tipo: this.tipo,
        classificacao: this.classificacao,
        conteudo: this.conteudo,
      };

      const dataJson = JSON.stringify(data);

      const req = await fetch(todoUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: dataJson,
      });

      const res = await req.json();

      console.log("Inclusão realizada com sucesso!");
      console.log(res);
      
      // clear message
      setTimeout(() => (this.msg = ""), 3000);

      // window.location.replace("/");
      this.$router.push("/");
    },
  },
  created() {
    this.tipos = [
      { nome: "Memorando" },
      { nome: "Circular" },
      { nome: "Decreto" },
      { nome: "Lei" },
    ];
    this.classificacoes = [{ nome: "Público" }, { nome: "Sigiloso" }];
  },
};
</script>
