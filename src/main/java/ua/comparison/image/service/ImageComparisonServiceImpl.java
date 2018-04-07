package ua.comparison.image.service;

import java.awt.image.BufferedImage;

import static ua.comparison.image.service.ImageComparisonTools.checkCorrectImageSize;
import static ua.comparison.image.service.ImageComparisonTools.populateTheMatrixOfTheDifferences;

public class ImageComparisonServiceImpl implements ImageComparisonService {
    @Override
    public BufferedImage compareImages(BufferedImage image1, BufferedImage image2) {
        checkCorrectImageSize(image1, image2);
        int[][] matrix = populateTheMatrixOfTheDifferences(image1, image2);
        BufferedImage outImg = ImageComparisonTools.copyImage(image2);
        return null;
    }
}
