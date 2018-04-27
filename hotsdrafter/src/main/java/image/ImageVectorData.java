package image;

import java.util.HashMap;

public class ImageVectorData {

    private HashMap<Integer,ImageVectorDataInstance> instances = new HashMap<Integer, ImageVectorDataInstance>();
    private static ImageVectorData instance;

    public static ImageVectorData getInstance(){
        if(instance == null){
            instance = new ImageVectorData();
        }
        return instance;
    }

    private ImageVectorData(){
        instances.put(1920, new ImageVectorDataInstance(1080, 1920));
        instances.put(1680, new ImageVectorDataInstance(1050, 1680));
    }

    public ImageVectorDataInstance getImageVectorDataInstance(int width){
        return instances.get(width);
    }

    public class ImageVectorDataInstance{

        double screenHeight;
        double screenWidth;

        final double pictureHeight = 30;
        final double pictureWidth = 140;
        final double x = 150;
        final double y = 400;
        final double offset = 220;

        public ImageVectorDataInstance(int height, int width){
            screenHeight = height;
            screenWidth = width;
        }

        public double getPictureHeight(){
            return (pictureHeight / 100) * (screenHeight / 1080 * 100);
        }

        public double getPictureWidth(){
            return (pictureWidth / 100 ) * (screenWidth / 1920 * 100);
        }

        public double getX(){
//            double ratio = (x / 100) * (screenWidth / 1920 * 100);
//            double delta = x - ratio;
//            return  x + delta;
            return  x;
        }

        public double getY() {
            double ratio = (y / 100) * (screenHeight / 1080 * 100);
            double delta = y - ratio;
            return y + delta;
//            return (y / 100) * (screenHeight / 1080 * 100);
        }

        public double getOffset(){
//            double ratio = (offset / 100) * (screenHeight / 1080 * 100);
//            double delta = offset - ratio;
//            return offset - delta;
            if(screenHeight == 1080) return offset;

            double ratio = (offset / 100) * (screenHeight / 1080 * 100);
            double delta = offset - ratio;
            int customOffset = (int) (offset - delta);

            double deltaoffset = offset - customOffset;
            double powerOff = Math.pow(2, deltaoffset);
            return powerOff + (2 * deltaoffset);
    }

        public int getScreenWidth(){
            return (int) screenWidth;
        }
    }

}
