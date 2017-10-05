package com.example.joeribes.joeribes_pset4;

import java.util.ArrayList;

/**
 * Created by Joeri Bes on 3-10-2017.
 */

public class TodoList {

    private String todoList;
    private ArrayList<TodoItem> todoItemList;

    public TodoList(String aGroup) {
        this.todoList = aGroup;
        this.todoItemList = new ArrayList<>();
    }

    public void set_todoList(String aGroup) {
        this.todoList = aGroup;
    }

    public void setTodoItemList(ArrayList<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    public String get_todoListName() {
        return todoList;
    }

    public ArrayList<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public void addtodoItem(TodoItem todoItem) {
        todoItemList.add(todoItem);
    }



}
