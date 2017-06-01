package com.jwetherell.algorithms.search.inserts;


import com.jwetherell.algorithms.search.BinarySearchApproximate;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by qijianpeng on 26/05/2017.
 */
public class BinarySearchInsert<T extends Comparable<T>> {

    public static <T extends Comparable<T>>  boolean insert(T value, List<T> sortedList,
                                                            Comparator<T> comparator){
       int index = BinarySearchApproximate.find(value, sortedList, comparator, true);
       if ( index < 0) return false;
       sortedList.add(index, value); return true;
    }

    //TODO: To be fixed.
    public static <T extends Comparable<T>>  boolean insert(T value, T[] sortedArrays,
                                                            Comparator<T> comparator){
        int index = BinarySearchApproximate.find(value, sortedArrays, comparator, true);
        if ( index < 0) return false;
        Object[] newArray = new Object[sortedArrays.length+1];
        for(int i = sortedArrays.length-1; i >=index; i--){
           newArray[i+1] = sortedArrays[i];
        }
        newArray[index] = sortedArrays[index];
        for (int i = 0; i < index; i ++){
            newArray[i] = sortedArrays[i];
        }
        return true;
    }
    @Test
    public void testInsert(){
        List<Double> list = new ArrayList<Double>();
        list.add(1.5);
        list.add(4.4);
        list.add(7.3);
        list.add(8.7);
        list.add(9.3);
        list.add(10.4);
        Double target = 7.0;
        Comparator<Double> c = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        };
        insert(target,list,c);
        System.out.println(list);
    }

}
