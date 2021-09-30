window.addEventListener("load", activeNav);

function activeNav() {
    var nav_links = document.querySelectorAll(".nav-wrapper>div>ul>li");
    var url = window.location.href.split('?')[0];
    console.log(url);
    for (var i = 0; i < nav_links.length; i++) {
        if (nav_links[i].getElementsByTagName("a")[0].href === url) {
            nav_links[i].className += 'active';
        }
    }
}