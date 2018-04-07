package ua.comparison.image.service;

import java.awt.image.BufferedImage;

public interface ValidationService {

    void validateImageSizes(BufferedImage image1, BufferedImage image2);
}
