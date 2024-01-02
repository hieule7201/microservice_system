<template>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container ">
            <router-link class="navbar-brand text-uppercase font-weight-bold" exact :to="{ path: '/' }">Logo</router-link>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="navbar-nav mr-auto">
                  <router-link v-for="link in links" :key="link.name" :to="link.path" class="nav-item nav-link text-uppercase">{{link.name}}</router-link>
                </div>

              <router-link :to="{path: '/login'}" class="btn btn-outline-success my-2 my-sm-0" >Login</router-link>

            </div>
        </div>
    </nav>
</template>

<script setup>

import {links} from "@/static.js";

</script>

<style scoped>
.link-active{
  font-weight: 600;
}
.search-home:focus .box-data{
  display: block;
}
</style>