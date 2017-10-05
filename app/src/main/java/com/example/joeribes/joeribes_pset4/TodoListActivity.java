package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    ListView activityListView;
    TodoManager dbHandler;
    String todoListName;
    ArrayList<TodoList> todoListsContainer;
    ArrayList<TodoItem> todoList;
    String todoItem;
    int todoItem_id;
    int todoFinished;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteDialog();
                return true;
            case R.id.action_edit:
                Intent intent4 = new Intent(getBaseContext(), EditListActivity.class);
                intent4.putExtra("todoListName", todoListName);
                startActivity(intent4);
                finish();
                return true;
            case R.id.action_add:
                Intent intent3 = new Intent(getBaseContext(), AddActivity.class);
                intent3.putExtra("todoListName", todoListName);
                startActivity(intent3);
                finish();

                return true;
            case R.id.action_home:
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        setTitle(todoListName);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        Intent i = getIntent();
        todoListName = i.getStringExtra("todoListName");

        dbHandler = TodoManager.getsInstance(this);

        todoListsContainer = dbHandler.read();

        int count = containsTodoList(todoListName, todoListsContainer);

        todoList = todoListsContainer.get(count).getTodoItemList();

        showAdapter();

        //saveToSharedPrefs(todoListName);

    }

    /*
    private void saveToSharedPrefs(String todoListName) {
        SharedPreferences myPref =getSharedPreferences("APP_SHARED_PREF", this.MODE_PRIVATE);
        String packageName = this.getPackageName();
        String className = this.getClass().getSimpleName();
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("last_activity",packageName+"."+className);
        editor.putString("test",todoListName);
        editor.apply();
    }
    */




    // Check if todoListsContainer contains a TodoList
    public int containsTodoList(String groupName, ArrayList<TodoList> todoListsContainer) {
        int count = -1;
        int counter = 0;

        for (TodoList todoList1 : todoListsContainer) {
            if (todoList1.get_todoListName().equals(groupName)) {
                count = counter;
            }
            counter++;
        }
        return count;
    }


    // Show the listView adapter
    public void showAdapter() {
        ListAdapter myAdapter = new CustomAdapter(this, todoList);
        activityListView = (ListView) findViewById(R.id.activityListView);
        assert activityListView != null;

        activityListView.setAdapter(myAdapter);

        activityListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Receive the todoItem and id
                        todoItem = todoList.get(position).get_todoItemName();
                        todoItem_id = todoList.get(position).get_id();
                        todoFinished = todoList.get(position).get_finished();

                        // Launching new TodoItem
                        Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                        i.putExtra("todoItem", todoItem);
                        i.putExtra("todoItem_id", todoItem_id);
                        i.putExtra("todoFinished", todoFinished);
                        i.putExtra("todoListName", todoListName);
                        startActivity(i);
                    }
                }
        );
    }


    // Create a delete dialog if the user agrees to delete his/her todoItem
    public void deleteDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(TodoListActivity.this);
        alert.setTitle("Delete todo List");
        alert.setMessage("Are you sure you want to delete the todo List " + todoListName + "?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete item
                dbHandler.deleteList(todoListName);
                Toast.makeText(TodoListActivity.this, "Deleted todo List " + todoListName , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
