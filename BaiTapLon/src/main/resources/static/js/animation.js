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