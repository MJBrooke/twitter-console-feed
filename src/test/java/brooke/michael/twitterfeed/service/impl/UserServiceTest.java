package brooke.michael.twitterfeed.service.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.builder.UserBuilder;
import brooke.michael.twitterfeed.reader.impl.TwitterTextFileReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private TwitterTextFileReader twitterTextFileReader;

    @Spy
    private UserBuilder userBuilder;

    @InjectMocks
    private UserService userService;

    @Before
    public void before() {
        ReflectionTestUtils.setField(userService, "usersFilePath", "C:/Users/some_user/users.txt");
    }

    @Test
    public void buildUsers_success() {
        //Given the following valid lines representing users and the users they follow
        when(twitterTextFileReader.readFile(anyString())).thenReturn(Arrays.asList(
                "Ward follows Alan",
                "Alan follows Martin",
                "Ward follows Martin, Alan"
        ));

        //When users are built
        UserMap userMap = userService.getUsers();

        assertEquals(3, userMap.size());
    }
}