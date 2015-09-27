package com.alph.excercise;

public class QuickSort {

    public void sort(int[] p) {
        quickSort(p, 0, p.length - 1);
    }

    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = partion(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    public int partion(int[] array, int start, int end) {
        int pivot = array[end];
        int pIndex = start;

        for (int i = start; i <= (end -1); i++) {
            if (array[i] <= pivot) {
                swap(array, i, pIndex);
                pIndex++;
            }
        }
        swap(array, pIndex, end);
        return pIndex;
    }

    private void swap(int[] array, int i, int j) {
        if (array.length -1 < (i + 1) || array.length <  (j + 1) || array[i] == array[j])
            return;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return;
    }
}
