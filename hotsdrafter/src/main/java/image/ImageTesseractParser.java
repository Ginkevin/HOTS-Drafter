package image;

import net.sourceforge.tess4j.util.LoadLibs;
import org.bytedeco.javacpp.*;

import java.io.File;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class ImageTesseractParser {

    private TessBaseAPI api;

    public ImageTesseractParser(){
        if(api == null) api = new TessBaseAPI();
        ReadImage();
    }

    public void ReadImage(){
        BytePointer outText;
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        if (api.Init(tessDataFolder.getAbsolutePath(), "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        PIX image = pixRead("C:/Users/Odyssey/Pictures/hots/NAZEEBO2.png");
        api.SetImage(image);
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }

}
