import {
  HTTP
} from '../../api/productApi'
import {
  PRODUCTS_V1,
  PRODUCT_V1
} from '../../api/paths'
import {
  getField,
  updateField
} from 'vuex-map-fields'

const namespaced = true

// initial state
const state = {
  all: [],
  product: {},
  page: {}
}

// getters
const getters = {
  allProducts: state => state.all,
  oneProduct: state => state.product,
  getField
}

// actions
const actions = {
  getAllProducts ({
    commit
  }) {
    return HTTP.get(PRODUCTS_V1, {withCredentials: true, crossDomain: true})
      .then((response) => {
        commit('setProducts', response.data.content)
      })
      .catch((error) => {
        console.log(error)
      })
  },
  getOneProduct ({
    commit
  }, id) {
    HTTP.get(PRODUCTS_V1 + '/' + id)
      .then((response) => {
        commit('setProduct', response.data)
      })
      .catch((error) => {
        console.log(error)
      })
  },
  putProduct ({
    commit
  }, product) {
    return HTTP.put(PRODUCT_V1, product)
      .then(function (response) {})
      .catch(function (error) {
        console.log(error)
      })
  },
  postProduct ({
    commit
  }, product) {
    return HTTP.post(PRODUCT_V1, product)
      .then(function (response) {})
      .catch(function (error) {
        console.log(error)
      })
  },
  deleteProduct ({
    commit
  }, id) {
    return HTTP.delete(PRODUCT_V1, {
      params: {
        'id': id
      }
    })
      .then(function (response) {})
      .catch(function (error) {
        console.log(error)
      })
  }
}

// mutations
const mutations = {
  setProducts (state, products) {
    state.all = products
  },
  setProduct (state, product) {
    state.product = product
  },
  updateProduct (state, field) {
    updateField(state.product, field)
  }
}

export default {
  state,
  getters,
  actions,
  mutations,
  namespaced
}
