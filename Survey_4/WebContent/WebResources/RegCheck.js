var $J = jQuery.noConflict();

function register()

{
	

    document.getElementById("registration").className="ColorChange1";
   
}


$J(document).ready(function() {
	
	
	 // $J("input[type=submit]").click(function() {

		  $J("#rsub").click(function() {
		   // var pat1=new RegExp("^(?=.*[a-zA-Z].*)([a-zA-Z0-9]+)$");
		    var pat2=new RegExp("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$");
		    var validG=true;
		    var validE=true;
		    var validP=true;
		  
		    
		    if($J("#checkUSN").text()=='true')
		    	validU=true;
		    else
		    	validU=false;
		    
		 
		    	for(var i=0;i<document.forms[0].elements.length;i++)
		    	  {
                     if (i==0||i==1||i==5)
                     {
		    		if(document.forms[0].elements[i].value.length==0)
		    	    {  //alert(i);
		    	    	validG=false;  
		    	    }
                     }
		    	  }
		    	
		    	  if(!pat2.test(document.forms[0].elements[5].value))
		    		  validE=false;
		    	  
		    	  if(document.forms[0].elements[1].value!=document.forms[0].elements[2].value)
		    		  validP=false;
		    	  
		    	  if(validG==false)
	    		  {  document.getElementById("Warn").style.visibility= 'visible';}
	    	  else
	    		 {  document.getElementById("Warn").style.visibility= 'hidden'; }
	    	 
	    	  if(validE==false)
	    		  { document.getElementById("WarnEmail").style.visibility= 'visible';}
	    	  else
	    		  {document.getElementById("WarnEmail").style.visibility= 'hidden';
	    		 }
	    	  if(validP==false)
	    		  {document.getElementById("WarnPassword").style.visibility= 'visible';}
	    	  else
	    		  { document.getElementById("WarnPassword").style.visibility= 'hidden';  }
	    	  
	    	 
		    	  
		    	  if(validG&&validE&&validP&&validU)
		    		  {
		    	  	alert("Registration is now complete, please login with your username and password");
		    		  }
		    	  
		    	  else
		    		  {		 
		    		  alert('Registration is not complete, please fill out all fields correctly');
		    		 return false;  
		    		 
		    		  }		    	
		  
		    });

	  
  

  	
    
    
  

})

    

