<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <div class="content">
    <RouterView />
  </div>
</template>

<style>
.content{
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  width: 100%;
}
</style>
