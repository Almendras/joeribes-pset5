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

public class EditListActivity extends AppCompatActivity {

    EditText listInput;
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;
    int activity_id;
    String todoListName;

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
        setContentView(R.layout.activity_edit_list);

        context = this;
        dbHandler = TodoManager.getsInstance(this);

        Intent i = getIntent();

        todoListName = i.getStringExtra("todoList");

        // Initializing views
        listInput = (EditText) findViewById(R.id.listInput);

        listInput.setText(todoListName);
        todoItem = dbHandler.readDescription(activity_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Add an todoItem to the database
    public void editButtonClicked(View view){
        // Edit todoItem with an TodoItem name and Description
        String new_listInput = (listInput.getText().toString());
        dbHandler.updateList(todoListName, new_listInput);

        Toast.makeText(EditListActivity.this, "The To-Do List has been changed to " + new_listInput , Toast.LENGTH_LONG).show();

        // Launching new TodoItem
        Intent intent = new Intent(getBaseContext(), TodoListActivity.class);
        intent.putExtra("todoListName", new_listInput);
        startActivity(intent);
        finish();

    }
}
