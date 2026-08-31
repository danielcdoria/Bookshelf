package com.example.Bookshelf.erro;

public class ErroResponse {
    private String message;

    public ErroResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
