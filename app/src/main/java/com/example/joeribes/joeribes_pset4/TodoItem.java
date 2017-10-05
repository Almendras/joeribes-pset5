package com.example.joeribes.joeribes_pset4;

import java.io.Serializable;

/**
 * Created by Joeri Bes on 25-9-2017.
 */

public class TodoItem implements Serializable{

    private int _id;
    private String todoItemName;
    private String description;
    private int finished;
    private String todoListName;

    public TodoItem(String aTodoItemName, String aDescription, String aTodoListName) {
        todoItemName = aTodoItemName;
        description = aDescription;
        todoListName = aTodoListName;
        finished = 0;

    }

    public TodoItem(String aTodoItemName, String aDescription, int id, int isFinished, String aGroupName) {
        todoItemName = aTodoItemName;
        description = aDescription;
        _id = id;
        finished = isFinished;
        todoListName = aGroupName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_groupID(String aGroupName) {
        this.todoListName = aGroupName;
    }

    public void set_todoItemName(String newItemName) {
        todoItemName = newItemName;
    }

    public void set_description(String newDescription) {
        description = newDescription;
    }

    public int get_id() {
        return _id;
    }

    public String get_todoListName() {
        return todoListName;
    }

    public String get_todoItemName() {
        return todoItemName;
    }


    public String get_description() {
        return description;
    }

    public int get_finished() {
        return finished;
    }

    public void set_Finished(int finished) {
        this.finished = finished;
    }


}
