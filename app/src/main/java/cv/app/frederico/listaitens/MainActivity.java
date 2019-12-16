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

import cv.app.frederico.listaitens.Model.Aluno;
import cv.app.frederico.listaitens.adapter.ContactAdapter;
import cv.app.frederico.listaitens.utils.Common;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private Aluno aluno;
    private int id;
    ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // metodo para adicionar itens na lista de nomes
        addItensList();

        listView = findViewById(R.id.main_LV);

        //adapta elementos da lista a um layout
        adapter = new ContactAdapter(this ,Common.dataList);

        //adicionar o adapter á listview
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();


        //click num item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //pega o elemento na possição passada como parametro
                Aluno aluno = (Aluno) listView.getItemAtPosition(position);

               // Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_SHORT).show();

                // remove item da lista atraves da possição
                Common.dataList.remove(position);
                //notificar que o conjunto de dados foi modificado
                adapter.notifyDataSetChanged();

            }
        });

        registerForContextMenu(listView);

    }


    // metodo para adicionar itens na lista de nomes
    private void addItensList() {
        //instancia do objeto
        Common.dataList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Common.dataList.add(new Aluno("Aluno " +i, "343546464", i));
        }
        //adicionar elementos
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
        aluno = (Aluno) listView.getItemAtPosition(adapterContextMenuInfo.position);
        id = adapterContextMenuInfo.position;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_fav:
                abrirFav();
                return true;
            case R.id.main_menu_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirFav() {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

    switch (item.getItemId()){
            case R.id.main_context_edit:
                //chama metodo atualizar item
                updateItem();
                return true;
            case R.id.main_context_fav:
                addFavorite();
                return true;
            case R.id.main_context_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, aluno.getName());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void addFavorite() {
        Common.favList.add(aluno);
    }

    private void updateItem() {
        Intent intent = new Intent(this, EditItem.class);
        intent.putExtra("Nome", aluno.getName());
        intent.putExtra("Id", id);

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
