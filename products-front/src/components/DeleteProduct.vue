<template>
  <div class="modal-dialog">
    <div class="modal-content">
      <form v-on:submit.prevent="deleteProduct">
        <div class="modal-header">
          <h4 class="modal-title">Delete Employee</h4>
          <button @click="$router.go(-1)" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Are you sure you want to delete these Records?</p>
          <p class="text-warning"><small>This action cannot be undone.</small></p>
        </div>
        <div class="modal-footer">
          <input @click="$router.go(-1)" type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-danger" value="Delete">
        </div>
      </form>
    </div>
  </div>
</template>

<script type="text/javascript">
/* eslint-disable no-undef */

export default {
  data () {
    return {
      msg: 'Delete Products'
    }
  },

  methods: {
    deleteProduct: function () {
      let obj = this
      obj.$store.dispatch('ProductsStore/deleteProduct', this.$route.params.id)
        .then(function (response) {
          obj.$store.dispatch('ProductsStore/getAllProducts')
        })
        .then(() => {
          $('#deleteProductModal').modal('hide')
          $('input[type=checkbox]:checked').prop('checked', false)
          obj.$router.go(-1)
        })
    }
  }
}
</script>
