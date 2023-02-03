package it.ispw.mangaeater.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtenteBeanFromView {

    private String email;
    private String psw;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UtenteBeanFromView(String email, String psw) {
        this.email = email;
        this.psw = psw;
    }

    public boolean controllaValiditaEmail() {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
    }
}
