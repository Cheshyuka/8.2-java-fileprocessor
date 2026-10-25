package org.example;

import org.example.core.ModuleRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class FileProcessorApplication implements CommandLineRunner {
    @Autowired
    private ModuleRunner moduleRunner;

    public static void main(String[] args) {
        SpringApplication.run(FileProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу или папке аргументом.");
            return;
        }
        File file = new File(args[0]);
        if (file.exists()) {
            moduleRunner.run(file);
        } else {
            System.out.println("Файл не найден: " + file.getAbsolutePath());
        }
    }
}