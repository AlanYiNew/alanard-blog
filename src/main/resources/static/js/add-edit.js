
function setupSimeditor(url){
	var items = ['title','bold','italic','underline','fontScale','color','ol','ul','code','table','link','image','hr', 'blockquote'
		]
	
    var editor = new Simditor({
		  textarea: $("#editor_id"),
		  toolbar: items,
		  upload:{
		    url: url,
		    params: null,
		    fileKey: 'file',
		    connectionCount: 3,
		    leaveConfirm: 'Uploading is in progress, are you sure to leave this page?'}
	});	
}


function messageHandler(result){
	if (result.error != 0){
		$.message({
	        message:result.message,
	        type:'error'
	    });
	}
}


function setupAjax(href){
	$("form button[type=submit]").click(function(e) {
		e.preventDefault(); // avoid to execute the actual submit of the form.
		var $form = $(this).parents('form');
	    var url = $form.attr('action'); // the script where you handle the form input.
	    
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: $form.serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	           		var result = JSON.parse(data);
	           		messageHandler(result);
	           		if (result.error == 0){
	           			window.location.href = href;
	           		}               
	           },
	           error: function(data){
	           		$.message({
				        message:'server error',
				        type:'error'
				    });
	           }
	    });
	});
}