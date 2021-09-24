window.onload = function () {
    var regionFrom = document.getElementById("invoiceRegionFrom");

    var cityFrom = document.getElementById("invoiceCityFrom");

    var cityFromList = document.getElementById("citiesFromList");

    var addressFrom = document.getElementById("invoiceAddressFrom");

    var addressFromList = document.getElementById("addressesFromList");

    var regionTo = document.getElementById("invoiceRegionTo");

    var cityTo = document.getElementById("invoiceCityTo");

    var cityToList = document.getElementById("citiesToList");

    var addressTo = document.getElementById("invoiceAddressTo");

    var addressToList = document.getElementById("addressesToList");

    regionFrom.onclick = function () {
        selectRegion(regionFrom, cityFrom, addressFrom, cityFromList);
    }

    regionTo.onclick = function () {
        selectRegion(regionTo, cityTo, addressTo, cityToList);
    }

    cityFrom.oninput = function () {
        selectCity(cityFrom, cityFromList, addressFrom, addressFromList);
    }

    cityTo.oninput = function () {
        selectCity(cityTo, cityToList, addressTo, addressToList);
    }
}

function selectRegion(region, city, address, cityList) {
    var selectedRegId = region.options[region.selectedIndex].getAttribute("data-region-id");
    city.removeAttribute("disabled");
    address.setAttribute("disabled", "true");
    city.value = "";
    address.value = "";
    disableNotSelectedOptions(selectedRegId, cityList.options, "region");
}

function selectCity(city, cityList, address, addressList) {
    var selectedCityId;
    var selectedCityValue = city.value;
    // search for selected city in dataList
    for (let cityOption of cityList.options) {
        if (cityOption.disabled === false && cityOption.value === selectedCityValue) {
            selectedCityId = cityOption.getAttribute("data-city-id");
        }
    }
    address.removeAttribute("disabled");
    address.value = "";
    disableNotSelectedOptions(selectedCityId, addressList.options, "city");
}

function disableNotSelectedOptions(selectedId, options, dataAttr) {
    for (let i = 0; i < options.length; i++) {
        options[i].disabled = options[i].getAttribute("data-" + dataAttr + "-id") !== selectedId;
    }
}