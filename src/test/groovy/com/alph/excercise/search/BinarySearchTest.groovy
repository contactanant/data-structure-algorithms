package com.alph.excercise.search

import com.alph.excercise.search.BinarySearch
import spock.lang.Specification

class BinarySearchTest extends Specification {

    BinarySearch binarySearch = new BinarySearch()

    def "should return index of element"() {
        given:
        int[] intArray = [ -5, 12, 15, 20, 30, 72, 456 ]

        expect:
        binarySearch.search(20, intArray) == 3
        binarySearch.search(30, intArray) == 4
    }

}
