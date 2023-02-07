package it.ispw.mangaeater.boundary;

import it.ispw.mangaeater.exception.EmailNotFoundException;

public interface BoundaryEmail {

    public void inviaEmail() throws EmailNotFoundException;
}
