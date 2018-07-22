<template>
<div class="modal-dialog">
  <div class="modal-content">
    <form v-on:submit.prevent="updateProduct">
      <div class="modal-header">
        <h4 class="modal-title">Edit Product</h4>
        <button @click="$router.go(-1)" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Name</label>
          <input type="text" v-model="product.name" class="form-control" required>
        </div>
        <div class="form-group">
          <label>Description</label>
          <textarea type="description" v-model="product.description" class="form-control"></textarea>
        </div>
        <div class="form-group">
          <label>Price</label>
          <input type="netPrice" v-model="product.netPrice" class="form-control" required>
        </div>
        <div class="form-group">
          <label>Tax</label>
          <input type="tax" v-model="product.tax" class="form-control" required>
        </div>
        <div class="form-group">
          <label>Market Price</label>
          <input type="marketPrice" v-model="product.marketPrice" class="form-control" required>
        </div>
      </div>
      <div class="modal-footer">
        <input @click="$router.go(-1)" type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
        <input type="submit" class="btn btn-info" value="Save">
        </input>
      </div>
    </form>
  </div>
</div>
</template>

<script>
import {
  createHelpers
} from 'vuex-map-fields';

const {
  mapFields
} = createHelpers({
  getterType: 'ProductsStore/getField',
  mutationType: 'ProductsStore/updateProduct',
})

export default {
  computed: ({
    ...mapFields(['product'])
  }),

  created: function() {
    this.$store.dispatch('ProductsStore/getOneProduct', this.$route.params.id)
  },

  methods: {
    updateProduct: function() {
      let obj = this
      obj.$store.dispatch('ProductsStore/putProduct', this.product)
        .then(function(response) {
          obj.$store.dispatch('ProductsStore/getAllProducts')
        })
        .then(() => {
          obj.$router.go(-1)
          $('#editProductModal').modal('hide')
        })
        .catch(function(error) {
          console.log(error);
        })
    }
  }
}
</script>
