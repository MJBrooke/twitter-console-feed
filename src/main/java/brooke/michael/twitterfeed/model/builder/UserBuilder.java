package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.exception.InvalidFileLineFormatException;
import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.map.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class UserBuilder {

    private static final String USER_LINE_VALIDATION_REGEX = "(\\w+) follows (\\w+)(,\\s+\\w+)*";
    private static final String MEMBER_FOLLOWS_SPLIT_DELIMITER = " follows ";
    private static final String FOLLOWS_LIST_DELIMITER = ", ";

    @Value("${users}")
    private String usersFilePath;

    private TwitterFileReader twitterFileReader;

    @Autowired
    public UserBuilder(TwitterFileReader twitterFileReader) {
        this.twitterFileReader = twitterFileReader;
    }

    public UserMap buildUsers() {
        var users = new UserMap();

        twitterFileReader.readFile(usersFilePath)
                .forEach(line -> users.put(buildUser(line)));

        return users;
    }

    private User buildUser(String line) {
        if(!line.matches(USER_LINE_VALIDATION_REGEX)) {
            throw new InvalidFileLineFormatException("Invalid line in the User file: '" + line + "'");
        }

        var tokens = line.split(MEMBER_FOLLOWS_SPLIT_DELIMITER);

        var following = tokens[1].split(FOLLOWS_LIST_DELIMITER);

        return new User(tokens[0], Arrays.stream(following).collect(Collectors.toSet()));
    }
}
