package org.example.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ModuleRunner {
    @Autowired
    private List<Module> allModules;

    public void run(File file) {
        List<Module> supported = allModules.stream()
                .filter(m -> m.supports(file))
                .collect(Collectors.toList());

        if (supported.isEmpty()) {
            System.out.println("Нет модулей для этого файла.");
            return;
        }

        System.out.println("Доступные действия:");
        for (int i = 0; i < supported.size(); i++) {
            System.out.println((i + 1) + ". " + supported.get(i).getDescription());
        }

        System.out.print("Выберите номер: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= supported.size()) {
            try {
                supported.get(choice - 1).process(file);
            } catch (Exception e) {
                System.err.println("Ошибка при выполнении: " + e.getMessage());
            }
        } else {
            System.out.println("Неверный выбор.");
        }
    }
}
