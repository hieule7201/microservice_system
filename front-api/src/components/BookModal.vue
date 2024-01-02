<script setup>

const { modalContent, showModal, modalTitle } = defineProps(['modalContent', 'showModal', 'modalTitle']);
</script>

<template>
  <div v-if="showModal" class="b-modal">
    <div class="modal-content container">
      <span class="close" @click="closeModal">&times;</span>
      <h2>{{ modalTitle }}</h2>

    </div>
  </div>
</template>

<style scoped>
.b-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}
.close:hover,
.close:focus {
  color: black;
}
</style>