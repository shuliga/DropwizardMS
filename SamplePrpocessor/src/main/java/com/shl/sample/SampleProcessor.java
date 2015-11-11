package com.shl.sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by yshuliga on 06.11.2015.
 */
public class SampleProcessor {

    public static final int SCALE = 3;

    public void process(File in, File out) throws IOException {
        BufferedImage image = ImageIO.read(in);
        int newWidth = image.getWidth() / SCALE;
        int newHeight = image.getHeight() / SCALE;
        Image scaledImage = image.getScaledInstance(newWidth, -1, Image.SCALE_SMOOTH);
        BufferedImage outImage = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB );
        outImage.createGraphics().drawImage(scaledImage, null, null);
        ImageIO.write(outImage, "jpg", out);
        System.out.println("Processed: from " + in + " to " + out);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 2){
            File in = new File(args[0]);
            if (in.exists()){
                new SampleProcessor().process(in, new File(args[1]));
            } else {
                System.out.println("File " + args[0] + " does not exist.");
            }
        } else {
            System.out.println("Argumets: <input jpeg image file>, <resized jpeg image file>");
        }
    }
}
