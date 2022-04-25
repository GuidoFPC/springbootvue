import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';

Vue.config.productionTip = false

import "bootstrap/dist/css/bootstrap.min.css";

import DocumentosLista from './components/DocumentosLista.vue'
import DocumentosInclusao from './components/DocumentosInclusao.vue'
import DocumentosAlteracao from './components/DocumentosAlteracao.vue'

Vue.use(VueRouter);

const routes = [
  { path: '/', component: DocumentosLista },
  { path: '/inclusao', component: DocumentosInclusao },
  { path: '/alteracao/:id', component: DocumentosAlteracao, props: true }
]
var router = new VueRouter({ routes })

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
