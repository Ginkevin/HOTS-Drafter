package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCapture {
    double screenWidth;
    double screenHeight;

    private String inputPath;
    private String outputPath;

    int x = 0;
    int y = 125;
    int width = 150;
    int height = 125;


    public ImageCapture(){
        inputPath = "C:/Users/Odyssey/Pictures/hots/draft.png";
        outputPath = "C:/Users/Odyssey/Pictures/hots/DEBUG.png";
        ImagePreProcessing processor = new ImagePreProcessing();
        BufferedImage image = processor.RotateImage(CaptureTeam(), -30);
        image = SliceImage(image);
        image = processor.GreyScale(image);
        image = processor.InvertImage(image);
        DEBUG(image);
    }

    private BufferedImage CaptureTeam(){
        BufferedImage image = null;

        try {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            image = robot.createScreenCapture( new Rectangle(x, y, width, height) );
        } catch(Exception e) {}

        return image;
    }

    private BufferedImage SliceImage(BufferedImage image){
        return image.getSubimage(0, y/2, width, height - 100);
    }

//    private void getResolution(){
//        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        if(graphicsEnvironment.getScreenDevices().length > 1){
//            GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
//            screenWidth = graphicsDevice.getDisplayMode().getWidth();
//            screenHeight = graphicsDevice.getDisplayMode().getHeight();
//        }
//        else{
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            screenWidth = screenSize.getWidth();
//            screenHeight = screenSize.getHeight();
//        }
//    }

    private void DEBUG(BufferedImage image){
        File output = new File(outputPath);
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private BufferedImage loadImage(){
//        BufferedImage image = null;
//
//        try {
//            image = ImageIO.read(new File(inputPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }
}
