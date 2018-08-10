<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <form v-on:submit.prevent="addProduct">
        <div class="modal-header">
          <h4 class="modal-title">Add Product</h4>
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
            <label>Category</label>
            <input type="category" v-model="product.category.id" class="form-control" required>
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
          <input type="submit" class="btn btn-success" value="Add">
        </div>
      </form>
    </div>
  </div>
</template>

<script>

export default {

  data () {
    return {
      product: {name: '', description: '', category: {id: ''}, netPrice: '', tax: '', marketPrice: ''}
    }
  },

  methods: {
    addProduct: function () {
      let obj = this
      obj.$store.dispatch('ProductsStore/postProduct', this.product)
        .then(function (response) {
          obj.$store.dispatch('ProductsStore/getAllProducts')
        })
        .then(() => {
          obj.$router.go(-1)
          $('#addProductModal').modal('hide')
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>
