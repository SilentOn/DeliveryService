window.addEventListener("load", pagination)

function pagination() {
    var wrapper = document.querySelector(".params");
    var sort = wrapper.getAttribute("data-sort");
    var filter = wrapper.getAttribute("data-filter");
    var itemsOnPage = wrapper.getAttribute("data-items-on-page");
    var radiosSort = document.querySelectorAll("input[type=radio][name=sort]");
    var filterOptions = document.getElementById("filter").options;
    var itemsOnPageOptions = document.getElementById("itemsOnPage").options;

    if (sort !== null && filter !== null && itemsOnPage !== null) {
        for (let i = 0; i < radiosSort.length; i++) {
            if (radiosSort[i].getAttribute("value") == sort) {
                radiosSort[i].checked = true;
            }
        }

        for (let i = 0; i < filterOptions.length; i++) {
            if (filterOptions[i].getAttribute("value") == filter) {
                filterOptions[i].selected = true;
            }
        }

        for (let i = 0; i < itemsOnPageOptions.length; i++) {
            if (itemsOnPageOptions[i].getAttribute("value") == itemsOnPage) {
                itemsOnPageOptions[i].selected = true;
            }
        }
    }
}