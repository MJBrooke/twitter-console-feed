package brooke.michael.twitterfeed.reader.impl;

import brooke.michael.twitterfeed.exception.FailedToReadFileException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TwitterTextFileReader.class)
public class TwitterTextFileReaderTest {

    private TwitterTextFileReader twitterTextFileReader = new TwitterTextFileReader();

    @Test
    public void readFile_success() throws IOException {
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("line1");
        expectedOutput.add("line2");

        PowerMockito.mockStatic(Paths.class);
        PowerMockito.when(Paths.get(anyString())).thenReturn(mock(Path.class));

        PowerMockito.mockStatic(Files.class);
        PowerMockito.when(Files.readAllLines(any(Path.class))).thenReturn(expectedOutput);

        List<String> linesFromFileReader = twitterTextFileReader.readFile("C:/Users/my_user/file_that_cannot_be_read.txt");

        assertEquals(expectedOutput, linesFromFileReader);
    }

    @Test(expected = FailedToReadFileException.class)
    public void readFile_failure_couldNotReadFile() throws IOException {
        PowerMockito.mockStatic(Paths.class);
        PowerMockito.when(Paths.get(anyString())).thenReturn(mock(Path.class));

        PowerMockito.mockStatic(Files.class);
        PowerMockito.when(Files.readAllLines(any(Path.class))).thenThrow(IOException.class);

        twitterTextFileReader.readFile("C:/Users/my_user/file_that_cannot_be_read.txt");
    }

    @Test(expected = FailedToReadFileException.class)
    public void readFile_failure_invalidPathProvided() {
        PowerMockito.mockStatic(Paths.class);
        PowerMockito.when(Paths.get(anyString())).thenThrow(InvalidPathException.class);

        twitterTextFileReader.readFile("/C:/Users/my_user/user.txt");
    }
}