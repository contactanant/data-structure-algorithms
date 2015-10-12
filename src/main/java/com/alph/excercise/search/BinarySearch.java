package com.alph.excercise.search;

public class BinarySearch<T> {

    public <T> int search(T item, T[] array) {
        int lowerIndex = 0;
        int higherIndex = array.length - 1;

        if (array == null) {
            return -1;
        }

        while (higherIndex >= lowerIndex) {
            int index = (higherIndex + lowerIndex) / 2;

            int comparison = ((Comparable) item).compareTo(array[index]);

            switch (comparison) {
                case 0: return index;
                case -1: higherIndex = index -1; break;
                case 1: lowerIndex = index + 1;
            }
        }
        return -1;
    }

}
