package it.ispw.mangaeater.observer_pattern;

public interface Subject {

    //registrare e rimuovere un observer per una certa istanza
    public void register(Observer obj);
    public void unregister(Observer obj);

    //notificare un observer
    public void notifyObservers();
}
