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

public class CustomAdapter extends ArrayAdapter<TodoItem> {

    Context customContext;
    TodoManager dbHandler;

    public CustomAdapter(Context context, ArrayList<TodoItem> activities) {
        super(context, R.layout.custom_row , activities);
        customContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = convertView;
        if(customView == null){
            customView = myInflater.inflate(R.layout.custom_row, parent, false);
        }

        //dbHandler = new TodoManager(customContext);
        dbHandler = TodoManager.getsInstance(customContext);

        // Initializing values
        final String todoItemName = getItem(position).get_todoItemName();
        final int isFinished = getItem(position).get_finished();
        final TodoItem todoItem = getItem(position);
        final TextView myText = (TextView) customView.findViewById(R.id.activityTextView);
        final CheckBox activityCheckBox = (CheckBox) customView.findViewById(R.id.activityCheckBox);

        // Check the checkbox if the todoItem is finished
        if(isFinished == 1) {
            activityCheckBox.setChecked(true);
        }

        // Create a listener for the checkboxes
        activityCheckBox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final boolean isChecked = activityCheckBox.isChecked();
                if(isChecked) {
                    todoItem.set_Finished(1);
                    dbHandler.update(todoItem);
                    Toast.makeText(customContext, "Marked " + todoItemName + " as finished", Toast.LENGTH_LONG).show();
                } else {
                    todoItem.set_Finished(0);
                    dbHandler.update(todoItem);
                    Toast.makeText(customContext, "Marked " + todoItemName  + " as unfinished", Toast.LENGTH_LONG).show();
                }
            }
        });

        myText.setText(todoItemName);
        return customView;
    }


}
