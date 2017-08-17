/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.learn.dev.typedependency;

import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author disen
 */
public class Engineer extends Employee
{
    private String[] skills;

    public Engineer(String name, int id, LocalDate joinDate, String[] skills) 
    {
        super(name, id, joinDate);
        this.skills = skills;
    }

    public String[] getSkills() 
    {
        return skills;
    }

    @Override
    public String toString() 
    {
        return "Engineer{" + "name=" + super.getName() + ", joiningDate=" + super.getJoiningDate() 
                + ", skills=" + Arrays.asList(skills).toString() + '}';
    }   
}
