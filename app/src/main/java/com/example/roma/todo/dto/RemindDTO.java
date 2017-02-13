package com.example.roma.todo.dto;

public class RemindDTO {
    private String title;
    private String details;
    private String type;
    private int done;
    private int _id;

    public RemindDTO(int _id, String title, String details, String type, int done) {
        this.title = title;
        this.details = details;
        this.type = type;
        this.done = done;
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDone() {
        if (done == 0){
            return false;
        }else{
            return true;
        }
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getId() {
        return _id;
    }

    @Override
    public String toString() {
        return title+ "   "+ isDone();
    }
}
