<script setup>

</script>

<template>
  <div class="container my-5 justify-content-center d-flex">
    <form class="border rounded p-5">
      <h2 class="text-center text-uppercase">login</h2>
      <div class="form-group">
        <label for="username" >Username</label>
        <input type="text" id="username" class="form-control" placeholder="Enter your username" />
      </div>
      <div class="form-group">
        <label for="password" >Password</label>
        <input type="text" id="password" class="form-control" placeholder="Enter your password" />
      </div>
      <button class="btn btn-primary text-uppercase">Login</button>
      <p>Don't you have account? <router-link :to="{path: '/signup'}" class="text-decoration-none">Sign Up</router-link></p>
    </form>

  </div>
</template>

<style scoped>

</style>