package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText activityInput;
    EditText descriptionInput;
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;
    String todoListName;

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
        setContentView(R.layout.activity_add);



        context = this;
        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(context);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.listInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

        Intent i = getIntent();
        todoListName = i.getStringExtra("todoListName");

        saveToSharedPrefs(todoListName);

    }

    //Add an todoItem to the database
    public void addButtonClicked(View view){
        // Create todoItem with an TodoItem name and Description
        todoItem = new TodoItem(activityInput.getText().toString(), descriptionInput.getText().toString(), todoListName);
        dbHandler.create(todoItem);

        // // Launching new TodoItem
        Intent intent = new Intent(getBaseContext(), TodoListActivity.class);
        intent.putExtra("todoListName", todoListName);
        startActivity(intent);
        finish();
    }

    private void saveToSharedPrefs(String todoListName) {
        SharedPreferences myPref =getSharedPreferences("APP_SHARED_PREF", this.MODE_PRIVATE);
        String packageName = this.getPackageName();
        String className = this.getClass().getSimpleName();
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("last_activity",packageName+"."+className);
        editor.putString("todoListName", todoListName);
        editor.apply();
    }



}
