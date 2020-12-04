package systems.enji.nine2eleven;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Demo for new features in Java 9 through 11.
 */
public class Features {

  public static void main(String[] args) throws Exception {

    httpClient();

    collectionFactories();

    stringUtilities();

    fileUtilities();

    predicateNot();

    toArray();

    varType();
    
    // jshell (REPL)
    // https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm#JSHEL-GUID-630F27C8-1195-4989-9F6B-2C51D46F52C8

    // Shebang:
    // #!/usr/bin/java --source 11
  }

  private static void httpClient() throws Exception {
    // HTTP client API in package java.net.http,
    // easier to use than old low-level APIs (HttpURLConnection)
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://foo.com/")).build();
    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    System.out.println(response.statusCode());
    System.out.println(response.body());
  }

  private static void collectionFactories() {
    // utility methods for creation of (immutable) collections:
    List<String> list = List.of("a", "b", "c");
    Set<String> set = Set.of("a", "b", "c");
    Map<String, Integer> of = Map.of("a", 1, "b", 2, "c", 3);
  }

  private static void stringUtilities() {

    // repeat a String n times
    System.out.println("Hello".repeat(3));

    // transform a String to a Stream line by line
    Stream<String> lines = "first\nsecond".lines();

    // remove white space in front of / after a String
    // (similar to String.trim(), but with slightly different white space definition)
    String stripMe = " Hello ";
    System.out.println("[" + stripMe.strip() + "]");
    System.out.println("[" + stripMe.stripLeading() + "]");
    System.out.println("[" + stripMe.stripTrailing() + "]");

    // check if a String is empty or completely white-space
    System.out.println("  ".isBlank());

  }

  private static void fileUtilities() throws Exception {
    // read a file into a String
    Files.readString(Path.of("file-to-read.txt"));
    // write a String to a file
    Files.writeString(Path.of("file-to-write.txt"), "hello");
  }

  private static void predicateNot() {
    // negating predicate
    "first\n    \nsecond".lines().filter(Predicate.not(String::isBlank)).forEach(System.out::println);
  }

  private static void toArray() {
    // conversion of collections (e.g., List) to Array
    List<String> stringList = new LinkedList<>();
    // previously available methods:
    String[] array1 = stringList.toArray(new String[stringList.size()]);
    String[] array2 = stringList.toArray(new String[0]);
    // new, slightly neue, slightly more convenient method
    String[] array3 = stringList.toArray(String[]::new);
  }

  private static void varType() {
    // types do not have to be provided explicitly;
    // handle with care (can reduce readability)
    var s = "";
  }
  
}
