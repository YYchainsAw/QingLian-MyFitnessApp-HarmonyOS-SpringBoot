<template>
  <div class="community_api-view bg-surface text-on-surface min-h-screen pt-20">
    <TheNavbar />
    
<!-- TopNavBar -->

<!-- Main Content Grid -->
<main class="pt-24 pb-24 lg:pb-0 min-h-screen max-w-screen-2xl mx-auto px-4 lg:px-8 grid grid-cols-1 lg:grid-cols-12 gap-8">
<!-- Left Column: Community Feed -->
<section class="lg:col-span-7 space-y-8">
<header class="flex items-end justify-between py-4">
<div>
<h1 class="font-headline text-3xl font-bold tracking-tight text-on-surface">社区动态</h1>
<p class="text-secondary text-sm">看看其他运动伙伴们的成就。</p>
</div>
<button class="bg-surface-container-highest text-on-surface px-4 py-2 rounded-xl text-sm font-medium flex items-center gap-2 hover:bg-surface-container-high transition-colors">
<span class="material-symbols-outlined text-sm">filter_list</span> 筛选
                </button>
</header>
<!-- Post 1: Multiple Images & Likes Count -->
<article class="bg-surface-container-lowest rounded-xl p-6 shadow-[0_40px_40px_-15px_rgba(0,0,0,0.04)]">
<div class="flex items-center justify-between mb-6">
<div class="flex items-center gap-3">
<div class="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center overflow-hidden">
<img alt="User avatar" class="w-10 h-10 object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuABZT04qGGzAvsAXT3GXKDgG2FNrXv9bupwiWvW_tC29gPFNfK57qCGJNSVzsO-Mm1VR7C9T1FXZzs6Ts83eTTevxxfG-ZQMwIX-8Zcc0K2-65kSa3yUKe1gCbvwHLvBGuancVzvmT-QxqnBwqDjRpnBxkFYFKSiU3E3Q9BykKCDSewrqepcuYNV-mWDHmhBFoos7mz0ZiHlTSkKbLWIhMVJ8I_ySRS7cCPWCvyDFD4grFM7rKz_DKk27Z7x-ayG0OxCVq5jZUfjAM"/>
</div>
<div>
<div class="flex items-center gap-2">
<h3 class="font-bold text-sm">张欣茹 Sarah</h3>
<button class="text-[10px] font-bold text-primary px-2 py-0.5 border border-primary/30 rounded-full hover:bg-primary/10 transition-colors">+ 关注</button>
</div>
<p class="text-xs text-secondary font-medium">4小时前 • HIIT达人</p>
</div>
</div>
<button class="material-symbols-outlined text-on-surface-variant">more_horiz</button>
</div>
<p class="text-on-surface-variant mb-6 leading-relaxed">今天完成了“腿部轰炸”计划。最后一组保加利亚分腿蹲简直是炼狱，但感觉非常值得。每一天都在变得更强。 #轻练打卡 #坚持不懈</p>
<!-- Multi-image layout (imageUrl support) -->
<div class="grid grid-cols-2 gap-2 rounded-xl overflow-hidden mb-6 aspect-[16/10]">
<img alt="Workout 1" class="w-full h-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDhl-dHeLZwAjcU4iTQrVf1nM2TST-Nzk6eJiIZYhTYWJ8vSS6tcwVOGljaryIUmHEDgVCtlSVOqiR6IbcvOtRp1-0LACbt_pfHNU9d_EF1ueDWfpXC_w5yqKPT2DmArBh-iDskbL1n0oAQ-4C4fKsjdHyGqBd1-izWq_x-vdHkptolvUuOpb_A6umbAxe1Lbk0wKJ1xhMm6v6BoxOjl23GWmWAbOQ3K_mOz5qDJXc3W8n0e1c57aoWIjS4T0FuS2u7yaVt9Tne9BA"/>
<img alt="Workout 2" class="w-full h-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCHmZ8FmR_E0T_nJjQfXfXzP_wLzH9k5R1C3Yk8O7Z8E9H1B3D2C1A0z9y8x7w6v5u4t3s2r1q0"/>
</div>
<div class="flex items-center gap-6">
<button class="flex items-center gap-2 text-primary font-bold text-sm">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">favorite</span>
<span>124</span> <!-- likesCount -->
</button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">chat_bubble</span> 18
                    </button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium ml-auto">
<span class="material-symbols-outlined">share</span>
</button>
</div>
</article>
<!-- Post 2: Support for Add Friend -->
<article class="bg-surface-container-lowest rounded-xl p-6 shadow-[0_40px_40px_-15px_rgba(0,0,0,0.04)]">
<div class="flex items-center justify-between mb-6">
<div class="flex items-center gap-3">
<div class="w-12 h-12 rounded-full bg-amber-100 flex items-center justify-center overflow-hidden">
<img alt="User avatar" class="w-10 h-10 object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuC7xSM4rTllqdv1ySxDB69dTha418KF9_TV4Z0XAp1gagUmR8ES_KfamM2o5AZWG8gLGfQbLRiX9m677KP2tgNIo3bHuEZrxXVahWwaUPmpRkbgxa6mhFlRdv0JYUv3oEOQE62sOlZrZizfeIApjUfLDrV1MWbWuraS2z3tRrcdz-0wEqApnyO7qlwH_7kBqC-fotCglkCNCvcT3UIkNx_QY578_bZNxX9U5NN4BpCDoBTreHkFVapGbTxoMqQRowogDt7BLu0C2HU"/>
</div>
<div>
<div class="flex items-center gap-2">
<h3 class="font-bold text-sm">陈大卫 David</h3>
<button class="text-[10px] font-bold text-slate-500 bg-slate-100 px-2 py-0.5 rounded-full hover:bg-slate-200 transition-colors flex items-center gap-1">
<span class="material-symbols-outlined text-[12px]">person_add</span>加好友
                                </button>
</div>
<p class="text-xs text-secondary font-medium">昨天 • 马拉松跑者</p>
</div>
</div>
</div>
<div class="bg-surface-container-low rounded-xl p-5 border border-outline-variant/10 flex flex-col md:flex-row items-center gap-6 mb-4">
<div class="w-20 h-20 rounded-2xl bg-gradient-to-br from-primary to-primary-fixed flex items-center justify-center text-white">
<span class="material-symbols-outlined text-4xl" style="font-variation-settings: 'FILL' 1;">fitness_center</span>
</div>
<div class="flex-1 text-center md:text-left">
<span class="text-[10px] font-bold text-primary uppercase tracking-widest">训练打卡</span>
<h4 class="font-headline font-bold text-lg leading-tight">核心力量与稳定性进阶</h4>
<p class="text-sm text-secondary">时长: 45分钟 • 强度: 高</p>
</div>
<div class="flex flex-col items-center px-6 py-2 border-l border-outline-variant/20">
<span class="text-2xl font-black text-on-surface">324</span>
<span class="text-[10px] text-secondary font-bold uppercase tracking-tighter">千卡消耗</span>
</div>
</div>
<div class="flex items-center gap-6">
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">favorite</span>
<span>89</span> <!-- likesCount -->
</button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">chat_bubble</span> 4
                    </button>
</div>
</article>
</section>
<!-- Right Column: Dual-Pane Chat Interface (FriendListVO style) -->
<section class="lg:col-span-5 hidden lg:flex flex-col h-[calc(100vh-8rem)] sticky top-24 bg-slate-50 border-r border-slate-100 rounded-3xl overflow-hidden shadow-xl">
<div class="flex h-full">
<!-- Chat List (Left Pane) -->
<aside class="w-24 xl:w-80 border-r border-slate-100 flex flex-col bg-slate-50">
<div class="p-6">
<div class="flex items-center justify-between mb-2">
<h2 class="font-headline font-bold text-lg hidden xl:block">私信消息</h2>
<span class="material-symbols-outlined text-primary text-xl hidden xl:block">edit_square</span>
</div>
<div class="mt-4 xl:bg-white rounded-xl flex items-center px-3 py-2">
<span class="material-symbols-outlined text-secondary text-sm">search</span>
<input class="bg-transparent border-none text-xs focus:ring-0 w-full hidden xl:block" placeholder="搜索联系人..." type="text"/>
</div>
</div>
<div class="flex-1 overflow-y-auto hide-scrollbar space-y-1">
<!-- Chat Item Active (FriendListVO) -->
<div class="px-4 xl:px-6 py-4 bg-white font-bold rounded-lg flex items-center gap-4 cursor-pointer relative mx-2">
<div class="absolute left-0 top-1/2 -translate-y-1/2 w-1 h-8 bg-cyan-500 rounded-r-full"></div>
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full bg-emerald-50" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDRLK6n0eg89Rdb4_aEphrOq2MSeEthKTLp9wyVMCYgMVAoi3Ypbls9lFnQ9UgO4MOQcDjO3CNS6raXDQ0GMKq9EZE-pYNoaOToWQMfP8aeISKz18cK8yjP0_b5b2Zs46Buoi8hU8oOvkuOxx1YAcn1NbY6njXfvnG8HazQ8UInsQyRcgG4-52Jvhix5XKb90hYLkl4mPWOk4HV2HHz01UJg5kQombTBGW3HLw2CMYeujOZzOzuX73hlfa31gTnPqJ7MgMUxbaVKRI"/>
<div class="absolute bottom-0 right-0 w-3 h-3 bg-primary border-2 border-white rounded-full"></div>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate text-cyan-600">王马克 Marcus</span>
<span class="text-[10px] text-secondary">下午 2:45</span>
</div>
<div class="flex justify-between items-center">
<p class="text-xs text-primary font-medium truncate">发送了一张训练卡片</p> <!-- lastMessage -->
<span class="bg-primary text-[#001a41] text-[9px] px-1.5 py-0.5 rounded-full font-bold">2</span> <!-- unreadCount -->
</div>
</div>
</div>
<!-- Chat Item 2 -->
<div class="px-4 xl:px-6 py-4 hover:bg-cyan-50 transition-all flex items-center gap-4 cursor-pointer mx-2 rounded-lg">
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full bg-orange-50" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDND58PBONtfA6__EmMdH1xOX_RIT_Go4ZOO6bxElTRfLzbSVE0SKucjc01ulCKpcyY91DeMqfd8yO0q8OPZsjyyjJqWipIU36NUETu88-qTU9qcbQbMAmBmOGi4wCVbzKUj_PaFDFaG01ZMCDSkRRSZTZcAmsRBX_QBy8s8WCgXXNQZuoxyn7V_oUVwunJIAF8qpibcpywTR7n5J0u534RjIbTw4Ofw5w27r3Ipc-bPKBUdaVgEmn1aKwrBpRuKxPe7y-p7QRkyak"/>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate text-slate-900">精英跑者小组</span>
<span class="text-[10px] text-secondary">上午 11:20</span>
</div>
<p class="text-xs text-slate-500 truncate">张欣茹: 明天去跑个10公里吧！</p>
</div>
</div>
<!-- Chat Item 3 -->
<div class="px-4 xl:px-6 py-4 hover:bg-cyan-50 transition-all flex items-center gap-4 cursor-pointer mx-2 rounded-lg">
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full bg-purple-50" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBvjjdAbH_0ZOkaUa7eHBaLa05qx7FNeUWN49fmB-F1wR5F7uBlcBAQiq2OZpFsNvIPWOIMzYPMzHXYlumx9-_TV6flUqHpInlTe8tYZUAYuom6JN4TjsN2m4dVKDn3xsR8WA8Gt8S5LY33l4IfhEfaFf_LeYv067pQNlvqeUoQx46D0kAsijQUxP3QZu2-b2mXxK1-DZAcULR3jYtatt1acm8S_7v9ag_jUcGd26Q_OO0Jo5wo6HD-ZrLvUvmi7wvYjzL2PfZ4OoE"/>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate text-slate-900">李晓燕 Elena</span>
<span class="text-[10px] text-secondary">周二</span>
</div>
<p class="text-xs text-slate-500 truncate">这份营养指南真的很有帮助。</p>
</div>
</div>
</div>
</aside>
<!-- Active Conversation (Right Pane) -->
<div class="flex-1 flex flex-col bg-white">
<header class="px-8 h-20 flex items-center justify-between border-b border-slate-100">
<div class="flex items-center gap-3">
<img alt="Active Chat Avatar" class="w-10 h-10 rounded-full bg-emerald-50" src="https://lh3.googleusercontent.com/aida-public/AB6AXuD6Z9Y6NBsn-K7fr8ZdD2gJg63N8A9kOqUXmJltZRHMBfOC2b8-RnosoLOXqBLX9w1BY_TpEKpbgKZ_mqZ2QyV7iwH-sUpiu2gbthwMJK-OGOIY4Fr8uyJlrcb55fKMAzeotFgcDzBJBamp4GQfwKLnpMBw6JaflnvGVJMitYBiL0UHQXa8pHNE1udR8Jx8Iaujxf1RjbVwOrZbLfeGHTbuur2sK4QOKciFfrXODjYNnKUXNwYTcbm90lQl5A3W0Xdk_Qwrg0f8vGE"/>
<div>
<h3 class="font-bold text-sm">王马克 Marcus</h3>
<span class="text-[10px] text-cyan-500 font-bold flex items-center gap-1 uppercase tracking-widest"><span class="w-1.5 h-1.5 rounded-full bg-cyan-500"></span> 正在线上</span>
</div>
</div>
<div class="flex items-center gap-2">
<button class="material-symbols-outlined text-secondary hover:text-cyan-400 p-2 transition-colors">call</button>
<button class="material-symbols-outlined text-secondary hover:text-cyan-400 p-2 transition-colors">videocam</button>
<button class="material-symbols-outlined text-secondary hover:text-cyan-400 p-2 transition-colors">info</button>
</div>
</header>
<div class="flex-1 overflow-y-auto p-8 space-y-6 hide-scrollbar">
<!-- Date Separator -->
<div class="flex justify-center">
<span class="px-3 py-1 bg-surface-container/50 text-[10px] font-bold text-secondary rounded-full uppercase tracking-widest">今天</span>
</div>
<!-- Message Inbound -->
<div class="flex items-end gap-3 max-w-[80%]">
<div class="bg-surface-container-low p-4 rounded-2xl rounded-bl-none text-sm leading-relaxed text-on-surface-variant">
                                嘿！早上的瑜伽课感觉怎么样？能跟上节奏吗？
                            </div>
</div>
<!-- Message Outbound -->
<div class="flex items-end gap-3 max-w-[80%] ml-auto flex-row-reverse">
<div class="bg-primary text-[#001a41] p-4 rounded-2xl rounded-br-none text-sm leading-relaxed shadow-lg shadow-primary/20 font-medium">
                                感觉太棒了！终于能坚持10秒乌鸦式了。刚打了卡！
                            </div>
</div>
<!-- Workout Check-in Card (Inbound) -->
<div class="flex items-end gap-3 max-w-[80%]">
<div class="bg-white border border-outline-variant/30 p-4 rounded-2xl rounded-bl-none shadow-sm">
<div class="flex items-center gap-4 mb-3">
<div class="w-12 h-12 rounded-xl bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">task_alt</span>
</div>
<div>
<span class="text-[10px] font-bold text-primary uppercase">打卡 Check-in</span>
<h5 class="font-bold text-sm">晨间冥想瑜伽</h5>
</div>
</div>
<div class="grid grid-cols-2 gap-2 text-[10px] font-bold text-secondary uppercase tracking-tighter">
<div class="bg-surface-container-low px-2 py-1.5 rounded-lg flex justify-between">
<span>时长</span>
<span class="text-on-surface">30分</span>
</div>
<div class="bg-surface-container-low px-2 py-1.5 rounded-lg flex justify-between">
<span>消耗</span>
<span class="text-on-surface">120</span>
</div>
</div>
<button class="w-full mt-3 py-2 bg-primary/10 text-primary text-[10px] font-bold rounded-lg hover:bg-primary/20 transition-colors uppercase">查看详情</button>
</div>
</div>
</div>
<!-- Chat Input -->
<footer class="p-6 border-t border-slate-100 bg-white/80 backdrop-blur-md">
<div class="bg-surface-container-low/50 rounded-2xl flex items-center px-4 py-3 gap-3">
<button class="material-symbols-outlined text-secondary hover:text-cyan-400">add_circle</button>
<input class="flex-1 bg-transparent border-none focus:ring-0 text-sm" placeholder="输入消息..." type="text"/>
<button class="material-symbols-outlined text-secondary hover:text-cyan-400">mood</button>
<button class="bg-primary text-[#001a41] p-2 rounded-xl flex items-center justify-center shadow-lg shadow-primary/30 active:scale-90 transition-all">
<span class="material-symbols-outlined">send</span>
</button>
</div>
</footer>
</div>
</div>
</section>
</main>
<!-- BottomNavBar (Mobile Only) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full flex justify-around items-center px-4 py-3 pb-safe bg-white/70 backdrop-blur-xl z-50 shadow-[0_-4px_20px_rgba(0,0,0,0.04)]">
<button class="flex flex-col items-center justify-center text-slate-400 px-4 py-1.5 hover:bg-slate-50 transition-all active:scale-95">
<span class="material-symbols-outlined">home</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Home</span>
</button>
<button class="flex flex-col items-center justify-center text-slate-400 px-4 py-1.5 hover:bg-slate-50 transition-all active:scale-95">
<span class="material-symbols-outlined">fitness_center</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Hub</span>
</button>
<button class="flex flex-col items-center justify-center text-slate-400 px-4 py-1.5 hover:bg-slate-50 transition-all active:scale-95">
<span class="material-symbols-outlined">leaderboard</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Ranks</span>
</button>
<button class="flex flex-col items-center justify-center bg-cyan-50 text-cyan-600 rounded-xl px-4 py-1.5 active:scale-95 transition-all">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">forum</span>
<span class="text-[11px] font-medium tracking-wide Inter mt-1">Social</span>
</button>
</nav>
<!-- Footer -->
<footer class="bg-slate-50 w-full py-16 px-8 border-t border-slate-200 mt-12 mb-20 lg:mb-0">
<div class="grid grid-cols-1 md:grid-cols-4 gap-12 max-w-7xl mx-auto">
<div class="md:col-span-1">
<span class="text-lg font-bold text-slate-900 mb-4 block">轻练 Light Fit</span>
<p class="text-slate-500 text-sm leading-relaxed">定义空灵之美。打造极简健身体验的未来。</p>
</div>
<div class="grid grid-cols-2 gap-8 md:col-span-3">
<div class="space-y-4">
<h4 class="font-bold text-sm uppercase tracking-wider text-slate-900">平台</h4>
<ul class="space-y-2">
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">训练计划</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">营养中心</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">动态社区</a></li>
</ul>
</div>
<div class="space-y-4">
<h4 class="font-bold text-sm uppercase tracking-wider text-slate-900">法律</h4>
<ul class="space-y-2">
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">隐私政策</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">服务条款</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-400 transition-colors" href="#">支持中心</a></li>
</ul>
</div>
</div>
</div>
<div class="max-w-7xl mx-auto mt-12 pt-8 border-t border-slate-200">
<p class="text-center font-inter text-sm text-slate-400">© 2024 轻练 Light Fit. 定义空灵之美。</p>
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
