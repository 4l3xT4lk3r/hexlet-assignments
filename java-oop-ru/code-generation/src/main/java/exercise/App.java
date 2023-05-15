package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
//    Создайте класс App с публичными статическими методами:
//
//    save() — принимает в качестве аргумента путь к файлу (объект класса Path) и объект класса Car.
//    Метод сохраняет представление объекта в файл по переданному пути.
//    Чтобы сохранить объект в файл, вам нужно будет представить его в виде строки (сериализовать).
//
//    extract() — принимает в качестве аргумента путь к файлу (объект класса Path),
//    в котором содержится JSON представление объекта Car и возвращает инстанс класса Car с соответствующими свойствами.

    public static void save(Path path, Car car){
        String json = car.serialize();
        try {
            Files.writeString(path,json);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Car extract(Path path){
        String json = null;
        try {
            json = Files.readString(path);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return Car.unserialize(json);
    }
}

// END
