package com.edm.kassimentz.meupontomobile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;

import jo.dis.library.data.DataCache;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnderecoFragment extends Fragment {

    DataCache dataCache = DataCache.get(this.getActivity());

    private Endereco endereco;
    EditText txtLogradouro, txtNumero, txtComplemento, txtCep;
    Spinner spinCidade, spinEstado, spinPais;

    public EnderecoFragment() {
        // Required empty public constructor
        endereco = new Endereco();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Funcionario funcionario = (Funcionario) dataCache.getObject("funcionario");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_endereco, container, false);

        txtLogradouro = (EditText) v.findViewById(R.id.txtLogradouroEndereco);
        txtNumero = (EditText) v.findViewById(R.id.txtCepEndereco);
        txtComplemento = (EditText) v.findViewById(R.id.txtComplementoEndereco);
        txtCep = (EditText) v.findViewById(R.id.txtCepEndereco);

        spinCidade = (Spinner) v.findViewById(R.id.spinCidadeEndereco);
        ArrayAdapter<CharSequence> adapterCidade = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.principaisCidades, android.R.layout.simple_spinner_item);
        adapterCidade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinCidade.setAdapter(adapterCidade);

        spinEstado = (Spinner) v.findViewById(R.id.spinEstadoEndereco);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.estados, android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinEstado.setAdapter(adapterEstado);

        spinPais = (Spinner) v.findViewById(R.id.spinPaisEndereco);
        ArrayAdapter<CharSequence> adapterPais = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.paisesLatinos, android.R.layout.simple_spinner_item);
        adapterPais.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinPais.setAdapter(adapterPais);


        return v;


    }

    private void setarEndereco() {
        String logradouro = null, complemento= null, cidade = null, estado = null, pais = null;
        Integer numero = null, cep = null;

        if(!txtLogradouro.getText().toString().isEmpty()){
            logradouro = txtLogradouro.getText().toString();
        }
        if(!txtComplemento.getText().toString().isEmpty()){
            complemento = txtComplemento.getText().toString();
        }
        if(!spinCidade.getSelectedItem().toString().isEmpty()){
            if(!spinCidade.getSelectedItem().toString().equals("Cidade")) {
                cidade = spinCidade.getSelectedItem().toString();
            }
        }
        if(!spinEstado.getSelectedItem().toString().isEmpty()){
            if(!spinEstado.getSelectedItem().toString().equals("Estado")){
                estado = spinEstado.getSelectedItem().toString();
            }
        }
        if(!spinPais.getSelectedItem().toString().isEmpty()){
            if(!spinPais.getSelectedItem().toString().equals("País")){
                pais = spinPais.getSelectedItem().toString();
            }
        }
        if(!txtNumero.getText().toString().isEmpty()){
            numero = Integer.valueOf(txtNumero.getText().toString());
        }
        if(!txtCep.getText().toString().isEmpty()){
            cep = Integer.valueOf(txtCep.getText().toString());
        }

        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setPais(pais);

    }

}
