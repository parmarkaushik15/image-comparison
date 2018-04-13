package ua.comparison.image.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ComparisonToolsService {

    /**
     * Populate binary matrix by "0" and "1". If the pixels are difference set it as "1", otherwise "0".
     * @param image1 {@code BufferedImage} object of the first image.
     * @param image2 {@code BufferedImage} object of the second image.
     * @return populated binary matrix.
     */
    int[][] populateTheMatrixOfTheDifferences(BufferedImage image1, BufferedImage image2);

    /**
     * Reads image from the provided path.
     * @param path the path where contains image.
     * @return the {@code BufferedImage} object of this specific image.
     */
    BufferedImage readImageFromResources(String path) throws URISyntaxException, IOException;

    /**
     * Make a copy of the {@code BufferedImage} object.
     * @param image the provided image.
     * @return copy of the provided image.
     */
    BufferedImage copyImage(BufferedImage image);

    /**
     * Save image to the provided path.
     * @param path the path to the saving image.
     * @param image the {@code BufferedImage} object of this specific image.
     */
    void saveImage(String path, BufferedImage image) throws IOException;
}
