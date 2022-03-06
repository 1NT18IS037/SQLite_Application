package com.example.sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,age;
    Button insert,delete,update,view;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.naam);
        contact=findViewById(R.id.contact);
        age=findViewById(R.id.age);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.del);
        view=findViewById(R.id.view);
        db=new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String conTxt=contact.getText().toString();
                String ageTxt=age.getText().toString();
                boolean qstat=db.insertData(nameTxt,conTxt,ageTxt);
                if(qstat==true)
                    Toast.makeText(MainActivity.this,"New insert is true",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New insert failed",Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String conTxt=contact.getText().toString();
                String ageTxt=age.getText().toString();
                boolean qstat=db.updateData(nameTxt,conTxt,ageTxt);
                if (qstat==true)
                    Toast.makeText(MainActivity.this,"New update is true",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New update failed",Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String conTxt=contact.getText().toString();
                String ageTxt=age.getText().toString();
                boolean qstat=db.deleteData(nameTxt);
                if (qstat==true)
                    Toast.makeText(MainActivity.this,"New delete is true",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New delete failed",Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.viewD();
                if(res.getCount()==0)
                    Toast.makeText(MainActivity.this,"Nahh",Toast.LENGTH_SHORT).show();
                else {
StringBuffer stringBuffer=new StringBuffer();
while (res.moveToNext()){
stringBuffer.append("Name"+res.getString(0)+"\n");
stringBuffer.append("Contact"+res.getString(1)+"\n");
stringBuffer.append("Age"+res.getString(2)+"\n");
}
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
builder.setTitle("User Deets");
builder.setMessage(stringBuffer.toString());
builder.show();
                }
            }
        });
    }
}