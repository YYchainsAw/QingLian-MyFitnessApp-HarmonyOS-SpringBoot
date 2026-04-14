const fs = require('fs');
const path = require('path');

const htmlPath = 'C:/Users/YYchainsaw/Downloads/default.html';
const html = fs.readFileSync(htmlPath, 'utf-8');

// Extract JSON
const match = html.match(/var\s+data\s*=\s*(\{[\s\S]*?\})\s*;/);
if (!match) {
    console.error('No JSON data found in default.html');
    process.exit(1);
}

const data = JSON.parse(match[1]);
const paths = data.paths;

const apiDir = path.join(__dirname, 'src', 'api');
if (!fs.existsSync(apiDir)) {
    fs.mkdirSync(apiDir, { recursive: true });
}

const apisByTag = {};

for (const [apiPath, methods] of Object.entries(paths)) {
    for (const [method, details] of Object.entries(methods)) {
        const tags = details.tags || ['default'];
        const tag = tags[0]; // Use first tag as group (e.g. AuthController -> Auth)
        
        let fileName = tag.replace(/Controller|管理|相关的API接口/g, '').trim();
        if(!fileName) fileName = 'default';
        // camelCase
        fileName = fileName.charAt(0).toLowerCase() + fileName.slice(1);
        // remove spaces or non a-z
        fileName = fileName.replace(/[^a-zA-Z0-9]/g, '');

        if (!apisByTag[fileName]) {
            apisByTag[fileName] = [];
        }

        // generate function name from operationId or path
        let funcName = details.operationId;
        if (!funcName) {
            const parts = apiPath.split('/').filter(Boolean);
            funcName = method + parts.map(p => p.replace(/[{}]/g, '').charAt(0).toUpperCase() + p.replace(/[{}]/g, '').slice(1)).join('');
        }
        funcName = funcName.replace(/_[0-9]+$/, ''); // remove _1, _2

        // params
        const parameters = details.parameters || [];
        const pathParams = parameters.filter(p => p.in === 'path').map(p => p.name);
        const queryParams = parameters.filter(p => p.in === 'query').map(p => p.name);
        const hasBody = !!details.requestBody;

        const sigParams = [...pathParams];
        if (hasBody) sigParams.push('data');
        if (queryParams.length > 0) {
             if (queryParams.length === 1) sigParams.push(queryParams[0]);
             else sigParams.push('params'); // general params object for many
        }

        let axiosConfig = `url: \`${apiPath.replace(/\{/g, '${')}\`, method: '${method}'`;
        if (hasBody) axiosConfig += `, data`;
        if (queryParams.length === 1) axiosConfig += `, params: { ${queryParams[0]} }`;
        else if (queryParams.length > 1) axiosConfig += `, params`;

        const argsStr = sigParams.join(', ');
        
        // Comment
        const summary = details.summary || '';
        
        apisByTag[fileName].push(`
// ${summary}
export const ${funcName} = (${argsStr}) => {
    return request({
        ${axiosConfig}
    });
};
        `);
    }
}

let indexExports = '';

for (const [fileName, codes] of Object.entries(apisByTag)) {
    const fileContent = `import request from '@/utils/request';\n` + codes.join('\n');
    fs.writeFileSync(path.join(apiDir, `${fileName}.js`), fileContent);
    indexExports += `export * as ${fileName}Api from './${fileName}';\n`;
    console.log(`Generated src/api/${fileName}.js`);
}

fs.writeFileSync(path.join(apiDir, 'index.js'), indexExports);
console.log('API generation complete!');

