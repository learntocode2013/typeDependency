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
 * Observations: <br>
 * 1. Collections.sort takes in a contravariant type parameter
 * 2. Collections.max takes in a covariant and a contravariant type parameter
 * 
 */
public class ContravariantParameters 
{
    private static void demoContravariantTypeParamInMethod() 
    {
        System.out.println("#=== Demonstrating contravariant type parameters in methods =====#");
        
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Dibakar",864033,LocalDate.of(2012, 12, 12)));
        employees.add(new Employee("Mark",864033,LocalDate.of(2001, 12, 12)));
        employees.add(new Employee("Kristeen",864033,LocalDate.of(2015, 12, 12)));
        
        
        System.out.println("#------Employees before sort ------#");
        employees.stream().forEach(emp -> System.out.println(emp));
        
        Collections.sort(employees, new JoiningDateComparator());
        
        System.out.println();
        System.out.println("#------Employees after sort ------#");
        employees.stream().forEach(emp -> System.out.println(emp));
        
        System.out.println();
             
        List<Engineer> engineers = new ArrayList<>();
        engineers.add(new Engineer("Dibakar",864033,LocalDate.of(2012, 12, 12),new String[]{"Java"}));
        engineers.add(new Engineer("Mark",864033,LocalDate.of(2001, 12, 12),new String[]{"Go"}));
        engineers.add(new Engineer("Kristeen",864033,LocalDate.of(2015, 12, 12),new String[]{"Scala"}));
        
        System.out.println("#------Engineers before sort ------#");
        engineers.stream().forEach(emp -> System.out.println(emp));
        
        //comparator is : Comparator<? super Engineer> c = new JoiningDateComparator<Employee>()
        // Note: Here the comparator has a contravariant parameter in comparator in sort method
        Collections.sort(engineers,new JoiningDateComparator());
        
        System.out.println();
        System.out.println("#------Engineers after sort ------#");
        engineers.stream().forEach(emp -> System.out.println(emp));
        System.out.println("");
    }
    
    private static void demoCovariantAndContravariantTypeParamsInMethod()
    {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Dibakar",864033,LocalDate.of(2012, 12, 12)));
        employees.add(new Employee("Mark",864033,LocalDate.of(2001, 12, 12)));
        employees.add(new Employee("Kristeen",864033,LocalDate.of(2015, 12, 12)));
        // Add in some subtype elements
        employees.add(new Engineer("Neal",864033,LocalDate.of(1999, 12, 12),new String[]{"Polyglot"}));
        employees.add(new Engineer("Mike",864033,LocalDate.of(1995, 12, 12),new String[]{"Polyglot"}));
        
        System.out.println("#------ List of Employees ------#");
        employees.stream().forEach(emp -> System.out.println(emp));
        System.out.println();
        
        // Param-1: Collection<? extends Employee>
        // Param-2: Comparator<? super Employee>
        Employee veteran = Collections.max(employees, new ExperienceComparator());
        System.out.printf("Veteran employee: %s %n",veteran);
    }
    
    public static void main(String[] args) 
    {
        demoContravariantTypeParamInMethod();
        demoCovariantAndContravariantTypeParamsInMethod();
    }
}
