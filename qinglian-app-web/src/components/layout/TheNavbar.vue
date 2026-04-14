<template>
<nav class="fixed top-0 w-full z-50 bg-white/70 dark:bg-slate-900/70 backdrop-blur-xl shadow-sm dark:shadow-none transition-all duration-500">
<div class="flex justify-between items-center w-full px-6 py-4 max-w-screen-2xl mx-auto">
<div class="text-2xl font-bold tracking-tight text-slate-900 dark:text-white font-headline">轻练</div>
<!-- Navigation Links -->
<div class="hidden md:flex items-center gap-10">
<router-link to="/home" class="text-slate-500 dark:text-slate-400 font-medium nav-link text-lg font-headline tracking-tight" active-class="nav-active pb-1 text-cyan-500">首页</router-link>
<router-link to="/training_hub" class="text-slate-500 dark:text-slate-400 font-medium nav-link text-lg font-headline tracking-tight" active-class="nav-active pb-1 text-cyan-500">训练大厅</router-link>
<router-link to="/community" class="text-slate-500 dark:text-slate-400 font-medium nav-link text-lg font-headline tracking-tight" active-class="nav-active pb-1 text-cyan-500">同城社区</router-link>
<router-link to="/leaderboard" class="text-slate-500 dark:text-slate-400 font-medium nav-link text-lg font-headline tracking-tight" active-class="nav-active pb-1 text-cyan-500">排行榜</router-link>
</div>
<!-- Actions -->
<div class="flex items-center gap-6">
<button class="hidden lg:block text-slate-600 font-medium hover:text-cyan-400 transition-all click-active">升级会员</button>
<div class="flex items-center gap-4">
<span class="material-symbols-outlined text-slate-500 cursor-pointer hover:text-cyan-400 transition-colors click-active">search</span>
<span class="material-symbols-outlined text-slate-500 cursor-pointer hover:text-cyan-400 transition-colors click-active">notifications</span>
<img alt="User profile" class="w-8 h-8 rounded-full cursor-pointer hover:ring-2 hover:ring-primary transition-all click-active" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDoO2Rfv5E07DpACNS3uj5BFHpfqGSwOgqfpB56VGDt3nT4HF5-VgJOG5hxVhrN2g5XHD4p_TZHCbhnp5i29ig3POJ3zr_gWHIX-ypOLyW2s3c8K4Xfl3ZQVG1rAJZS9Pf7hz5ETiq3jUTc6hnoW4Th_7T1T7FWjsdiHbt8C9SFLErwcU_VO3MxTc3CRECmizJkuRKIBwwI-Ui5R-HZdCZBj869KfYg4-ailKYnjTni9bC7k56rpDG0kCnmqV5C2AC29DFKlzz_NZE"/>
</div>
</div>
</div>
</nav>
</template>

<script setup>
</script>
