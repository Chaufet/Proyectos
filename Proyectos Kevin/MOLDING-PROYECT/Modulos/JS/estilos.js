const app = document.getElementById('typewriter');

const typewriter =new typewriter(app, {
loop: true, 
delay: 62
})

typewriter
.typeString("2525")
.pauseFor (200)
.start();

