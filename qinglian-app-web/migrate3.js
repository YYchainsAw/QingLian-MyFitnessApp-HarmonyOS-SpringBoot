import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const sourceDir = "C:/Users/YYchainsaw/Downloads/stitch_/stitch_";
const viewsDir = path.join(__dirname, "src/views");
const componentsDir = path.join(__dirname, "src/components/layout");

if (!fs.existsSync(viewsDir)) fs.mkdirSync(viewsDir, { recursive: true });
if (!fs.existsSync(componentsDir)) fs.mkdirSync(componentsDir, { recursive: true });

const folders = fs.readdirSync(sourceDir).filter(f => {
    try {
        return fs.statSync(path.join(sourceDir, f)).isDirectory();
    } catch(e) { return false; }
});

let routes = [];
let navbarExtracted = false;
let footerExtracted = false;

const extractTag = (html, tag) => {
    const regex = new RegExp(`<${tag}[^>]*>[\\s\\S]*?</${tag}>`, "i");
    const match = html.match(regex);
    if (match) {
        return { matched: match[0], content: match[0], replacedHTML: html.replace(regex, "") };
    }
    return { matched: null, replacedHTML: html };
};

for (const folder of folders) {
    const codeFile = path.join(sourceDir, folder, "code.html");
    if (!fs.existsSync(codeFile)) continue;
    
    let content = fs.readFileSync(codeFile, "utf8");
    const bodyMatch = content.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
    if (!bodyMatch) continue;
    
    let bodyContent = bodyMatch[1];
    
    // Extract Nav
    const navResult = extractTag(bodyContent, "nav");
    bodyContent = navResult.replacedHTML;
    if (navResult.matched && !navbarExtracted) {
        navbarExtracted = true;
        const navCode = `<template>\n${navResult.content}\n</template>\n\n<script setup>\n</script>\n`;
        fs.writeFileSync(path.join(componentsDir, "TheNavbar.vue"), navCode);
        console.log("Created TheNavbar.vue");
    }
    
    // Extract block starting with "Stitch画布上" if any ? No, just the body.
    
    // Vue Name
    let vueName = folder.split("_").map(w => w.charAt(0).toUpperCase() + w.slice(1)).join("") + "View";
    vueName = vueName.replace(/ApiView$/, "View");
    if (vueName.match(/^\d/)) vueName = "Page" + vueName;
    if (folder === "api") vueName = "HomeView";
    
    const vueContent = `<template>
  <div class="${folder}-view bg-surface text-on-surface min-h-screen pt-20">
    <TheNavbar />
    ${bodyContent}
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
`;
    // Write Vue View
    fs.writeFileSync(path.join(viewsDir, vueName + ".vue"), vueContent);
    console.log("Created " + vueName + ".vue");
    
    let routePath = "/" + folder.replace("_api", "");
    if (folder === "api") routePath = "/home";
    
    routes.push({
        path: routePath,
        name: vueName,
        componentName: vueName
    });
}

// Router
const routerConfig = `import { createRouter, createWebHistory } from 'vue-router'

${routes.map(r => `import ${r.componentName} from '../views/${r.componentName}.vue'`).join("\n")}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/home' },
${routes.map(r => `    { path: '${r.path}', name: '${r.name}', component: ${r.componentName} }`).join(",\n")}
  ],
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router
`;

const routerDir = path.join(__dirname, "src/router");
if (!fs.existsSync(routerDir)) fs.mkdirSync(routerDir);
fs.writeFileSync(path.join(routerDir, "index.js"), routerConfig);
console.log("Router configured.");

