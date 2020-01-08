package com.study.webapi.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * @author chenglutao
 * @date 2020-01-03 9:31
 */
public class BarcodeUtils {

    /**
      * 生成文件
      *
      * @param msg
      * @param path
      * @return
      */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
      * 生成字节
      *
      * @param msg
      * @return
      */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
    /**
      * 生成到流
      *
      * @param msg
      * @param ous
      */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }

        EAN13Bean bean = new EAN13Bean();
        final int dpi = 130;
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        bean.setModuleWidth(moduleWidth);
        bean.setBarHeight(3);
        bean.doQuietZone(true);
        bean.setMsgPosition();
        String format = "image/png";
        try {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            bean.generateBarcode(canvas, msg);
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String msg = "333333789093";
        String path = "D:/barcode.png";
        System.out.println(new String(generate(msg)));;
    }
}