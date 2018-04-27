package image;

import net.sourceforge.lept4j.Pix;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.bytedeco.javacpp.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.awt.image.SampleModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static net.sourceforge.lept4j.util.LeptUtils.convertImageToPix;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class ImageTesseractParser {


    public ImageTesseractParser(){

    }

    public String TesseractParseBufferedImage(BufferedImage image){
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
        tesseract.setLanguage("eng");
        String result = null;
        try {
            result = tesseract.doOCR(image);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
}
