<template>
  <div class="leaderboard_api-view bg-surface text-on-surface min-h-screen pt-20">
    <TheNavbar />
    
<!-- TopNavBar (Shared Component) -->

<div class="flex min-h-screen pt-20">
<!-- SideNavBar (Shared Component) -->
<aside class="hidden lg:flex flex-col w-80 h-[calc(100vh-5rem)] bg-slate-50 dark:bg-slate-950 border-r border-slate-100 dark:border-slate-800 p-6 space-y-4 sticky top-20">
<div class="flex items-center space-x-4 mb-4">
<div class="w-10 h-10 rounded-full bg-slate-200 overflow-hidden">
<img alt="User avatar" class="w-full h-full object-cover" src="https://lh3.googleusercontent.com/a/ACg8ocL8_v_x_8_v_x_8_v_x_8_v_x_8_v_x_8_v_x_8_v_x_8=s96-c"/>
</div>
<div>
<h3 class="text-xl font-bold text-slate-900 dark:text-white font-headline">社区交流</h3>
<p class="text-xs text-cyan-600 dark:text-cyan-400 font-medium Inter">正在活跃</p>
</div>
</div>
<nav class="flex-1 space-y-1">
<a class="flex items-center space-x-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 px-4 py-3 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">chat_bubble</span>
<span class="font-headline text-sm font-medium">私信对话</span>
</a>
<a class="flex items-center space-x-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 px-4 py-3 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">group</span>
<span class="font-headline text-sm font-medium">健身好友</span>
</a>
<a class="flex items-center space-x-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 px-4 py-3 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">public</span>
<span class="font-headline text-sm font-medium">全球动态</span>
</a>
<a class="flex items-center space-x-3 text-cyan-600 dark:text-cyan-400 bg-white dark:bg-slate-900 font-bold px-4 py-3 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">leaderboard</span>
<span class="font-headline text-sm font-medium">荣誉排行</span>
</a>
<a class="flex items-center space-x-3 text-slate-500 dark:text-slate-400 hover:bg-cyan-50 dark:hover:bg-cyan-900/20 px-4 py-3 rounded-lg transition-all" href="#">
<span class="material-symbols-outlined">bookmark</span>
<span class="font-headline text-sm font-medium">收藏帖</span>
</a>
</nav>
<div class="pt-6 border-t border-slate-200 dark:border-slate-800">
<button class="w-full bg-slate-900 dark:bg-slate-800 text-white py-3.5 rounded-xl font-headline font-bold text-sm tracking-wide shadow-lg hover:opacity-90 active:scale-95 transition-all">
                开启训练
            </button>
</div>
</aside>
<!-- Main Content -->
<main class="flex-1 px-6 md:px-12 py-12 bg-surface">
<!-- Header Section -->
<div class="max-w-5xl mx-auto mb-16">
<div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
<div>
<span class="text-primary font-headline font-bold tracking-widest uppercase text-xs mb-3 block">社区巅峰时刻</span>
<h1 class="text-4xl md:text-5xl font-headline font-extrabold text-on-surface tracking-tighter">荣誉墙</h1>
<p class="text-secondary mt-4 max-w-md text-lg leading-relaxed font-light">追踪您的排名并向全球健身领军者发起挑战。</p>
</div>
<!-- Personal Stats Widget -->
<div class="bg-surface-container-lowest border border-outline-variant/10 rounded-2xl p-6 shadow-[0_8px_30px_rgb(0,0,0,0.02)] min-w-[280px]">
<div class="flex items-center justify-between mb-4">
<span class="text-secondary text-sm font-medium">您的当前排名</span>
<span class="bg-primary/10 text-primary px-3 py-1 rounded-full text-xs font-bold">前 5%</span>
</div>
<div class="flex items-baseline space-x-2">
<span class="text-4xl font-headline font-black text-on-surface">第 42 名</span>
<span class="text-secondary text-sm">/ 12,408</span>
</div>
<div class="mt-4 pt-4 border-t border-outline-variant/10 flex justify-between items-center">
<div class="flex items-center space-x-2">
<span class="material-symbols-outlined text-primary text-sm" style="font-variation-settings: 'FILL' 1;">bolt</span>
<span class="text-on-surface font-bold">2,840</span>
<span class="text-secondary text-xs">战力积分</span>
</div>
<span class="material-symbols-outlined text-outline-variant text-sm">chevron_right</span>
</div>
</div>
</div>
</div>
<!-- Top 3 Highlights -->
<div class="max-w-5xl mx-auto grid grid-cols-1 md:grid-cols-12 gap-6 mb-12">
<!-- Rank 1: Main Feature -->
<div class="md:col-span-6 bg-surface-container-lowest rounded-3xl p-8 relative overflow-hidden group transition-all duration-500 hover:shadow-[0_20px_50px_rgba(0,229,255,0.08)] border border-outline-variant/5">
<div class="absolute top-0 right-0 p-8">
<div class="w-16 h-16 flex items-center justify-center bg-primary/5 rounded-full">
<span class="material-symbols-outlined text-primary text-3xl" style="font-variation-settings: 'FILL' 1;">workspace_premium</span>
</div>
</div>
<div class="relative z-10">
<div class="w-24 h-24 rounded-2xl overflow-hidden mb-6 bg-primary/5 p-1 avatar-animate">
<img alt="冠军头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAvLeIt7EtbIkbS0jqXNwaCsYyQ0biHX79fjkFm7yXHTPWJtn-DKoT2qqDFSH83mklXTgKFN8DPjDuaoJdERpVqCcSSUKhlncotX8C_yrIbRxqV9zdtO8itIYO7Pm-ii24awFFnHtMFVBGP7UWJs06RrC8yZtZWkV7Ca3hvD1O58ZQjWHy-2l15rwlSpe9kyi_ewx8r_I6fKLIEeRC8Za4ic6AQI66wXngY856nxu9rhi91gS3RgWwEUBCXzKei2KjIJClp4cNrp0Y"/>
</div>
<span class="text-primary font-bold text-sm tracking-widest uppercase mb-1 block">全场总冠军</span>
<h2 class="text-3xl font-headline font-extrabold text-on-surface mb-2">Sasha K.</h2>
<div class="flex items-center space-x-3">
<span class="text-4xl font-headline font-black bg-gradient-to-r from-primary to-tertiary bg-clip-text text-transparent">8,920</span>
<span class="text-secondary font-medium">战力积分</span>
</div>
<div class="mt-8 w-full h-1 bg-primary/10 rounded-full overflow-hidden">
<div class="h-full aura-kinetic-gradient w-[100%]"></div>
</div>
</div>
<div class="absolute -bottom-12 -right-12 w-64 h-64 bg-primary/5 rounded-full blur-3xl group-hover:bg-primary/10 transition-colors"></div>
</div>
<!-- Rank 2 & 3: Secondary Column -->
<div class="md:col-span-6 flex flex-col gap-6">
<!-- Rank 2 -->
<div class="bg-surface-container-low rounded-3xl p-6 flex items-center justify-between border border-outline-variant/5 hover:bg-surface-container-lowest transition-all group">
<div class="flex items-center space-x-6">
<div class="relative">
<div class="w-16 h-16 rounded-xl overflow-hidden bg-slate-100 p-1 avatar-animate">
<img alt="排名2头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBlz57RALeCPzMF3jpetntebKlmq-HVRHkpGo3RuOIDb6hmVBnD76qluNoR5FcrZvdcbwRxv6YyyWGhor1B0ZHu2Z1uq8VTC0mSi2jvIzdlfeSr-KFB2fsJaid5awfchxeUI958JTpnR_OISkpIg_5o6-5TDUeSY6zxEwKxxKoURf0lQ6UIyCsstQi-96UDDQLqQtd2MzHnCQBzPtAZ25JdZYZBZWm4QD58ne7KdO95bDUIt93NmfKSRWWACN87PG-a0LD6xiOXMM0"/>
</div>
<div class="absolute -top-2 -left-2 w-8 h-8 bg-primary text-on-primary rounded-full flex items-center justify-center font-headline font-bold text-xs shadow-sm">2</div>
</div>
<div>
<h3 class="text-xl font-headline font-bold text-on-surface">陈子昂</h3>
<p class="text-secondary text-sm">稳健先锋</p>
</div>
</div>
<div class="text-right">
<span class="block text-2xl font-headline font-black text-primary">7,450</span>
<span class="text-xs text-secondary font-bold uppercase tracking-widest">积分</span>
</div>
</div>
<!-- Rank 3 -->
<div class="bg-surface-container-low rounded-3xl p-6 flex items-center justify-between border border-outline-variant/5 hover:bg-surface-container-lowest transition-all group">
<div class="flex items-center space-x-6">
<div class="relative">
<div class="w-16 h-16 rounded-xl overflow-hidden bg-slate-100 p-1 avatar-animate">
<img alt="排名3头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCBWPX2uUb8M3wawpmar3VoP3dh4WUACHQr687yqd6BqpnIu_3dTc4Tlq4o-1_6iZ1-4cpUlhBRsfdWZIxI9ROW6SA56EbH2pxfQ8RRh7ISFn3Wrb2CeFGAYFndwoxudtyQPMXBhsccQE8KMWBVJ8zQGJLjw4OPqbRzJ18Rb8Zg8gR-72XpjsycTPe0WN3b0xuuQZin9WExtJ2Acv-6mMx4XqT5N38q6K3G9Sa4AVpQsPsdyBSf70-uZXJbruuJeDdjBXwniI8GS2c"/>
</div>
<div class="absolute -top-2 -left-2 w-8 h-8 bg-secondary-fixed text-secondary rounded-full flex items-center justify-center font-headline font-bold text-xs shadow-sm">3</div>
</div>
<div>
<h3 class="text-xl font-headline font-bold text-on-surface">Elena R.</h3>
<p class="text-secondary text-sm">耐力专家</p>
</div>
</div>
<div class="text-right">
<span class="block text-2xl font-headline font-black text-primary">7,120</span>
<span class="text-xs text-secondary font-bold uppercase tracking-widest">积分</span>
</div>
</div>
</div>
</div>
<!-- Full Ranking Table -->
<div class="max-w-5xl mx-auto mt-16">
<div class="flex flex-col md:flex-row md:items-center justify-between mb-8 gap-4 px-4">
<div class="flex items-center space-x-4">
<h2 class="text-2xl font-headline font-extrabold text-on-surface">排行榜</h2>
<div class="flex p-1 bg-surface-container-low rounded-xl">
<button class="px-4 py-1.5 rounded-lg bg-white shadow-sm text-primary text-sm font-bold transition-all">全球</button>
<button class="px-4 py-1.5 rounded-lg text-secondary text-sm font-medium hover:text-on-surface transition-colors">好友</button>
</div>
</div>
<div class="flex space-x-2 bg-surface-container-low p-1 rounded-xl">
<button class="px-4 py-2 rounded-lg bg-white shadow-sm text-primary text-sm font-bold">周榜</button>
<button class="px-4 py-2 rounded-lg text-secondary text-sm font-medium hover:text-on-surface transition-colors">月榜</button>
<button class="px-4 py-2 rounded-lg text-secondary text-sm font-medium hover:text-on-surface transition-colors">总榜</button>
</div>
</div>
<div class="space-y-1">
<!-- Table Header -->
<div class="grid grid-cols-12 gap-4 px-8 py-4 text-xs font-bold uppercase tracking-widest text-secondary/60">
<div class="col-span-1">名次</div>
<div class="col-span-5 md:col-span-6">运动员</div>
<div class="col-span-3 text-center">训练总数</div>
<div class="col-span-3 text-right">战力积分</div>
</div>
<!-- Ranking Item 4 -->
<div class="grid grid-cols-12 gap-4 items-center px-8 py-5 bg-surface-container-lowest border-b border-surface-container hover:bg-white transition-colors group">
<div class="col-span-1 font-headline font-black text-secondary group-hover:text-primary transition-colors">04</div>
<div class="col-span-5 md:col-span-6 flex items-center space-x-4">
<div class="w-10 h-10 rounded-full overflow-hidden bg-slate-50 border border-slate-100 avatar-animate">
<img alt="排名4头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuC22D6Gp8lGoq0qlquXz8nGAvNzqxk9T2sotKxHoH6_YltGT18LB0s6qEh-gdONijzLjt7exCHmpxEDvGhGbqKLEvzVdBzAtIdhrzcRHIF38ozTG4kXqktD9X4L_ZWzgNXwRynvnz75OV0HcMIk4RspzwEMeAwA_Dli8xF2Bh16_bIriUfn5yMOZF8W7_D3PYliZzT5MBs4OQd9oYPMase2QCUa4uWtcrk7txpN6ExKvMCp5nMHcGv2mrbh2c7s5D7avQS4waXTD5k"/>
</div>
<div>
<span class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors block">Julian Weber</span>
<span class="text-[10px] text-secondary/60 uppercase font-bold">上次训练: 2小时前</span>
</div>
</div>
<div class="col-span-3 text-center font-headline font-medium text-secondary">142 次</div>
<div class="col-span-3 text-right font-headline font-bold text-on-surface">6,840</div>
</div>
<!-- Ranking Item 5 -->
<div class="grid grid-cols-12 gap-4 items-center px-8 py-5 bg-surface-container-lowest border-b border-surface-container hover:bg-white transition-colors group">
<div class="col-span-1 font-headline font-black text-secondary group-hover:text-primary transition-colors">05</div>
<div class="col-span-5 md:col-span-6 flex items-center space-x-4">
<div class="w-10 h-10 rounded-full overflow-hidden bg-slate-50 border border-slate-100 avatar-animate">
<img alt="排名5头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDS09SWC4saU2w-nnX6znhpcpeR9PyROXBFv4Eo7JhnpGcO3lfEdH4hVkSWZ9-CWhVKFul9kMB3cEZMp4dVbgNwNG-VWyz8mQ3E2jkM09VAsi-MfC92gwi2BBF6-qRGLQr8YxdXosaPnJiJypr1QXnapsvI3U7bz7VDQO6Wf7sVcLGFzZKyU0t0SAzQFqqk0Dr3FbY67ZV1PQ7ceKk3H6okcbW-syDidNC7AIzHNRuY_9zWlfcn1-5wa0roHCMyTFLnu-btklasdlw"/>
</div>
<div>
<span class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors block">Mila Sovic</span>
<span class="text-[10px] text-secondary/60 uppercase font-bold">上次训练: 今天</span>
</div>
</div>
<div class="col-span-3 text-center font-headline font-medium text-secondary">118 次</div>
<div class="col-span-3 text-right font-headline font-bold text-on-surface">6,210</div>
</div>
<!-- Ranking Item 6 -->
<div class="grid grid-cols-12 gap-4 items-center px-8 py-5 bg-surface-container-lowest border-b border-surface-container hover:bg-white transition-colors group">
<div class="col-span-1 font-headline font-black text-secondary group-hover:text-primary transition-colors">06</div>
<div class="col-span-5 md:col-span-6 flex items-center space-x-4">
<div class="w-10 h-10 rounded-full overflow-hidden bg-slate-50 border border-slate-100 avatar-animate">
<img alt="排名6头像" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDhLc-O20Nb6zvlwEexGHrODaUsdW5MlsROgIskbhy69O9Fy8kZCeG41uU3ydoIR7lYG1-yJw2e5KbtVj96l_AIaYKOZz2XnMXTuYplGNlj-3Nq4R7E6iVb7gjHkDFVT_TSWTFuJ8PmMvYlNK7pF-_Gj7PnN-_gw5vdRsHuKVcdjWxiDgF4QtXl_w_57uB12TH4ldU2tOFPCzP068eyrMNGM3yEGyjzKJumv5B1Pbh-N9XUp3xSmxQfJAu4qH0yTRzf6cCoje_m7vo"/>
</div>
<div>
<span class="font-headline font-bold text-on-surface group-hover:text-primary transition-colors block">David Okafor</span>
<span class="text-[10px] text-secondary/60 uppercase font-bold">上次训练: 昨天</span>
</div>
</div>
<div class="col-span-3 text-center font-headline font-medium text-secondary">95 次</div>
<div class="col-span-3 text-right font-headline font-bold text-on-surface">5,980</div>
</div>
</div>
<div class="mt-8 text-center">
<button class="text-primary font-headline font-bold text-sm tracking-widest uppercase hover:underline decoration-primary underline-offset-8 transition-all">查看全部 12,408 位运动员</button>
</div>
</div>
</main>
</div>
<!-- BottomNavBar (Shared Component) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 py-3 pb-safe bg-white/70 dark:bg-slate-900/70 backdrop-blur-xl z-50 shadow-[0_-4px_20px_rgba(0,0,0,0.04)]">
<div class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95">
<span class="material-symbols-outlined mb-1">home</span>
<span class="font-medium tracking-wide Inter text-[11px]">Home</span>
</div>
<div class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95">
<span class="material-symbols-outlined mb-1">fitness_center</span>
<span class="font-medium tracking-wide Inter text-[11px]">Hub</span>
</div>
<div class="flex flex-col items-center justify-center bg-cyan-50 dark:bg-cyan-900/30 text-cyan-600 dark:text-cyan-300 rounded-xl px-4 py-1.5 active:scale-95">
<span class="material-symbols-outlined mb-1" style="font-variation-settings: 'FILL' 1;">leaderboard</span>
<span class="font-medium tracking-wide Inter text-[11px]">Ranks</span>
</div>
<div class="flex flex-col items-center justify-center text-slate-400 dark:text-slate-500 px-4 py-1.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-all active:scale-95">
<span class="material-symbols-outlined mb-1">forum</span>
<span class="font-medium tracking-wide Inter text-[11px]">Social</span>
</div>
</nav>
<!-- Footer (Shared Component) -->
<footer class="bg-slate-50 w-full py-16 px-8 border-t border-slate-200">
<div class="grid grid-cols-1 md:grid-cols-4 gap-12 max-w-7xl mx-auto">
<div>
<span class="text-lg font-bold text-slate-900 mb-4 block">轻练 Light Fit</span>
<p class="text-slate-500 font-inter text-sm leading-relaxed">通过精准数据、极简设计与精英社区，重新定义现代健身生活。</p>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6 uppercase tracking-widest text-xs">平台功能</h4>
<ul class="space-y-4">
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">健身训练</a></li>
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">训练计划</a></li>
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">社区互动</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6 uppercase tracking-widest text-xs">相关资源</h4>
<ul class="space-y-4">
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">隐私政策</a></li>
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">服务条款</a></li>
<li><a class="text-slate-500 hover:text-primary transition-colors text-sm" href="#">帮助中心</a></li>
</ul>
</div>
<div>
<h4 class="font-headline font-bold text-slate-900 mb-6 uppercase tracking-widest text-xs">关注我们</h4>
<div class="flex space-x-4">
<button class="w-10 h-10 rounded-full bg-slate-200 flex items-center justify-center text-slate-600 hover:aura-kinetic-gradient hover:text-on-primary transition-all">
<span class="material-symbols-outlined text-sm">share</span>
</button>
<button class="w-10 h-10 rounded-full bg-slate-200 flex items-center justify-center text-slate-600 hover:aura-kinetic-gradient hover:text-on-primary transition-all">
<span class="material-symbols-outlined text-sm">link</span>
</button>
</div>
</div>
</div>
<div class="max-w-7xl mx-auto mt-16 pt-8 border-t border-slate-200 text-center">
<p class="font-inter text-sm text-slate-500 opacity-60">© 2024 轻练 Light Fit. Defined by Ethereal Precision.</p>
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
