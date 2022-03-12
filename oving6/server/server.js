const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 6969 });

let num = 1;

wss.on('connection', ws => {
    ws.id = num;
    num++;
    console.log("Client connected. Client id: " + ws.id);

    ws.on('message', (data) => {
        console.log("Received message from client " + ws.id + ":" + data.toString());
     
        wss.clients.forEach(client => {
            if (client !== ws && client.readyState === WebSocket.OPEN) {
                client.send("User" + ws.id + ": " + data.toString());
            }
        })
    });
    
    ws.on('close', () => {
        console.log("Client disconnected");
    });
})