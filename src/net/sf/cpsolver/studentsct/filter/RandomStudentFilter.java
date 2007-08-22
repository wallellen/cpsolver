package net.sf.cpsolver.studentsct.filter;

import java.util.HashSet;

import net.sf.cpsolver.studentsct.model.Student;

/**
 * This student filter accepts every student with the given 
 * probability. The choice for each student is remembered,
 * i.e., if the student is passed to the filter multiple times
 * the same answer is returned.    
 * 
 * @version
 * StudentSct 1.1 (Student Sectioning)<br>
 * Copyright (C) 2007 Tomas Muller<br>
 * <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 * Lazenska 391, 76314 Zlin, Czech Republic<br>
 * <br>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <br><br>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <br><br>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
public class RandomStudentFilter implements StudentFilter {
    private double iProb = 1.0;
    private HashSet iAcceptedStudents = new HashSet();
    private HashSet iRejectedStudents = new HashSet();
    
    /**
     * Constructor
     * @param prob probability of acceptance of a student
     */
    public RandomStudentFilter(double prob) {
        iProb = prob;
    }
    
    /**
     * A student is accepted with the given probability
     */
    public boolean accept(Student student) {
        if (iAcceptedStudents.contains(student)) return true;
        if (iRejectedStudents.contains(student)) return false;
        boolean accept = (Math.random()<iProb);
        if (accept)
            iAcceptedStudents.add(student);
        else
            iRejectedStudents.add(student);
        return accept;
    }

}