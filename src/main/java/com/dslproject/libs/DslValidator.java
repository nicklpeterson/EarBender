package com.dslproject.libs;

import ch.qos.logback.core.boolex.EvaluationException;
import com.dslproject.ast.Program;
import com.dslproject.ast.Statement;
import com.dslproject.ast.declarations.Declaration;
import com.dslproject.ast.declarations.DslList;
import com.dslproject.ast.declarations.Function;
import com.dslproject.ast.declarations.Variable;
import com.dslproject.ast.executions.Execution;
import com.dslproject.ast.executions.PlaySimul;
import com.dslproject.ast.executions.PlaySync;
import com.dslproject.ast.executions.Rhythm;
import com.dslproject.exceptions.ValidatorException;
import com.dslproject.util.simulLayer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class DslValidator {
    private final Program ast;
    private List<Statement> statements;
    private static boolean isValid = true;

    public  static DslValidator getValidator(Program ast) {
        return new DslValidator(ast);
    }

    public boolean validateProgram(){
        statements = ast.getStatements();
        for (Statement s: statements) {
            if(s.getClass().equals(PlaySync.class)){

                List<Declaration> declarations = ((PlaySync) s).getDeclarations();

            } else if(s.getClass().equals(PlaySimul.class)){
                List<Declaration> declarations = ((PlaySimul) s).getDeclarations();
                isValid = validateSimul(declarations);

            } else if(s.getClass().equals(Rhythm.class)){
            }
        }

        return isValid;
    };


    public boolean validateSimul(List<Declaration> declarations){
        boolean isValid = true;
        ArrayList<simulLayer> layers = new ArrayList<>();

        //each declaration is a layer
        for (Declaration d: declarations) {
            layers.add(validateLayer(d));

        }
        int varNum = 0;
        ArrayList<Integer> tempoList = new ArrayList<>();
        ArrayList<Integer> notesList = new ArrayList<>();
        for (simulLayer l: layers) {
            if (varNum == 0) {
                varNum = l.getVariables().size();
                notesList = l.getNotesList();
                tempoList = l.getTempoList();
            } else {
                //check if all layers have the same number of variable
                if (varNum != l.getVariables().size()) {
                    return false;
                } else if (!notesList.equals(l.getNotesList()) || !tempoList.equals(l.getTempoList())) {
                    return false;
                }
            }
        }

        //after checking all layers have the same number of variables, we start to check the notes number and tempo between layers


        return true;
    }
    //check if all tempo and number of variable within simul play layer are the same
    public simulLayer validateLayer(Declaration d){
        simulLayer layer = new simulLayer();

        if (d.getClass().equals(Variable.class)) {
        }else if (d.getClass().equals(DslList.class)) {

            List<Declaration> subDeclarations = ((DslList) d).getDeclarations();
            List<Variable> variables = (List<Variable>)(List<?>) subDeclarations;
            for (Variable var: variables) {
                layer.addNotes(var.getNotes().size());
                layer.addTempo(var.getTempo());
                layer.addVariables(var);
            }

        }else if (d.getClass().equals(Function.class)) {
            List<Execution> executions = ((Function) d).getExecutions();
        }

        return layer;
    }
    public boolean validateNotes(){
        return false;
    }
    public boolean validateTempo(){
        return false;
    }
}
