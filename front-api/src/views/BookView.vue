
<template>
    <div class="container my-3">
      <h2 class="text-center mb-4" >Book Table</h2>
      <div class="row my-4 align-items-center">
        <div class="col-lg">
          <input v-model="keyword" type="search" class="form-control" placeholder="Enter name or genres" @input="searchBook">
        </div>
        <div class="col-lg d-flex justify-content-end">
          <button class="btn btn-success" @click.prevent="openModal">Create</button>
          <BookModal
              v-if="showModal"
              :modalContent="modalContent"
              :modalTitle="modalTitle"
              :showModal="showModal"
              @close="closeModal"/>
        </div>
      </div>
      <DataTable :books="books"/>
    </div>
</template>
<script setup>


import DataTable from "@/components/BookTable.vue";
import {computed, onMounted, ref} from "vue";
import store from "@/store/index.js";
import BookModal from "@/components/BookModal.vue";
const books = computed(()=>store.state.allBooks)
const showModal = ref(false);
const modalContent = ref('');
const modalTitle = ref('Modal Title');

const keyword = ref('')
function searchBook () {
  if (keyword.value)
    store.dispatch('getAllBookAction', keyword.value)

  else
    store.dispatch('getAllBookAction', [])

}
const openModal = () => {
  showModal.value = true;
};
const closeModal = () => {
  showModal.value = false;
};

onMounted(()=>{
  searchBook()
})
</script>


<style lang="scss" scoped></style>