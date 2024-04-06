let slideIndex = 0;

function showSlide(index) {
    let slides = document.getElementsByClassName("carousel-member-item");
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    if (index >= slides.length) {
        slideIndex = 0;
    } else if (index < 0) {
        slideIndex = slides.length - 1;
    } else {
        slideIndex = index;
    }
    slides[slideIndex].style.display = "block";
}

function prevSlide() {
    showSlide(slideIndex - 1);
}

function nextSlide() {
    showSlide(slideIndex + 1);
}

// Show the first slide initially
showSlide(0);
