package org.example.modules.dir;

import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class DirectoryListModule implements Module {
    @Override
    public boolean supports(File file) { return file.isDirectory(); }

    @Override
    public String getDescription() { return "Вывод списка файлов в каталоге"; }

    @Override
    public void process(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(f -> System.out.println(f.getName()));
        }
    }
}