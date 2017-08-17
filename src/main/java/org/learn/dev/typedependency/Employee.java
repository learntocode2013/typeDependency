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
 */
public class Employee extends Human implements Comparable<Employee>
{
    private final String name;
    private final int id;
    private final LocalDate joiningDate;
   
    public Employee(String name, int id, LocalDate joinDate) 
    {
        super(LocalDate.now().getYear() - joinDate.getYear());
        this.name = name;
        this.id = id;
        this.joiningDate = joinDate;
    }

    public String getName() 
    {
        return name;
    }

    public int getId() 
    {
        return id;
    }

    public LocalDate getJoiningDate() 
    {
        return joiningDate;
    }

    @Override
    public String toString() 
    {
        return "Employee{" + "name=" + name + ", id=" + id + ", joiningDate=" + joiningDate + '}';
    }

    // We purposely compare only by id by default.
    @Override
    public int compareTo(Employee o) 
    {
        return this.id - o.id ;
    }
}
