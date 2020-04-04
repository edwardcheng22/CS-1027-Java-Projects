
public class EmptyStackException extends RuntimeException{
    public EmptyStackException(String message){
        super("The " + message + " is empty");
    }
}
