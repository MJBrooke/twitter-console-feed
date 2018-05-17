package brooke.michael.twitterfeed.map.impl;

import brooke.michael.twitterfeed.map.UserMap;
import brooke.michael.twitterfeed.model.User;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class UserTreeMapTest {

    @Test
    public void testPut() {
        //Given an empty map
        UserMap userMap = new UserTreeMap();

        //When we add a new user
        userMap.put(new User("Jake", Sets.newSet("Alan", "Jacky")));

        //And when we add another user with the same username, but a different set of users being followed
        userMap.put(new User("Jake", Sets.newSet("Alan", "Jacky", "Jemimah")));

        //And when we add another unique user
        userMap.put(new User("Alan", Sets.newSet("Jake")));

        //Then both users and users being followed exists as user objects in the map
        assertEquals(4, userMap.size());
    }

    @Test
    public void testGet() {
        UserMap userMap = new UserTreeMap();

        //Given a user that is put in the map
        User jakeBeforeInsertion = new User("Jake", Sets.newSet("Alan", "Jacky"));
        userMap.put(jakeBeforeInsertion);

        //When the user is retrieved by username
        User jakeFromGet = userMap.get("Jake");

        //Then it should be the same user that was inserted
        assertEquals(jakeBeforeInsertion, jakeFromGet);
    }

    @Test
    public void testEntrySet() {
        //Given a  map with 3 users
        UserMap userMap = new UserTreeMap();
        userMap.put(new User("Jake", Sets.newSet("Alan", "Jacky")));
        userMap.put(new User("Alan", Sets.newSet("Jake")));

        //When the entry set is retrieved
        Set<Map.Entry<String, User>> entries = userMap.entrySet();

        //Then it should reflect the contents of the user map
        assertEquals(3, entries.size());
    }
}