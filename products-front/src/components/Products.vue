<template>
<div>
  <div class="container">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-6">
            <h2><b>Products</b></h2>
          </div>
          <div class="col-sm-6">
            <router-link :to="{name: 'Add_Product'}">
              <a href="#addProductModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">
              &#xE147;
            </i> <span>Add New Product</span></a></router-link>
            <router-link :to="{name: 'Delete_All_Product'}">
              <a href="#deleteAllProductModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
            </router-link>
          </div>
        </div>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>
              <span class="custom-checkbox">
								<input @click="selectAll" type="checkbox" id="selectAll">
								<label for="selectAll"></label>
							</span>
            </th>
            <th>ID</th>
            <th>NAME</th>
            <th>DESCRIPTION</th>
            <th>CATEGORY</th>
            <th>NET PRICE</th>
            <th>TAX</th>
            <th>MARKET PRICE</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products">
            <td>
              <span class="custom-checkbox">
								<input class="row-checkbox" type="checkbox" v-bind:id="'checkbox' + product.id" name="options[]" v-bind:value="product.id">
								<label v-bind:for="'checkbox' + product.id"></label>
							</span>
            </td>
            <td>{{ product.id }}</td>
            <td>{{ product.name }}</td>
            <td>{{ product.description }}</td>
            <td>{{ product.category.name }}</td>
            <td>{{ product.netPrice }}</td>
            <td>{{ product.tax }}</td>
            <td>{{ product.marketPrice }}</td>
            <td>
              <router-link :to="{name: 'Edit_Product', params: {id: product.id }}">
                <a href="#editProductModal" class="edit" data-toggle="modal">
                  <i class="material-icons" data-toggle="tooltip" title="Edit">
                  &#xE254;
                  </i>
                </a>
              </router-link>
              <router-link :to="{name: 'Delete_Product', params: {id: product.id }}">
                <a href="#deleteProductModal" class="delete" data-toggle="modal">
                  <i class="material-icons" data-toggle="tooltip" title="Delete">
                      &#xE872;
                  </i>
                </a>
              </router-link>
            </td>
          </tr>

        </tbody>
      </table>
      <div class="clearfix">
        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
        <ul class="pagination">
          <li class="page-item disabled"><a href="#">Previous</a></li>
          <li class="page-item"><a href="#" class="page-link">1</a></li>
          <li class="page-item"><a href="#" class="page-link">2</a></li>
          <li class="page-item active"><a href="#" class="page-link">3</a></li>
          <li class="page-item"><a href="#" class="page-link">4</a></li>
          <li class="page-item"><a href="#" class="page-link">5</a></li>
          <li class="page-item"><a href="#" class="page-link">Next</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div id="editProductModal" class="modal fade">
    <router-view/>
  </div>
  <div id="addProductModal" class="modal fade">
    <router-view/>
  </div>
  <div id="deleteProductModal" class="modal fade">
    <router-view/>
  </div>
  <div id="deleteAllProductModal" class="modal fade">
    <router-view/>
  </div>
</div>
</template>


<script type="text/javascript">
import {
  mapGetters
} from 'vuex'

export default {
  name: 'Products',

  data() {
    return {
      msg: 'Products list',
    }
  },

  computed: mapGetters({
    products: 'ProductsStore/allProducts'
  }),

  created: function() {
    this.$store.dispatch('ProductsStore/getAllProducts')
  },

  methods: {
    selectAll: function() {
      $("#selectAll").click(function() {
        $('input:checkbox').not(this).prop('checked', this.checked);
      });
    }
  }
}
</script>
