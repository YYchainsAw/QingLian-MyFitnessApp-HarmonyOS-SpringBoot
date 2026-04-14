import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// The HTML from Knife4j usually contains "var data = { ... }" with a JSON string or object
// Or it has a table of endpoints.
const apiDir = path.join(__dirname, 'src/api');
if(!fs.existsSync(apiDir)) fs.mkdirSync(apiDir, {recursive: true});

// Since parsing Knife4j HTML directly can be tricky without a full DOM parser, Let's parse the original Controller Java files, which is 100% accurate and easy!
const backendControllersPath = path.join(__dirname, '../qinglian-app-root/src/main/java/com/yychainsaw/controller');

const files = fs.readdirSync(backendControllersPath);

let fullExport = '';

for (const file of files) {
  if(!file.endsWith('Controller.java')) continue;
  const content = fs.readFileSync(path.join(backendControllersPath, file), 'utf8');
  let baseName = file.replace('Controller.java', '');
  baseName = baseName.charAt(0).toLowerCase() + baseName.slice(1); // camelCase
  
  // Extract RequestMapping path
  const reqMatch = content.match(/@RequestMapping\("([^"]+)"\)/);
  const basePath = reqMatch ? reqMatch[1] : '';

  let apiCode = `import request from '@/utils/request';\n\n`;

  // Extract Methods
  const methodRegex = /@(PostMapping|GetMapping|PutMapping|DeleteMapping)[\s\S]*?public\s+[\w<>,?\s]+?\s+(\w+)\(/g;
  let methodMatch;
  while ((methodMatch = methodRegex.exec(content)) !== null) {
    const startIdx = methodMatch.index;
    const blockEnd = content.indexOf('{', startIdx);
    const annBlock = content.substring(startIdx, blockEnd);
    
    const type = methodMatch[1].replace('Mapping', '').toLowerCase();
    const funcName = methodMatch[2];
    
    const pathMatch = annBlock.match(/@(PostMapping|GetMapping|PutMapping|DeleteMapping)\("([^"]+)"\)/);
    const endPath = pathMatch ? pathMatch[2] : '';
    const fullPath = (basePath + endPath).replace('//', '/');
    
    // Check if there's @RequestBody
    const hasBody = annBlock.includes('@RequestBody');
    
    // Check path variables
    const pathVars = [];
    const pvRegex = /@PathVariable(?:\("([^"]+)"\))?\s+(?:[A-Za-z0-9_]+)\s+(\w+)/g;
    let pvMatch;
    while((pvMatch = pvRegex.exec(annBlock)) !== null) {
      pathVars.push(pvMatch[1] || pvMatch[2]);
    }
    
    // Check request params
    const reqVars = [];
    const rpRegex = /@RequestParam(?:\(value\s*=\s*"([^"]+)"[^\)]*\))?\s+(?:[A-Za-z0-9_]+)\s+(\w+)/g;
    let rpMatch;
    while((rpMatch = rpRegex.exec(annBlock)) !== null) {
      reqVars.push(rpMatch[1] || rpMatch[2]);
    }

    // Check query params if it's GET/DELETE and has model attribute or parameters without annotations
    // Let's just group them into params object if it's not a path variable.

    const sigParams = [...pathVars, ...reqVars];
    if (hasBody) sigParams.push('data');
    if (type === 'get' && reqVars.length === 0 && !hasBody) sigParams.push('params');

    let urlStr = `\`${fullPath.replace(/\{(\w+)\}/g, '${$1}')}\``;

    const axiosCfg = [`url: ${urlStr}`, `method: '${type}'`];
    if (hasBody) axiosCfg.push(`data`);
    if (sigParams.includes('params') && type === 'get') axiosCfg.push(`params`);
    else if (reqVars.length > 0) axiosCfg.push(`params: { ${reqVars.join(', ')} }`);
    
    const argsStr = sigParams.length ? sigParams.join(', ') : '';
    apiCode += `export const ${funcName} = (${argsStr}) => {\n  return request({\n    ${axiosCfg.join(',\n    ')}\n  });\n};\n\n`;
  }
  
  fs.writeFileSync(path.join(apiDir, `${baseName}.js`), apiCode);
  fullExport += `import * as ${baseName}Api from './${baseName}';\nexport { ${baseName}Api };\n`;
  console.log(`Generated src/api/${baseName}.js`);
}

fs.writeFileSync(path.join(apiDir, 'index.js'), fullExport);
console.log('Finished generating API index.js');
