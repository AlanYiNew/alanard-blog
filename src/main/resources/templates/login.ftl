<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title><@spring.message "index_title" /></title>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/modalize.css">
  <link rel="stylesheet" href="/css/message.css">
  
  <meta name="apple-mobile-web-app-status-bar-style" content="blank" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0"/>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  <script src='/js/jquery.md5.js'></script>
  <script src='/js/message.js'></script>
  <!--[if IE]>
  	<script src='/js/messagIE.js'></script>
  <![endif]-->
</head>
<body>

<div class="dialog">
    <div class="content">
	    <span class="left-half">Alan</span><span class="right-half">ard</span>
		<div class="switch">
			 <div class="signin-tab">
			 	 <form id="signin-form" action="login.do">
			 	 	 <div class="input-group">
					   <input type="text" name="email" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "email"/></label>
					 </div>
					
					 <div class="input-group">
					   <input type="password" id="surface_password" required>
					   <input type="hidden" name="password" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "password"/></label>
					 </div>
	    	 
			    	 <footer style="margin:10px 0">
			    		 <a class="sign-up" href="">
			        	 	<@spring.message "sign_Up"/>
			        	 </a><span style="display:inline-block;margin-left:4px">|</span>
			    	     <a class="forget-password reset" href="">
			        	 	<@spring.message "forget_Password"/>
			        	 </a>
			        	 <button type="submit" data-modal-btn="dismiss" class="submit">></button>
			    	 </footer>
		         </form>  	 
			 </div>
			 <div class="signup-tab" style="display:none">
			 	 <form id="signup-form" action="signup.do">
			 	 	 <div class="input-group">
					   <input type="text" name="email" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "email"/></label>
					 </div>
			 	 	
			 	 	 <div class="input-group">
					   <input type="text" name="username" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "username"/></label>
					 </div>
			 	 
			         <div class="input-group">
					   <input type="password" class="passwordEntered" required>
					   <input type="hidden" class="password" name="password" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "password"/></label>
					 </div>
					 
					 <div class="input-group">
					   <input type="password" class="confirmPasswordEntered" required>
					   <input type="hidden" class="confirmPassword" name="confirmPassword" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "confirm_Password"/></label>
					 </div>
			    	 
			    	  <div class="input-group">
					   <input type="text" name="code" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "invitationcode"/></label>
					 </div>		    
			    	 
			    	 <footer>
			    		 <a class="sign-in" href="">
			        	 	<@spring.message "sign_In"/>
			        	 </a><span style="display:inline-block;margin-left:4px">|</span>
			    	     <a class="forget-password reset" href="">
			        	 	<@spring.message "forget_Password"/>
			        	 </a>
			        	 <button type="submit" data-modal-btn="dismiss" class="submit">></button>
			    	 </footer>  
		    	 </form>	 
			 </div>
			 <div class="reset-tab" style="display:none">
			 	 <form id="reset-form" action="reset.do">
			         <div class="input-group">
					   <input type="text" name="email" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "email"/></label>
					   <button class="send-mail"><@spring.message "send_Mail"/></button>
					 </div>
					 
					
		         
			         <div class="input-group">
					   <input type="password" class="passwordEntered" required>
					   <input type="hidden" class="password" name="password" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "password"/></label>
					 </div>   
			       
			    	 <div class="input-group">
					   <input type="password" class="confirmPasswordEntered" required>
					   <input type="hidden" class="confirmPassword" name="confirmPassword" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "confirm_Password"/></label>
					 </div>
			    	 
			    	 
			    	 <div class="input-group">
					   <input type="text" name="pin" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label><@spring.message "pin"/></label>
					 </div>
			    	 
			    	 <footer style="margin:10px 0">
			    		 <a class="sign-in" href="">
			        	 	<@spring.message "sign_In"/>
			        	 </a><span style="display:inline-block;margin-left:4px">|</span>
			    	     <a class="sign-up" href="">
			        	 	<@spring.message "sign_Up"/>
			        	 </a>
			        	 <button type="submit" data-modal-btn="dismiss" class="submit">></button>
			    	 </footer>
			    </form> 	 
			 </div>
		</div>
    
	    
	</div>
</div>           

  <script>
	function messageHandler(result){
		if (result.error != 0){
   			$.message({
		        message:result.message,
		        type:'error'
		    });
   		}
	}
	
   
	$('.sign-up').on('click',function(e){
		e.preventDefault();
		console.log('xxx');
		$('.signin-tab').hide();
		$('.reset-tab').hide();
		$('.signup-tab').fadeIn();
	});
	
	$('.sign-in').on('click',function(e){
		e.preventDefault();
		console.log('xxx');
		$('.signup-tab').hide();
		$('.reset-tab').hide();
		$('.signin-tab').fadeIn();
	});
	
	$('.reset').on('click',function(e){
		e.preventDefault();
		console.log('xxx');
		$('.signup-tab').hide();
		
		$('.signin-tab').hide();
		$('.reset-tab').fadeIn();
	});
	$('.bg').height($('.bg').height())
	
	var wait=60;
	var text;
	function timer(){
    	text = '<@spring.message "send_Mail"/>';
	    if (wait != 0){
		   wait--;
		   $(".send-mail").attr("disabled",true);
		   $(".send-mail").text(text+"("+wait+"s)");	
		   setTimeout("timer()",1000);
	    }  else{
		   wait =60;
		   $(".send-mail").text(text);
		   $(".send-mail").removeAttr("disabled");
	    }
	}
	
	$(".send-mail").click(function(){
	     timer();
	     $.ajax({
	     type: "POST",
         url: '/admin/sendmail.do',
         data: {'email':$('.reset-tab input[name="email"]').val()}, // serializes the form's elements.
         success: function(data)
         {
       		var result = JSON.parse(data);
       		messageHandler(result);
             
         },
         error: function(data){
       		$.message({
		        message:'<@spring.message "error_13"/>',
		        type:'error'
		    });
         }
         }); 
	 });
	$("form button[type=submit]").click(function(e) {
		e.preventDefault(); // avoid to execute the actual submit of the form.
		var $form = $(this).parents('form');
	    var url = $form.attr('action'); // the script where you handle the form input.

		
		if ($form.attr('id') == 'signin-form'){
			var p = $('#surface_password').val();
			$('.signin-tab input[name="password"]').val($.md5(p));
		}
		
		//simple passwords checking before signing up
		if ($form.attr('id') == 'signup-form' || $form.attr('id') == 'reset-form'){
			var p1 = $form.find('.passwordEntered').val();
			var p2 = $form.find('.confirmPasswordEntered').val();
		
			if ( p1 != p2 ){
				messageHandler({'result':12,'message':'<@spring.message "error_12"/>'});
				return;
			}	else if (p1.length < 8){
				messageHandler({'result':2,'message':'<@spring.message "error_2"/>'});
				return;
			}	else{
				
				$form.find('.password').val($.md5(p1));
				$form.find('.confirmPassword').val($.md5(p2));
				//console.log($form.find('.password')[0]);
				//console.log($form.find('.confirmPassword')[0]);
			}
		}
	    
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: $form.serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	           		var result = JSON.parse(data);
	           		messageHandler(result);
	           		if (result.error == 0){
	           			if ($form.attr('id') == "signup-form" || $form.attr('id') == 'signin-form'){
	           				window.location.href = '/user/'+result.uservalue+'/userpage';
	           			}	else{
	           				window.location.href = '/';
	           			}
	           			
	           		}               
	           },
	           error: function(data){
	           		$.message({
				        message:'<@spring.message "error_13"/>',
				        type:'error'
				    });
	           }
	         });
	
	    
	});
  </script>
</body>
</html>
