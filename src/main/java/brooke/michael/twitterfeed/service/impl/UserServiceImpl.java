package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.map.impl.UserTreeMap;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import brooke.michael.twitterfeed.reader.TwitterFileReader;
import brooke.michael.twitterfeed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Value("${users}")
    private String usersFilePath;

    private TwitterFileReader twitterFileReader;

    private UserBuilder userBuilder;

    @Autowired
    public UserServiceImpl(TwitterFileReader twitterFileReader, UserBuilder userBuilder) {
        this.twitterFileReader = twitterFileReader;
        this.userBuilder = userBuilder;
    }

    public UserMap getUsers() {
        UserMap users = new UserTreeMap();

        twitterFileReader.readFile(usersFilePath)
                .forEach(line -> users.put(userBuilder.buildUser(line)));

        return users;
    }
}
