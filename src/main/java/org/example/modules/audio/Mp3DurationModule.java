package org.example.modules.audio;

import com.mpatric.mp3agic.Mp3File;
import org.example.core.Module;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3DurationModule implements Module {
    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() { return "Вывод длительности в секундах"; }

    @Override
    public void process(File file) throws Exception {
        Mp3File mp3file = new Mp3File(file);
        System.out.println("Длительность: " + mp3file.getLengthInSeconds() + " сек.");
    }
}