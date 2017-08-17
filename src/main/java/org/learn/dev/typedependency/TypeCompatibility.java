/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

/**
 *
 * @author disen
 */
public class TypeCompatibility 
{
    private Number numberType ;
    private Integer intType ;

    public TypeCompatibility(Number i_number, Integer i_int) 
    {
        numberType = i_number;
        intType    = i_int;
    }
    
    public void display() 
    {
        System.out.printf("#-- Before compatibility change: Number: %d | Integer: %d %n"
                , numberType,
                intType);
        Number origNumber = numberType;
        Integer origInt = intType;
        
        // Integer is implicitly compatible to Number
        numberType = intType;
        System.out.printf("#-- Integer is implicitly compatible to number.Number: %d | Integer: %d %n"
                , numberType,
                intType);
        numberType = origNumber;
        
        // Number is explicitly compatible to Integer
        intType = (Integer)numberType;
        System.out.printf("#-- Number is explicitly compatible to Integer. Number: %d | Integer: %d %n"
                , numberType,
                intType);
        intType = origInt;
    }
    
    
    
    public static void main(String[] args) 
    {
        TypeCompatibility tc = new TypeCompatibility(Integer.MAX_VALUE, 11);
        tc.display();
    }
}
