/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Demonstrates co-variance with dependent type - array <br>
 * <br>
 * 1. Demonstrates covariance with reference type arrays <br>
 * 
 */
public class DemoCovariance 
{
    private Integer[] intArray; // dependent on type Integer
    private Number[] numArray; // dependent on type Number
    private Employee[] superArray;
    private Engineer[] subArray;
    
    public DemoCovariance(Integer[] i_intArray, Number[] i_numArray) 
    {
        intArray = i_intArray;
        numArray = i_numArray;
    }

    public DemoCovariance(Employee[] superArray, Engineer[] subArray) 
    {
        this.superArray = superArray;
        this.subArray = subArray;
    }
    
    public void display(boolean inbuilt) 
    {
        if(inbuilt) { displayNumberRefs(); return ; }
        displayCustomRefs();
    }
    
    private void displayCustomRefs() 
    {
        System.out.printf("#-- Before covariance: Super_Array: %s | Sub_Array: %s %n", 
                Arrays.asList(superArray),
                Arrays.asList(subArray));
        
        superArray = subArray;
        System.out.printf("#-- Arrays of reference types are implicitly covariant. Super_Array: %s "
                + "| Sub_Array: %s %n", 
                Arrays.asList(superArray),
                Arrays.asList(subArray));
        
        // This will give runtime exception since due to covariance, the compiler cannot 
        // check array element assignment at compile time, but the JVM does it at runtime.
        superArray[0] = new Employee("Martin", 0,LocalDate.now());
    }
    
    private void displayNumberRefs() 
    {
        System.out.printf("#-- Before covariance: Num_Array: %s | Int_Array: %s %n", 
                Arrays.asList(numArray),
                Arrays.asList(intArray));
        
        Integer[] origIntArray = intArray;
        Number[] origNumArray = numArray;
        
        // Since Integer is compatible to Number, integer array is covariant to number array
        numArray = intArray;
        System.out.printf("#-- Arrays of reference types are implicitly covariant. Num_Array: %s | Int_Array: %s %n", 
                Arrays.asList(numArray),
                Arrays.asList(intArray));
        
      numArray = origNumArray;
      
      // Array types are not implicitly contravariant in Java
      intArray = (Integer[])numArray;
      System.out.printf("#-- Arrays of reference types are explicitly contravariant in Java. Num_Array: %s | Int_Array:"
              + " %s %n", Arrays.asList(numArray),Arrays.asList(intArray));
      
      /* ************************************
      primitive arrays are invariant in Java
      ***************************************
      int[] a  = {1,2,3,4};
      long[] b = {10,20,30,40};
      
      b = (long[])a;
      ************************ */
    }
    
    public static void main(String[] args) 
    {
       //new DemoCovariance(new Integer[]{1,2,3,4}, new Integer[]{10,20,30,40}).display();
        new DemoCovariance(new Employee[]{new Employee("dibakar",1,LocalDate.now())}, 
                new Engineer[]{new Engineer("dibakar",1,LocalDate.now(),new String[]{"java"})})
                .display(false);
    }
    
}
