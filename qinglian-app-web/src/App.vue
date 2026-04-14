<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <RouterView />
</template>

<style>
/* 自定义全局样式如动画可在这里保留 */
.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
.glass-card {
  background: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
}
.hero-gradient {
  background: linear-gradient(180deg, rgba(25, 28, 29, 0.4) 0%, rgba(25, 28, 29, 0) 40%, rgba(25, 28, 29, 0.8) 100%);
}
.text-glow {
  text-shadow: 0 0 20px rgba(0, 229, 255, 0.3);
}
</style>
