package ua.comparison.image.service;

import java.awt.image.BufferedImage;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateImageSizes(BufferedImage image1, BufferedImage image2) {
        if(image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
            throw new IllegalArgumentException("Images dimensions mismatch");
        }
    }
}
