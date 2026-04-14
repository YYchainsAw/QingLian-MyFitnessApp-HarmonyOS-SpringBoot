<script setup>
// BottomNavBar 特有的逻辑，比如控制当前点中了哪个 tab
</script>

<template>
  <nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 pt-3 pb-8 bg-white/80 dark:bg-slate-900/80 backdrop-blur-xl border-t border-white/20 z-50">
    <a class="flex flex-col items-center justify-center bg-primary/10 text-primary rounded-2xl px-6 py-2" href="#">
      <span class="material-symbols-outlined" data-icon="home">home</span>
      <span class="font-headline text-[10px] uppercase tracking-widest font-bold mt-1">主页</span>
    </a>
    <a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2" href="#">
      <span class="material-symbols-outlined" data-icon="search">search</span>
      <span class="font-headline text-[10px] uppercase tracking-widest font-bold mt-1">探索</span>
    </a>
    <a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2" href="#">
      <span class="material-symbols-outlined" data-icon="leaderboard">leaderboard</span>
      <span class="font-headline text-[10px] uppercase tracking-widest font-bold mt-1">动态</span>
    </a>
    <a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2" href="#">
      <span class="material-symbols-outlined" data-icon="person">person</span>
      <span class="font-headline text-[10px] uppercase tracking-widest font-bold mt-1">我的</span>
    </a>
  </nav>
</template>

<style scoped>
</style>
