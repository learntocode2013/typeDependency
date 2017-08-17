/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author disen
 */
public class BoxedBindingOfTypeParams 
{
    /**
     * Here, we're not as interested in the compatibility of <br>
     * references as we are in binding the instantiation. <br>
     */
    private static void demoGenericInstantiationBinding() 
    {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Dibakar",864033,LocalDate.of(2012, 12, 12)));
        employees.add(new Employee("Mark",362122,LocalDate.of(2001, 12, 12)));
        employees.add(new Employee("Kristeen",541298,LocalDate.of(2015, 12, 12)));
        System.out.println("#------Employees before sort ------#");
        employees.stream().forEach(emp -> System.out.println(emp));
        
        /**
         * Takes in List<T> where <T extends Comparable<? super T>>
         * <br>
         * The lower bound of the type parameter allows additional flexibility, <br>
         * however. Comparable doesn't necessarily need to be implemented in the <br>
         * element class; it's enough to have implemented it in the superclass <br>
         */ 
        Collections.sort(employees);
        
        System.out.println();
        System.out.println("#------Employees after sort ------#");
        employees.stream().forEach(emp -> System.out.println(emp));
        System.out.println();
        
        List<Engineer> engineers = new ArrayList<>();
        engineers.add(new Engineer("Dibakar",864033,LocalDate.of(2012, 12, 12),new String[]{"Java"}));
        engineers.add(new Engineer("Mark",362122,LocalDate.of(2001, 12, 12),new String[]{"Go"}));
        engineers.add(new Engineer("Kristeen",541298,LocalDate.of(2015, 12, 12),new String[]{"Scala"}));
        System.out.println("#------Engineers before sort ------#");
        engineers.stream().forEach(emp -> System.out.println(emp));
        
        // static <T extends Comparable<T>> void sort(List<T> list) would have <br>
        // caused this line to be rejected by the compiler.
        Collections.sort(engineers);
        
        System.out.println();
        System.out.println("#------Engineers after sort ------#");
        engineers.stream().forEach(emp -> System.out.println(emp));
    }
    
    public static void main(String[] args) 
    {
        demoGenericInstantiationBinding();
    }
}
