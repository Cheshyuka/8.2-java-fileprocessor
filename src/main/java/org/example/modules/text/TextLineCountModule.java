package org.example.modules.text;

import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Component
public class TextLineCountModule implements Module {
    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() { return "Подсчет количества строк"; }

    @Override
    public void process(File file) throws Exception {
        long count = Files.lines(file.toPath()).count();
        System.out.println("Количество строк: " + count);
    }
}