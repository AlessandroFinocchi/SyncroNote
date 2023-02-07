package com.example.syncronote.logic.patterns;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;

    protected Subject() {
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
