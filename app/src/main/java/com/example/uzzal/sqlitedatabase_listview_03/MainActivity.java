package com.example.uzzal.sqlitedatabase_listview_03;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper databaseHelper;

    private EditText editTextId, editTextUserName;
    private Button buttonSave, buttonShow, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase  = databaseHelper.getWritableDatabase();

        editTextId = (EditText) findViewById(R.id.editTextUserId_id);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName_id);

        buttonSave = (Button) findViewById(R.id.buttonSave_id);
        buttonShow = (Button) findViewById(R.id.buttonShow_id);
        buttonDelete = (Button) findViewById(R.id.buttonDelete_id);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate_id);

        buttonSave.setOnClickListener(this);
        buttonShow.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String id = editTextId.getText().toString();
        String name = editTextUserName.getText().toString();

        if(v.getId()==R.id.buttonSave_id){
            
            
                      // ** jodi id, name faka thake tahole **Please Enter all the Data show korbe**
            if(id.equals("")&&name.equals("")){

                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();
                
            }else{
                
               long rowNumber =  databaseHelper.saveData(id,name);
               if(rowNumber >- 1){

                   Toast.makeText(this, "Successfully Inserted is Data ", Toast.LENGTH_SHORT).show();
                   editTextId.setText("");
                   editTextUserName.setText("");
               }else {


               }
                
                
            }


        }

        else if(v.getId()==R.id.buttonShow_id){

            Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(intent);


        }

        else if(v.getId()==R.id.buttonUpdate_id){

           Boolean isUpdated =  databaseHelper.updateData(id,name);

           if(isUpdated==true){
               Toast.makeText(this, "Successfully , Data is updated", Toast.LENGTH_SHORT).show();
           }else{

               Toast.makeText(this, "Unsuccessfully, Data isn't updated", Toast.LENGTH_SHORT).show();
           }


        }

        else if(v.getId()==R.id.buttonDelete_id){

            int value  = databaseHelper.deleteData(id);

            if(value<0){

                Toast.makeText(this, "Data is not Deleted", Toast.LENGTH_SHORT).show();
            }
            else {

                Toast.makeText(this, "Data is Deleted", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
