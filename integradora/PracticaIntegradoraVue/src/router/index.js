import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/tiendas',
      name: 'tiendas',
      component: () => import('../views/DondeEstamos.vue')
    },
    {
      path: '/catalogo',
      name: 'catalogo',
      component: () => import('../views/CatalogoView.vue')
    },
    {
      path: '/aeg',
      name: 'aeg',
      component: () => import('../views/AegView.vue')
    },
    {
      path: '/gbb',
      name: 'gbb',
      component: () => import('../views/GbbView.vue')
    },
    {
      path: '/loggedhome',
      name: 'loggedhome',
      component: () => import('../views/logged/LoggedHome.vue')
    },
    {
      path: '/loggedabout',
      name: 'loggedabout',
      component: () => import('../views/logged/LoggedAbout.vue')
    },
    {
      path: '/loggedtiendas',
      name: 'loggedtiendas',
      component: () => import('../views/logged/LoggedDondeEstamos.vue')
    },
    {
      path: '/loggedcatalogo',
      name: 'loggedcatalogo',
      component: () => import('../views/logged/LoggedCatalogo.vue')
    },
    {
      path: '/loggedaeg',
      name: 'loggedaeg',
      component: () => import('../views/logged/LoggedAEG.vue')
    },
    {
      path: '/loggedgbb',
      name: 'loggedgbb',
      component: () => import('../views/logged/LoggedGBB.vue')
    },
    {
      path: '/carrito',
      name: 'carrito',
      component: () => import('../views/logged/CarritoOnlyLogged.vue')
    },
    {
      path: '/detalle/:idProducto',
      name: 'detalle',
      component: () => import('../views/logged/ProductoDetalle.vue')
    },
    {
      path: '/:catchAll(.*)',
      component: () => import('../views/Error.vue')
    },
   
  ]
})

export default router
