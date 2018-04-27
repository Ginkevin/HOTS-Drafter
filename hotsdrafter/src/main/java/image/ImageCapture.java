package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCapture {

    private String inputPath;
    private String outputPath;


    public ImageCapture(){
        inputPath = "C:/Users/Odyssey/Pictures/hots/draft.png";
        outputPath = "C:/Users/Odyssey/Pictures/hots/DEBUG.png";
        ImagePreProcessing processor = new ImagePreProcessing();
        ImageVectorData.ImageVectorDataInstance display = ImageVectorData.getInstance().getImageVectorDataInstance(1680);
        BufferedImage image = processor.RotateImage(ReadImage(), -30, (int) display.getY());
        image = SliceImage(image, display);
        image = processor.GreyScale(image);
//        image = processor.InvertImage(image);
        WriteImage(image);
    }

    /**
    private BufferedImage CaptureTeam(){
        BufferedImage image = null;

        try {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            image = robot.createScreenCapture( new Rectangle(x, y, width, height) );
        } catch(Exception e) {}

        return image;
    }**/

    private BufferedImage SliceImage(BufferedImage image, ImageVectorData.ImageVectorDataInstance display){
        int x = (int) display.getX();
        int width = (int) display.getPictureWidth();
        int height = (int) display.getPictureHeight();
        int offset = (int) display.getOffset();
        int y = (int) display.getY();
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("offset: " + offset); //145

//        return image.getSubimage(x, y+offset , width, height);
        return image.getSubimage(x, y+offset , width, height);
    }

    /**
    private void getResolution(){
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if(graphicsEnvironment.getScreenDevices().length > 1){
            GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
            screenWidth = graphicsDevice.getDisplayMode().getWidth();
            screenHeight = graphicsDevice.getDisplayMode().getPictureHeight();
        }
        else{
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            screenWidth = screenSize.getWidth();
            screenHeight = screenSize.getPictureHeight();
        }
    }**/

    private void WriteImage(BufferedImage image){
        File output = new File(outputPath);
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage ReadImage(){
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(inputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
