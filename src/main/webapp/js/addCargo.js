$(document).ready(function () {
    var max_fields = 10;
    var wrapper = $(".containerCargo");
    var add_button = $("#add_form_field");

    var x = 1;
    var amount = 1;
    $(add_button).click(function (e) {
        e.preventDefault();
        if (amount < max_fields) {
            x++;
            amount++;
            $(wrapper).append(/*'<div>' +
                '<label for="cargoType' + x + '">Cargo type: </label>' +
                '<select class = "browser-default" onchange="selectCargoType(this,' + x + ')" ' +
                'name="cargoType' + x + '" id="cargoType' + x + '">' +
                '<option value="1">parcels and cargoes</option>' +
                '<option value="2">documents</option>' +
                '</select> ' +
                '<br/>' +
                '<br/>' +
                '<label id="weight' + x + 'Label" for="weight' + x + '">Weight: </label>' +
                '<input type="number" min="1" max="10000" name="weight' + x + '" id="weight' + x + '" value="0" step="any"/> ' +
                '<label id="length' + x + 'Label" for="length' + x + '">Length: </label>' +
                '<input type="number" min="1" max="1000" name="length' + x + '" id="length' + x + '" value="0" step="any"/> ' +
                '<label id="width' + x + 'Label" for="width' + x + '">Width: </label>' +
                '<input type="number" min="1" max="1000" name="width' + x + '" id="width' + x + '" value="0" step="any"/> ' +
                '<label id="height' + x + 'Label" for="height' + x + '">Height: </label>' +
                '<input type="number" min="1" max="1000" name="height' + x + '" id="height' + x + '" value="0" step="any"/> ' +
                '<button class="delete waves-effect waves-light btn">Delete</button>' +
                '</div>'*/


                '<div class="card-panel row">' +
                '<div class="col l3 m8 s12">' +
                '<label for="cargoType' + x + '">Cargo type</label>' +
                '<select class="browser-default" onchange="selectCargoType(this,' + x + ')"' +
                'name="cargoType' + x + '" id = "cargoType' + x + '" >' +
                '<option value="1">parcels and cargoes</option>' +
                '<option value="2">documents</option>' +
                '</select>' +
                '</div>' +

                '<div class="col l2 m4 s6">' +
                '<label for="weight' + x + '">Weight</label>' +
                '<input class="right-align" type="number" min="1" max="10000"' +
                'name="weight' + x + '" id="weight' + x + '" value = "0" step = "any" / >' +
                '</div>' +

                '<div class="col l2 m4 s6">' +
                '<label id="length' + x + 'Label" for="length' + x + '">Length</label>' +
                '<input class="right-align" type="number" min="1" max="1000"' +
                'name="length' + x + '" id="length' + x + '" value = "0" step = "any" / >' +
                '</div>' +

                '<div class="col l2 m4 s6">' +
                '<label id="width' + x + 'Label" for="width' + x + '">Width</label>' +
                '<input class="right-align" type="number" min="1" max="1000"' +
                'name="width' + x + '" id="width' + x + '" value = "0" step = "any" / >' +
                '</div>' +

                '<div class="col l2 m4 s6">' +
                '<label id="height' + x + 'Label" for="height' + x + '">Height</label>' +
                '<input class="right-align" type="number" min="1" max="1000"' +
                ' name="height' + x + '" id="height' + x + '" value="0" step="any"/>' +
                '</div>' +
                '<div class="col l1 m4 s6">' +
                '<button class="delete waves-effect waves-light btn"><i class="material-icons prefix">clear</i></button>' +
                '</div>' +
                '</div>'
            );
        } else {
            alert('You Reached the limits')
        }
    });

    $(wrapper).on("click", ".delete", function () {
        $(this).parent('div').parent('div').remove();
        amount--;
    })
});