package com.edm.kassimentz.meupontomobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.edm.kassimentz.meupontomobile.model.Funcionario;



/**
 * A simple {@link Fragment} subclass.
 */
public class DadosPessoaisFragment extends Fragment {

    private Funcionario funcionario;
    EditText txtNome, txtCpf, txtUsuario, txtSenha;


    public DadosPessoaisFragment() {
        // Required empty public constructor
        funcionario = new Funcionario();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dados_pessoais, container, false);
        txtNome = (EditText) v.findViewById(R.id.txtNome);
        txtCpf = (EditText) v.findViewById(R.id.txtCpf);
        txtUsuario = (EditText) v.findViewById(R.id.txtNome);
        txtSenha = (EditText) v.findViewById(R.id.txtSenha);


        return v;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {
                setarFuncionario();
                ((CadastroActivity)getActivity()).setFuncionario(funcionario);
            }
        }
    }

    private void setarFuncionario() {

        String nome = null, cpf = null, usuario = null, senha = null;

        if(!txtNome.getText().toString().isEmpty()){
            nome = txtNome.getText().toString();
        }
        if(!txtCpf.getText().toString().isEmpty()){
            cpf = txtCpf.getText().toString();
        }
        if(!txtSenha.getText().toString().isEmpty()){
            senha = txtSenha.getText().toString();
        }
        if(!txtUsuario.getText().toString().isEmpty()){
            usuario = txtUsuario.getText().toString();
        }

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setUsuario(usuario);
        funcionario.setSenha(senha);
    }


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
