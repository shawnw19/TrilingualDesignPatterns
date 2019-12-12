package Iterators;

import java.util.*;

public class WordList {
    public static void main(String[] args) {
        List<String> words =List.of("hello","sky","red","green","blue");
        for (Iterator iter =words.iterator();iter.hasNext();) {
            System.out.println("Word "+iter.next());
        }
        System.out.println();

        //follows code in zetcode where .length() is invocated from String class
        for (String word: words) {

            System.out.printf("The word %s has %d characters%n",
                    word, word.length());
        }

        System.out.println();
        //Using internal iterators:
        words.stream().forEach(e -> System.out.printf("The word %s has %d characters%n",e,e.length()));
        //lamda expression is used

        //a thread safe removal

        System.out.println();
        System.out.println("This is thread safe: ");
        //ArrayList/Linkedlist is mutable
        List<String> newWords =new LinkedList<>();
        for (String w: words
             ) {
            newWords.add(w);
        }

        Iterator<String> iter =newWords.iterator();
        while (iter.hasNext()){
            String s=iter.next();
            if ("red".equals(s))
                iter.remove();
        }

        newWords.stream().forEach(e -> System.out.printf("The word %s has %d characters%n",e,e.length()));
    }
}
