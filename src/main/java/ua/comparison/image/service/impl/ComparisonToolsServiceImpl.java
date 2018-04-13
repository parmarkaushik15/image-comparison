package ua.comparison.image.service.impl;

import ua.comparison.image.service.ComparisonToolsService;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ComparisonToolsServiceImpl implements ComparisonToolsService {

    @Override
    public int[][] populateTheMatrixOfTheDifferences(BufferedImage image1, BufferedImage image2) {
        return new int[0][];
    }

    @Override
    public BufferedImage readImageFromResources(String path) {
        return null;
    }

    @Override
    public BufferedImage copyImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    @Override
    public void saveImage(String path, BufferedImage image) {

    }
}
