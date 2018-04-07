package ua.comparison.image.service;

import java.awt.image.BufferedImage;

public interface ImageComparisonService {
    BufferedImage compareImages(BufferedImage image1, BufferedImage image2);
}
