<template>
  <div>
    <h3 class="d-flex justify-content-center">Vue 2 e Springboot</h3>
    <h6 class="d-flex justify-content-center">CRUD Documentos</h6>

    <div class="row">
      <div class="col-3">
        <button
          type="button"
          class="btn btn-outline-primary"
          @click="$router.push({ path: '/inclusao' })"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-file-plus"
            viewBox="0 0 16 16"
          >
            <path
              d="M8.5 6a.5.5 0 0 0-1 0v1.5H6a.5.5 0 0 0 0 1h1.5V10a.5.5 0 0 0 1 0V8.5H10a.5.5 0 0 0 0-1H8.5V6z"
            />
            <path
              d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm10-1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1z"
            />
          </svg>
          Novo documento
        </button>

        <!-- router-link to="/inclusao" >Pag 1</router-link -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <table class="table table-bordered table-striped mt-3">
          <thead>
            <tr>
              <th>Documentos</th>
              <th class="text-center">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, i) in todoList" :key="i">
              <td class="align-middle w-75">
                {{ item.nome }}: {{ item.conteudo }}
              </td>
              <td class="align-middle text-center w-25">
                <button
                  class="btn btn-primary btn-sm mx-1"
                  @click="handleEdit(item.id)"
                  title="Editar registro"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="currentColor"
                    class="bi bi-pencil-square"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
                    />
                  </svg>
                </button>
                <button
                  class="btn btn-danger btn-sm mx-1"
                  @click="handleDelete(item.id)"
                  title="Apagar registro"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="currentColor"
                    class="bi bi-trash-fill"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"
                    />
                  </svg>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import docsapi from "@/services/docsapi";

export default {
  name: "DocumentosLista",
  data() {
    return {
      todoList: [],
      todoItem: {},
    };
  },
  methods: {
    handleEdit(id) {
      // this.editMode = true;
      // this.todoItem = this.todoList.find((item) => item.id == id);
      this.$router.push({ path: `/alteracao/${id}` });
    },
    async handleDelete(id) {
      if (!confirm("Confirma exclusão?")) {
        return;
      }

      await docsapi.delete(`/${id}`);

      docsapi
        .get("/")
        .then((response) => (this.todoList = response.data.content));
    },
    // Servia p/ salvar um item rápido da tela;
    async handleToDoItem() {
      const id = this.todoItem.id;

      if (this.editMode) {
        await docsapi.put("/${id}", this.todoItem);
        this.editMode = false;
        this.todoItem.content = "";
      } else {
        await docsapi.post("/", this.todoItem);
        this.todoItem.content = "";
      }

      docsapi
        .get("/")
        .then((response) => (this.todoList = response.data.content));
    },
  },
  created() {
    docsapi
      .get("/")
      .then((response) => (this.todoList = response.data.content));
  },
  components: {},
};
</script>
