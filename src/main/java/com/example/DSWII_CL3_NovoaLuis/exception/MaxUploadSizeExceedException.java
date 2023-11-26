package com.example.DSWII_CL3_NovoaLuis.exception;

public class MaxUploadSizeExceedException extends RuntimeException{
    public MaxUploadSizeExceedException (String message){
        super(message);
    }
}
