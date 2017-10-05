package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Joeri Bes on 4-10-2017.
 */

public class TodoListsAdapter extends ArrayAdapter<TodoList> {

    Context customContext;
    TodoManager dbHandler;

    public TodoListsAdapter(Context context, ArrayList<TodoList> activities) {
        super(context, R.layout.custom_row , activities);
        customContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = convertView;
        if(customView == null){
            customView = myInflater.inflate(R.layout.custom_row_lists, parent, false);
        }

        //dbHandler = new TodoManager(customContext);
        dbHandler = TodoManager.getsInstance(customContext);

        TodoList list = getItem(position);


        final TextView myText = (TextView) customView.findViewById(R.id.activityTextView);

        myText.setText(list.get_todoListName());
        return customView;
    }


}

