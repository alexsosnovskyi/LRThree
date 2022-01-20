package com.example.secondtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RadioGroup price;
    RadioGroup firm;
    RadioButton radioButtonPrice;
    RadioButton radioButtonFirm;
    TextView textView;

    String[] dishes = {"Dishes", "plate", "spoon", "fork", "glass", "pan", "scoop"};


    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dishes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void saveText(View view){

        FileOutputStream fos = null;
        try {
            price = findViewById(R.id.radioGroupPrice);
            firm = findViewById(R.id.radioGroupFirm);
            Spinner spinner = findViewById(R.id.spinner);

            String selected = spinner.getSelectedItem().toString();
            int id = spinner.getSelectedItemPosition();
            int radioIdPrice = price.getCheckedRadioButtonId();
            int radioIdFirm = firm.getCheckedRadioButtonId();

            if ((radioIdPrice == -1 || radioIdFirm == -1 || id == 0)) {
                Toast.makeText(MainActivity.this, "Please, enter all information",
                        Toast.LENGTH_SHORT).show();
            } else {
                radioButtonPrice = findViewById(radioIdPrice);
                radioButtonFirm = findViewById(radioIdFirm);

                String text = "Selected items: ";
                String space = "    ";
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fos.write(text.getBytes());
                fos.write(selected.getBytes());
                fos.write(space.getBytes());
                fos.write(radioButtonPrice.getText().toString().getBytes());
                fos.write(space.getBytes());
                fos.write(radioButtonFirm.getText().toString().getBytes());
                Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
            }
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}