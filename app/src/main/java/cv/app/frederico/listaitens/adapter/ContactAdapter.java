package cv.app.frederico.listaitens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cv.app.frederico.listaitens.MainActivity;
import cv.app.frederico.listaitens.R;

public class ContactAdapter extends BaseAdapter {

    private List<String> lista;
    private Context context;


    public ContactAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.lista = dataList;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View contentview, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_main, null);

        TextView tv_nome = view.findViewById(R.id.name_item_list_main);
        tv_nome.setText(lista.get(position));

        return view;
    }
}
