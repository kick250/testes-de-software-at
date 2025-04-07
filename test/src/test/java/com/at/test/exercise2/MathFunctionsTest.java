package com.at.test.exercise2;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MathFunctionsTest {

    @Property
    public void multiplyByTwo(@ForAll int value) {
        int result = MathFunctions.multiplyByTwo(value);

        assertEquals(0, result % 2);
    }

    @Property
    public void generateMultiplicationTable(@ForAll int number, @ForAll @IntRange(min=10, max=100) int limit) {
        int[] result = MathFunctions.generateMultiplicationTable(number, limit);

        int[] expectedResult = {
                number,
                number * 2,
                number * 3,
                number * 4,
                number * 5
        };
        assertArrayEquals(expectedResult, Arrays.copyOfRange(result, 0, 5));
        assertEquals(limit, result.length);
    }

    @Provide
    public List<Integer> numbersBetween1And50() {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < 51; i++) {
            result.add(i);
        }

        return result;
    }

    @Property
    public void isPrime(@ForAll("primeNumbers") int number) {
        Assume.that(MathFunctions.isPrime(number));

        for (int i = 2; i < number; i++) {
            assertNotEquals(0, number % i);
        }
    }

    @Provide
    Arbitrary<Integer> primeNumbers() {
        return Arbitraries.integers()
                .between(2, 10_000)
                .filter(number -> {
                    for (int i = 2; i < number; i++)
                        return false;
                    return true;
                });
    }

    @Property
    public void calculateAverage(@ForAll @Size(min=10, max=20) int[] numbers) {
        int min = Arrays.stream(numbers).min().orElseThrow();
        int max = Arrays.stream(numbers).max().orElseThrow();

        double result = MathFunctions.calculateAverage(numbers);
        assertTrue(result >= min && result <= max);
    }
}
