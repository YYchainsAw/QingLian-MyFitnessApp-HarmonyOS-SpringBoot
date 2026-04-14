<script setup>
// 这里可以放 TopNavBar 特有的逻辑，比如控制菜单开关
</script>

<template>
  <nav class="fixed top-0 w-full z-50 transition-all duration-500 bg-surface/70 backdrop-blur-md">
    <div class="flex justify-between items-center px-8 h-20 w-full max-w-screen-2xl mx-auto">
      <div class="text-2xl font-extrabold tracking-tighter text-slate-900 font-headline">轻练</div>
      <!-- Navigation Links -->
      <div class="hidden md:flex items-center gap-10">
        <a class="text-primary font-bold border-b-2 border-primary pb-1 font-headline tracking-tight" href="#">训练计划</a>
        <a class="text-slate-600 font-medium hover:text-slate-900 transition-colors font-headline tracking-tight" href="#">进阶方案</a>
        <a class="text-slate-600 font-medium hover:text-slate-900 transition-colors font-headline tracking-tight" href="#">营养膳食</a>
        <a class="text-slate-600 font-medium hover:text-slate-900 transition-colors font-headline tracking-tight" href="#">会员社区</a>
      </div>
      <!-- Actions -->
      <div class="flex items-center gap-6">
        <button class="hidden lg:block text-slate-600 font-medium hover:text-slate-900 transition-all">升级会员</button>
        <div class="flex items-center gap-4">
          <span class="material-symbols-outlined text-on-surface-variant cursor-pointer hover:scale-110 transition-transform">notifications</span>
          <span class="material-symbols-outlined text-on-surface-variant cursor-pointer hover:scale-110 transition-transform">account_circle</span>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
/* 如果有该组件专属的样式，可以写在这里 */
</style>
