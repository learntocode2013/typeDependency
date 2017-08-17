/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * //--------------- Principles -------------//
 * <br>
 * 
 * 1. Generic types in java are implicitly invariant, meaning that different instantiations of a <br>
 * generic type are not compatible among each other. Even type casting will not result in        <br>   
 * compatibility                                                                                 <br>
 * 
 */
public class DemoVarianceInGenericTypes 
{
    // Covariance at work. Not type safe for array element assignments
    static void non_generic_swap(Object[] array, int i, int j) 
    {
        Objects.requireNonNull(array, "Cannot operate on a null array!");
        Object first = array[i];
        array[i] = array[j];
        array[j] = first;
    }
    
    static <T> void generic_swap(T[] array, int i, int j) 
    {
        Objects.requireNonNull(array, "Cannot operate on a null array!");
        T first = array[i];
        array[i] = array[j];
        array[j] = first;
    }
    
    static <T> void generic_concreteList_swap(ArrayList<T> list, int i, int j) 
    {
        Objects.requireNonNull(list, "Cannot operate on a null list!");
        T first = list.get(i);
        T second = list.get(j);
        list.remove(i);
        list.add(i, second);
        list.remove(j);
        list.add(j,first);
    }
    
    // Unspecified covariance at work here.
    static void wildcard_generic_swap(List<?> aList, int i, int j)
    {
        Objects.requireNonNull(aList, "Cannot perform swap on a null aggregate");
        Object first  = aList.get(i);
        Object second = aList.get(j);
        aList.remove(i); 
        // ? is nothing but ? extends Object
        // since, ? = object is not allowed because wildcard is not a type, we need this explicit
        // compatibility via casting
        ((List<Object>)aList).add(i,second);
        aList.remove(j);
        ((List<Object>)aList).add(j,first);
    }
    
    static List<?> bounded_wildcard_increment(List<? extends Number> numberList) 
    {
        Objects.requireNonNull(numberList);
        
        if(numberList.isEmpty()) { return numberList; }
        
        Number elem = numberList.get(0);
        boolean hasFloats = (elem.getClass() == Float.class);
        
        if(hasFloats) 
        {
            return numberList
                    .stream()
                    .map(each -> each.floatValue() + 1.0f)
                    .collect(Collectors.toList());
        }
        return numberList
                .stream()
                .map(each -> each.intValue() + 1)
                .collect(Collectors.toList());
        
    }
    
    /**
     * The following is not possible because the base class of both method param is same: ArrayList
     * 
     * static void doSomething(ArrayList<Object> superGeneric) {}
     * 
     * static void doSomething(ArrayList<Integer> subGeneric) {}
     * @param args
     */
     
   
    public static void main(String[] args) 
    {
        Integer[] intArray = {1,2,3,4};
        String[] strArray = {"one","two","three","four"};
        
        System.out.printf("Array contents before swap: %s = %s | %s = %s %n",
                "intArray",Arrays.asList(intArray),
                "strArray",Arrays.asList(strArray));
        //non_generic_swap(intArray, 0, 3);
        //non_generic_swap(strArray, 0, 3);
        generic_swap(intArray, 0, 3);
        generic_swap(strArray, 0, 3);
        System.out.printf("Array contents after swap: %s = %s | %s = %s %n",
                "intArray",Arrays.asList(intArray),
                "strArray",Arrays.asList(strArray));
        
        
        ArrayList<String> strList = new ArrayList<>();
        strList.add("one");strList.add("two");strList.add("three");strList.add("four");
        
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);intList.add(2);intList.add(3);intList.add(4);
        System.out.printf("List contents before wildcard_generic_swap swap: %s = %s | %s = %s %n",
                "intList",intList,
                "strList",strList);
        //generic_concreteList_swap(intList,0,3);
        //generic_concreteList_swap(strList, 0, 3);
        wildcard_generic_swap(intList, 0, 3);
        wildcard_generic_swap(strList, 0, 3);
        System.out.printf("List contents after wildcard_generic_swap swap: %s = %s | %s = %s %n",
                "intList",intList,
                "strList",strList);
        
        ArrayList<Object> superGeneric = new ArrayList<>();
        ArrayList<Integer> subGeneric  = new ArrayList<>();
        
        System.out.printf("Class of superGeneric: %s | Class of subGeneric: %s %n",
                superGeneric.getClass(),
                subGeneric.getClass());
        
        System.out.println(""); System.out.println("");
        
        strList.clear();
        strList.add("one");strList.add("two");strList.add("three");strList.add("four");
        
        intList.clear();
        intList.add(1);intList.add(2);intList.add(3);intList.add(4);
        
        ArrayList<Float> floatList = new ArrayList<Float>();
        floatList.add(1.1f);floatList.add(2.1f);
        
        System.out.printf("List contents before increment: %s = %s | %s = %s %n",
                "floatList",floatList,
                "intList",intList); 
        List<?> resultFloatList = bounded_wildcard_increment(floatList);
        List<?> resultIntList   = bounded_wildcard_increment(intList);
        
        System.out.printf("List contents after increment: %s = %s | %s = %s %n",
                "floatList",resultFloatList,
                "intList",resultIntList);
    }
}
