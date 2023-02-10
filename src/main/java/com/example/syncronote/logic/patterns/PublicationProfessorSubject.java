package com.example.syncronote.logic.patterns;

import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PublicationProfessorSubject extends PublicationStudentBean {
    private List<Observer> observers;

    protected PublicationProfessorSubject(File file, boolean privateNote, String category) throws InvalidFormatException {
        super(file, privateNote, category);
        observers = new ArrayList<>() ;
    }

    public void attach(Observer o) {
        observers.add(o) ;
    }

    public void detach(Observer o) {
        observers.remove(o) ;
    }

    protected void notifyObservers() {

        for (Observer o : observers) {
            o.update();
        }
    }
}
