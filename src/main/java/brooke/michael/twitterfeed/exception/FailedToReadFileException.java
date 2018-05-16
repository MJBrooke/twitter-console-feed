package brooke.michael.twitterfeed.exception;

import org.springframework.boot.ExitCodeGenerator;

public class FailedToReadFileException extends RuntimeException implements ExitCodeGenerator {

    private static final int FILE_READER_EXIT_CODE = -1;

    public FailedToReadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getExitCode() {
        return FILE_READER_EXIT_CODE;
    }
}
