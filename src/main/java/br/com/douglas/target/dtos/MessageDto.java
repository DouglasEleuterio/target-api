package br.com.douglas.target.dtos;

import java.util.Date;

public class MessageDto {

    private String message;

    private Date date;

    private Object obj;

    public MessageDto(String message, Date date, Object obj) {
        this.message = message;
        this.date = date;
        this.obj = obj;
    }

    public MessageDto(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
