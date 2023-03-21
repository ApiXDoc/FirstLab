package com.mradking.mylibrary.modal;

public class Modal {
       String Name,paths,status;
    int _id;

    public Modal(String name, String paths, String status) {
        Name = name;
        this.paths = paths;
        this.status = status;

    }

    public Modal() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
