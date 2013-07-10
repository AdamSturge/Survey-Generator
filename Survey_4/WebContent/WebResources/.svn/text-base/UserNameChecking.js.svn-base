

function GetXmlHttpObject() {
    var xmlHttp = null;

    
    try {
        xmlHttp = new XMLHttpRequest();
    }
    catch (e) {
        // Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}


$J(document).ready(function() {


    $J('.username').keyup(function() {
      var usename= $J('.username').val();
    	$J.ajax({
       
            url: "UseName.jsp",
            type: 'POST',
            data: ({
                "username": usename 
            }),
            success: function(results) {
                //alert(results);
                $J("#ucheck").html(results);
              
            	   
            	   
            }
        });
    });

});

