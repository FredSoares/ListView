package cv.app.frederico.listaitens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cv.app.frederico.listaitens.utils.Common;

public class EditItem extends AppCompatActivity {
    private EditText editText;
    private Button btUpdate;
    private String nome;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editText = findViewById(R.id.edtName);

        //pegar informação dentro da intent
        nome = getIntent().getStringExtra("Nome");
        id = (int) getIntent().getExtras().get("Id");

        // colocar texto no campo edittext
        editText.setText(nome);

        btUpdate = findViewById(R.id.btUpdate);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarItem();
            }
        });

    }

    private void atualizarItem() {

        nome = editText.getText().toString();

        //Common.dataList.set(id, nome);

        finish();
    }
}
