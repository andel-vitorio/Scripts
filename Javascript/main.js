var input = require('fs').readFileSync('/dev/stdin', 'utf8');
var lines = input.split('\n');

lines.forEach((line) => {
    if ( line !== "") 
    { 
        let valores = new Uint32Array(3);
        valores[0] = (/\d+/.exec(line)); 
        valores[1] = (/(?<=\s)\d+/.exec(line)); 
        valores[2] = valores[0] ^ valores[1]; 
        console.log(valores[2]) 
    }
})
