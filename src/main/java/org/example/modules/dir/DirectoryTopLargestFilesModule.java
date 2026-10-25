package org.example.modules.dir;

import org.example.core.Module;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DirectoryTopLargestFilesModule implements Module {
    @Override
    public boolean supports(File file) { return file.isDirectory(); }

    @Override
    public String getDescription() { return "Найти ТОП-3 самых больших файлов"; }

    @Override
    public void process(File dir) {
        List<File> allFiles = new ArrayList<>();
        collectFiles(dir, allFiles);

        allFiles.stream()
                .sorted(Comparator.comparingLong(File::length).reversed())
                .limit(3)
                .forEach(f -> System.out.printf("%s - %d КБ%n", f.getAbsolutePath(), f.length() / 1024));
    }

    private void collectFiles(File dir, List<File> filesList) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    collectFiles(f, filesList);
                } else {
                    filesList.add(f);
                }
            }
        }
    }
}