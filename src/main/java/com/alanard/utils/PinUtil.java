package com.alanard.utils;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics2D;  
import java.awt.geom.AffineTransform;  
import java.awt.image.BufferedImage;  
import java.io.File;
import java.util.Random;

import javax.servlet.http.HttpSession;

public final class PinUtil {
	private static int width = 90;
	private static int height = 32;
	private static int fontheight = 40;
	private static int codeNum = 4;
	private static char codes[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	private static char[] code = new char[4];
	public static BufferedImage getImage(HttpSession session){
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = bi.createGraphics();
		Random rand = new Random();
		gd.setColor(new Color(255, 255, 255));
		gd.fillRect(0, 0, width, height);
		gd.setColor(new Color(0, 0, 0));
		double unit_x = (double)width/4;
		
		//load identified matrix
		AffineTransform Tx = new AffineTransform();
		gd.setTransform(Tx);
		
		//draw lines to disturb machine reading
		for (int i = 0; i < 25;i++){
			int x1 = rand.nextInt(width);
			int x2 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int y2 = rand.nextInt(height);
			gd.drawLine(x1, y1, x2, y2);
		}
		
		for (int i = 0, x = 0;i < codeNum;i++,x+=unit_x ){
			//give certain offset to the character
			int offset_x = (rand.nextInt(3)-1)*2;
			int offset_y = (rand.nextInt(3)-1)*2;
			//give certain rotation to the character
			double offset_d = (rand.nextFloat() -0.5)* Math.PI/3;
			//random index and random scale
			int pos = rand.nextInt(36);
			double sizeScale = rand.nextFloat()/2 + 0.75;
			
			//load identified matrix
			gd.setTransform(Tx);
			gd.translate(x, 0);//move
			gd.rotate(offset_d);//rotate
			
			code[i] = codes[pos];
			
			//random font size
			Font font = new Font("Romans",Font.BOLD,(int) (fontheight*sizeScale));
			gd.setFont(font);
			
			gd.drawChars(codes, pos, 1, offset_x, height-1+offset_y);	
		}
		gd.dispose();
		session.setAttribute("pin", String.valueOf(code));
		return bi;	
	}
}
