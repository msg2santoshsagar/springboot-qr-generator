package com.alife.qrcodegenerator.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



@RestController
@RequestMapping(value="/api/qr")
public class QrCodeController {
	
	@PostMapping(value="/generate",consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> generateQr(@RequestBody Map<String, String> mapParam){
		
		System.out.println("Request to generate qr for "+mapParam);
		
		return mapParam;
	}
	
	@GetMapping(value="/static",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String generateStaticQr() throws WriterException, IOException{
		
		Map<String, String> map = new HashMap<>();
		
		map.put("name", "Santosh Sagar");
		map.put("age", "24");
		map.put("work", "Programmer");
		
		System.out.println("Request to generate static qr for "+map);
		
		
		byte[] imageBytes = getQRCodeImage(map.toString(), 500, 500);
		
		String b64 =Base64.encodeBase64String(imageBytes);
		
		return "data:image/png;base64,"+b64;
		
	}
	
	
	private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	   
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	

}
