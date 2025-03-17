package com.hotel.service.exceptions;

public class NullPointerExceptionHandler extends NullPointerException {

    public NullPointerExceptionHandler(String message) {
        super(message);
    }

    public NullPointerExceptionHandler() {
        super("Hotel not found!!");
    }

}
