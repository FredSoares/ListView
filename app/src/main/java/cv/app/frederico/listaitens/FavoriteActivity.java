package cv.app.frederico.listaitens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import cv.app.frederico.listaitens.utils.Common;

public class FavoriteActivity extends AppCompatActivity {

    ListView  listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        listView = findViewById(R.id.list_fav);

        //adapta elementos da lista a um layout
        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, Common.favList);

        //adicionar o adapter á listview
        listView.setAdapter(adapter);
    }
}
