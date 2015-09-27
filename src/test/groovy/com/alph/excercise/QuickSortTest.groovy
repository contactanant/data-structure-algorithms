package com.alph.excercise

class QuickSortTest extends spock.lang.Specification {

    QuickSort quickSort = new QuickSort()


    def "should partition the array correctly and return pivot"() {
        given:
        int[] intArray = [5, 15, 10, 3, 2, 1, 4]

        when:
        def pIndex = quickSort.partion(intArray, 0, 6)

        then:
        pIndex == 3
        intArray == [3, 2, 1, 4, 15, 10, 5]
    }

    def "should Sort the array correctly"() {
        given:
        int[] intArray = [5, 10, 3, 2, 1, 4, 5, 12]

        when:
        quickSort.sort(intArray)

        then:
        intArray == [1, 2, 3, 4, 5, 5, 10, 12]
    }
}
