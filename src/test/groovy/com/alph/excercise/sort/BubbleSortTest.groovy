package com.alph.excercise.sort

import com.alph.excercise.sort.BubbleSort

class BubbleSortTest extends spock.lang.Specification {

    BubbleSort bubbleSort = new BubbleSort()


    def "should Sort the array"() {
        given:
        int[] intArray = [5, 10, 3, 2, 1]

        when:
        def sortedArray = bubbleSort.bubbleSort(intArray)

        then:
        sortedArray == [1, 2, 3, 5, 10]

    }
}
