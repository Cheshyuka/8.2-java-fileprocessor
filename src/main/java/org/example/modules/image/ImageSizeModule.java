package org.example.modules.image;

import org.example.core.Module;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class ImageSizeModule implements Module {
    @Override
    public boolean supports(File file) {
        String n = file.getName().toLowerCase();
        return file.isFile() && (n.endsWith(".jpg") || n.endsWith(".png") || n.endsWith(".jpeg"));
    }

    @Override
    public String getDescription() { return "Вывод размера изображения"; }

    @Override
    public void process(File file) throws Exception {
        BufferedImage img = ImageIO.read(file);
        if (img != null) {
            System.out.println("Размер: " + img.getWidth() + "x" + img.getHeight());
        } else {
            System.out.println("Не удалось прочитать изображение.");
        }
    }
}