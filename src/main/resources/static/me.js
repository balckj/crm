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

function validateSelect(id,key,url){
    $("#"+id).change(function(event){
        var param = {};
        param[key] = $("#"+id).val();
        v(id,param,url);
    });
}

function notValidateSelect(id){
    $("#"+id).unbind("change");
}

function validateDiv(id,key,url){
    $("#"+id).bind('DOMNodeInserted', function(e) {
        var param = {};
        param[key] = $("#"+id).children.length==2?"a":"";
        console.info($("#"+id).html());
        v2(id,param,url);
    });
}
function validateMultipleSelect(id,key,url){
    $("#"+id).change(function(event){
        var param = {};
        var value = $("#"+id).val().toString();
        if(value == "" || value == null){
            param[key] = null;
            v(id,param,url);
        }else{
            param[key] = value;
            v(id,param,url);
        }

    });
}
function v2(id,param,url){
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
                $("#"+id).next().html(data.txt);
            }else{
                $("#"+id+"Form").removeClass("has-error")
                $("#"+id).next().html("<i class='fa fa-check' style='color:#21b384;'/>");
            }
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
toastr.options = {
    "closeButton": true,
    "debug": false,
    "progressBar": true,
    "preventDuplicates": false,
    "positionClass": "toast-top-center",
    "onclick": null,
    "showDuration": "400",
    "hideDuration": "1000",
    "timeOut": "7000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
