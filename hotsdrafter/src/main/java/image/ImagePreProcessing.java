package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePreProcessing {

    public ImagePreProcessing() {

    }

    public BufferedImage RotateImage(BufferedImage image, int radians, int y){
        double rotationRequired = Math.toRadians (radians);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2 - y;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(image, null);
    }

    public BufferedImage GreyScale(BufferedImage image){
        int width;
        int height;

        try {

            width = image.getWidth();
            height = image.getHeight();

            for(int i=0; i<height; i++){

                for(int j=0; j<width; j++){

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() * (0.114 / 2));
                    Color newColor = new Color(red+green+blue,

                            red+green+blue,red+green+blue);

                    image.setRGB(j,i,newColor.getRGB());
                }
            }
        } catch (Exception e) {}

        return image;
    }

    public BufferedImage InvertImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = image.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                p = (a<<24) | (r<<16) | (g<<8) | b;
                image.setRGB(x, y, p);
            }
        }
        return image;
    }


}