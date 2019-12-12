import java.util.*;
import gray.adts.shapes.collection.*;

public class IteratorEx {
    public static void main(String[] args) {
        Collection<String> movies = new BasicCollection<String>();
        movies.add("Cinema Paradiso");
        movies.add("Lilies of the Field");
        movies.add("In the Heat of the Night");

        System.out.println("Titles in the collection are: ");
        Iterator<String> iter;

        for (iter = movies.iterator(); iter.hasNext(); )//the collection "movies" entails the interface "iterator"
            System.out.println("    " + iter.next());

        //use iterator's method to remove all titles containing "of"
        iter = movies.iterator();//declare another iterator
        while (iter.hasNext()) {
            String str = iter.next();
            if (str.contains("of"))//str.indexOf("of") != -1
                iter.remove();
        }
        System.out.println();
        System.out.println("After removing all titles containing");
        System.out.println("\"of\", here is the remainder: ");
        //for each(Enhanced for?): for(data_type item : collection)  colon reads "in"
        for (String movie : movies)
            System.out.println("    " + movie);
    }
}
