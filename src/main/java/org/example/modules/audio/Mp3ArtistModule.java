package org.example.modules.audio;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import org.example.core.Module;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class Mp3ArtistModule implements Module {
    @Override
    public boolean supports(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() { return "Вывод исполнителя и альбома"; }

    @Override
    public void process(File file) throws Exception {
        Mp3File mp3file = new Mp3File(file);
        if (mp3file.hasId3v2Tag()) {
            ID3v2 tag = mp3file.getId3v2Tag();
            System.out.println("Исполнитель: " + tag.getArtist());
            System.out.println("Альбом: " + tag.getAlbum());
        } else if (mp3file.hasId3v1Tag()) {
            ID3v1 tag = mp3file.getId3v1Tag();
            System.out.println("Исполнитель: " + tag.getArtist());
            System.out.println("Альбом: " + tag.getAlbum());
        } else {
            System.out.println("Теги не найдены.");
        }
    }
}