package ua.comparison.image.service;

import ua.comparison.image.model.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.awt.Color.RED;

public class ImageComparison {

    /**
     * The threshold which means the max distance between non-equal pixels.
     * Could be changed according size and requirements to the image.
     */
    public static int threshold = 5;

    /**
     * The number which marks how many rectangles. Beginning from 2.
     */
    private int counter = 2;

    /**
     * The number of the marking specific rectangle.
     */
    private int regionCount = counter;

    private final BufferedImage image1;
    private final BufferedImage image2;
    private int[][] matrix;

    public ImageComparison(String image1Name, String image2Name) throws IOException, URISyntaxException {
        image1 = ImageComparisonTools.readImageFromResources( image1Name );
        image2 = ImageComparisonTools.readImageFromResources( image2Name );
        matrix = ImageComparisonTools.populateTheMatrixOfTheDifferences( image1, image2 );
    }

    public static void main( String[] args ) throws IOException, URISyntaxException {
        ImageComparison comparison = new ImageComparison( "image1.png", "image2.png" );
        ImageComparisonTools.createGUI( comparison.compareImages() );
    }

    /**
     * Draw rectangles which cover the regions of the difference pixels.
     * @return the result of the drawing.
     */
    public BufferedImage compareImages() throws IOException, URISyntaxException {
        // check images for valid
        ImageComparisonTools.checkCorrectImageSize( image1, image2 );

        BufferedImage outImg = ImageComparisonTools.deepCopy( image2 );

        Graphics2D graphics = outImg.createGraphics();
        graphics.setColor( RED );

        groupRegions();
        drawRectangles( graphics );

        //save the image:
        ImageComparisonTools.saveImage( "build/result2.png", outImg );

        return outImg;
    }

    /**
     * Draw rectangles with the differences pixels.
     * @param graphics the Graphics2D object for drawing rectangles.
     */
    private void drawRectangles( Graphics2D graphics ) {
        if( counter > regionCount ) return;

        Rectangle rectangle = ImageComparisonTools.createRectangle( matrix, counter );

        graphics.drawRect( rectangle.getMinY(), rectangle.getMinX(), rectangle.getWidth(), rectangle.getHeight() );
        counter++;
        drawRectangles( graphics );
    }

    /**
     * Group rectangle regions in binary matrix.
     */
    private void groupRegions() {
        for ( int row = 0; row < matrix.length; row++ ) {
            for ( int col = 0; col < matrix[row].length; col++ ) {
                if ( matrix[row][col] == 1 ) {
                    joinToRegion( row, col );
                    regionCount++;
                }
            }
        }
    }

    /**
     * The recursive method which go to all directions and finds difference
     * in binary matrix using {@code threshold} for setting max distance between values which equal "1".
     * and set the {@code groupCount} to matrix.
     * @param row the value of the row.
     * @param col the value of the column.
     */
    private void joinToRegion( int row, int col ) {
        if ( row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] != 1 ) return;

        matrix[row][col] = regionCount;

        for ( int i = 0; i < threshold; i++ ) {
            // goes to all directions.
            joinToRegion( row - 1 - i, col );
            joinToRegion( row + 1 + i, col );
            joinToRegion( row, col - 1 - i );
            joinToRegion( row, col + 1 + i );

            joinToRegion( row - 1 - i, col - 1 - i );
            joinToRegion( row + 1 + i, col - 1 - i );
            joinToRegion( row - 1 - i, col + 1 + i );
            joinToRegion( row + 1 + i, col + 1 + i );
        }
    }
}
