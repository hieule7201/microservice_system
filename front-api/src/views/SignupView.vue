<script setup>

</script>

<template>
  <div class="container my-5 justify-content-center d-flex">
    <form class="border rounded p-5">
      <h2 class="text-center text-uppercase">Sign Up</h2>
      <div class="form-group">
        <label for="name" >Your name</label>
        <input type="text" id="name" class="form-control" placeholder="Enter your name" />
      </div>
      <div class="form-group">
        <label for="username" >Username</label>
        <input type="text" id="username" class="form-control" placeholder="Enter your username" />
      </div>
      <div class="form-group">
        <label for="password" >Password</label>
        <input type="text" id="password" class="form-control" placeholder="Enter your password" />
      </div>
      <button class="btn btn-primary text-uppercase">Sign Up</button>
      <p>Have you account? <router-link :to="{path: '/login'}" class="text-decoration-none">Login</router-link></p>
    </form>

  </div>
</template>

<style scoped>

</style>