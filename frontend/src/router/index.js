import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: () => import('../views/DocumentosLista.vue')},
  { path: '/inclusao', component: () => import('../views/DocumentosInclusao.vue')},
  { path: '/alteracao/:id', component: () => import('../views/DocumentosAlteracao.vue')},
]

const router = new VueRouter({
  routes
})

export default router
