<template>
  <div class="api-view bg-surface text-on-surface min-h-screen pt-20">
    <TheNavbar />
    
<!-- TopNavBar -->

<!-- Hero Section -->
<section class="relative h-screen w-full flex items-center justify-center overflow-hidden">
<img class="absolute inset-0 w-full h-full object-cover" data-alt="Cinematic shot of a focused person practicing yoga in a high-end minimalist studio with soft natural sunlight and morning mist" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAy2EnqYoslFagXZtIB37Gs_aQQoK-iF5tFFm1No0yMKsdAQks2CCHlwitiz1XyEgrN85Z9HE3x5MjZS7bTRsuiToZ0qUhin99GpjvVsnaz4Rjc_L9UnErDOZroKdDUMlKk2HwkCprAhTW9aXqEDxWaf3BlSJfGDSNKwNmM-WGme9oVbtwnFK8qQct-y7LmBRe9xKmftrx4BAOTV5gzrWz1UK0iy1dk9QXsI4zf-oAEpf2XwzEAjyYb7DTPK9L6hU488IouOtE6whg"/>
<div class="absolute inset-0 hero-gradient"></div>
<div class="relative z-10 text-center px-6">
<h1 class="font-headline text-5xl md:text-8xl font-extrabold text-white tracking-tighter mb-6 drop-shadow-2xl">
                轻练 - 定义你的轻盈力量
            </h1>
<p class="text-white/80 text-lg md:text-xl font-light tracking-widest max-w-2xl mx-auto mb-10">
                超越传统的重量，寻找身心合一的精准控制。在呼吸之间，重塑自我。
            </p>
<button class="px-10 py-5 bg-primary text-black rounded-full font-headline font-extrabold text-lg hover:scale-105 transition-transform shadow-lg shadow-primary/40">
                开启进化之旅
            </button>
</div>
<div class="absolute bottom-10 left-1/2 -translate-x-1/2 animate-bounce">
<span class="material-symbols-outlined text-white text-4xl">expand_more</span>
</div>
</section>
<!-- Daily Summary Section (New) -->
<section class="py-24 px-8 bg-surface-container-low">
<div class="max-w-7xl mx-auto">
<div class="flex flex-col md:flex-row items-baseline gap-4 mb-12">
<h2 class="text-3xl font-headline font-bold text-on-surface">今日概览</h2>
<span class="text-secondary text-sm font-medium">欢迎回来，Alex. 您的训练数据已同步。</span>
</div>
<div class="grid grid-cols-1 md:grid-cols-3 gap-8">
<!-- Calories Card -->
<div class="bg-white dark:bg-slate-900 p-8 rounded-2xl shadow-sm border border-slate-100 dark:border-slate-800 flex items-center justify-between">
<div>
<p class="text-slate-500 text-xs font-bold tracking-widest uppercase mb-2">今日消耗</p>
<div class="flex items-baseline gap-1">
<span class="text-4xl font-headline font-extrabold text-cyan-500">1,240</span>
<span class="text-slate-400 text-sm font-medium">kcal</span>
</div>
</div>
<div class="w-16 h-16 rounded-full border-4 border-cyan-500/20 border-t-cyan-500 flex items-center justify-center">
<span class="material-symbols-outlined text-cyan-500">local_fire_department</span>
</div>
</div>
<!-- Active Plans Card (Multi-column span) -->
<div class="md:col-span-2 bg-white dark:bg-slate-900 p-8 rounded-2xl shadow-sm border border-slate-100 dark:border-slate-800">
<div class="flex justify-between items-center mb-6">
<p class="text-slate-500 text-xs font-bold tracking-widest uppercase">活跃训练计划</p>
<a class="text-cyan-500 text-xs font-bold hover:underline" href="#">查看全部</a>
</div>
<div class="flex flex-col sm:flex-row gap-6">
<div class="flex-1 flex gap-4 p-4 rounded-xl bg-slate-50 dark:bg-slate-800/50">
<div class="w-12 h-12 rounded-lg bg-cyan-100 dark:bg-cyan-900/30 flex items-center justify-center text-cyan-600">
<span class="material-symbols-outlined">fitness_center</span>
</div>
<div>
<h4 class="font-bold text-sm">核心稳定性进阶</h4>
<p class="text-slate-500 text-xs mt-1">进度: 12/21 天</p>
<div class="w-32 h-1 bg-slate-200 dark:bg-slate-700 rounded-full mt-2">
<div class="w-[57%] h-full bg-cyan-500 rounded-full"></div>
</div>
</div>
</div>
<div class="flex-1 flex gap-4 p-4 rounded-xl bg-slate-50 dark:bg-slate-800/50">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined">self_improvement</span>
</div>
<div>
<h4 class="font-bold text-sm">晨间冥想唤醒</h4>
<p class="text-slate-500 text-xs mt-1">进度: 5/7 天</p>
<div class="w-32 h-1 bg-slate-200 dark:bg-slate-700 rounded-full mt-2">
<div class="w-[71%] h-full bg-primary rounded-full"></div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</section>
<!-- Brand Philosophy (Storytelling) -->
<section class="py-32 px-8 bg-surface">
<div class="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-2 gap-24 items-center">
<div class="space-y-8">
<span class="text-primary font-headline font-bold tracking-widest uppercase text-sm">品牌哲学</span>
<h2 class="text-4xl md:text-6xl font-headline font-medium leading-tight text-on-surface">
                    不只是健身，<br/>是一场数字化的精致修行。
                </h2>
<p class="text-secondary text-lg leading-relaxed max-w-md">
                    我们摒弃了嘈杂的工业感，利用尖端的数据模型与美学设计，为您打造一个能够深度专注的训练空间。每一卡路里的消耗，都是对生命质感的精准雕琢。
                </p>
<div class="flex gap-4 pt-4">
<div class="w-12 h-[1px] bg-outline-variant self-center"></div>
<span class="text-outline italic">始于 2024 / 数字工坊</span>
</div>
</div>
<div class="relative">
<div class="aspect-[4/5] rounded-xl overflow-hidden shadow-2xl">
<img class="w-full h-full object-cover grayscale hover:grayscale-0 transition-all duration-700" data-alt="Minimalist fitness equipment in a clean white architectural space with dramatic shadows and morning light" src="https://lh3.googleusercontent.com/aida-public/AB6AXuA4CNlyt1Y3kX6QXUSlJNXxwEHwBftks4Ak2JDkopzns1TLvRfDHFELD_uxtuTfjztgRqphYGre_VStonL2TibgFjv6hzkinQ881cVbVpXGnhKW5rrNKl-hJ20w7Uo3NSqWZUt0urQENdwVwKXP9fqEffUG9patpquWW3UCDvAYhlQhKkQ7aKzS5QeL3T--5Nz26Jd0DXL7d3BbHLzsc2RNOtxRsLTA0J3UyFP-_KUVj8d4ulYzoE0BoAuZEaghWNJEX_fxQd8EG70"/>
</div>
<div class="absolute -bottom-10 -left-10 glass-card p-8 rounded-xl shadow-xl hidden md:block max-w-xs">
<p class="font-headline font-bold text-primary mb-2 text-2xl">98%</p>
<p class="text-secondary text-sm">的用户反馈在四周内感受到了前所未有的身心平衡。</p>
</div>
</div>
</div>
</section>
<!-- Core Features (Bento Grid Style) -->
<section class="py-32 px-8 bg-surface-container-low">
<div class="max-w-7xl mx-auto">
<div class="flex flex-col md:flex-row justify-between items-end mb-20 gap-8">
<div>
<span class="text-primary font-headline font-bold tracking-widest uppercase text-sm">核心功能</span>
<h2 class="text-4xl md:text-5xl font-headline font-medium mt-4">模块化智能训练</h2>
</div>
<p class="text-secondary max-w-xs text-right">三大核心支柱，为您构建全方位的进化闭环。</p>
</div>
<div class="grid grid-cols-1 md:grid-cols-12 gap-6 h-auto md:h-[600px]">
<!-- Training (Large Card) -->
<div class="md:col-span-8 group relative overflow-hidden rounded-xl bg-surface-container-lowest shadow-sm hover:shadow-xl transition-all duration-500">
<div class="absolute inset-0 opacity-10 group-hover:opacity-20 transition-opacity">
<img class="w-full h-full object-cover" data-alt="Abstract close-up of flowing water ripples and light reflections, symbolizing fluid movement" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBLl3Fgr3vC1ge3WW0n2rShTtORUttgYiKjzvFWj8fEZF7L1XSs-MB-FazlJCx9O32O_g9jXVlK7peC39itQJJWDvT4HK6qha9P1X_n6o2vFXyXULRKyu3iiiuChVjwg8GtM63vebJ7n_kzrScSn1854K7jkgW35FowSLeXhelig9CIOOhOlfHTpbssJhBCmDbPoNTYpz_MAZ2ogjnUERnvKfFePSFfvAosSRNzoyjvzPTgVOre0JJ7Gp0MqGzlT_-RKcQnWL8BlNo"/>
</div>
<div class="relative h-full p-12 flex flex-col justify-between">
<div>
<span class="material-symbols-outlined text-primary text-5xl mb-6">fitness_center</span>
<h3 class="text-3xl font-headline font-bold mb-4">智能训练系统</h3>
<p class="text-secondary max-w-md leading-relaxed">基于生物力学的动态课程，通过AI视觉实时纠正体态。您的私人教练，现在触手可及。</p>
</div>
<div class="flex items-center gap-4 text-primary font-bold cursor-pointer group-hover:translate-x-2 transition-transform">
                            了解详情 <span class="material-symbols-outlined">arrow_forward</span>
</div>
</div>
</div>
<!-- Community -->
<div class="md:col-span-4 group relative overflow-hidden rounded-xl bg-surface-container-lowest shadow-sm hover:shadow-xl transition-all duration-500">
<div class="h-full p-10 flex flex-col bg-primary/5">
<span class="material-symbols-outlined text-primary text-4xl mb-6">groups</span>
<h3 class="text-2xl font-headline font-bold mb-3">共鸣社区</h3>
<p class="text-secondary text-sm flex-grow">与志同道合的精英会员分享心得，在“轻练”圈层中寻找共同进化的力量。</p>
<div class="mt-8 flex -space-x-3">
<img alt="Avatar" class="w-10 h-10 rounded-full border-2 border-white bg-slate-100 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDoO2Rfv5E07DpACNS3uj5BFHpfqGSwOgqfpB56VGDt3nT4HF5-VgJOG5hxVhrN2g5XHD4p_TZHCbhnp5i29ig3POJ3zr_gWHIX-ypOLyW2s3c8K4Xfl3ZQVG1rAJZS9Pf7hz5ETiq3jUTc6hnoW4Th_7T1T7FWjsdiHbt8C9SFLErwcU_VO3MxTc3CRECmizJkuRKIBwwI-Ui5R-HZdCZBj869KfYg4-ailKYnjTni9bC7k56rpDG0kCnmqV5C2AC29DFKlzz_NZE"/>
<img alt="Avatar" class="w-10 h-10 rounded-full border-2 border-white bg-slate-100 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuB5dppaYwyk_UFGZK584EYAlvIf7KInshcFswJF5jpFNJS3H9GSRkM5O3cRP3wsfzmrm_OSce22tCsFslRnwhZYAnxAiEqCAL7vrAMc9BkEpTh-_1tn5kJoHJH7ZNHdT8F6LADMW-VqDlWgCtY60i63F9QbCNJz0MFfI1S4IcDFvqzdb3nQ9urb_UAoQ1gQR0IwJ5-BjMAL1RfLdSC9SE_xu1h6MLda9uB_vuJ_M4HdeanHluN5wTM9-xjji2cz2w-xZV0xd9hvWkQ"/>
<img alt="Avatar" class="w-10 h-10 rounded-full border-2 border-white bg-slate-100 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBuw77eZn7rnz3LMhcUurCFLNQN0MklMBZp7GEusPGSAiC57ZAikpBhNJ1Xz8Fxn-SB0RB_MXFL6p6Qf9Uh4Kl5_QsFAgTLJNhxJZGbviQB-eN2M-5-b13RSBL7zlsJVwNcktaOaaMl_1xZpVmu-0Rk4FlGwJq7G_bzog2XB1R5RBU7EzFAL0bhnn0k1eFqsmbHuMOO_GTaz9nvvRcQDBD0AzB7DjvV4VhRIvh3roahml9JicIPFv1PZdZtLxacj9saq6Seh7-yPpk"/>
<div class="w-10 h-10 rounded-full border-2 border-white bg-primary flex items-center justify-center text-black text-xs font-bold">+12k</div>
</div>
</div>
</div>
<!-- Analytics -->
<div class="md:col-span-4 group relative overflow-hidden rounded-xl bg-slate-900 text-white shadow-sm hover:shadow-xl transition-all duration-500">
<div class="h-full p-10 flex flex-col justify-between">
<div>
<span class="material-symbols-outlined text-primary text-4xl mb-6">insights</span>
<h3 class="text-2xl font-headline font-bold mb-3">深度数据分析</h3>
<p class="text-slate-400 text-sm">多维度的生理指标追踪，将汗水转化为可视化的成长曲线。</p>
</div>
<div class="h-16 flex items-end gap-1">
<div class="w-full bg-primary/20 h-2/3 rounded-t-sm"></div>
<div class="w-full bg-primary h-1/2 rounded-t-sm"></div>
<div class="w-full bg-primary/40 h-full rounded-t-sm"></div>
<div class="w-full bg-primary h-3/4 rounded-t-sm"></div>
<div class="w-full bg-primary/60 h-2/3 rounded-t-sm"></div>
</div>
</div>
</div>
<!-- Featured Program -->
<div class="md:col-span-8 group relative overflow-hidden rounded-xl bg-surface-container-lowest shadow-sm hover:shadow-xl transition-all duration-500">
<div class="grid grid-cols-1 sm:grid-cols-2 h-full">
<div class="p-10 flex flex-col justify-center">
<span class="px-3 py-1 bg-primary/20 text-primary text-[10px] font-bold tracking-widest rounded-full w-max mb-4 uppercase">本月主打</span>
<h3 class="text-2xl font-headline font-bold mb-2">晨间唤醒：21天塑形</h3>
<p class="text-secondary text-sm mb-6">专为忙碌的职场精英设计，每日15分钟，唤醒核心能量。</p>
<button class="w-max px-6 py-3 border-2 border-primary/30 rounded-full text-sm font-bold hover:bg-primary hover:text-black transition-colors">立即加入</button>
</div>
<div class="relative overflow-hidden">
<img class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700" data-alt="Athlete stretching in a contemporary minimalist gym with floor-to-ceiling windows" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAU9n4wSuBl6-WwzBaBkGq17-Sk7LEZoh8Cm9FyGbUgZRR4Ao7CaMiv4oikVDQwgdVwwpq7bOyRHYloS4awnwhMmuvtV-dDVjmKnXkOrAgRim4F8VXCsphK42K_zVPZ6p72okbkBl08x30Zi6Zym2pypSM9b_WSWgvwU9M477wBnC-ZrWIgw4g_nHIm694j29rxq0nlevOjMsLCSCG49u_24gjC1jrWf4BDYtwGGn_BHtLzarPyVL4dsRmXjTOU2FgNb2sDz_DVeDA"/>
</div>
</div>
</div>
</div>
</div>
</section>
<!-- Scroll-Triggered Modular Quote -->
<section class="relative py-48 bg-white overflow-hidden">
<div class="max-w-4xl mx-auto text-center px-8 relative z-10">
<span class="material-symbols-outlined text-primary/10 text-8xl absolute -top-10 -left-10">format_quote</span>
<h2 class="text-4xl md:text-6xl font-headline font-extrabold tracking-tighter leading-tight italic">
                “真正的力量不在于你举起多重，而在于你能多轻盈地掌控自己的生命。”
            </h2>
<p class="mt-12 text-secondary font-headline tracking-[0.3em] uppercase">— 轻练 品牌宣言</p>
</div>
<div class="absolute top-0 right-0 w-64 h-64 bg-primary/5 rounded-full blur-3xl -translate-y-1/2 translate-x-1/2"></div>
<div class="absolute bottom-0 left-0 w-96 h-96 bg-primary/5 rounded-full blur-3xl translate-y-1/2 -translate-x-1/2"></div>
</section>
<!-- Footer -->
<footer class="bg-slate-50 dark:bg-slate-950 pt-20 pb-10 px-8 border-t border-slate-200 dark:border-slate-800">
<div class="max-w-7xl mx-auto">
<div class="grid grid-cols-1 md:grid-cols-4 gap-12 mb-20">
<div class="col-span-1 md:col-span-1">
<span class="text-2xl font-black text-slate-900 dark:text-white mb-6 block">轻练</span>
<p class="text-slate-500 text-sm leading-relaxed mb-8">
                        定义你的轻盈力量。<br/>探索前沿健身美学，开启全方位的数字化身心进化。
                    </p>
<div class="flex gap-4">
<span class="material-symbols-outlined text-slate-400 hover:text-primary cursor-pointer transition-colors">public</span>
<span class="material-symbols-outlined text-slate-400 hover:text-primary cursor-pointer transition-colors">smartphone</span>
<span class="material-symbols-outlined text-slate-400 hover:text-primary cursor-pointer transition-colors">share</span>
</div>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">快速链接</h4>
<ul class="space-y-4 text-sm text-slate-500">
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">课程中心</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">专家导师</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">智能硬件</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">商业合作</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">支持与服务</h4>
<ul class="space-y-4 text-sm text-slate-500">
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">隐私政策</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">服务条款</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">帮助中心</a></li>
<li><a class="hover:text-primary transition-colors underline-offset-4 hover:underline" href="#">媒体资源</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6">订阅通讯</h4>
<p class="text-slate-500 text-sm mb-6">获取最新的健康资讯与会员专享活动。</p>
<div class="flex gap-2">
<input class="bg-white border-none rounded-lg px-4 py-3 text-sm focus:ring-2 focus:ring-primary/20 w-full shadow-sm" placeholder="您的邮箱" type="email"/>
<button class="bg-slate-900 text-primary p-3 rounded-lg flex items-center justify-center hover:bg-slate-800 transition-colors">
<span class="material-symbols-outlined">send</span>
</button>
</div>
</div>
</div>
<div class="flex flex-col md:flex-row justify-between items-center pt-8 border-t border-slate-200 text-slate-400 text-xs gap-4">
<p>© 2024 轻练 Light Fit. 匠心定义纯粹力量。</p>
<div class="flex gap-8">
<span>备案号: 沪ICP备2024XXXX号</span>
<span>简体中文</span>
</div>
</div>
</div>
</footer>
<!-- BottomNavBar (Mobile only) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 py-3 pb-safe bg-white/70 dark:bg-slate-900/70 backdrop-blur-xl z-50 shadow-[0_-4px_20px_rgba(0,0,0,0.04)]">
<a class="flex flex-col items-center justify-center bg-cyan-50 dark:bg-cyan-900/30 text-cyan-600 dark:text-cyan-300 rounded-xl px-4 py-1.5 click-active" href="#">
<span class="material-symbols-outlined">home</span>
<span class="text-[11px] font-medium tracking-wide mt-0.5">Home</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all click-active" href="#">
<span class="material-symbols-outlined">fitness_center</span>
<span class="text-[11px] font-medium tracking-wide mt-0.5">Hub</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all click-active" href="#">
<span class="material-symbols-outlined">leaderboard</span>
<span class="text-[11px] font-medium tracking-wide mt-0.5">Ranks</span>
</a>
<a class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all click-active" href="#">
<span class="material-symbols-outlined">forum</span>
<span class="text-[11px] font-medium tracking-wide mt-0.5">Social</span>
</a>
</nav>
<!-- FAB -->
<button class="fixed bottom-32 right-8 w-16 h-16 bg-primary text-black rounded-full flex items-center justify-center shadow-2xl hover:scale-110 active:scale-95 transition-all z-40 lg:bottom-12">
<span class="material-symbols-outlined text-3xl font-bold">add</span>
</button>

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
