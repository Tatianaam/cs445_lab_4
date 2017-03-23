package cs445.a1;

import java.util.Arrays;

public class Set<E> implements SetInterface<E> {
    private E[] set;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;


    public Set(int capacity) {
        if(capacity <= 0)
            capacity = 1;
        @SuppressWarnings("unchecked")
        E[] tempSet = (E[]) new Object[capacity];
        set = tempSet;
        numberOfEntries = 0;
    }

    public Set() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        if (getCurrentSize() <= 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean add(E newEntry) throws SetFullException, IllegalArgumentException {
        boolean result = false;
        if ((newEntry != null) && (!contains(newEntry))) {
            if (isFull()) {
                doubleCapacity();
            }
            set[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        } else if (newEntry == null) {
            throw new IllegalArgumentException("Entry is null");
        } else if (isFull()) {
            throw new SetFullException();
        } else
            return false;
    }

    @Override
    public boolean remove(E entry) throws IllegalArgumentException {
        int index;
        E result = null;
        if (contains(entry)) {
            index = getIndexOf(entry);
            result = removeEntry(index);
        }

        return entry.equals(result);
    }

    @Override
    public E remove() {
        E result = removeEntry(numberOfEntries - 1);
        return result;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    @Override
    public boolean contains(E entry) throws IllegalArgumentException {
        if (entry == null)
            throw new IllegalArgumentException("Entry is null");
        return getIndexOf(entry) > -1;
    }

    @Override
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] result = (E[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++){
            result[index] = set[index];
        }
        return result;
    }

    private boolean isFull() {
        return numberOfEntries >= set.length;
    }

    private int getCapacity() {
        return set.length;
    }

    private int getIndexOf(E entry) {
        int where = -1;
        boolean found = false;
        int index = 0;
        while (!found && (index < numberOfEntries)){
            if (entry.equals(set[index])) {
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }

    private E removeEntry(int givenIndex) {
        E result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = set[givenIndex];
            set[givenIndex] = set[numberOfEntries - 1];

            set[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    private void doubleCapacity() {
        int newLength = 2 * set.length;
        set = Arrays.copyOf(set, newLength);
    }

}