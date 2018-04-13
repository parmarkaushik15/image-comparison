package ua.comparison.image.service.impl;

import ua.comparison.image.ImageComparisonTools;
import ua.comparison.image.service.ComparisonService;
import ua.comparison.image.service.ComparisonToolsService;
import ua.comparison.image.service.ValidationService;

import java.awt.image.BufferedImage;

import static ua.comparison.image.ImageComparisonTools.populateTheMatrixOfTheDifferences;

public class ComparisonServiceImpl implements ComparisonService {

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

    private ValidationService validationService = new ValidationServiceImpl();

    private ComparisonToolsService comparisonToolsService = new ComparisonToolsServiceImpl();

    @Override
    public BufferedImage compareImages(BufferedImage image1, BufferedImage image2) {
        validationService.validateImageSizes(image1, image2);
        int[][] matrix = comparisonToolsService.populateTheMatrixOfTheDifferences(image1, image2);
        BufferedImage outImg = comparisonToolsService.copyImage(image2);

        return null;
    }
}
