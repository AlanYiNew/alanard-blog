<#setting url_escaping_charset='utf-8'> 
<!DOCTYPE html>  
<html>
<#include "head.ftl">
<body>
  <#include "header.ftl">
  <div class="content-wrapper">
        <div class="content-wrapper__inner">
            <div class="main-post-list">            	
				<form name="example" method="post" action="changeinfo.do">				
					 <div class="input-group input-group-longer">
					   <input type="text" name="username" value="${user.username}" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label class="mylabel"><@spring.message "username"/></label>
					 </div>
				
					<div class="input-group input-group-longer">
					   <input type="text" name="introduction" value="${user.introduction}" required>
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label class="mylabel"><@spring.message "user_Introduction"/></label>
					</div>
					
					<div class="input-group input-group-img">
					   <input type="text" name="pofolioPic" value="${user.pofolioPic}" required>   
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label class="mylabel"><@spring.message "avatar"/></label>
					</div>	
					
					<span id="avatar_file_upload_btn" class="btn-file">
				       <i class="fa fa-folder-open"></i>
				       <input type="file" id="avatar-upload" name="avatar" accept="image/*">
					</span>

					<div id="avatar-clipArea">
					
					</div>

					<div class="preview-container">
						<div class="preview" style="background-image:url(${user.pofolioPic})" >
							<strong></strong>
						</div>					
						
						<div class="avatar-btn-container">
							<button class="btn-clip">
								<@spring.message "clip"/>
							</button>
							
							<button class="btn-avatar-upload tooltip" disabled>
								<@spring.message "upload"/>
								<span class="tooltiptext">还未截图</span>
							</button>							
						
						</div>
					</div>
					
					<div class="input-group input-group-img">
					   <input type="text" name="github" value="${user.github!''}" required>   
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label class="mylabel"><@spring.message "github_address"/></label>
					</div>	
				
					<div class="input-group input-group-img">
					   <input type="text" name="background" value="${user.background}" required>   
					   <span class="highlight"></span>
					   <span class="bar"></span>
					   <label class="mylabel"><@spring.message "index_Background"/></label>
					</div>	
					
					<div class="sk-cube-grid" style="display:none">
					  <div class="sk-cube sk-cube1"></div>
					  <div class="sk-cube sk-cube2"></div>
					  <div class="sk-cube sk-cube3"></div>
					  <div class="sk-cube sk-cube4"></div>
					  <div class="sk-cube sk-cube5"></div>
					  <div class="sk-cube sk-cube6"></div>
					  <div class="sk-cube sk-cube7"></div>
					  <div class="sk-cube sk-cube8"></div>
					  <div class="sk-cube sk-cube9"></div>
					</div>
					
					<span id="background_file_upload_btn" class="btn-file">
					       <i class="fa fa-folder-open"></i>
					       <input type="file" id="background-upload" accept="image/*">
					</span>
					
					<div>
					<div class="post-submit-footer">						
						<button type="submit" class="btn-submit"><@spring.message "submit" /></button>
					</div>
				</form>
		 
		</div>
		<#include "footer.ftl">
       </div>
  </div>


 	<script src="/js/iscroll-zoom-min.js"></script>
	<script src="/js/hammer.min.js"></script>
	<script src="/js/lrz.all.bundle.js"></script>
	<script src="/js/jquery.photoClip.js"></script>
	<script src="/js/circle-progress.min.js"></script>
	<script src="/js/simpleCompress.js"></script>
	<script>
	var global;
	
	function displayMessage(mess){
		$.message({
        	message:mess,
        	type:'error'
    	});
	}
	
	function dataURItoBlob(dataURI) {
	    // convert base64 to raw binary data held in a string
	    // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
	    var byteString = atob(dataURI.split(',')[1]);
	
	    // separate out the mime component
	    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
	
	    // write the bytes of the string to an ArrayBuffer
	    var ab = new ArrayBuffer(byteString.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < byteString.length; i++) {
	        ia[i] = byteString.charCodeAt(i);
	    }

	
	    //New Code
	    return new Blob([ab], {type: mimeString});
	}

	
	var pc = new PhotoClip('#avatar-clipArea',{
		'done':function(dataURL){
			$('.preview').css("background-image","url("+ dataURL +")");
			global = dataURL;
			$('.btn-avatar-upload').prop('disabled',false);  
   		}
	});
	
  	$('input[name="pofolioPic"]').change(function(){
		var self = this;
  		var img = new Image();
  		img.onload = function(){
			var photoCanvas = document.createElement("canvas");  
            var ctx = photoCanvas.getContext("2d");
            try{
  				ctx.drawImage(img, 0, 0, img.naturalWidth, img.naturalHeight);
  				
  				pc.load(self.value);
  			}	catch (e){
		       	displayMessage('<@spring.message "pic_error" />');
  			}  
  		}
  		
  		img.onerror = function(){
				displayMessage('<@spring.message "pic_error" />');
  		}
  		img.src = this.value;
  	});
	
	$('#avatar-upload').change(function() {
    	pc.load(this.files[0]);
	});
	
	$('.btn-clip').click(function(e){
		e.preventDefault();
		pc.clip();
	});
	
	var formData = new FormData();
	
	$('.btn-avatar-upload').on('touchstart',function(){
		if ($(this).is(':disabled')){
			$(this).find('.tooltiptext').show();
		}
	}).on('touchend',function(){
		if ($(this).is(':disabled')){
			$(this).find('.tooltiptext').hide();
		}
	});
	
	
	$('.btn-avatar-upload').click(function(e){
		e.preventDefault();
		var blob = dataURItoBlob(global);
		var data = new FormData();
        data.append('file', blob, 'avatar.'+blob.type.split("/")[1]);

        $.ajax({
            url :  "/admin/${user.id}/upload.do",
            type: 'POST',
            data: data,
            contentType: false,
            processData: false,
            xhr: function(){
            	  var xhr = new window.XMLHttpRequest();
			      //Upload progress
			      xhr.upload.addEventListener("progress", function(evt){
			        if (evt.lengthComputable) {
			           var percentComplete = evt.loaded / evt.total;
			           //Do something with upload progress
			           c4.circleProgress('value',percentComplete);
			        }
			      }, false);
			      return xhr;
            },
            
            success: function(data) {
               if (data.success == false){
                   displayMessage('<@spring.message "upload_error" />');
               }   else {
               	   formData.append('pofolioPic',data.file_path);
               }
            },    
	        error: function(xhr, status, error) {
	           var err =  JSON.parse(xhr.responseText);
	           displayMessage(err.Message);
	        }
      	});
	})
	
	var c4 = $('.preview').circleProgress({
        value: 0,
	    size: 162,
	    thickness: 7,
	    lineCap: 'round',
	    fill: {
	      gradient: ["#38B99B", "#4285f4"]
	    }
	}).on('circle-animation-progress', function(event, progress, stepValue) {
    	$(this).find('strong').html(Math.round(100 * stepValue) + '<i>%</i>');
  	});
	
	
	$('.post-submit-footer button').click(function(e){
		e.preventDefault();
		if (!formData.has('pofolioPic')){
			formData.append('pofolioPic',$('input[name="pofolioPic"]').val());
		}	
		
		formData.append('username',$('input[name="username"]').val());
		formData.append('introduction',$('input[name="introduction"]').val());
		formData.append('background', $('input[name="background"]').val());
		formData.append('github', $('input[name="github"]').val());
		
		$.ajax({
            url :  "/admin/${user.id}/changeinfo.do",
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
        
            
            
            success: function(data) {
               var result = JSON.parse(data);
               if (result.error == 0){
                   window.location.href= '/user/${user.id}/userpage'
               }	else{
               	   displayMessage(result.message);
               }
            },    
	        error: function(xhr, status, error) {
	           var err =  JSON.parse(xhr.responseText);
	           displayMessage(err.error);
	        }
      	});
	})
	
	$('#background-upload').change(function(){
		  var data = new FormData();
		  sc($(this)[0].files[0],function(blob){
		  	 data.append('file', blob, 'background.'+blob.type.split("/")[1]);
		  	 $.ajax({
	             url :  "/admin/${user.id}/upload.do",
	             type: 'POST',
	             data: data,
	             contentType: false,
	             processData: false,
	            
	         	 beforeSend:function(){
	         		$('.sk-cube-grid').show();
	         		$('#background_file_upload_btn').hide();
	         	 },
	            
	             success: function(data) { 
	               if (data.success != true){
	               	   displayMessage('<@spring.message "upload_error" />');
	               }	else{
	                   $('input[name="background"]').val(data.file_path);
	                   $('.sk-cube-grid').hide();
	         	       $('#background_file_upload_btn').show();
	         	   }
	             },    
		         error: function(xhr, status, error) {
		           var err =  JSON.parse(xhr.responseText);
		           displayMessage(err.error);
		           $('.sk-cube-grid').hide();
	         	   $('#background_file_upload_btn').show();
		         }   
	      	 });
		});		 
	})

   

	</script>
</body>
</html>