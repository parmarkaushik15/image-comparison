package ua.comparison.image.service.impl;

import ua.comparison.image.ImageComparison;
import ua.comparison.image.service.ComparisonToolsService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ComparisonToolsServiceImpl implements ComparisonToolsService {

    @Override
    public BufferedImage readImageFromResources(String path) throws URISyntaxException, IOException {
        File file = new File( ImageComparison.class.getClassLoader().getResource ( path ).toURI().getPath() );
        return ImageIO.read( file );
    }

    @Override
    public BufferedImage copyImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    @Override
    public void saveImage(String path, BufferedImage image) throws IOException {
        // make dir if it's not using from Gradle.
        new File( path ).mkdirs();
        ImageIO.write( image, "png", new File( path ) );
    }

    @Override
    public int[][] populateTheMatrixOfTheDifferences(BufferedImage image1, BufferedImage image2) {
        int[][] matrix = new int[image1.getWidth()][image1.getHeight()];
        for ( int y = 0; y < image1.getHeight(); y++ ) {
            for ( int x = 0; x < image1.getWidth(); x++ ) {
                matrix[x][y] = isDifferent( image1.getRGB( x, y ), image2.getRGB( x, y ) ) ? 1 : 0;
            }
        }
        return matrix;
    }

    /**
     * Says if the two pixels equal or not. The rule is the difference between two pixels
     * need to be more then 10%.
     * @param rgb1 the RGB value of the Pixel of the Image1.
     * @param rgb2 the RGB value of the Pixel of the Image2.
     * @return {@code true} if they' are difference, {@code false} otherwise.
     */
    private boolean isDifferent( int rgb1, int rgb2){
        int red1 = ( rgb1 >> 16 ) & 0xff;
        int green1 = ( rgb1 >> 8 ) & 0xff;
        int blue1 = ( rgb1 ) & 0xff;
        int red2 = ( rgb2 >> 16 ) & 0xff;
        int green2 = ( rgb2 >> 8 ) & 0xff;
        int blue2 = ( rgb2 ) & 0xff;
        double result = Math.sqrt( Math.pow( red2 - red1, 2 ) +
                Math.pow( green2 - green1, 2) +
                Math.pow( blue2 - blue1, 2 ) )
                /
                Math.sqrt( Math.pow( 255, 2 ) * 3 );
        return result > 0.1;
    }
}
