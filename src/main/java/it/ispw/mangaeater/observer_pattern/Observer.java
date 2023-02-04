package it.ispw.mangaeater.observer_pattern;

public interface Observer {

    //messaggio dal subject quando vuole notificare una modifica
    public void update();

    //associo un subject all'observer
    public void setSubject(Subject sub);
}
