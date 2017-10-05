package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText activityInput;
    EditText descriptionInput;
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;
    String activityName;
    int todoItem_id;

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
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        context = this;
        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(this);

        Intent i = getIntent();
        activityName = i.getStringExtra("todos");
        todoItem_id = i.getIntExtra("todos_id", 0);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.listInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

        activityInput.setText(activityName);
        todoItem = dbHandler.readDescription(todoItem_id);
        descriptionInput.setText(todoItem.get_description());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Add an todoItem to the database
    public void editButtonClicked(View view){
        // Edit todoItem with an TodoItem name and Description
        todoItem.set_todoItemName(activityInput.getText().toString());
        todoItem.set_description(descriptionInput.getText().toString());

        dbHandler.update(todoItem);

        Toast.makeText(EditActivity.this, "The todoItem has been changed" , Toast.LENGTH_LONG).show();

        // Launching new TodoItem
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }



}
