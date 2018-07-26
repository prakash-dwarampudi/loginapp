$(function(){
    $("#logout").on('click', function(e){
        e.preventDefault();
        $.ajax({
            url: '/bin/logout',
            type:'GET',
            success: function(result){
                location.reload();
            },
            error: function(err){}
        })
    });
})