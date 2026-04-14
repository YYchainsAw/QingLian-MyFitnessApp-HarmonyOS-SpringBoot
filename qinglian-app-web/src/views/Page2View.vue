<template>
  <div class="_2-view">
    
<!-- Top Navigation Shell -->
<nav class="fixed top-0 w-full z-50 bg-surface/70 dark:bg-slate-900/70 backdrop-blur-md border-b border-slate-100 dark:border-slate-800">
<div class="flex justify-between items-center px-8 h-20 w-full max-w-screen-2xl mx-auto">
<div class="text-2xl font-bold tracking-tighter text-slate-900 dark:text-white">轻练</div>
<div class="hidden md:flex items-center gap-8 font-manrope tracking-tight font-medium">
<a class="text-slate-600 dark:text-slate-400 font-medium hover:text-slate-900 dark:hover:text-white transition-colors" href="#">动作训练</a>
<a class="text-cyan-600 dark:text-cyan-400 font-bold border-b-2 border-cyan-500 pb-1" href="#">训练计划</a>
<a class="text-slate-600 dark:text-slate-400 font-medium hover:text-slate-900 dark:hover:text-white transition-colors" href="#">营养饮食</a>
<a class="text-slate-600 dark:text-slate-400 font-medium hover:text-slate-900 dark:hover:text-white transition-colors" href="#">社区互动</a>
</div>
<div class="flex items-center gap-4">
<button class="material-symbols-outlined p-2 hover:bg-slate-100/50 rounded-full transition-all">notifications</button>
<div class="w-10 h-10 rounded-full bg-slate-200 dark:bg-slate-800 flex items-center justify-center overflow-hidden cursor-pointer">
<svg class="w-8 h-8 text-slate-500" fill="currentColor" viewbox="0 0 24 24">
<circle cx="12" cy="8" r="4"></circle>
<path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z"></path>
</svg>
</div>
<button class="hidden md:block bg-primary text-on-primary px-6 py-2.5 rounded-xl font-bold text-sm tracking-tight shadow-sm hover:opacity-90 transition-all">开通会员</button>
</div>
</div>
</nav>
<div class="flex min-h-screen pt-20">
<!-- Sidebar Navigation -->
<aside class="hidden lg:flex flex-col h-screen w-72 border-r border-slate-200/50 dark:border-slate-800/50 bg-slate-50 dark:bg-slate-950 fixed left-0 py-6 overflow-y-auto">
<div class="px-6 py-8">
<h2 class="text-xl font-black text-slate-900 dark:text-white">训练中心</h2>
<p class="text-xs text-secondary mt-1 uppercase tracking-widest font-bold">动作库</p>
</div>
<nav class="flex-1 space-y-1">
<div class="px-8 py-4 text-xs font-bold text-secondary uppercase tracking-widest">分类浏览</div>
<a class="bg-white dark:bg-slate-900 text-cyan-600 dark:text-cyan-400 shadow-sm rounded-xl mx-4 my-1 flex items-center px-4 py-3 gap-3 transition-all translate-x-1" href="#">
<span class="material-symbols-outlined">fitness_center</span>
<span class="font-manrope text-sm font-medium">力量训练</span>
</a>
<a class="text-slate-500 dark:text-slate-400 hover:text-slate-900 dark:hover:text-white mx-4 my-1 hover:bg-white/80 dark:hover:bg-slate-900/80 rounded-xl flex items-center px-4 py-3 gap-3 transition-all" href="#">
<span class="material-symbols-outlined">self_improvement</span>
<span class="font-manrope text-sm font-medium">瑜伽伸展</span>
</a>
<a class="text-slate-500 dark:text-slate-400 hover:text-slate-900 dark:hover:text-white mx-4 my-1 hover:bg-white/80 dark:hover:bg-slate-900/80 rounded-xl flex items-center px-4 py-3 gap-3 transition-all" href="#">
<span class="material-symbols-outlined">directions_run</span>
<span class="font-manrope text-sm font-medium">有氧燃脂</span>
</a>
<a class="text-slate-500 dark:text-slate-400 hover:text-slate-900 dark:hover:text-white mx-4 my-1 hover:bg-white/80 dark:hover:bg-slate-900/80 rounded-xl flex items-center px-4 py-3 gap-3 transition-all" href="#">
<span class="material-symbols-outlined">bolt</span>
<span class="font-manrope text-sm font-medium">核心强化</span>
</a>
<a class="text-slate-500 dark:text-slate-400 hover:text-slate-900 dark:hover:text-white mx-4 my-1 hover:bg-white/80 dark:hover:bg-slate-900/80 rounded-xl flex items-center px-4 py-3 gap-3 transition-all" href="#">
<span class="material-symbols-outlined">pool</span>
<span class="font-manrope text-sm font-medium">恢复拉伸</span>
</a>
</nav>
<div class="px-6 py-8 mt-auto">
<div class="bg-primary/5 rounded-2xl p-4 border border-primary/10">
<p class="text-xs font-bold text-primary uppercase tracking-widest mb-2">新品速递</p>
<p class="text-sm font-semibold text-on-surface mb-3">能量流动瑜伽 2.0 课程现已上线。</p>
<button class="w-full bg-primary text-on-primary py-2 rounded-lg text-xs font-bold">立即探索</button>
</div>
</div>
</aside>
<!-- Main Content Area -->
<main class="flex-1 lg:ml-72 bg-surface p-8 pb-32">
<!-- Search & Filter Header -->
<header class="max-w-6xl mx-auto mb-12">
<div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
<div class="space-y-2">
<h1 class="text-4xl md:text-5xl font-headline font-bold text-on-surface tracking-tight">重塑你的运动姿态</h1>
<p class="text-lg text-secondary font-body max-w-xl">由专业教练精心打造的精控动作教程，追求极致精准的每一次律动。</p>
</div>
<div class="relative w-full md:w-96 group">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline group-focus-within:text-primary transition-colors">search</span>
<input class="w-full pl-12 pr-4 py-4 bg-surface-container rounded-2xl border-none focus:ring-1 focus:ring-primary/20 focus:bg-surface-container-lowest transition-all placeholder:text-outline/60 text-sm font-medium" placeholder="搜索训练项目..." type="text"/>
</div>
</div>
</header>
<!-- Featured Training Grid -->
<section class="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-12 gap-6">
<!-- Main Featured Card (2/3 width) -->
<div class="md:col-span-8 group relative overflow-hidden rounded-3xl bg-slate-200 h-[450px]">
<img class="absolute inset-0 w-full h-full object-cover transition-transform duration-700 group-hover:scale-110" data-alt="Cinematic shot of a professional athlete performing a heavy deadlift in a minimalist high-end gym" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBdB2kH-NvnU-HdKNu959esXMjxQBsBxZXGnzvErdXGKr40WIhgILt_UbpBRIcv77ffGOIQ3zM8XW9yV1c44r2SHYOYF6cec1PY8gK362XUgPI0TMXnddwarUVaiaD7DDHvkeyG9pt3DADaj0Q32RjnHfQXEPe7q6wR8uQcCVWEMmGw-n2FxidvK96Wv3TKh8NojAjkiyATqlP_-sflKqU_QspVfUPdBH_PNv4zpcQkRC4u2JNGa8clVJJg8nPNCrtJbKGhwcxZHzM"/>
<div class="absolute inset-0 bg-gradient-to-t from-slate-900/80 via-slate-900/20 to-transparent"></div>
<div class="absolute bottom-0 left-0 p-8 w-full">
<div class="flex gap-3 mb-4">
<span class="glass-card px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-wider text-on-surface">进阶训练</span>
<span class="glass-card px-3 py-1 rounded-full text-[10px] font-bold uppercase tracking-wider text-on-surface">45 分钟</span>
</div>
<h3 class="text-3xl font-headline font-bold text-white mb-2">硬拉全解析大师课</h3>
<p class="text-slate-300 max-w-md text-sm mb-6 font-body">以奥林匹克级别的精度，掌握后侧链的基本力学原理。</p>
<button class="inline-flex items-center gap-2 bg-white text-slate-900 px-6 py-3 rounded-xl font-bold text-sm hover:bg-cyan-50 transition-colors">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">play_arrow</span>
                            观看教程
                        </button>
</div>
</div>
<!-- Secondary Stack (1/3 width) -->
<div class="md:col-span-4 flex flex-col gap-6">
<div class="flex-1 group relative overflow-hidden rounded-3xl bg-slate-200">
<img class="absolute inset-0 w-full h-full object-cover transition-transform duration-500 group-hover:scale-105" data-alt="Yoga morning mobility session" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAPjOGyiYKpAKS3AofPkt0rDrNxugMoPxhN4kjKKiNOCm4b8WF9MECmYUOlkXXPibvrDsrDQyFwtmcLcdFfDHbiHqaycGX301701F13UQ8ztpKoK2A6nz2O2-fgS2s1IzO8eT0Gasi1l2fZaF1IpTOiM8dBqHwDSlvifOKRetKrzx-AbaCEu0PTUwhO3ihbD17_pNTN2CmDjJubjsBlasyIcWk_wo0NWEft4B5bsALJL0WJhtxONwk-uhDJwCQspMZpbjtNqzAaBeg"/>
<div class="absolute inset-0 bg-slate-900/40"></div>
<div class="absolute inset-0 p-6 flex flex-col justify-end">
<span class="text-[10px] font-bold uppercase tracking-widest text-white/80 mb-1">瑜伽与流动</span>
<h4 class="text-xl font-headline font-bold text-white">晨间活动性</h4>
</div>
</div>
<div class="flex-1 group relative overflow-hidden rounded-3xl bg-slate-200">
<img class="absolute inset-0 w-full h-full object-cover transition-transform duration-500 group-hover:scale-105" data-alt="Gym equipment and texture" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAtNNJ6FlFPS16uUtWM6gvJZgOfDEcy4xWs9FeX6pw0-dlnSp5S4wo-xZzZ7cEMHqJhAzgglHlOT7rneivVWrSZmkoaXVJfB8Zn7xnG-_c1M1g4Na1B01kFoGGNJe8Wz4siS3QI9Iye4q-kyktfoBYKOQR3iAZUIXJuGP9Me3m9vuTDQ0kODn5_MyfBOcV2-861ObvoJrh_qoXXB6ADUwTnDh3UMgubEJCPtQ9dR0uE4pLod3AYrxndP_OWctXBBtl6Pe_i4jQG3go"/>
<div class="absolute inset-0 bg-slate-900/40"></div>
<div class="absolute inset-0 p-6 flex flex-col justify-end">
<span class="text-[10px] font-bold uppercase tracking-widest text-white/80 mb-1">器械指南</span>
<h4 class="text-xl font-headline font-bold text-white">最佳握力技巧</h4>
</div>
</div>
</div>
</section>
<!-- Categorized Grid -->
<section class="max-w-6xl mx-auto mt-20">
<div class="flex items-center justify-between mb-8">
<h2 class="text-2xl font-headline font-bold text-on-surface">热门动作技巧</h2>
<a class="text-sm font-bold text-primary hover:underline decoration-2 underline-offset-4" href="#">查看全部</a>
</div>
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
<!-- Exercise Card 1 -->
<div class="group cursor-pointer">
<div class="relative aspect-video rounded-2xl overflow-hidden mb-4 shadow-sm transition-all duration-300 group-hover:shadow-xl group-hover:-translate-y-1">
<img class="w-full h-full object-cover" data-alt="Kettlebell swings" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDSIgq6A3DXRlm4zNve-rWueAGuXb6UNVFNbG_caYLM1wN7xy7ddmvDPB6oie-Q1qIScVFZbIgWSCd3YWIpAWx15Q8tisEQ33JDOhMp4Or_okjBv6oJevNHkU6hUdFf3y7GAgohtKeKBMwZbDYprOB54ma3RTqUY9QdW6sXhvrmcJGRw5l0LW1VzZfk0Ls1JtiW_xkS_sxhDjFtCKNUzdyK0j8OXNs2cs_3ZZMWV4SRR30Xq3HYmU2xk-S3XaDfA7X9GSQOH0ut-_o"/>
<div class="absolute bottom-3 left-3 flex gap-2">
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">高强度</span>
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">中级</span>
</div>
</div>
<div class="px-1">
<h3 class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors">壶铃爆发力摆动</h3>
<div class="flex items-center gap-4 mt-2 text-secondary text-xs">
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">schedule</span> 12 分钟</span>
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">local_fire_department</span> 180 千卡</span>
</div>
</div>
</div>
<!-- Exercise Card 2 -->
<div class="group cursor-pointer">
<div class="relative aspect-video rounded-2xl overflow-hidden mb-4 shadow-sm transition-all duration-300 group-hover:shadow-xl group-hover:-translate-y-1">
<img class="w-full h-full object-cover" data-alt="Box jumps" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAWhQOO2Un6Fo63btUudLCXXeDbHzJou1DsUWtpmSNhRdPETVBHhXIYR80NtYeNqPoDY5JmvWBSY5sx22v5mozG_mTnbRohvukAsYjp9nwjcmAgIU8Q8tZh1K6mswYgERlbAzyZxFUtgvn-B9EoX9PqtXz3hpPm0M9MitQBv3RBquNHKJ4ZpNdPAOAWE9EhsyZ-XGT4tZwFqDjwaxbSxfRIEfm8DlPxYu2S_mED19lrgQAznhJezTzT43P4oUp1WQggV5vThSteYQI"/>
<div class="absolute bottom-3 left-3 flex gap-2">
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">爆发力</span>
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">专家</span>
</div>
</div>
<div class="px-1">
<h3 class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors">增强式跳箱训练</h3>
<div class="flex items-center gap-4 mt-2 text-secondary text-xs">
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">schedule</span> 15 分钟</span>
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">local_fire_department</span> 240 千卡</span>
</div>
</div>
</div>
<!-- Exercise Card 3 -->
<div class="group cursor-pointer">
<div class="relative aspect-video rounded-2xl overflow-hidden mb-4 shadow-sm transition-all duration-300 group-hover:shadow-xl group-hover:-translate-y-1">
<img class="w-full h-full object-cover" data-alt="Planks" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDeoXmdIs6Di2qng8qmf7RavE12cLAjAjPmg3GDf_Owj1Cx4en4NuaxfnzNaEBZnEhwDdZxTtxbLPaKjC9N9gmrosJg4oyveEucngAiJ2xCnJvtvqV4Eje3FfXvdl7KRl7anIE1DZvlV-nr6kPPVwSA84b_V3g4841HH-c-5goAytxgkqUzbU06lEbvpBpKHoCmlJarrpWDIMFAI2hTwbRL1ZdnKaQPS9EdsQxZp4qveT5y7ii--bQ6VHgCd1Ox5a9BwvDTZsdxD8k"/>
<div class="absolute bottom-3 left-3 flex gap-2">
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">核心</span>
<span class="glass-card text-[9px] font-bold px-2 py-1 rounded-lg">初级</span>
</div>
</div>
<div class="px-1">
<h3 class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors">标准平板支撑</h3>
<div class="flex items-center gap-4 mt-2 text-secondary text-xs">
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">schedule</span> 8 分钟</span>
<span class="flex items-center gap-1"><span class="material-symbols-outlined text-[14px]">local_fire_department</span> 60 千卡</span>
</div>
</div>
</div>
</div>
</section>
</main>
</div>
<!-- Bottom Navigation Shell (Mobile Only) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 pt-3 pb-8 bg-white/80 dark:bg-slate-900/80 backdrop-blur-xl z-50 rounded-t-3xl border-t border-white/20 shadow-[0_-8px_30px_rgb(0,0,0,0.04)]">
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2 hover:scale-110 transition-transform" href="#">
<span class="material-symbols-outlined">home</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">主页</span>
</a>
<a class="flex flex-col items-center justify-center bg-cyan-50 dark:bg-cyan-900/30 text-cyan-600 dark:text-cyan-300 rounded-2xl px-6 py-2 scale-90" href="#">
<span class="material-symbols-outlined">search</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">发现</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2 hover:scale-110 transition-transform" href="#">
<span class="material-symbols-outlined">leaderboard</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">动态</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-6 py-2 hover:scale-110 transition-transform" href="#">
<div class="w-6 h-6 rounded-full bg-slate-200 dark:bg-slate-800 flex items-center justify-center overflow-hidden mb-0.5">
<svg class="w-5 h-5 text-slate-500" fill="currentColor" viewbox="0 0 24 24">
<circle cx="12" cy="8" r="4"></circle>
<path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z"></path>
</svg>
</div>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold">我的</span>
</a>
</nav>
<!-- Footer -->
<footer class="bg-slate-50 dark:bg-slate-950 border-t border-slate-200 dark:border-slate-800 w-full py-16 px-8 mt-20">
<div class="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-4 gap-12">
<div class="md:col-span-2">
<span class="text-lg font-bold text-slate-900 dark:text-white mb-4 block">轻练 Light Fit</span>
<p class="font-inter text-sm text-slate-500 max-w-sm mb-6">专为追求精准、平衡与身体美学的运动者设计，通过优雅的数字化体验定义健身体验。</p>
<div class="flex gap-4">
<button class="w-10 h-10 rounded-full border border-outline-variant flex items-center justify-center hover:bg-slate-200 transition-colors">
<span class="material-symbols-outlined text-sm">share</span>
</button>
<button class="w-10 h-10 rounded-full border border-outline-variant flex items-center justify-center hover:bg-slate-200 transition-colors">
<span class="material-symbols-outlined text-sm">mail</span>
</button>
</div>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">探索</h4>
<ul class="space-y-4 font-inter text-sm">
<li><a class="text-slate-500 hover:text-slate-900 hover:underline decoration-cyan-500 underline-offset-4" href="#">隐私政策</a></li>
<li><a class="text-slate-500 hover:text-slate-900 hover:underline decoration-cyan-500 underline-offset-4" href="#">服务条款</a></li>
<li><a class="text-slate-500 hover:text-slate-900 hover:underline decoration-cyan-500 underline-offset-4" href="#">帮助中心</a></li>
<li><a class="text-slate-500 hover:text-slate-900 hover:underline decoration-cyan-500 underline-offset-4" href="#">品牌素材</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">联系我们</h4>
<div class="bg-surface-container-low p-4 rounded-xl">
<p class="text-xs text-secondary font-medium mb-3">订阅我们的精英简报，获取专业训练洞察。</p>
<div class="relative">
<input class="w-full bg-white border-none text-xs rounded-lg p-2.5 focus:ring-1 focus:ring-primary" placeholder="电子邮箱" type="email"/>
<button class="absolute right-1 top-1 bg-primary text-white p-1.5 rounded-md">
<span class="material-symbols-outlined text-xs">arrow_forward</span>
</button>
</div>
</div>
</div>
</div>
<div class="max-w-7xl mx-auto mt-16 pt-8 border-t border-slate-200 dark:border-slate-800">
<p class="font-inter text-sm text-slate-500 text-center">© 2024 轻练 Light Fit. 极致精准，定义不凡.</p>
</div>
</footer>

  </div>
</template>

<script setup>
</script>

<style scoped>
</style>
