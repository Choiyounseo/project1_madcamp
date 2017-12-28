package com.example.user.album_tabb;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import static android.app.Activity.RESULT_OK;

public class FragmentA extends Fragment {

    PhoneBook info;
    ArrayAdapter<String> listAdapter;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1phone, container, false);

        //setContentView(R.layout.tab1phone);

        info = new PhoneBook();
        info.insert("A","010-1111-1111");
        info.insert("B","010-2222-2222");
        info.insert("C","010-3333-3333");
        info.insert("D","010-4444-4444");
        info.insert("E","010-5555-5555");
        info.insert("F","010-6666-6666");

        final ListView list = rootView.findViewById(R.id.listPhoneBook);

        listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, info.getName());
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_detail = new Intent(getActivity().getApplicationContext(), FragA_detail.class);
                intent_detail.putExtra("Name", info.getName().get(i));
                intent_detail.putExtra("Phone", info.getPhone().get(i));
                intent_detail.putExtra("Index", i);
                startActivityForResult(intent_detail, 0);
            }
        });

        Button add = rootView.findViewById(R.id.listAdd);

        add.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent intent_add = new Intent(getActivity().getApplicationContext(), FragA_add.class);
                startActivityForResult(intent_add, 1);
            }
        });

        EditText editTextFilter = rootView.findViewById(R.id.text_search);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString();
                if (filterText.length() > 0)
                {
                    list.setFilterText(filterText);
                }
                else
                    list.clearTextFilter();
            }
        });
    return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            int del = data.getIntExtra("index_del", 0);
            info.removeIndex(del);
            listAdapter.notifyDataSetChanged();

            Toast.makeText(getActivity().getApplicationContext(), "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            String newName = data.getStringExtra("insertedName");
            String newPhone = data.getStringExtra("insertedPhone");

            info.insert(newName, newPhone);
            listAdapter.notifyDataSetChanged();

            Toast.makeText(getActivity().getApplicationContext(), "연락처가 추가되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    class PhoneBook
    {
        private ArrayList<String> name;
        private ArrayList<String> phone;

        public PhoneBook()
        {
            name = new ArrayList<String>();
            phone = new ArrayList<String>();
        }

        public void insert(String s1, String s2)
        {
            name.add(s1);
            phone.add(s2);
        }

        public void removeIndex(int i)
        {
            name.remove(i);
            phone.remove(i);
        }

        public ArrayList<String> getName()
        {
            return name;
        }

        public ArrayList<String> getPhone()
        {
            return phone;
        }

    }
}

