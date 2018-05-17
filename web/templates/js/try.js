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

    var max = (new Date().getFullYear());
    min = max - 30;
    select = document.getElementById("selectElementId");

    for (var i = max; i>=min; i--){
        var opt = document.createElement('option');
        opt.value = i-1;
        opt.innerHTML = (i-1)+"/"+i;
        select.appendChild(opt);

    }
}