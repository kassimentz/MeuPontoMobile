package com.edm.kassimentz.meupontomobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edm.kassimentz.meupontomobile.R;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;

import java.util.List;

/**
 * Created by kassi on 28/05/16.
 */
public class FuncionarioAdapter extends BaseAdapter{

    private List<Funcionario> listaFuncionarios;
    private Context contexto;

    public FuncionarioAdapter(Context ctx, List<Funcionario> listaFuncionarios){
        this.contexto = ctx;
        this.listaFuncionarios = listaFuncionarios;
    }

    @Override
    public int getCount() {
        return listaFuncionarios.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFuncionarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Funcionario funcionario = listaFuncionarios.get(position);
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_funcionarios, null);

        TextView txtNome = (TextView) view.findViewById(R.id.txtNomeItem);
        txtNome.setText(funcionario.getNome());

        TextView txtCpf = (TextView) view.findViewById(R.id.txtCpfItem);
        txtCpf.setText(funcionario.getCpf());

        TextView txtCidade = (TextView) view.findViewById(R.id.txtCidade);

        String cidade = null;
        for (Endereco e : funcionario.getEnderecos()) {
            cidade = e.getCidade();
        }
        txtCidade.setText(cidade);


        return view;
    }
}
