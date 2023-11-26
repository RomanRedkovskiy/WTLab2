package albumForum.exception;

public class UserNotLoggedException extends RuntimeException{

    public UserNotLoggedException(){
        super("User is not logged");
    }
}
