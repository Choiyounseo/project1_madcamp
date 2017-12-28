package com.example.user.album_tabb;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragA_add extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_add);

        Button btnReturn = (Button) findViewById(R.id.add_complete);

        final EditText newName = (EditText) findViewById(R.id.new_name);
        final EditText newPhone = (EditText) findViewById(R.id.new_phone);

        btnReturn.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){

                if (newName.getText().toString().length() == 0)
                {
                    Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(getApplicationContext(), "이름을 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, outIntent);
                }
                else {
                    Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                    outIntent.putExtra("insertedName", newName.getText().toString());
                    outIntent.putExtra("insertedPhone", newPhone.getText().toString());
                    setResult(RESULT_OK, outIntent);
                    finish();
                }
            }
        });
    }
}