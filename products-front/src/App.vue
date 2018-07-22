<template>
<div id="app">
  <router-view/>
</div>
</template>

<script>
$(document).ready(function () {
  // Activate tooltip
  $('[data-toggle="tooltip"]').tooltip()

  // Select/Deselect checkboxes
  var checkbox = $('table tbody input[type="checkbox"]')
  $('#selectAll').click(function () {
    if (this.checked) {
      checkbox.each(function () {
        this.checked = true
      })
    } else {
      checkbox.each(function () {
        this.checked = false
      })
    }
  })
  checkbox.click(function () {
    if (!this.checked) {
      $('#selectAll').prop('checked', false)
    }
  })
})

export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}

.main-logo {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 60px;
}
</style>
