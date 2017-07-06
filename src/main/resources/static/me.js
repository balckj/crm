//function loadPage(url){
//    $('#main').load(url);
//}
function loadPage(url,callback){
    $('#main').load(url,{},callback);
}
function validate(id,key,url){

    $("#"+id).koala( {
        delay: 300,
        keyup: function(event){
            var param = {};
            param[key] = $("#"+id).val();
            v(id,param,url);
        }
    });
}

function validate2(id,key,param,url){

    $("#"+id).koala( {
        delay: 300,
        keyup: function(event){
            param[key] = $("#"+id).val();
            v(id,param,url);
        }
    });
}

function v(id,param,url){
    $.ajax({
        url : url,
        async : true,
        type : 'GET',
        data : param,
        contentType: 'application/json; charset=utf-8',
        dataType : 'json',
        timeout : 300000,
        beforeSend: function () {
            //spinner.spin(target);
        },
        error : function(result) {
//                            alert("通信错误！");
        },
        success : function(data) {
            if(data.res == 0){
                $("#"+id+"Form").addClass("has-error")
                $("#"+id).parent().next().html(data.txt);
            }else{
                $("#"+id+"Form").removeClass("has-error")
                $("#"+id).parent().next().html("<i class='fa fa-check' style='color:#21b384;'/>");
            }
        }
    });
}
