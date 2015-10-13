package com.alph.excercise.collections

import spock.lang.Specification

class SimpleHashMapTest extends Specification {

    Map<Integer, String> simpleHashMap

    void setup() {
        simpleHashMap = new SimpleHashMap<>()
    }

    def "should store various keys"() {
        given:
        simpleHashMap.put(101, "first")
        simpleHashMap.put(21, "second")
        simpleHashMap.put(21, "second-updated")

        expect:
        simpleHashMap.get(101) == "first"
        simpleHashMap.get(21) == "second-updated"
        simpleHashMap.get(3) == null
        simpleHashMap.size() == 2
        !simpleHashMap.isEmpty()
        simpleHashMap.containsKey(101)
    }

    def "should store null values"() {
        simpleHashMap.put(null, "nullValue")

        expect:
        simpleHashMap.get(null) == "nullValue"
        !simpleHashMap.isEmpty()
        simpleHashMap.containsKey(null)
    }

    def "should resize table as needed"() {
        given:
        (1..50).forEach { t -> simpleHashMap.put(t, "value$t") }

        expect:
        simpleHashMap.get(50) == "value50"
        !simpleHashMap.isEmpty()
    }

    def "should return empty flag if no element"() {
        expect:
        simpleHashMap.isEmpty()
        simpleHashMap.remove(1) == null
        !simpleHashMap.containsKey(1)
    }

    def "should remove second element from same hash and update next link accordingly"() {
        simpleHashMap.put(101, "value1")
        simpleHashMap.put(21, "value2")
        simpleHashMap.put(5, "value3")

        expect:
        simpleHashMap.remove(21) == "value2"
        simpleHashMap.size() == 2
        simpleHashMap.get(101) == "value1"
        simpleHashMap.get(5) == "value3"
    }

    def "should remove first element from same hash"() {
        simpleHashMap.put(101, "value1")
        simpleHashMap.put(21, "value2")

        expect:
        simpleHashMap.remove(101) == "value1"
        simpleHashMap.size() == 1
        simpleHashMap.get(21) == "value2"
    }

    def "put all values of one map into another"() {
        (1..2).forEach { t -> simpleHashMap.put(t, "value$t") }
        Map<Integer, String> secondMap = new SimpleHashMap<>()
        secondMap.putAll(simpleHashMap)

        expect:
        secondMap[1] == "value1"
        secondMap[2] == "value2"
    }

    def "should return set with entries"() {
        (1..2).forEach { t -> simpleHashMap.put(t, "value$t") }

        expect:
        simpleHashMap.entrySet() != null
        simpleHashMap.entrySet().size() == 2

        def iterator = simpleHashMap.entrySet().iterator()

        while (iterator.hasNext()) {
            println iterator.next()
        }

    }

    def "should return string representation of map"(){
        (101..102).forEach { t -> simpleHashMap.put(t, "value$t") }

        expect:
        simpleHashMap.toString() == "{ key=101, value=value101}{ key=102, value=value102}"
    }
}
