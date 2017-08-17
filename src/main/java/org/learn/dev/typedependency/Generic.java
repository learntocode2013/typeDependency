/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.time.LocalDate;

/**
 *
 * @author disen
 * @param <T>
 */
public class Generic<T> 
{
    public T _data;
    
    void write(T data) 
    {
        _data = data;
    }
    
    T read() 
    {
        return _data;
    }
    
    /**
     * Wildcard is not a type, so no type is compatible to it, not event Object <br>
     * so, ? = new T() , ? = new Object() will not work. <br>
     * <br>
     * Only, Object o = ? works. <br>
     * No type is compatible to ? but ? is compatible to the upper bound.
     */
    public static void main(String[] args) 
    {
        Generic<?> wildcardReference = new Generic();
        //wildcardReference.write(new Object()); type error
        ((Generic<Object>)wildcardReference).write(new Object());
        
        Object data = wildcardReference.read();
        
        Generic<? extends Employee> boundedwildcardReference = new Generic<Engineer>();
        //boundedwildcardReference.write(new Employee("disen",1)); //type error
        //boundedwildcardReference.write(new Engineer("disen",1,new String[]{"java"})); //type error
        ((Generic<Employee>)boundedwildcardReference).write(new Employee("disen",1,LocalDate.now()));
        ((Generic<Employee>)boundedwildcardReference).write(new Engineer("disen",1,LocalDate.now(),new String[]{"java"}));
        ((Generic<Engineer>)boundedwildcardReference).write(new Engineer("disen",1,LocalDate.now(),new String[]{"java"}));
        
        boundedwildcardReference = new Generic<Engineer>();
        Object oData = boundedwildcardReference.read();
        
        Employee superType = boundedwildcardReference.read();
        //Engineer subType1   = boundedwildcardReference.read(); //type error
        Engineer subType2 = ((Generic<Engineer>)boundedwildcardReference).read();
        Engineer subType3 = (Engineer)boundedwildcardReference.read();
        
        //========================================================================================//
        // Rules for writing/accessing types in type parameters
        //========================================================================================//
        wildcardReference = new Generic();
        //wildcardReference._data = new Object(); //type error: remember nothing is compatible to ?
        // ? is compatible to its upper bound, which is object in this case.
        Object wildRead = wildcardReference.read();
        //String str = wildcardReference._data; //type error: ? is not compatible to anything but Object
        Generic<? extends String> covariant = new Generic<String>();
        //covariant._data = "text"; //type error: remember nothing is compatible to ?
        ((Generic<String>)covariant)._data = "text"; // see, we need explicit casting
        String str = covariant.read(); // remember ? is compatible to its upper bound.
        
        //========================================================================================//
        // Contravariance rules in Generic types
        //========================================================================================//
        Generic<? super Engineer> contraviantReference = null;
        contraviantReference = new Generic<Employee>(); // contravariant
        contraviantReference = new Generic<Engineer>();
        contraviantReference = new Generic<Object>();
        
        Generic<? super Object> contravariant0 = null;
        contravariant0 = new Generic<Object>();
        contravariant0._data = new Object();
        contravariant0.write(new Object());
        Object oRead = contravariant0.read();
        
        Generic<? super String> contravariantS = new Generic<String>();
        contravariantS._data = "text";
        contravariantS.write("text");
        //String read = contravariantS.read(); //type error: since now ? is compatible to Object and
                                             // not String
                                             
        //=======================================================//
        // Contravariant accessing variables of a type parameter
        //========================================================
        Generic<? extends Human> covariantReference = null;
        Generic<? super Engineer> contravariantReference = null;
        
        contravariantReference.write(new Engineer("Dibakar",864033,LocalDate.of(2012, 12, 12),new String[]{"Java"}));
        contravariantReference.write(new SoftwareEngineer("Dibakar",864033,LocalDate.of(2012, 12, 12),new String[]{"Java"}));
        //contravariantReference.write(new Employee("Dibakar", 1, LocalDate.MIN)); type error
        ((Generic<? super Employee>)contravariantReference).write(new Employee("Dibakar", 1, LocalDate.MIN));
        
        // Engineer e = contravariantReference.read(); type error
    }
}
