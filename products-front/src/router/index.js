import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/Products'
import Edit_Product from '@/components/Edit_Product'
import Delete_Product from '@/components/Delete_Product'
import Add_Product from '@/components/Add_Product'
import Delete_All_Product from '@/components/Delete_All_Product'
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
        component: Edit_Product,
        name: 'Edit_Product'
      },
      {
        path: ':id',
        component: Delete_Product,
        name: 'Delete_Product'
      },
      {
        path: 'addProduct',
        component: Add_Product,
        name: 'Add_Product'
      },
      {
        path: 'deleteALlProducts',
        component: Delete_All_Product,
        name: 'Delete_All_Product'
      }
      ]
    }
  ]
})
