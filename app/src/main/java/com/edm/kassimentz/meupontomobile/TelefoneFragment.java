package com.edm.kassimentz.meupontomobile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.edm.kassimentz.meupontomobile.model.Telefone;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelefoneFragment extends Fragment {

    private Telefone telefone;
    EditText txtDdd, txtTelefone;
    Button btnSalvar;
    public TelefoneFragment() {
        // Required empty public constructor
        telefone = new Telefone();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_telefone, container, false);

        txtDdd = (EditText) v.findViewById(R.id.txtDddTelefone);
        txtTelefone = (EditText) v.findViewById(R.id.txtNumeroTelefone);

        btnSalvar = (Button) v.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("info", "onclick");
                ((CadastroActivity) getActivity()).persistirFuncionario();
            }
        });

        return inflater.inflate(R.layout.fragment_telefone, container, false);
    }



    private void setarTelefone() {

        String ddd = null, telefone = null;

        if(!txtTelefone.getText().toString().isEmpty()){
            telefone = txtTelefone.getText().toString();
        }
        if(!txtDdd.getText().toString().isEmpty()){
            ddd = txtDdd.getText().toString();
        }

        this.telefone.setNumero(telefone);
        this.telefone.setDdd(ddd);


    }

}
