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
public class SoftwareEngineer extends Engineer
{
    
    public SoftwareEngineer(String name, int id, LocalDate joinDate, String[] skills) 
    {
        super(name, id, joinDate, skills);
    }
    
}
