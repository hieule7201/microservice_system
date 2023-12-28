<script setup>
  defineProps({
    items: {
      type: Array,
      required: true
    }
  })
</script>

<template>
  <div class="container">
    <h2 class="text-center my-4">Borrow Table</h2>
    <div class="row mb-4">
      <div class="col-lg">
        <input type="search" placeholder="Enter params" name="key" class="form-control">
      </div>
      <div class="col-lg">
        <input type="date" name="date" class="form-control" >
      </div>
      <div class="col-lg d-flex justify-content-end">
        <button class="btn btn-success">Create</button>
      </div>
    </div>
    <table class="table table-dark">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">Handle</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in items" :key="item.id">
        <th scope="row">{{item.id}}</th>
        <td>{{item.name}}</td>
      </tr>

      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>