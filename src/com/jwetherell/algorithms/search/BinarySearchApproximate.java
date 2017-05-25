package com.jwetherell.algorithms.search;

import org.junit.Test;

public class BinarySearchApproximate {

    public static enum ORDER{
        ASCENDING, DESCENDING
    }
    private static final int SWITCH_TO_BRUTE_FORCE = 200;

    private static int[] sorted = null;
    private static ORDER order = ORDER.ASCENDING;

    // Assuming the array is sorted
    public static final int find(int value, int[] array, ORDER order, boolean optimize) {
        if (!BinarySearchApproximate.order.equals(order))BinarySearchApproximate.order = order;
        BinarySearchApproximate.sorted = array;
        switch (order){
            case ASCENDING:
                try {
                    return recursiveFind(value, 0, BinarySearchApproximate.sorted.length - 1, optimize);
                } finally {
                    BinarySearchApproximate.sorted = null;
                }
            case DESCENDING:
                try {
                    return recursiveFindDescending(value, 0, BinarySearchApproximate.sorted.length - 1, optimize);
                } finally {
                    BinarySearchApproximate.sorted = null;
                }
        }
        return Integer.MAX_VALUE;
    }

    private static int recursiveFindDescending(int value, int start, int end, boolean optimize) {
        if (end - start <= 1) {
            int lastValue = sorted[end]; // start==end
            if (value >= lastValue)
                return start; // start==end

            else return Integer.MAX_VALUE;
        }

        int low = start;
        int high = end + 1; // zero indexed, so add one.
        int middle = low + ((high - low) / 2);

        int middleValue = sorted[middle];
        if (value == middleValue)
            return middle;
        if (value > middleValue) {
            if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
                return linearSearchDescending(value, start, middle-1);
            return recursiveFindDescending(value, start, middle-1, optimize);
        }
        if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
            return linearSearchDescending(value, middle+1, end);
        return recursiveFindDescending(value, middle+1, end, optimize);
    }



    private static int recursiveFind(int value, int start, int end, boolean optimize) {
        if (end - start <= 1) {
            int lastValue = sorted[end]; // start==end
            if (value <= lastValue)
                return end; // start==end

            else return Integer.MAX_VALUE;
        }

        int low = start;
        int high = end + 1; // zero indexed, so add one.
        int middle = low + ((high - low) / 2);

        int middleValue = sorted[middle];
        if (value == middleValue)
            return middle;
        if (value > middleValue) {
            if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
                return linearSearch(value, middle + 1, end);
            return recursiveFind(value, middle + 1, end, optimize);
        }
        if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
            return linearSearch(value, start, middle - 1);
        return recursiveFind(value, start, middle - 1, optimize);
    }

    private static final int linearSearch(int value, int start, int end) {
        for (int i = start; i <= end ; i++) {
            int iValue = sorted[i];
            if (value > iValue)continue;
            else return i;
        }
        return Integer.MAX_VALUE;
    }
    private static int linearSearchDescending(int value, int start, int end) {
        for (int i = start; i <= end ; i++) {
            int iValue = sorted[i];
            if (value < iValue)continue;
            else return i;
        }
        return Integer.MAX_VALUE;
    }
/*    @Test
    public void test(){
        int[] array = {1, 4, 7, 8, 9,10};
        Integer target = 10;
       // System.out.println(BinarySearchApproximate.find(target,array,false));
        System.out.println(BinarySearchApproximate.find(target,array, ORDER.ASCENDING,false));
        int[] arrayReverse = {10, 9, 8, 7, 4, 1};
        System.out.println(BinarySearchApproximate.find(target,arrayReverse, ORDER.DESCENDING,false));
    }*/
}
