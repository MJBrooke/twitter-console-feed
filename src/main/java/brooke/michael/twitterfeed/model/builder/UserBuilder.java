package brooke.michael.twitterfeed.model.builder;

import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.set.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
//TODO - make an interface
public class UserBuilder {

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

        List<String> strings = twitterFileReader.readFile(usersFilePath);

        strings.forEach(line -> users.put(buildUser(line)));

        return users;
    }

    private User buildUser(String line) {
        //TODO - REGEX check before continuing - exception if messed up
        var tokens = line.split(MEMBER_FOLLOWS_SPLIT_DELIMITER);

        var following = tokens[1].split(FOLLOWS_LIST_DELIMITER);

        return new User(tokens[0], Arrays.stream(following).collect(Collectors.toSet()));
    }
}
