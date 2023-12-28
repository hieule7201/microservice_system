
<template>
  <div class="container">
    <div class="row my-4 align-items-center">
      <div class="col-lg">
        <input type="search" class="form-control" placeholder="Enter params">
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
<script setup>
defineProps({
  items: {
    type: Array,
    required: true
  }
})
</script>


<style  scoped></style>