package org.example.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageExifModule implements Module {
    @Override
    public boolean supports(File file) {
        String n = file.getName().toLowerCase();
        return file.isFile() && (n.endsWith(".jpg") || n.endsWith(".jpeg"));
    }

    @Override
    public String getDescription() { return "Вывод EXIF информации"; }

    @Override
    public void process(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        ExifIFD0Directory dir = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        if (dir != null) {
            dir.getTags().forEach(tag -> System.out.println(tag.getTagName() + ": " + tag.getDescription()));
        } else {
            System.out.println("EXIF данные не найдены.");
        }
    }
}