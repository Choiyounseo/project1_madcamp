package com.example.user.album_tabb;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragA_detail extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String phone = intent.getStringExtra("Phone");
        final int index = intent.getIntExtra("Index", 0);

        TextView nameView = (TextView) findViewById(R.id.detai_name);
        TextView phoneView = (TextView) findViewById(R.id.detai_phone);

        nameView.setText(name);
        phoneView.setText(phone);

        Button btnDelete = (Button) findViewById(R.id.delete_info);
        btnDelete.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder check = new AlertDialog.Builder(FragA_detail.this);
                check.setTitle("연락처를 삭제하겠습니까?");
                check.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                check.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                        outIntent.putExtra("index_del", index);
                        setResult(RESULT_OK, outIntent);
                        finish();
                    }
                });
                check.show();

            }
        });
    }
}