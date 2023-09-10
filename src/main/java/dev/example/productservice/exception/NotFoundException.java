package dev.example.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }
}
