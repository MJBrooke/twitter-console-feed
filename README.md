# twitter-console-feed
Small command line app that reads in text files representing users and tweets and prints it as formatted output to the console.

In order to compile the project, simply open up the project's root directory and enter either:
- gradlew build (if you are on Windows CMD)
- ./gradlew build (if you are using Bash)

### Note!
Due to incompatibilities between JDK 9/10 and some libraries that were used, the project must be compiled with, and run using JDK 8.
At the time of writing, I was using JDK 8 u172.

Once compiled, you can find the Jar file in:
- root project directory> build> libs> twitterfeed-x.x.x.jar

To run the program, you will need to provide 2 file paths leading to the text files representing the Users and Tweets.
- eg. java -jar twitterfeed-1.0.0.jar --users="C:/Users/some_user/users.txt" --tweets="C:/Users/some_user/tweets.txt"

For reference, a well-formed User text file looks as follows:  
Ward follows Alan  
Alan follows Martin  
Ward follows Martin, Alan

Similarly, a well-formed Tweet text file looks as follows:  
Alan> If you have a procedure with 10 parameters, you probably missed some.  
Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.  
Alan> Random numbers should not be generated with a method chosen at random.

## Assumptions Made
- Names provided in the Users and Tweets files are considered unique usernames
- There will not be a tweet from a User that was not listed in the Users file
