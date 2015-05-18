var k=[];
var check;
/**function getContacts() {
    $.getJSON('contacts?action=list', function(data) {
        $.each(data, function(key, val) {
            var tr = $('<tr></tr>');
            $.each(val, function(k, v) {
                $('<td>' + v + '</td>').appendTo(tr);
            });
            tr.appendTo('#response');
        });
    });
}*/

function getContacts() {
        $.get('contacts?action=list', function(data) {
            for(var i = 0; i < data.length; ++i){
                check = false;
                 for(var j = 0; j < k.length; ++j){
                    if (data[i].id == k[j].id){
                        check = true;
                        break;
                    }
                    else check = false;
                 }
                 if(!check){
                    var tr = $('<tr></tr>');
                    $('<td>' +data[i].nick+': '+data[i].mess+ '</td>').appendTo(tr);
                    tr.appendTo('#response');
                 }
            }
            k = data;
            document.getElementById('chatik').scrollTop=document.getElementById('chatik').scrollHeight;
            }
        );
    };

function get_cookie ( cookie_name )
{
  var results = document.cookie.match ( '(^|;) ?' + cookie_name + '=([^;]*)(;|$)' );
 
  if ( results )
    return ( unescape ( results[2] ) );
  else
    return null;
}

function addBtn() {
    $('form').submit(function() {
        var arr = $(this).serializeArray();
        $.getJSON('contacts?action=add&mesText=' + arr[0].value +('&nickname=') + get_cookie("user")
        );
        return false;
    });
}

function addBtnUser() {
    $('#newUser').submit(function() {
        var arr = $(this).serializeArray();
        $.getJSON('contacts?action=addUser&login=' + arr[0].value + '&pass=' +
                arr[1].value);
        $('#addcontact').modal('hide');
        return false;
    });
}