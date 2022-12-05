package mk.ukim.finki.labwp.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException(){
        super("Passwords do not match exception");
    }

}
