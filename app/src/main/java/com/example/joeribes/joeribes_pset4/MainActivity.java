package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    TodoManager dbHandler;
    ListView activityListView;
    ArrayList<TodoList> todoListsContainer;
    String todoListName;
    String lastActivity;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent1 = new Intent(getBaseContext(), AddTodoListActivity.class);
                startActivity(intent1);
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
        setContentView(R.layout.activity_main);

        dbHandler = TodoManager.getsInstance(this);

        printDatabase();
        showAdapter();

    }

    /*
    @Override
    public void onPause(){
        super.onPause();

        getLastActivity();

        try {
            Intent fooActivity = new Intent(this,Class.forName(lastActivity));
            fooActivity.putExtra("todoListName", todoListName);
            startActivity(fooActivity);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    */

    /*
    private void getLastActivity(){
        SharedPreferences myPref = getSharedPreferences("APP_SHARED_PREF", this.MODE_PRIVATE);
        lastActivity = myPref.getString("last_activity", "");
        //todoListName = myPref.getString("todoListName", "");
    }
    */




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Print the database
    public void printDatabase(){
        todoListsContainer = dbHandler.read();
        showAdapter();
    }

    // Show the listView adapter
    public void showAdapter() {
        ListAdapter myAdapter = new TodoListsAdapter(this, todoListsContainer);
        activityListView = (ListView) findViewById(R.id.activityListView);
        assert activityListView != null;

        activityListView.setAdapter(myAdapter);

        activityListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        todoListName = todoListsContainer.get(position).get_todoListName();
                        // Launching new TodoItem
                        Intent i = new Intent(getApplicationContext(), TodoListActivity.class);
                        i.putExtra("todoListName", todoListName);
                        startActivity(i);
                    }
                }
        );
    }





}

