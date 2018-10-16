package michelle.healthylife;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DBhandler myDBhandler;
    Button btnAdd, btnViewData, btnDelete;
    EditText editText, editText1, editText2;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editText = findViewById(R.id.editTextRec);
        editText1 = findViewById(R.id.editTextIng);
        editText2 =  findViewById(R.id.editTextIns);
        btnAdd =  findViewById(R.id.btnAdd);
        btnDelete =  findViewById(R.id.btnDelete);
        btnViewData =  findViewById(R.id.btnView);



        myDBhandler = new DBhandler(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("recipeName");


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form.this, ListDataActivity.class);
                editText.setText("");
                startActivity(intent);

            }
        });


        //ADD data button & method
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    //editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
        //DELETE data button & method
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
              String deleteData = editText.getText().toString();
               // myDBhandler.deleteData(editText.getText().toString());
                DeleteData(deleteData);

                if (deleteData.length() !=0)
                toastMessage("Removed from Database");
                else
                    toastMessage("Data not Deleted");
            }
        });
    }
        public void AddData (String newEntry) {
            boolean insertData = myDBhandler.addData(newEntry);

            if (insertData) {
                toastMessage("Data Successfully Inserted!");
            } else {
                toastMessage("Something went wrong");
            }
        }
            private void toastMessage (String message){
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        public void DeleteData(String editEntry) {
            String deleteEntry = myDBhandler.deleteData(editEntry);

            if (deleteEntry.length()!=0) {
                toastMessage("Data Successfully Deleted");
            }else {
                toastMessage("Something went wrong");
            }


            }



    @Override
    public void onItemSelected(AdapterView<?> spinner, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}


