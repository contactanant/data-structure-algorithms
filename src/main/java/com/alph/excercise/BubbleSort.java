package com.alph.excercise;


public class BubbleSort {

    public <T> T[] bubbleSort(T[] array) {

        if (array == null || array.length == 1) {
            return array;
        }

        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (((Comparable) array[j]).compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    private <T> void swap(T[] array, int i, int j) {
        if (array.length -1 < (i + 1) || array.length <  (j + 1))
            return;
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return;
    }
}
