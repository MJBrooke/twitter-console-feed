package brooke.michael.twitterfeed.reader;

import java.util.List;

public interface TwitterFileReader {

    /**
     * Takes a file path and creates a list of strings representing each line of the file
     *
     * @param path The path leading to the file to be read
     * @return A list of Strings - one for each line of the file
     */
    List<String> readFile(String path);
}
