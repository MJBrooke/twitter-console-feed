package brooke.michael.twitterfeed.runner;

import brooke.michael.twitterfeed.model.User;
import brooke.michael.twitterfeed.set.UserMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class TwitterFeedCommandLineRunner implements CommandLineRunner {

    @Value("${users}")
    private String usersFilePath;

    @Value("${tweets}")
    private String tweetsFilePath;

    @Override
    public void run(String... args) throws Exception {
        UserMap users = getUsers();
        System.out.println(users);
    }

    private UserMap getUsers() throws IOException {
        var users = new UserMap();

        var path = Paths.get(usersFilePath);

        //TODO - Check that the file exists before continuing - exception otherwise
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> users.put(buildUser(line)));
        lines.close();

        return users;
    }

    private User buildUser(String line) {
        //TODO - REGEX check before continuing - exception if messed up
        var tokens = line.split(" follows ");

        var following = tokens[1].split(", ");

        return new User(tokens[0], Arrays.stream(following).collect(Collectors.toSet()));
    }
}
