package net.sf.cpsolver.exam.heuristics;

import net.sf.cpsolver.exam.model.Exam;
import net.sf.cpsolver.exam.model.ExamModel;
import net.sf.cpsolver.exam.model.ExamPlacement;
import net.sf.cpsolver.ifs.heuristics.VariableSelection;
import net.sf.cpsolver.ifs.model.Variable;
import net.sf.cpsolver.ifs.solution.Solution;
import net.sf.cpsolver.ifs.solver.Solver;
import net.sf.cpsolver.ifs.util.DataProperties;
import net.sf.cpsolver.ifs.util.ToolBox;

/**
 * Unassigned variable selection. The "biggest" variable (using
 * {@link Variable#compareTo(Object)}) unassigned variable is selected. One is
 * selected randomly if there are more than one of such variables.
 * 
 * @version ExamTT 1.2 (Examination Timetabling)<br>
 *          Copyright (C) 2008 - 2010 Tomas Muller<br>
 *          <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 *          Lazenska 391, 76314 Zlin, Czech Republic<br>
 * <br>
 *          This library is free software; you can redistribute it and/or modify
 *          it under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation; either version 2.1 of the
 *          License, or (at your option) any later version. <br>
 * <br>
 *          This library is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details. <br>
 * <br>
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with this library; if not, write to the Free Software
 *          Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *          02110-1301 USA
 */
public class ExamUnassignedVariableSelection implements VariableSelection<Exam, ExamPlacement> {
    private boolean iRandomSelection = true;

    /** Constructor */
    public ExamUnassignedVariableSelection(DataProperties properties) {
        iRandomSelection = properties.getPropertyBoolean("ExamUnassignedVariableSelection.random", iRandomSelection);
    }

    /** Initialization */
    public void init(Solver<Exam, ExamPlacement> solver) {
    }

    /** Variable selection */
    public Exam selectVariable(Solution<Exam, ExamPlacement> solution) {
        ExamModel model = (ExamModel) solution.getModel();
        if (model.nrUnassignedVariables() == 0)
            return null;
        if (iRandomSelection)
            return ToolBox.random(model.unassignedVariables());
        Exam variable = null;
        for (Exam v : model.unassignedVariables()) {
            if (variable == null || v.compareTo(variable) < 0)
                variable = v;
        }
        return variable;
    }
}
