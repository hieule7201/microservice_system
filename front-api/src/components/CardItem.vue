<script setup>
import {IMG_URL} from "@/api/instance.js";


const {book} = defineProps({
  book: {type: Object, required: true}
})

</script>

<template>
  <div  class="card h-100">
    <img :src="IMG_URL+book.img" alt="" class="card-img-top" style="height: 20rem">
    <div class="card-body row">
      <div class="col-lg-8">
        <p class="card-title font-weight-bold">{{book.nameBook}}</p>
        <p class="card-text">{{book.genresBook}}</p>
      </div>
      <div class="col-lg-4 d-flex align-items-end">
        <button class="btn btn-primary">Borrow</button>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>