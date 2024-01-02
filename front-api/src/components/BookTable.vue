
<template>

    <table class="table table-dark">
      <thead>
      <tr>
        <th scope="col">ID</th>
        <th scope="col">NAME</th>
        <th scope="col">GENRES</th>
        <th scope="col">IMAGE</th>
        <th scope="col">ACTION</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="book in books" :key="book.idBook">
        <th scope="row">{{book.idBook}}</th>
        <td>{{book.nameBook}}</td>
        <td>{{book.genresBook}}</td>
        <td><img :src="IMG_URL + book.img" alt="" class="img-w-h"></td>
        <td class="d-flex">
          <button class="btn btn-warning m-1" data-toggle="modal" data-target="#editBook">Edit</button>
          <BookModal id="editBook" title="Edit Book" />
          <button class="btn btn-danger m-1">Delete</button>
        </td>

      </tr>

      </tbody>
    </table>
</template>
<script setup>
import {IMG_URL} from "@/api/instance.js";
import BookModal from "@/components/BookModal.vue";

defineProps({
  books: {
    type: Array,
    required: true
  }
})
</script>


<style  scoped>
.img-w-h{
  max-height: 5rem;
}
</style>