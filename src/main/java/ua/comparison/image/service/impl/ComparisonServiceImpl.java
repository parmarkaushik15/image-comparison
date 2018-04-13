package ua.comparison.image.service.impl;

import ua.comparison.image.ImageComparisonTools;
import ua.comparison.image.service.ComparisonService;
import ua.comparison.image.service.ComparisonToolsService;
import ua.comparison.image.service.ValidationService;

import java.awt.image.BufferedImage;

import static ua.comparison.image.ImageComparisonTools.populateTheMatrixOfTheDifferences;

public class ComparisonServiceImpl implements ComparisonService {

    private ValidationService validationService = new ValidationServiceImpl();

    private ComparisonToolsService comparisonToolsService = new ComparisonToolsServiceImpl();

    @Override
    public BufferedImage compareImages(BufferedImage image1, BufferedImage image2) {
        validationService.validateImageSizes(image1, image2);
        int[][] matrix = comparisonToolsService.populateTheMatrixOfTheDifferences(image1, image2);
        BufferedImage outImg = ImageComparisonTools.copyImage(image2);

        return null;
    }
}
