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
public class ExperienceComparator implements Comparator<Human>
{

    @Override
    public int compare(Human o1, Human o2) 
    {
        return o1.getYearsOfExp() - o2.getYearsOfExp();
    } 
}
