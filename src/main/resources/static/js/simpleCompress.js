function sc(file, callback){
	var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
	var img = new Image;
	img.onload = function() {
		canvas.width=img.naturalWidth;
		canvas.height=img.naturalHeight;
	    ctx.drawImage(img,0,0,img.naturalWidth, img.naturalHeight);
	    canvas.toBlob(
	    	function(blob){
	    		callback(blob);
	    	}, 'image/jpeg',
	    	0.85
	    );
	    
	}
	img.src = URL.createObjectURL(file);
	
 
}