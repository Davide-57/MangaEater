package it.ispw.mangaeater.bean;

import it.ispw.mangaeater.controller_grafici.HomeController;
import it.ispw.mangaeater.observer_pattern.Observer;
import it.ispw.mangaeater.observer_pattern.Subject;

import java.util.ArrayList;
import java.util.List;

public class SessioneBean implements Observer, Subject {

    private final List<Observer> observers;
    private boolean changed;

    public SessioneBean(HomeController homeControllerGrafico) {
        this.observers = new ArrayList<>();
        this.register(homeControllerGrafico);
    }

    @Override
    public void update() {
        this.changed=true;
        notifyObservers();
    }


    @Override
    public void setSubject(Subject sub) {
        changed = false;
    }

    @Override
    public void register(Observer obj) {
        if(obj == null){
            throw new NullPointerException("Null Observer");
        }

        if(!observers.contains(obj)) {
            observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal;
        if (!changed)
            return;
        observersLocal = new ArrayList<>(this.observers);
        this.changed=false;
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

}
