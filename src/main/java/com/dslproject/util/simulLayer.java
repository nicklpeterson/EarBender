package com.dslproject.util;

import com.dslproject.ast.declarations.Variable;

import java.util.ArrayList;

public class simulLayer {
    private int tempo;
    private int notes;
    private ArrayList<Variable> variables = new ArrayList<>();
    private ArrayList<Integer> notesList= new ArrayList<>();
    private ArrayList<Integer> tempoList= new ArrayList<>();
    public void addNotes(int i) {
        notesList.add(i);
    }
    public void addTempo(int i) {
        tempoList.add(i);
    }
    public ArrayList<Integer> getNotesList() {
        return notesList;
    }

    public void setNotesList(ArrayList<Integer> notesList) {
        this.notesList = notesList;
    }

    public ArrayList<Integer> getTempoList() {
        return tempoList;
    }

    public void setTempoList(ArrayList<Integer> tempoList) {
        this.tempoList = tempoList;
    }



    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }
    public void addVariables(Variable variable){
        this.variables.add(variable);
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
    }



}
