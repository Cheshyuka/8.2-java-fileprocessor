package org.example.modules.image;

import org.example.core.Module;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class ImageDominantColorModule implements Module {
    @Override
    public boolean supports(File file) {
        String n = file.getName().toLowerCase();
        return file.isFile() && (n.endsWith(".jpg") || n.endsWith(".png") || n.endsWith(".jpeg"));
    }

    @Override
    public String getDescription() { return "Определить доминирующий цвет"; }

    @Override
    public void process(File file) throws Exception {
        BufferedImage img = ImageIO.read(file);
        if (img == null) return;

        Map<Integer, Integer> colorCount = new HashMap<>();
        for (int x = 0; x < img.getWidth(); x += 5) {
            for (int y = 0; y < img.getHeight(); y += 5) {
                int rgb = img.getRGB(x, y) & 0xFFFFFF;
                colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
            }
        }

        int dominantRgb = colorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(0);

        System.out.printf("Доминирующий цвет (HEX): #%06X%n", dominantRgb);
    }
}