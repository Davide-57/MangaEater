package it.ispw.mangaeater.boundary;

public class VenditoreBoundaryCP implements BoundaryEmail{

    private String emailVenditore;

    public VenditoreBoundaryCP(String emailVenditore) {
        this.emailVenditore = emailVenditore;
    }

    @Override
    public void inviaEmail(){

    }
}
