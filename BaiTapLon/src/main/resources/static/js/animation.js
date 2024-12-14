var animationElements = document.querySelectorAll('.show-on-scroll')

function toggleAnimationElementInWindow(element){

    // thong so cua so 
    var rect = element.getClientRects()[0];
    // chieu cao man hinh chrome
    var viewHeight = window.innerHeight;

    // day la dieu kien o trong man hinh
    if(((rect.top <= 0 && rect.bottom >= 0) ||
        (rect.bottom >= viewHeight && rect.top <= viewHeight) ||
        (rect.top >= 0 && rect.bottom <= viewHeight))){
            element.classList.add('start');
        } else {
            element.classList.remove('start');
    }
}

function checkAnimation(){

    animationElements.forEach(el => {
        toggleAnimationElementInWindow(el);
    })
}

window.onscroll = checkAnimation

checkAnimation()

// tuyet roi
const snowflakeCount = 300;

const body = document.body;

for (let i = 0; i < snowflakeCount; i++) {
    const snowflake = document.createElement('div');
    snowflake.classList.add('snowflake');
    snowflake.innerHTML = '❄';
    snowflake.style.left = Math.random() * 100 + 'vw';
    snowflake.style.animationDuration = Math.random() * 5 + 5 + 's';
    snowflake.style.fontSize = Math.random() * 5 + 5 + 'px'; 
    snowflake.style.opacity = Math.random();
    body.appendChild(snowflake);
}

document.querySelectorAll('.snowflake').forEach(snowflake => {
    snowflake.animate([
        { transform: `translateY(${window.innerHeight + 10}px)` }
    ], {
        duration: Math.random() * 5000 + 5000, 
        iterations: Infinity 
    });
});