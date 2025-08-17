package com.alife.qrcodegenerator.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/qr")
public class QrCodeController {

    @PostMapping(value = "/generate", consumes = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQr(@RequestBody Map<String, String> mapParam) throws WriterException, IOException {

        log.info("Request to generate qr code for : {} ", mapParam);

        return getQRCodeImage(mapParam.toString(), 500, 500);
    }

    @GetMapping(value = "/static", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateStaticQr() throws WriterException, IOException {

        Map<String, String> map = new HashMap<>();

        map.put("name", "Santosh Sagar");
        map.put("role", "Software Engineer");
        map.put("email", "msg2santoshsagar@gmail.com");
        map.put("website", "https://msg2santoshsagar.github.io");
        map.put("location", "Chennai, India");
        map.put("timestamp", Instant.now().toString());
        map.put("note", "Static demo QR code with generic details");

        log.info("Request to generate static qr code for : {} ", map);

        return getQRCodeImage(map.toString(), 500, 500);
    }


    private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

}
