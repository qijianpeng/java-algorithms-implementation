package com.jwetherell.algorithms.search;

import org.junit.Test;


public class BinarySearchApproximate<T extends Comparable> {

    public static enum ORDER{
        ASCENDING, DESCENDING
    }
    private static final int SWITCH_TO_BRUTE_FORCE = 200;

    //private static T[] sorted = null;
    private static ORDER order = ORDER.ASCENDING;
    // Assuming the array is sorted
    public static final  <T extends Comparable<T>>  int find(T value, T[] sorted, ORDER order, boolean optimize) {
        if (!BinarySearchApproximate.order.equals(order))BinarySearchApproximate.order = order;
        switch (order){
            case ASCENDING:
                return recursiveFind(value,sorted, 0, sorted.length - 1, optimize);
            case DESCENDING:
                return recursiveFindDescending(value, sorted,0, sorted.length - 1, optimize);
        }
        return -1;
    }

    private static  <T extends Comparable<T>>  int recursiveFindDescending(T value, T[] sorted, int start,
                                                                           int end, boolean optimize) {
        if (end - start <= 1) {
            T lastValue = sorted[end]; // start==end
            if (value.compareTo(lastValue) >= 0)
                return start; // start==end

            else return Integer.MAX_VALUE;
        }

        int low = start;
        int high = end + 1; // zero indexed, so add one.
        int middle = low + ((high - low) / 2);

        T middleValue = sorted[middle];
        if (value.equals(middleValue))
            return middle;
        if (value.compareTo(middleValue) > 0) {
            if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
                return linearSearchDescending(value, sorted, start, middle-1);
            return recursiveFindDescending(value, sorted, start, middle-1, optimize);
        }
        if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
            return linearSearchDescending(value, sorted, middle+1, end);
        return recursiveFindDescending(value, sorted,middle+1, end, optimize);
    }



    private static  <T extends Comparable<T>>  int recursiveFind(T value, T[] sorted, int start,
                                                                 int end, boolean optimize) {
        if (end - start <= 1) {
            T lastValue = sorted[end]; // start==end
            if (value.compareTo(lastValue) <= 0)
                return end; // start==end

            else return Integer.MAX_VALUE;
        }

        int low = start;
        int high = end + 1; // zero indexed, so add one.
        int middle = low + ((high - low) / 2);

        T middleValue = sorted[middle];
        if (value.equals(middleValue))
            return middle;
        if (value.compareTo(middleValue) > 0) {
            if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
                return linearSearch(value,sorted, middle + 1, end);
            return recursiveFind(value, sorted,middle + 1, end, optimize);
        }
        if (optimize && (end - middle) <= SWITCH_TO_BRUTE_FORCE)
            return linearSearch(value, sorted, start, middle - 1);
        return recursiveFind(value, sorted, start, middle - 1, optimize);
    }

    private static final  <T extends Comparable<T>>  int linearSearch(T value, T[] sorted, int start, int end) {
        for (int i = start; i <= end ; i++) {
            T iValue = sorted[i];
            if (value.compareTo(iValue) > 0)continue;
            else return i;
        }
        return Integer.MAX_VALUE;
    }
    private static  <T extends Comparable<T>>  int linearSearchDescending(T value, T[] sorted, int start, int end) {
        for (int i = start; i <= end ; i++) {
            T iValue = sorted[i];
            if (value.compareTo(iValue) < 0)continue;
            else return i;
        }
        return Integer.MAX_VALUE;
    }
   @Test
    public void test(){
        Integer[] array = {1, 4, 7, 8, 9,10};
        Integer target = 10;
       // System.out.println(BinarySearchApproximate.find(target,array,false));
        System.out.println(BinarySearchApproximate.find(target,array, ORDER.ASCENDING,true));
        Integer[] arrayReverse = {10, 9, 8, 7, 4, 1};
        System.out.println(BinarySearchApproximate.find(target,arrayReverse, ORDER.DESCENDING,true));
    }


}
