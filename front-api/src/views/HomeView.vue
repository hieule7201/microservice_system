<template>
    <div class="container my-4">
      <div class="row">
        <div v-for="b in book" :key="b.id" class="col-lg-4 col-md-6 col-sm-6 mb-3">
          <div  class="card h-100">
            <img :src="b.img" alt="" class="card-img-top" style="height: 20rem">
            <div class="card-body row">
              <div class="col-lg-8">
                <p class="card-title font-weight-bold">{{b.name}}</p>
                <p class="card-text">{{b.genres}}</p>
              </div>
              <div class="col-lg-4 d-flex align-items-end">
                <button class="btn btn-primary">Borrow</button>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
</template>

<script setup>
import {book} from "@/static.js";

</script>
<style scoped></style>