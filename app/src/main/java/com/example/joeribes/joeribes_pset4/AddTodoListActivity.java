package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddTodoListActivity extends AppCompatActivity {

    EditText todoInput;
    EditText descriptionInput;
    EditText todoListInput;
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo_list);

        context = this;
        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(context);

        // Initializing views
        todoListInput = (EditText) findViewById(R.id.todoListInput);
        todoInput = (EditText) findViewById(R.id.listInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

    }

    //Add an todoItem to the database
    public void addButtonClicked(View view){
        // Create a To-Do list with an TodoList Name, TodoItem name and Description
        todoItem = new TodoItem(todoInput.getText().toString(), descriptionInput.getText().toString(), todoListInput.getText().toString());
        dbHandler.create(todoItem);

        // // Launching Main activity
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
