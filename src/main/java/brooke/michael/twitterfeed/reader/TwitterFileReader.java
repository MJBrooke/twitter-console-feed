package brooke.michael.twitterfeed.reader;

import brooke.michael.twitterfeed.exception.FailedToReadFileException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class TwitterFileReader {

    public List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch(InvalidPathException | IOException ex) {
            throw new FailedToReadFileException("Failed to read file at path " + path, ex);
        }
    }

}
