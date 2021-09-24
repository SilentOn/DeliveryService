function selectCargoType(cargo, nItem) {
    var selType = cargo.value;

    var weight = document.getElementById("weight" + nItem);
    var length = document.getElementById("length" + nItem);
    var width = document.getElementById("width" + nItem);
    var height = document.getElementById("height" + nItem);
    var lengthLabel = document.getElementById("length" + nItem + "Label");
    var widthLabel = document.getElementById("width" + nItem + "Label");
    var heightLabel = document.getElementById("height" + nItem + "Label");

    if (selType === "2") {
        weight.setAttribute("min", "0.05");
        length.setAttribute("value", "40");
        width.setAttribute("value", "10");
        height.setAttribute("value", "1");
        length.value = 40;
        width.value = 10;
        height.value = 1;
        length.hidden = true;
        width.hidden = true;
        height.hidden = true;
        lengthLabel.hidden = true;
        widthLabel.hidden = true;
        heightLabel.hidden = true;
    } else {
        weight.setAttribute("min", "1");
        length.setAttribute("value", "0");
        width.setAttribute("value", "0");
        height.setAttribute("value", "0");
        length.value = 0;
        width.value = 0;
        height.value = 0;
        length.hidden = false;
        width.hidden = false;
        height.hidden = false;
        lengthLabel.hidden = false;
        widthLabel.hidden = false;
        heightLabel.hidden = false;
    }
}