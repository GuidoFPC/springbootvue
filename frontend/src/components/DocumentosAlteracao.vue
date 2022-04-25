<template>
  <div>
    <h3 class="d-flex justify-content-center">Springboot e Vue 2</h3>
    <h6 class="d-flex justify-content-center">
      Formulário para alteração do documento.
    </h6>

    <div class="d-flex align-items-center justify-content-center bg-light">
      <form id="doc-form" method="POST" @submit="createDoc">
        <br />
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Nome</span>
          <input type="text" class="form-control" v-model="doc.nome" />
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Tipo</span>
          <select class="form-select" v-model="doc.tipo">
            <option v-for="(tipo, i) in tipos" :key="i">
              {{ tipo.nome }}
            </option>
          </select>
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text w-25">Classificação</span>
          <select class="form-select" v-model="doc.classificacao">
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
            v-model="doc.conteudo"
          />
        </div>

        <div class="container bg-light">
          <div class="col-md-12 text-center">
            <button type="submit" class="btn btn-primary btn-sm me-1">
              Alterar
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
import axios from "axios";

const todoUrl = "http://localhost:8180/documentos";

export default {
  name: "DocumentosInclusao",
  data() {
    return {
      id: null,
      doc: {},
      tipos: [],
      classificacoes: [],
    };
  },
  props: {
    
  },
  methods: {
    async createDoc(e) {

      e.preventDefault();

      if (!confirm("Confirma a alteração do registro?")) {
        return;
      }

      const data = {
        id: this.id,
        nome: this.doc.nome,
        tipo: this.doc.tipo,
        classificacao: this.doc.classificacao,
        conteudo: this.doc.conteudo,
      };

      const dataJson = JSON.stringify(data);

      const req = await fetch(`http://localhost:8180/documentos/${this.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: dataJson,
      });

      const res = await req.json();

      console.log("Alteração realizada com sucesso!");
      // console.log(res);

      // clear message
      setTimeout(() => (this.msg = ""), 3000);

      // window.location.replace("/");
      this.$router.push("/");
    },
  },
  async created() {
    this.tipos = [
      { nome: "Memorando" },
      { nome: "Circular" },
      { nome: "Decreto" },
      { nome: "Lei" },
    ];
    this.classificacoes = [{ nome: "Público" }, { nome: "Sigiloso" }];

    this.id = this.$route.params.id;

    await axios
      .get(`http://localhost:8180/documentos/${this.id}`)
      .then((response) => {
         this.doc = response.data;
      });

  },
};
</script>
