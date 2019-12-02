package cv.app.frederico.listaitens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    // Lista de nomes
    List<String> dataList;
    //String[] data = {"test", "aulsa"};

    String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // metodo para adicionar itens na lista de nomes
        addItensList();

        listView = findViewById(R.id.main_LV);

        //adapta elementos da lista a um layout
        final ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, dataList);

        //adicionar o adapter á listview
        listView.setAdapter(adapter);

        //click num item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //pega o elemento na possição passada como parametro
                String nome = (String) listView.getItemAtPosition(position);

               // Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_SHORT).show();

                // remove item da lista atraves da possição
                dataList.remove(position);
                //notificar que o conjunto de dados foi modificado
                adapter.notifyDataSetChanged();
            }
        });

        registerForContextMenu(listView);

    }


    // metodo para adicionar itens na lista de nomes
    private void addItensList() {
        //instancia do objeto
        dataList = new ArrayList<>();

        //adicionar elementos
        dataList.add("Junior");
        dataList.add("Yuri");
        dataList.add("Jenuina");
        dataList.add("Marlon");
        dataList.add("Steven");
        dataList.add("Lisiane");
        dataList.add("Dani");
        dataList.add("Elton");
        dataList.add("Hernani");
        dataList.add("William");
        dataList.add("Irian");
        dataList.add("Jerry");
        dataList.add("Frederico");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
       getMenuInflater().inflate(R.menu.main_menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

        // pegar informações do item clicado no menuInfo
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // Pegar valor do item atraves da possiçao
        nome = (String) listView.getItemAtPosition(adapterContextMenuInfo.position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

    switch (item.getItemId()){
            case R.id.main_context_edit:
                Toast.makeText(this, nome +" "+ item.getTitle(), Toast.LENGTH_SHORT).show();

            case R.id.main_context_fav:
                Toast.makeText(this, nome +" "+ item.getTitle(), Toast.LENGTH_SHORT).show();

            case R.id.main_context_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, nome);


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                Toast.makeText(this, nome +" "+ item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
