package org.example.modules.text;

import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TextCharFrequencyModule implements Module {
    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() { return "Частота вхождения каждого символа"; }

    @Override
    public void process(File file) throws Exception {
        String content = Files.readString(file.toPath());
        content.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((c, count) -> System.out.println("'" + c + "': " + count));
    }
}
