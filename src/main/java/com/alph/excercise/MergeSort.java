package com.alph.excercise;

public class MergeSort {

    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        System.arraycopy(array, 0, left, 0, mid);
        sort(left);
        int[] right = new int[array.length - mid];
        System.arraycopy(array, mid, right, 0, array.length - mid);
        sort(right);
        merge(left, right, array);
        return array;
    }

    public void merge(int[] left, int[] right, int[] mergedArray) {

        int i = 0;
        int j = 0;
        int k = 0;
        int nLeft = left.length;
        int nRight = right.length;

        while (k < (nLeft + nRight)) {
            if (j >= nRight || (i < nLeft && left[i] <= right[j])) {
                mergedArray[k] = left[i];
                i++;
            } else {
                mergedArray[k] = right[j];
                j++;
            }
            k++;
        }
    }
}
