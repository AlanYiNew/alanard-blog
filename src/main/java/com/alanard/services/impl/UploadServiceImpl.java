package com.alanard.services.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


@Service
public class UploadServiceImpl{
	private Auth auth = Auth.create("fQkegwnvh6oTCFmZJ1YW8Bsmw_jbUd4lXwrr7uum", "0RKkzRbsSFhGTa-vDI5qugJlrIHT2GLo9HLJQ1HN");	
	Configuration cfg = new Configuration(Zone.zone0());
	
	public String uploadFile(MultipartFile file,int uid,String rootPath, String bucket){
		
		String key = uid + "/pic/";
		String savePath = rootPath + key;
		String fileExt = file.getOriginalFilename()
				.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
				.toLowerCase();
		
		File savefile = new File(savePath);
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		key+=newFileName;

		File newfile = new File(savePath, newFileName);
		
	    try {
	    	 
	    	file.transferTo(newfile);
	    	BufferedImage image = ImageIO.read(newfile);
	    	Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("jpeg"); 
	    	ImageWriter writer= it.hasNext()?it.next():null; 
			if (writer !=null) {
				ImageWriteParam params = writer.getDefaultWriteParam(); 
			    params.setProgressiveMode(ImageWriteParam.MODE_DEFAULT);  
			    ImageOutputStream output = ImageIO.createImageOutputStream(newfile); 
			    writer.setOutput(output); 
			    writer.write(null,new IIOImage(image,null,null), params); 
			    output.flush(); 
			    writer.dispose(); 			     
				
		    	UploadManager upm = new UploadManager(cfg);
		        Response res = upm.put(newfile, key, auth.uploadToken(bucket,key));
		        newfile.delete();
		        if (res.isOK()){
		        	return "http://7xpvht.com1.z0.glb.clouddn.com/"+key;
		        }
			}	
	        // log.info(res);
	        // log.info(res.bodyString());
	        // Ret ret = res.jsonToObject(Ret.class);
	    } catch (QiniuException e) {
	        //Response r = e.response;
	        //log.error(r.toString());
	        e.printStackTrace();
	    } catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}
