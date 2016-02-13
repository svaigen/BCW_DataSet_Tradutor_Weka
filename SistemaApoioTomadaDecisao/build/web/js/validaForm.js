function alteraTreinamento(){
    var trein = $("#trein").val();
    $("#class").val(100-trein);
}

function alteraClassificacao(){
    var clas = $("#class").val();
    $("#trein").val(100-clas);
}

function valida(){
    var count = 0;
    $(":input[type='radio']:checked").each(function() {        
        if ($(this).hasClass("decisao")){
            count++;
        }
    });
    if (count===0){
        alert("Deve haver um atributo definido como de decisão!");
        return false;
    } else if (count > 1){
        alert("Deve haver apenas um atributo definido como de decisão!");
        return false;
    }
    return true;
    
}