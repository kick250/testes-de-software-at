package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SortOrderAgnosticBinarySearchTest {

    @Test
    public void testAscending() {
        int[] arr = {1, 2, 3, 4, 5}; // for ascending order.
        int target = 2;
        int ans = SortOrderAgnosticBinarySearch.find(arr, target);
        int excepted = 1;
        assertEquals(excepted, ans);
    }

    @Test
    public void testAscending_keyGreaterThanMid() {
        int[] arr = {1, 3, 5, 7, 9};
        int key = 7;
        int result = SortOrderAgnosticBinarySearch.find(arr, key);
        assertEquals(3, result);
    }

    @Test
    public void testAscending_WhenNotFound() {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 10;
        int ans = SortOrderAgnosticBinarySearch.find(arr, target);
        int excepted = -1;
        assertEquals(excepted, ans);
    }

    @Test
    public void testDescending() {
        int[] arr = {5, 4, 3, 2, 1}; // for descending order.
        int target = 2;
        int ans = SortOrderAgnosticBinarySearch.find(arr, target);
        int excepted = 3;
        assertEquals(excepted, ans);
    }

    @Test
    public void testDescending_keyLessThanMid() {
        int[] arr = {10, 8, 6, 4, 2, 1, 0 };
        int key = 2;
        int result = SortOrderAgnosticBinarySearch.find(arr, key);
        assertEquals(4, result);
    }
}
