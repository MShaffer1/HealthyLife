package michelle.healthylife;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends Form {

    private static final String TAG = "ListDataActivity";

    DBhandler mDataHelper;

    private ListView rListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        rListView = findViewById(R.id.LVrecipes);

        mDataHelper = new DBhandler(this);

        populateListView();

    }

    public void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get data and show in list
        Cursor data = mDataHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()) {
            //get data from database and add to list
            listData.add(data.getString(1));


        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        rListView.setAdapter(adapter);

    }

}