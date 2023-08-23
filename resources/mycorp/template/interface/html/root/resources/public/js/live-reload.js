console.log("starting live reload...")

// Create WebSocket connection.
const socket = new WebSocket("ws://localhost:3000/live-reload");

// Listen for messages
socket.addEventListener("message", (event) => {
  console.log("Message from server ", event.data);
  location.reload();
});
