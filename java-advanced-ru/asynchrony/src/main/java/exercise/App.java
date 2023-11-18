package exercise;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

class App {

    // BEGIN
    public static CompletableFuture<byte[]> readFile(String source) {
        CompletableFuture<byte[]> readSource = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readAllBytes(Paths.get(source));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex->{
            System.out.println(ex.getMessage());
            return null;
        });
        return readSource;
    }

    public static CompletableFuture<String> unionFiles(String src1, String src2, String dest) {
        CompletableFuture<byte[]> b1 = readFile(src1);
        CompletableFuture<byte[]> b2 = readFile(src2);
        CompletableFuture<String> writeDest = b1.thenCombine(b2, (v1, v2) -> {
            try {
                Path path = Paths.get(dest);
                Files.write(path, v1);
                Files.write(path, v2, StandardOpenOption.APPEND);
                return String.join("", Files.readAllLines(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).exceptionally(ex->{
            System.out.println(ex.getMessage());
            return null;
        });
        return writeDest;
    }

    public static CompletableFuture<Long> getDirectorySize(String dir){
        CompletableFuture<Long> dirSize = CompletableFuture.supplyAsync(()->{
            try {
                Path path = Paths.get(dir);
                return Files.list(path)
                        .filter(p->!Files.isDirectory(p))
                        .map(p->p.toFile().length())
                        .mapToLong(s->s).sum();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex->{
            System.out.println(ex.getMessage());
            return null;
        });
        return dirSize;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/dest.txt");

        CompletableFuture<Long> dirsize = App.getDirectorySize("src/main/resource/");

        // END
    }
}

