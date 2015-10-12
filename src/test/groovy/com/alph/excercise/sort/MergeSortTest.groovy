package com.alph.excercise.sort

import com.alph.excercise.sort.MergeSort

class MergeSortTest extends spock.lang.Specification {

    MergeSort mergeSort = new MergeSort()


    def "should Sort the array"() {
        given:
        int[] intArray = [5, 10, 3, 2, 1]

        when:
        def sortedArray = mergeSort.sort(intArray)

        then:
        sortedArray == [1, 2, 3, 5, 10]
    }

    def "should merge the two sorted array"() {
        given:
        int[] intArray1 = [5, 10]
        int[] intArray2 = [1, 2, 3]
        int[] finalArray = new int[intArray1.length + intArray2.length]

        when:
        mergeSort.merge(intArray1, intArray2, finalArray)

        then:
        finalArray == [1, 2, 3, 5, 10]
    }
}
