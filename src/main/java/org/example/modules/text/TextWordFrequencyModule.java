package org.example.modules.text;

import org.example.core.Module;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TextWordFrequencyModule implements Module {
    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() { return "ТОП-5 самых частых слов в тексте"; }

    @Override
    public void process(File file) throws Exception {
        String content = Files.readString(file.toPath()).toLowerCase();
        Arrays.stream(content.split("[^a-zA-Zа-яА-Я]+"))
                .filter(word -> word.length() > 3)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue() + " раз"));
    }
}