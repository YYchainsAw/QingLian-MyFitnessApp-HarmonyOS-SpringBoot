<template>
  <div class="community_chat-view">
    
<!-- TopNavBar -->
<nav class="fixed top-0 w-full z-50 bg-surface/70 backdrop-blur-md">
<div class="flex justify-between items-center px-8 h-20 w-full max-w-screen-2xl mx-auto">
<div class="text-2xl font-bold tracking-tighter text-slate-900">轻练</div>
<div class="hidden md:flex items-center gap-8 font-headline tracking-tight font-medium">
<router-link class="text-slate-600 hover:text-slate-900 transition-colors" to="/training_hub">Workouts</router-link>
<router-link class="text-slate-600 hover:text-slate-900 transition-colors" to="/landing_page">Programs</router-link>
<router-link class="text-slate-600 hover:text-slate-900 transition-colors" to="/leaderboard">Nutrition</router-link>
<router-link class="text-cyan-600 font-bold border-b-2 border-cyan-500 pb-1" to="/community_chat">Community</router-link>
</div>
<div class="flex items-center gap-4">
<button class="material-symbols-outlined text-on-surface-variant hover:bg-slate-100/50 p-2 rounded-full transition-all">notifications</button>
<button class="material-symbols-outlined text-on-surface-variant hover:bg-slate-100/50 p-2 rounded-full transition-all">account_circle</button>
<button class="hidden lg:block bg-gradient-to-br from-primary to-primary-container text-on-primary px-6 py-2.5 rounded-xl font-headline font-bold text-sm shadow-sm active:scale-95 transition-all">Go Premium</button>
</div>
</div>
</nav>
<!-- Main Content Grid -->
<main class="pt-24 pb-24 lg:pb-0 min-h-screen max-w-screen-2xl mx-auto px-4 lg:px-8 grid grid-cols-1 lg:grid-cols-12 gap-8">
<!-- Left Column: Community Feed -->
<section class="lg:col-span-7 space-y-8">
<header class="flex items-end justify-between py-4">
<div>
<h1 class="font-headline text-3xl font-bold tracking-tight text-on-surface">Community Feed</h1>
<p class="text-secondary text-sm">See what your fellow athletes are achieving.</p>
</div>
<button class="bg-surface-container-highest text-on-surface px-4 py-2 rounded-xl text-sm font-medium flex items-center gap-2 hover:bg-surface-container-high transition-colors">
<span class="material-symbols-outlined text-sm">filter_list</span> Filter
                </button>
</header>
<!-- Post 1: High Quality Fitness Photo -->
<article class="bg-surface-container-lowest rounded-xl p-6 shadow-[0_40px_40px_-15px_rgba(0,0,0,0.04)]">
<div class="flex items-center justify-between mb-6">
<div class="flex items-center gap-3">
<div class="w-12 h-12 rounded-full bg-slate-200 overflow-hidden">
<img alt="User avatar" data-alt="Close-up portrait of a fitness enthusiast woman with focused expression, soft natural lighting in a modern gym setting" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBIlk0KqdVtMGLd6QzgHBOwJH5yh5-QBzSe8Asuj-RtstpT_9TWwZyc1EEx36onECkWxVDC3kdgQ7zMeUnuTbj9-yssD2TV2QVOZdTmwq_lD4DSNDFHLZbDyDZThnVzhF9jpL_ISTPA85cbT8MbONKY9hMdcEKljCC-OfMGVuePfHxb8hWYsUkdRBldnu0EJc2UVY83ftwAjYA4SYlITlaCmf1ANg3f-v7iiBH1ouxgGpcOseodS3iFa471xSa2SI5KgFMvpgv6jKE"/>
</div>
<div>
<h3 class="font-bold text-sm">Sarah Jenkins</h3>
<p class="text-xs text-secondary font-medium">4 hours ago • HIIT Warrior</p>
</div>
</div>
<button class="material-symbols-outlined text-on-surface-variant">more_horiz</button>
</div>
<p class="text-on-surface-variant mb-6 leading-relaxed">Pushed through the "Leg Day" program today. That final set of Bulgarian Split Squats was brutal but worth it. Feeling stronger every day. #LightFit #Consistency</p>
<div class="rounded-xl overflow-hidden mb-6 aspect-[16/10]">
<img alt="Post image" class="w-full h-full object-cover" data-alt="Atmospheric shot of a modern minimalist gym space with sun rays hitting a row of kettlebells and yoga mats on dark floor" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDhl-dHeLZwAjcU4iTQrVf1nM2TST-Nzk6eJiIZYhTYWJ8vSS6tcwVOGljaryIUmHEDgVCtlSVOqiR6IbcvOtRp1-0LACbt_pfHNU9d_EF1ueDWfpXC_w5yqKPT2DmArBh-iDskbL1n0oAQ-4C4fKsjdHyGqBd1-izWq_x-vdHkptolvUuOpb_A6umbAxe1Lbk0wKJ1xhMm6v6BoxOjl23GWmWAbOQ3K_mOz5qDJXc3W8n0e1c57aoWIjS4T0FuS2u7yaVt9Tne9BA"/>
</div>
<div class="flex items-center gap-6">
<button class="flex items-center gap-2 text-primary font-bold text-sm">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">favorite</span> 124
                    </button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">chat_bubble</span> 18
                    </button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium ml-auto">
<span class="material-symbols-outlined">share</span>
</button>
</div>
</article>
<!-- Post 2: Workout Check-in Card in Feed -->
<article class="bg-surface-container-lowest rounded-xl p-6 shadow-[0_40px_40px_-15px_rgba(0,0,0,0.04)]">
<div class="flex items-center gap-3 mb-6">
<div class="w-12 h-12 rounded-full bg-slate-200 overflow-hidden">
<img alt="User avatar" data-alt="Portrait of a smiling athletic man in his 30s, wearing high-performance activewear, urban fitness background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAHU8C-9cUfXuUbGbECfriL9hX0e0PF0vnIltOKYfWsRSvkq8SIDrVjL9LtIWSev6lxBwkSMGDpLaHsBDIQl-_iBn6F6MY-xktoX8bpgVinS23roAXgi-oP1-BBrNP-meJa5tD5Siq8S9zzx6e_iT-c7joxLCEtlv-ycoqZh4ZwQm2WwuIrV3F0XxvJhxbc4Iikm1f-eS5LfxBWGqouQYYXiQD_8NISm-L1inUKbPONX-QwK4w5yWVt8BQyURPFgpMEbT_7I3RvgSI"/>
</div>
<div>
<h3 class="font-bold text-sm">David Chen</h3>
<p class="text-xs text-secondary font-medium">Yesterday • Marathon Runner</p>
</div>
</div>
<div class="bg-surface-container-low rounded-xl p-5 border border-outline-variant/10 flex flex-col md:flex-row items-center gap-6 mb-4">
<div class="w-20 h-20 rounded-2xl bg-gradient-to-br from-primary to-tertiary-container flex items-center justify-center text-white">
<span class="material-symbols-outlined text-4xl" style="font-variation-settings: 'FILL' 1;">fitness_center</span>
</div>
<div class="flex-1 text-center md:text-left">
<span class="text-[10px] font-bold text-primary uppercase tracking-widest">Workout Check-in</span>
<h4 class="font-headline font-bold text-lg leading-tight">Advanced Core &amp; Stability</h4>
<p class="text-sm text-secondary">Duration: 45m • Intensity: High</p>
</div>
<div class="flex flex-col items-center px-6 py-2 border-l border-outline-variant/20">
<span class="text-2xl font-black text-on-surface">324</span>
<span class="text-[10px] text-secondary font-bold uppercase tracking-tighter">Kcal Burn</span>
</div>
</div>
<div class="flex items-center gap-6">
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">favorite</span> 89
                    </button>
<button class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors text-sm font-medium">
<span class="material-symbols-outlined">chat_bubble</span> 4
                    </button>
</div>
</article>
</section>
<!-- Right Column: Dual-Pane Chat Interface -->
<section class="lg:col-span-5 hidden lg:flex flex-col h-[calc(100vh-8rem)] sticky top-24 bg-surface-container-low rounded-3xl overflow-hidden border border-outline-variant/10 shadow-xl">
<div class="flex h-full">
<!-- Chat List (Left Pane) -->
<aside class="w-24 xl:w-80 border-r border-outline-variant/10 flex flex-col bg-surface-container-lowest/50">
<div class="p-6">
<h2 class="font-headline font-bold text-lg hidden xl:block">Messages</h2>
<div class="mt-4 xl:bg-surface-container rounded-xl flex items-center px-3 py-2">
<span class="material-symbols-outlined text-secondary text-sm">search</span>
<input class="bg-transparent border-none text-xs focus:ring-0 w-full hidden xl:block" placeholder="Search..." type="text"/>
</div>
</div>
<div class="flex-1 overflow-y-auto hide-scrollbar">
<!-- Chat Item Active -->
<div class="px-4 xl:px-6 py-4 bg-white shadow-sm flex items-center gap-4 cursor-pointer relative">
<div class="absolute left-0 top-1/2 -translate-y-1/2 w-1 h-8 bg-primary rounded-r-full"></div>
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full" data-alt="Close-up portrait of a female fitness coach with a kind smile and athletic build" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCLpxWdCyRzvJ_0D4vSryT5LlAppBJrJUOPH-i_gaHleec-tPnOolvL8-zjke_LQVgNiQe8l_hPcS5ZY2bpPOzYjavGDmzkrzC9PsIWTq05QSK77ZD_M3h-8S9OG7M-kqdpo-yfs5GhYogcPqv0ZPO8h4SLjE5sah3GXSh58u9SShJFUs_habizr0X9Xr511A03VVjJYBOWjsEvZ7ZtKJSBqU5slMt-2Z81NOI_w8ytGnhpvhrxADxuJ9GZxfQZ7f3Qbf7qwg0t-Bs"/>
<div class="absolute bottom-0 right-0 w-3 h-3 bg-green-500 border-2 border-white rounded-full"></div>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate">Marcus Wright</span>
<span class="text-[10px] text-secondary">2:45 PM</span>
</div>
<p class="text-xs text-primary font-medium truncate">Sent a workout card</p>
</div>
</div>
<!-- Chat Items -->
<div class="px-4 xl:px-6 py-4 hover:bg-white/50 flex items-center gap-4 cursor-pointer transition-colors">
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full" data-alt="Portrait of a male fitness instructor looking professional and welcoming, clean studio background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuCouaEwr4gnLQDehCdTX0FGkF4Gf4EKkxilzmv4SQvc7fe7XzZgdZer7WK7CKYnzgIdEVK1rNBNby10HQpBG7lf_ep1edPIWKwJFDm7uK2iFykXvLkkhUtR7cgnGZGwpmxfCYdB_G5b3_nocUTtGQGQ5HBXHDvARqfvrhsNraW733gvBG-pRj_fXUICwF3xLgJQF6-3rTLtPHdicZeVNvyV1zpi1oFkrYZRnGfxpt1iETtTJEvbwIiD45U2bohy7d0clQffTvZ0L1c"/>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate">Elite Runners Group</span>
<span class="text-[10px] text-secondary">11:20 AM</span>
</div>
<p class="text-xs text-secondary truncate">Sarah: Let's do 10k tomorrow!</p>
</div>
</div>
<div class="px-4 xl:px-6 py-4 hover:bg-white/50 flex items-center gap-4 cursor-pointer transition-colors">
<div class="relative min-w-[48px]">
<img alt="Avatar" class="w-12 h-12 rounded-full" data-alt="Young woman with headphones, mid-workout, urban aesthetic" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAL1aKIQ98SbEzav88j-nfXdhWJ8xhnWeZtbtHUkDo2i7fHAdBKNazj0i9Te6G_ReW2xWVLXEU6Lgb-KCw_HydI0yIoli4F9Z5JPeDB8s7k47cKG8blEsZp_9oHM9b6F8R3WfXqG6QaNOeKq9wkIjk_br4QridCZObREi3zPvJhEgFl4AJBdXTwVA34rkRCif1slM67oC_I_2Sbe4DTtRU6K2YyPzmzubYildY4h-kTrYfDfvdfbO2jpF8HXI-JrVWD0ldQkZ-5z9M"/>
</div>
<div class="hidden xl:block flex-1 min-w-0">
<div class="flex justify-between items-baseline">
<span class="font-bold text-sm truncate">Elena Ray</span>
<span class="text-[10px] text-secondary">Tue</span>
</div>
<p class="text-xs text-secondary truncate">The nutrition guide was great.</p>
</div>
</div>
</div>
</aside>
<!-- Active Conversation (Right Pane) -->
<div class="flex-1 flex flex-col bg-white">
<header class="px-8 h-20 flex items-center justify-between border-b border-outline-variant/10">
<div class="flex items-center gap-3">
<img alt="Active Chat Avatar" class="w-10 h-10 rounded-full" data-alt="Portrait of Marcus Wright, athletic male coach" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDz9bSq-eAp7lr1iVGzw6pP1ugxYdWgmbUf2PsfyK29TKoW9maNlEapO9Ox3lc6HlzfvV1HgWm0_ABfEsFDNVRWmVBgKqRQK0D_tbIr07zAdXSCmyE8VxEh5tmOAy-KsPalalw2QUPnd5ezcqB8nIQ7C_8rZ3wjXYIVwkeffU4JjqGnz8e4-g9e67pLUXu7s5A1phLHwuD3unVQejXfJgwi8eY-pFEBxaALsieTo3jreKz3HjK8xRqfq0nUTkKDWs2xTmUXgIlWwSw"/>
<div>
<h3 class="font-bold text-sm">Marcus Wright</h3>
<span class="text-[10px] text-tertiary font-bold flex items-center gap-1 uppercase tracking-widest"><span class="w-1.5 h-1.5 rounded-full bg-tertiary"></span> Active Now</span>
</div>
</div>
<div class="flex items-center gap-2">
<button class="material-symbols-outlined text-secondary hover:text-primary p-2 transition-colors">call</button>
<button class="material-symbols-outlined text-secondary hover:text-primary p-2 transition-colors">videocam</button>
<button class="material-symbols-outlined text-secondary hover:text-primary p-2 transition-colors">info</button>
</div>
</header>
<div class="flex-1 overflow-y-auto p-8 space-y-6 hide-scrollbar">
<!-- Date Separator -->
<div class="flex justify-center">
<span class="px-3 py-1 bg-surface-container text-[10px] font-bold text-secondary rounded-full uppercase tracking-widest">Today</span>
</div>
<!-- Message Inbound -->
<div class="flex items-end gap-3 max-w-[80%]">
<div class="bg-surface-container-low p-4 rounded-2xl rounded-bl-none text-sm leading-relaxed text-on-surface-variant">
                                Hey! How was the morning yoga session? Did you manage to follow the flow?
                            </div>
</div>
<!-- Message Outbound -->
<div class="flex items-end gap-3 max-w-[80%] ml-auto flex-row-reverse">
<div class="bg-primary text-on-primary p-4 rounded-2xl rounded-br-none text-sm leading-relaxed shadow-lg shadow-primary/10">
                                It was great! Managed to hold the crow pose for 10 seconds finally. Just checked in my workout!
                            </div>
</div>
<!-- Workout Check-in Card (Inbound) -->
<div class="flex items-end gap-3 max-w-[80%]">
<div class="bg-surface-container-lowest border border-outline-variant/30 p-4 rounded-2xl rounded-bl-none shadow-sm">
<div class="flex items-center gap-4 mb-3">
<div class="w-12 h-12 rounded-xl bg-primary-container/30 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">task_alt</span>
</div>
<div>
<span class="text-[10px] font-bold text-primary uppercase">打卡 Check-in</span>
<h5 class="font-bold text-sm">Morning Zen Yoga</h5>
</div>
</div>
<div class="grid grid-cols-2 gap-2 text-[10px] font-bold text-secondary uppercase tracking-tighter">
<div class="bg-surface-container-low px-2 py-1.5 rounded-lg flex justify-between">
<span>Time</span>
<span class="text-on-surface">30m</span>
</div>
<div class="bg-surface-container-low px-2 py-1.5 rounded-lg flex justify-between">
<span>Cal</span>
<span class="text-on-surface">120</span>
</div>
</div>
<button class="w-full mt-3 py-2 bg-primary/5 text-primary text-[10px] font-bold rounded-lg hover:bg-primary/10 transition-colors uppercase">View Details</button>
</div>
</div>
<!-- Message Inbound Typing -->
<div class="flex items-center gap-1.5">
<div class="w-2 h-2 rounded-full bg-slate-300"></div>
<div class="w-2 h-2 rounded-full bg-slate-300"></div>
<div class="w-2 h-2 rounded-full bg-slate-300"></div>
</div>
</div>
<!-- Chat Input -->
<footer class="p-6 border-t border-outline-variant/10 bg-white">
<div class="bg-surface-container-low rounded-2xl flex items-center px-4 py-3 gap-3">
<button class="material-symbols-outlined text-secondary hover:text-primary">add_circle</button>
<input class="flex-1 bg-transparent border-none focus:ring-0 text-sm" placeholder="Type a message..." type="text"/>
<button class="material-symbols-outlined text-secondary hover:text-primary">mood</button>
<button class="bg-primary text-on-primary p-2 rounded-xl flex items-center justify-center shadow-lg shadow-primary/20 active:scale-90 transition-all">
<span class="material-symbols-outlined">send</span>
</button>
</div>
</footer>
</div>
</div>
</section>
</main>
<!-- BottomNavBar (Mobile Only) -->
<nav class="lg:hidden fixed bottom-0 left-0 w-full bg-white/80 backdrop-blur-xl flex justify-around items-center px-4 pt-3 pb-8 z-50 rounded-t-3xl border-t border-white/20 shadow-[0_-8px_30px_rgb(0,0,0,0.04)]">
<button class="flex flex-col items-center justify-center text-slate-400 px-6 py-2 hover:scale-110 transition-transform">
<span class="material-symbols-outlined">home</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">Home</span>
</button>
<button class="flex flex-col items-center justify-center text-slate-400 px-6 py-2 hover:scale-110 transition-transform">
<span class="material-symbols-outlined">search</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">Explore</span>
</button>
<button class="flex flex-col items-center justify-center bg-cyan-50 text-cyan-600 rounded-2xl px-6 py-2 hover:scale-110 transition-transform">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">leaderboard</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">Activity</span>
</button>
<button class="flex flex-col items-center justify-center text-slate-400 px-6 py-2 hover:scale-110 transition-transform">
<span class="material-symbols-outlined">person</span>
<span class="font-manrope text-[10px] uppercase tracking-widest font-bold mt-1">Profile</span>
</button>
</nav>
<!-- Footer -->
<footer class="bg-slate-50 w-full py-16 px-8 border-t border-slate-200 mt-12">
<div class="grid grid-cols-1 md:grid-cols-4 gap-12 max-w-7xl mx-auto">
<div class="md:col-span-1">
<span class="text-lg font-bold text-slate-900 mb-4 block">轻练 Light Fit</span>
<p class="text-slate-500 text-sm leading-relaxed">Defined by Ethereal Precision. Crafting the future of minimalist fitness experiences.</p>
</div>
<div class="grid grid-cols-2 gap-8 md:col-span-3">
<div class="space-y-4">
<h4 class="font-bold text-sm uppercase tracking-wider text-slate-900">Platform</h4>
<ul class="space-y-2">
<li><router-link class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" to="/landing_page">Programs</router-link></li>
<li><router-link class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" to="/leaderboard">Nutrition</router-link></li>
<li><router-link class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" to="/community_chat">Community</router-link></li>
</ul>
</div>
<div class="space-y-4">
<h4 class="font-bold text-sm uppercase tracking-wider text-slate-900">Legal</h4>
<ul class="space-y-2">
<li><a class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" href="#">Privacy Policy</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" href="#">Terms of Service</a></li>
<li><a class="text-sm text-slate-500 hover:text-cyan-600 transition-colors" href="#">Support Center</a></li>
</ul>
</div>
</div>
</div>
<div class="max-w-7xl mx-auto mt-12 pt-8 border-t border-slate-200">
<p class="text-center font-inter text-sm text-slate-400">© 2024 轻练 Light Fit. Defined by Ethereal Precision.</p>
</div>
</footer>

  </div>
</template>

<script setup>
</script>

<style scoped>
</style>
