import Vue from 'vue'
import Vuex from 'vuex'
import ProductsStore from './modules/productsStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    ProductsStore
  },
  strict: false
})
