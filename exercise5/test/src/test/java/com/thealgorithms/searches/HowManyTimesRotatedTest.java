package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class HowManyTimesRotatedTest {

    @Test
    public void testHowManyTimesRotated() {
        int[] arr1 = {5, 1, 2, 3, 4};
        assertEquals(1, HowManyTimesRotated.rotated(arr1));
        int[] arr2 = {15, 17, 2, 3, 5};
        assertEquals(2, HowManyTimesRotated.rotated(arr2));
    }

    @Test
    public void testMain() {
        String simulatedInput = String.join(System.lineSeparator(),
                "8",
                "11", "12", "15", "18", "2", "5", "6", "8"
        );

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setIn(testIn);
        System.setOut(new PrintStream(testOut));

        try {
            HowManyTimesRotated.main(new String[]{});

            String output = testOut.toString();

            assertEquals("The array has been rotated 4 times\n", output);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
