window.onload = function () {
    var nav_links = document.querySelectorAll(".nav-wrapper>div>ul>li");
    console.log(window.location.href);
    for (var i = 0; i < nav_links.length; i++) {
        if (nav_links[i].getElementsByTagName("a")[0].href === window.location.href) {
            nav_links[i].className += 'active';
        }
    }
}