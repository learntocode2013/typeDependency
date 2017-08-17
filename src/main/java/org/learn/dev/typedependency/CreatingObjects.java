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
public class CreatingObjects 
{
    private static void demoGenericArrayCreation() 
    {
        // We can create array objects only of an unbounded wildcard type
        //Generic<Object>[] genericArray = new Generic<Object>[20]; //type error
        Generic<?>[] wildcardArray = new Generic<?>[20];
        Generic<Object>[] genericArray = (Generic<Object>[])wildcardArray;
        genericArray[0] = new Generic<Object>();
        //genericArray[0] = new Generic<String>(); // type error
        wildcardArray[0] = new Generic<String>();
    }
    
    public static void main(String[] args) 
    {
        demoGenericArrayCreation();
    }
}
