package ua.comparison.image.service;

import java.awt.image.BufferedImage;

public interface ComparisonService {
    BufferedImage compareImages(BufferedImage image1, BufferedImage image2);
}
