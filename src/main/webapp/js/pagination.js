window.addEventListener("load", pagination)

function pagination() {
    var wrapper = document.querySelector("ul.pagination");
    var pages_count = wrapper.getAttribute("data-pages-count");
    var page = wrapper.getAttribute("data-page");

    wrapper.appendChild(addPageItem(1, page));

    var startI = parseInt(page) - 2;
    var endI = parseInt(page) + 2;
    if (startI <= 1 || startI === 3) {
        startI = 2;
    }
    if (startI !== 2) {
        var s = document.createElement("span");
        s.textContent = "...";
        wrapper.appendChild(s);
    }

    while (startI <= endI && startI <= pages_count) {
        wrapper.appendChild(addPageItem(startI, page));
        startI++;
    }
    if (endI < pages_count - 2) {
        wrapper.appendChild(document.createTextNode("..."));
        wrapper.appendChild(addPageItem(pages_count, page));
    }
    if (endI === pages_count - 2) {
        wrapper.appendChild(addPageItem(pages_count - 1, page));
        wrapper.appendChild(addPageItem(pages_count, page));
    }
    if (endI === pages_count - 1) {
        wrapper.appendChild(addPageItem(pages_count, page));
    }
}

function addPageItem(iPage, page_current) {
    var pageItem = document.createElement("li");
    if (iPage == page_current) {
        pageItem.classList.add("active");
    }
    pageItem.classList.add("waves-effect");
    var aItem = document.createElement("input");
    aItem.setAttribute("name", "p");
    aItem.setAttribute("type", "submit");
    aItem.setAttribute("value", iPage);
    aItem.classList.add("btn-flat");
    aItem.textContent = iPage;
    pageItem.appendChild(aItem);

    return pageItem;
}