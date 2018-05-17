// var min = new Date().getFullYear();
// max = min + 20;
// select = document.getElementById("selectElementId");
//
// for (var i = min; i<=max; i++){
//     var opt = document.createElement('option');
//     opt.value = i;
//     opt.innerHTML = i;
//     select.appendChild(opt);
// }
//
// select.value = new Date().getFullYear();

function carica() {

    var min = (new Date().getFullYear())-50;
    max = min + 20 +50;
    select = document.getElementById("selectElementId");

    for (var i = min; i<=max; i++){
        var opt = document.createElement('option');
        opt.value = i;
        opt.innerHTML = i;
        select.appendChild(opt);

    }
}