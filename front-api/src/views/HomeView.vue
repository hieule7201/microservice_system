<template>
    <div class="container my-4">
      <div class="form-inline my-2 my-lg-0 d-flex justify-content-end">
        <input v-model="keyword" class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search" @input.prevent="searchBook">
      </div>
      <div class="row mt-3">
        <div v-for="book in books" :key="book.id" class="col-lg-4 col-md-6 col-sm-6 mb-3">
            <CardItem :book="book"/>
        </div>
        <div v-if="!books.length">There are no book</div>
      </div>

    </div>
</template>

<script setup>
import store from "@/store/index.js";
import CardItem from "@/components/CardItem.vue";
import { onMounted, ref} from "vue";
import {computed} from 'vue'
import {useRoute} from "vue-router";

const keyword = ref("")
const route = useRoute()
const books = computed(() => store.state.allBooks)
function searchBook () {
  if (keyword.value)
  store.dispatch('getAllBookAction', keyword.value)

  else
    store.dispatch('getAllBookAction', [])

}
onMounted(()=>{
  keyword.value = route.params.keyword
  searchBook()
})


</script>
<style scoped></style>