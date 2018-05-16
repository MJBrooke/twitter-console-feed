package brooke.michael.twitterfeed.exception;

import org.springframework.boot.ExitCodeGenerator;

public class InvalidFileLineFormatException extends RuntimeException implements ExitCodeGenerator {

    private static final int INVALID_FILE_LINE_EXIT_CODE = -2;

    public InvalidFileLineFormatException(String message) {
        super(message);
    }

    @Override
    public int getExitCode() {
        return INVALID_FILE_LINE_EXIT_CODE;
    }
}