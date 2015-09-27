package com.alph.excercise

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
}
