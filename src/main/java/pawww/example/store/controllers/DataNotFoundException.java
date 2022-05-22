package pawww.example.store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No data with this id ")
public class DataNotFoundException extends RuntimeException {
    String message;
    public DataNotFoundException(String msg){
        message="ERROR with data id "+msg+" in cart";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
