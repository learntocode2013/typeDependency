/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.util.Comparator;

/**
 *
 * @author disen
 */
public class JoiningDateComparator implements Comparator<Employee> 
{

    @Override
    public int compare(Employee o1, Employee o2) 
    {
        if(o1.getJoiningDate().isBefore(o2.getJoiningDate())) { return -1 ; }
        if(o1.getJoiningDate().isEqual(o2.getJoiningDate())) { return 0 ; }
        return 1;
    }
    
}
