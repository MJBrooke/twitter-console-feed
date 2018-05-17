package brooke.michael.twitterfeed.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class TweetServiceTest {


//    @Test
//    public void addTweets_success() {
//        //Given we have a map of the following 2 users
//        UserMap userMap = new UserTreeMap();
//        userMap.put(new User("Alan"));
//        userMap.put(new User("Ward"));
//
//        //And we have the following set of tweets relating to those users
//        when(twitterTextFileReader.readFile(anyString())).thenReturn(Arrays.asList(
//                "Alan> If you have a procedure with 10 parameters, you probably missed some.",
//                "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.",
//                "Alan> Random numbers should not be generated with a method chosen at random."
//        ));
//
//        //When we add tweets
//        tweetBuilder.addTweets(userMap);
//
//        //Then the tweets are appropriately assigned to the Users in the UserMap, with the correct owner and content
//        assertEquals(2, userMap.get("Alan").getTweets().size());
//        assertEquals("If you have a procedure with 10 parameters, you probably missed some.", userMap.get("Alan").getTweets().get(0).getContent());
//        assertEquals("Alan", userMap.get("Alan").getTweets().get(0).getOwner());
//        assertEquals("Random numbers should not be generated with a method chosen at random.", userMap.get("Alan").getTweets().get(1).getContent());
//        assertEquals("Alan", userMap.get("Alan").getTweets().get(1).getOwner());
//
//        assertEquals(1, userMap.get("Ward").getTweets().size());
//        assertEquals("There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.", userMap.get("Ward").getTweets().get(0).getContent());
//        assertEquals("Ward", userMap.get("Ward").getTweets().get(0).getOwner());
//    }

    @Test
    public void getTweets() {
    }
}