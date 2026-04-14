<template>
  <div class="training_hub_api-view bg-surface text-on-surface min-h-screen pt-20">
    <TheNavbar />
    
<!-- Top Navigation Shell -->

<div class="flex min-h-screen">
<!-- Sidebar Navigation -->
<aside class="hidden lg:flex flex-col w-80 h-screen sticky top-[72px] bg-slate-50 dark:bg-slate-950 border-r border-slate-100 dark:border-slate-800 p-6 space-y-4">
<div class="mb-6">
<h2 class="text-xl font-bold text-slate-900 font-headline">Community</h2>
<p class="text-sm text-slate-500 font-body">Active Now</p>
</div>
<nav class="flex-1 space-y-2">
<a class="flex items-center gap-3 px-4 py-3 text-cyan-600 dark:text-cyan-400 bg-white dark:bg-slate-900 font-bold rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">chat_bubble</span>
<span class="font-body text-md">Direct Messages</span>
</a>
<a class="flex items-center gap-3 px-4 py-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">group</span>
<span class="font-body text-md">Workout Buddies</span>
</a>
<a class="flex items-center gap-3 px-4 py-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">public</span>
<span class="font-body text-md">Global Feed</span>
</a>
<a class="flex items-center gap-3 px-4 py-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">bookmark</span>
<span class="font-body text-md">Saved posts</span>
</a>
</nav>
<div class="bg-surface-container-low p-5 rounded-2xl">
<p class="text-xs font-bold text-cyan-500 uppercase tracking-widest mb-2">新品速递</p>
<p class="text-sm font-semibold text-on-surface mb-4">能量流动瑜伽 2.0 课程现已上线。</p>
<button class="w-full bg-primary text-on-primary py-2.5 rounded-xl text-xs font-bold hover:brightness-95 active:scale-[0.98] transition-all">立即探索</button>
</div>
</aside>
<!-- Main Content Area -->
<main class="flex-1 bg-surface p-8 pb-32">
<!-- Search Header -->
<header class="max-w-6xl mx-auto mb-16">
<div class="space-y-8">
<div class="space-y-2">
<h1 class="text-4xl md:text-5xl font-headline font-bold text-on-surface tracking-tight">重塑你的运动姿态</h1>
<p class="text-lg text-secondary font-body max-w-xl">基于 MovementVO 规范打造的精控动作库，追求每一次律动的极致精准。</p>
</div>
<!-- Prominent Search Bar (searchMovements API context) -->
<div class="relative w-full max-w-2xl group">
<span class="material-symbols-outlined absolute left-5 top-1/2 -translate-y-1/2 text-slate-400 group-focus-within:text-cyan-500 transition-colors text-2xl">search</span>
<input class="w-full pl-14 pr-6 py-5 bg-surface-container-low rounded-3xl border-none focus:ring-4 focus:ring-cyan-500/10 focus:bg-white transition-all placeholder:text-slate-400 text-lg font-medium shadow-sm" placeholder="搜索动作、部位或训练难度..." type="text"/>
<button class="absolute right-4 top-1/2 -translate-y-1/2 bg-cyan-500 text-white px-6 py-2 rounded-2xl font-bold text-sm hover:bg-cyan-600 transition-colors">搜索</button>
</div>
</div>
</header>
<!-- MovementVO Structured Training Grid -->
<section class="max-w-6xl mx-auto mt-20">
<div class="flex items-center justify-between mb-10">
<h2 class="text-3xl font-headline font-bold text-on-surface">动作探索</h2>
<div class="flex gap-4">
<button class="px-4 py-2 bg-surface-container-highest rounded-xl text-sm font-bold text-secondary">全部分类</button>
<button class="px-4 py-2 bg-surface-container-highest rounded-xl text-sm font-bold text-secondary">难度筛选</button>
</div>
</div>
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-10">
<!-- MovementVO Card 1 -->
<div class="group cursor-pointer flex flex-col h-full bg-white rounded-[2rem] overflow-hidden border border-slate-100 shadow-sm hover:shadow-xl hover:-translate-y-2 transition-all duration-300">
<div class="relative aspect-[4/3] overflow-hidden">
<img class="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110" data-alt="Kettlebell swings" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDSIgq6A3DXRlm4zNve-rWueAGuXb6UNVFNbG_caYLM1wN7xy7ddmvDPB6oie-Q1qIScVFZbIgWSCd3YWIpAWx15Q8tisEQ33JDOhMp4Or_okjBv6oJevNHkU6hUdFf3y7GAgohtKeKBMwZbDYprOB54ma3RTqUY9QdW6sXhvrmcJGRw5l0LW1VzZfk0Ls1JtiW_xkS_sxhDjFtCKNUzdyK0j8OXNs2cs_3ZZMWV4SRR30Xq3HYmU2xk-S3XaDfA7X9GSQOH0ut-_o"/>
<div class="absolute top-4 left-4 flex gap-2">
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-cyan-700">Strength</span>
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-amber-700">Intermediate</span>
</div>
</div>
<div class="p-8 flex flex-col flex-1">
<h3 class="text-xl font-headline font-bold text-on-surface mb-3 group-hover:text-cyan-500 transition-colors">壶铃爆发力摆动</h3>
<p class="text-secondary text-sm font-body leading-relaxed mb-6 line-clamp-3">
                            通过髋部爆发力驱动壶铃，强化后侧链肌群，并显著提升爆发力与心肺耐力。
                        </p>
<div class="mt-auto flex items-center justify-between border-t border-slate-50 pt-6">
<div class="flex items-center gap-4 text-xs font-bold text-slate-400">
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">timer</span> 12m</span>
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">bolt</span> 180kcal</span>
</div>
<span class="material-symbols-outlined text-cyan-500 group-hover:translate-x-1 transition-transform">arrow_forward</span>
</div>
</div>
</div>
<!-- MovementVO Card 2 -->
<div class="group cursor-pointer flex flex-col h-full bg-white rounded-[2rem] overflow-hidden border border-slate-100 shadow-sm hover:shadow-xl hover:-translate-y-2 transition-all duration-300">
<div class="relative aspect-[4/3] overflow-hidden">
<img class="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110" data-alt="Box jumps" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAWhQOO2Un6Fo63btUudLCXXeDbHzJou1DsUWtpmSNhRdPETVBHhXIYR80NtYeNqPoDY5JmvWBSY5sx22v5mozG_mTnbRohvukAsYjp9nwjcmAgIU8Q8tZh1K6mswYgERlbAzyZxFUtgvn-B9EoX9PqtXz3hpPm0M9MitQBv3RBquNHKJ4ZpNdPAOAWE9EhsyZ-XGT4tZwFqDjwaxbSxfRIEfm8DlPxYu2S_mED19lrgQAznhJezTzT43P4oUp1WQggV5vThSteYQI"/>
<div class="absolute top-4 left-4 flex gap-2">
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-cyan-700">Explosive</span>
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-rose-700">Advanced</span>
</div>
</div>
<div class="p-8 flex flex-col flex-1">
<h3 class="text-xl font-headline font-bold text-on-surface mb-3 group-hover:text-cyan-500 transition-colors">增强式跳箱训练</h3>
<p class="text-secondary text-sm font-body leading-relaxed mb-6 line-clamp-3">
                            通过快速伸缩复合练习，提升神经肌肉系统反应速度与垂直弹跳能力，专注落地平稳。
                        </p>
<div class="mt-auto flex items-center justify-between border-t border-slate-50 pt-6">
<div class="flex items-center gap-4 text-xs font-bold text-slate-400">
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">timer</span> 15m</span>
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">bolt</span> 240kcal</span>
</div>
<span class="material-symbols-outlined text-cyan-500 group-hover:translate-x-1 transition-transform">arrow_forward</span>
</div>
</div>
</div>
<!-- MovementVO Card 3 -->
<div class="group cursor-pointer flex flex-col h-full bg-white rounded-[2rem] overflow-hidden border border-slate-100 shadow-sm hover:shadow-xl hover:-translate-y-2 transition-all duration-300">
<div class="relative aspect-[4/3] overflow-hidden">
<img class="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110" data-alt="Planks" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDeoXmdIs6Di2qng8qmf7RavE12cLAjAjPmg3GDf_Owj1Cx4en4NuaxfnzNaEBZnEhwDdZxTtxbLPaKjC9N9gmrosJg4oyveEucngAiJ2xCnJvtvqV4Eje3FfXvdl7KRl7anIE1DZvlV-nr6kPPVwSA84b_V3g4841HH-c-5goAytxgkqUzbU06lEbvpBpKHoCmlJarrpWDIMFAI2hTwbRL1ZdnKaQPS9EdsQxZp4qveT5y7ii--bQ6VHgCd1Ox5a9BwvDTZsdxD8k"/>
<div class="absolute top-4 left-4 flex gap-2">
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-cyan-700">Core</span>
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-emerald-700">Beginner</span>
</div>
</div>
<div class="p-8 flex flex-col flex-1">
<h3 class="text-xl font-headline font-bold text-on-surface mb-3 group-hover:text-cyan-500 transition-colors">标准平板支撑</h3>
<p class="text-secondary text-sm font-body leading-relaxed mb-6 line-clamp-3">
                            基础核心稳定性训练，重点在于脊柱中立位与盆底肌参与，建立全身体支撑结构。
                        </p>
<div class="mt-auto flex items-center justify-between border-t border-slate-50 pt-6">
<div class="flex items-center gap-4 text-xs font-bold text-slate-400">
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">timer</span> 8m</span>
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">bolt</span> 60kcal</span>
</div>
<span class="material-symbols-outlined text-cyan-500 group-hover:translate-x-1 transition-transform">arrow_forward</span>
</div>
</div>
</div>
<!-- MovementVO Card 4 -->
<div class="group cursor-pointer flex flex-col h-full bg-white rounded-[2rem] overflow-hidden border border-slate-100 shadow-sm hover:shadow-xl hover:-translate-y-2 transition-all duration-300">
<div class="relative aspect-[4/3] overflow-hidden">
<img class="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110" data-alt="Yoga morning mobility" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAPjOGyiYKpAKS3AofPkt0rDrNxugMoPxhN4kjKKiNOCm4b8WF9MECmYUOlkXXPibvrDsrDQyFwtmcLcdFfDHbiHqaycGX301701F13UQ8ztpKoK2A6nz2O2-fgS2s1IzO8eT0Gasi1l2fZaF1IpTOiM8dBqHwDSlvifOKRetKrzx-AbaCEu0PTUwhO3ihbD17_pNTN2CmDjJubjsBlasyIcWk_wo0NWEft4B5bsALJL0WJhtxONwk-uhDJwCQspMZpbjtNqzAaBeg"/>
<div class="absolute top-4 left-4 flex gap-2">
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-cyan-700">Yoga</span>
<span class="glass-card text-[10px] font-bold px-3 py-1.5 rounded-full uppercase tracking-wider text-emerald-700">Beginner</span>
</div>
</div>
<div class="p-8 flex flex-col flex-1">
<h3 class="text-xl font-headline font-bold text-on-surface mb-3 group-hover:text-cyan-500 transition-colors">晨间流动伸展</h3>
<p class="text-secondary text-sm font-body leading-relaxed mb-6 line-clamp-3">
                            结合呼吸与流畅动作，唤醒身体关节灵活性，缓解肌肉僵硬，开启充满活力的一天。
                        </p>
<div class="mt-auto flex items-center justify-between border-t border-slate-50 pt-6">
<div class="flex items-center gap-4 text-xs font-bold text-slate-400">
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">timer</span> 20m</span>
<span class="flex items-center gap-1.5"><span class="material-symbols-outlined text-sm text-cyan-500">bolt</span> 110kcal</span>
</div>
<span class="material-symbols-outlined text-cyan-500 group-hover:translate-x-1 transition-transform">arrow_forward</span>
</div>
</div>
</div>
</div>
</section>
</main>
</div>
<!-- Bottom Navigation Shell (Mobile Only) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 py-3 pb-safe bg-white/70 dark:bg-slate-900/70 backdrop-blur-xl z-50 shadow-[0_-4px_20px_rgba(0,0,0,0.04)]">
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95" href="#">
<span class="material-symbols-outlined">home</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Home</span>
</a>
<a class="flex flex-col items-center justify-center bg-cyan-50 dark:bg-cyan-900/30 text-cyan-600 dark:text-cyan-300 rounded-xl px-4 py-1.5 active:scale-95 transition-all" href="#">
<span class="material-symbols-outlined">fitness_center</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Hub</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95" href="#">
<span class="material-symbols-outlined">leaderboard</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Ranks</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95" href="#">
<span class="material-symbols-outlined">forum</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Social</span>
</a>
</nav>
<!-- Footer -->
<footer class="bg-slate-50 border-t border-slate-200 w-full py-16 px-8 mt-20">
<div class="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-4 gap-12">
<div class="md:col-span-2">
<span class="text-lg font-bold text-slate-900 mb-4 block">轻练 Light Fit</span>
<p class="font-inter text-sm text-slate-500 max-w-sm mb-6">专为追求精准、平衡与身体美学的运动者设计，通过优雅的数字化体验定义健身体验。</p>
<div class="flex gap-4">
<button class="w-10 h-10 rounded-full border border-outline-variant flex items-center justify-center hover:bg-cyan-500/10 hover:border-cyan-500/50 transition-colors">
<span class="material-symbols-outlined text-sm">share</span>
</button>
<button class="w-10 h-10 rounded-full border border-outline-variant flex items-center justify-center hover:bg-cyan-500/10 hover:border-cyan-500/50 transition-colors">
<span class="material-symbols-outlined text-sm">mail</span>
</button>
</div>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">探索</h4>
<ul class="space-y-4 font-inter text-sm">
<li><a class="text-slate-500 hover:text-cyan-500 hover:underline decoration-cyan-500 underline-offset-4" href="#">隐私政策</a></li>
<li><a class="text-slate-500 hover:text-cyan-500 hover:underline decoration-cyan-500 underline-offset-4" href="#">服务条款</a></li>
<li><a class="text-slate-500 hover:text-cyan-500 hover:underline decoration-cyan-500 underline-offset-4" href="#">帮助中心</a></li>
<li><a class="text-slate-500 hover:text-cyan-500 hover:underline decoration-cyan-500 underline-offset-4" href="#">品牌素材</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">联系我们</h4>
<div class="bg-surface-container-low p-4 rounded-xl border border-slate-200">
<p class="text-xs text-secondary font-medium mb-3">订阅我们的精英简报，获取专业训练洞察。</p>
<div class="relative">
<input class="w-full bg-white border-slate-200 text-xs rounded-lg p-2.5 focus:ring-1 focus:ring-cyan-500 focus:border-cyan-500" placeholder="电子邮箱" type="email"/>
<button class="absolute right-1 top-1 bg-cyan-500 text-white p-1.5 rounded-md hover:brightness-95 transition-all">
<span class="material-symbols-outlined text-xs">arrow_forward</span>
</button>
</div>
</div>
</div>
</div>
<div class="max-w-7xl mx-auto mt-16 pt-8 border-t border-slate-200">
<p class="font-inter text-sm text-slate-500 text-center">© 2024 轻练 Light Fit. 极致精准，定义不凡.</p>
</div>
</footer>

  </div>
</template>

<script setup>
import TheNavbar from "../components/layout/TheNavbar.vue";
import { onMounted, ref } from "vue";

// Data
const pageData = ref(null);

onMounted(async () => {
    // API request template placeholder
    // try {
    //     const res = await fetch('/api/endpoint');
    //     pageData.value = await res.json();
    // } catch(e) { console.error(e); }
});
</script>

<style scoped>
</style>
