package it.ispw.mangaeater.sessione;

import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.observer_pattern.Observer;
import it.ispw.mangaeater.observer_pattern.Subject;

import java.util.ArrayList;
import java.util.List;

public class Sessione implements Subject {

    private Utente utenteLoggato = null;


    private final List<Observer> observers;
    private boolean changed;



    public Sessione() {
        this.observers = new ArrayList<>();
    }

    public boolean isLogged() {
        return utenteLoggato != null;
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

    public void setUtenteLoggato(Utente utenteLoggato) {
        this.utenteLoggato = utenteLoggato;
        this.changed=true;
        notifyObservers();
    }
}
