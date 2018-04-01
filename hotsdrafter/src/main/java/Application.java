import image.ImageCapture;
import image.ImagePreProcessing;

public class Application {
    public static void main(String[] args){
        System.out.println("Hello World!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ImageCapture();
    }
}
