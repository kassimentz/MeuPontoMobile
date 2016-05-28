package com.edm.kassimentz.meupontomobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;

public class LoginActivity extends AppCompatActivity {

    FuncionarioDAOImpl funcionarioDAO;
    EditText txtLogin, txtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

    }

    public void cadastrar (View v){
        Intent cadastro =  new Intent(this, CadastroActivity.class);
        startActivity(cadastro);
    }

    public void logar (View v){

        Toast toast;
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;
        String texto = null;

        if(txtLogin.getText().toString().isEmpty() || txtLogin.getText().toString().isEmpty()){
            texto = "Usuário e senha são obrigatórios";
            toast= Toast.makeText(contexto, texto,duracao);
            toast.show();
            return;
        }


        funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());
        if(funcionarioDAO.login(txtLogin.getText().toString(), txtSenha.getText().toString())){
            texto = "Login realizado com sucesso";
            toast= Toast.makeText(contexto, texto,duracao);
            toast.show();
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(main);
            finish();
        }else{
            txtSenha.requestFocus();
            texto = "Usuário ou senha não encontrados";
            toast= Toast.makeText(contexto, texto,duracao);
            toast.show();
        }


    }
}
