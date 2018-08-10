import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/Products'
import EditProduct from '@/components/EditProduct'
import DeleteProduct from '@/components/DeleteProduct'
import AddProduct from '@/components/AddProduct'
import DeleteAllProduct from '@/components/DeleteAllProduct'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/products',
      name: 'Products',
      component: Products,
      children: [{
        path: ':id',
        component: EditProduct,
        name: 'EditProduct'
      },
      {
        path: ':id',
        component: DeleteProduct,
        name: 'DeleteProduct'
      },
      {
        path: 'addProduct',
        component: AddProduct,
        name: 'AddProduct'
      },
      {
        path: 'deleteALlProducts',
        component: DeleteAllProduct,
        name: 'DeleteAllProduct'
      }
      ]
    }
  ]
})
