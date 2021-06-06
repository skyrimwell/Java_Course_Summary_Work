let i = 0;
let multipleTotalPrice = (x,y) => {
    $(document).ready(function(){
        let brickCount = $("#brickCount"+y).val();
        let result = x*brickCount;
        $('#total'+y).text(result);
    })
    $("#brickCount"+y).change(function(){
        let brickCount = $("#brickCount"+y).val();
        let result = x*brickCount;
        $('#total'+y).text(result);
    })
}

for (let index = i; index > 0; index--) {
    $('#checkbox'+index).click(function(){
        if($("#checkbox"+index).is(":checked")){
            $('#checkbox'+index).prop("checked",true)
        }else{
            $('#checkbox'+index).prop("checked",false)
        }
    })

}

$(document).ready(function(){
    $('#allCheck').change(function(){
        if($("#allCheck").is(":checked")){
            $("input:checkbox").prop("checked",true);
        }else{
            $("input:checkbox").prop("checked",false);
        }
    })
    for (let index = i; index > 0; index--) {
        $('#checkbox'+index).change(function(){

            let a = 0;
            for (let index1 = i; index1 > 0; index1--) {
                if($("#checkbox"+index1).prop("checked") == true){
                    a++;
                }
            }
            if(a == i){
                $("#allCheck").prop("checked",true);
            }else{
                $("#allCheck").prop("checked",false);
            }
        })
    }
})