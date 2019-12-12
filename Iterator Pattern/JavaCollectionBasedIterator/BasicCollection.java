package gray.adts.shapes.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class BasicCollection<E> extends AbstractCollection<E> implements Cloneable {
    private static final int DEFAULT_SIZE =100;

    private E[] collection;

    private int size;

    protected  transient int modCount;

    public BasicCollection() {
        collection = (E[])(new Object[DEFAULT_SIZE]);
        size = 0;
        modCount = 0;
    }
    public BasicCollection(Collection<? extends E>c){
        if (c==null)
            throw new java.lang.IllegalArgumentException();
        int size=c.size()>DEFAULT_SIZE ? (c.size()*110)/100 :DEFAULT_SIZE;
        collection =(E[]) new Object[size];
        c.toArray(collection);
        modCount =0;
    }

//from util.AbstractCollection
    /*    public boolean add(E e) {
        throw new UnsupportedOperationException();
    */
/*both methods have same erasure yet neither overrides the other
    */
    public boolean add (E element) {

        if (element == null)
            throw new java.lang.IllegalArgumentException();
        if (this.size==collection.length)
            resize(this.size+this.size/2);
        collection[size]=element;
        size++;
        modCount++;
        return true;
    }

    public boolean remove(Object element){
        if (element ==null)
            throw new java.lang.IllegalArgumentException();
        int p = find(element);
        if (p==-1)return false;
        for (int i = p; i <size-1; i++) {
            collection[i]=collection[i=1];
        }
        collection[size-1]=null;

        size--;
        modCount++;
        return true;
    }

    public Object clone() {
        BasicCollection c = null;
        //1.clone the entire collection
        try {
            c = (BasicCollection) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        //2.clone the collection array
        c.collection = (Object[]) this.collection.clone();
        return c;
    }

    public int size() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return new BasicIterator();
    }

    private void resize(int new_length) {
        E[] temp = (E[]) new Object [new_length];
        for (int i = 0; i < collection.length; i++) {
            temp[i] = collection[i];
            collection[i] = null;
        }
        collection = temp;
    }

    private int find(Object element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(collection[i]))
                return i;
        }
        return -1;//failure
    }

    private class BasicIterator implements Iterator {
        private int cursor;

        private int expectedModcount;

        private boolean okToRemove;

        public BasicIterator() {
            cursor = 0;
            expectedModcount = modCount;
            okToRemove = false;
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            if (expectedModcount != modCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            okToRemove = true;

            E element = collection[cursor];
            cursor++;
            return element;
        }

        public void remove() {
            if (expectedModcount != modCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            okToRemove = false;

            --cursor;
            BasicCollection.this.remove(collection[cursor]);
            expectedModcount++;
        }
    }
}
