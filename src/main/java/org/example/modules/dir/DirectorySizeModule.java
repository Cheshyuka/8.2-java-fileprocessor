package org.example.modules.dir;

import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectorySizeModule implements Module {
    @Override
    public boolean supports(File file) { return file.isDirectory(); }

    @Override
    public String getDescription() { return "Подсчет размера всех файлов в каталоге"; }

    @Override
    public void process(File dir) {
        long size = calculateSize(dir);
        System.out.println("Общий размер: " + size + " байт (" + (size / 1024) + " КБ)");
    }

    private long calculateSize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                size += f.isDirectory() ? calculateSize(f) : f.length();
            }
        }
        return size;
    }
}