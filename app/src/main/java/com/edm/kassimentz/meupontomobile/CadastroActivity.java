package com.edm.kassimentz.meupontomobile;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    FuncionarioDAOImpl funcionarioDAO;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_dados_pessoais,
            R.drawable.ic_tab_endereco,
            R.drawable.ic_tab_telefone
    };

    private Funcionario funcionario;
    private Endereco endereco;
    private Telefone telefone;

	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }


    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DadosPessoaisFragment(), "Dados");
        adapter.addFragment(new EnderecoFragment(), "Endereço");
        adapter.addFragment(new TelefoneFragment(), "Telefone");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }
    }

    public void persistirFuncionario(){

        Toast toast;

        funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());
		Funcionario f = this.getFuncionario();
        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(this.getEndereco());

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(this.getTelefone());
        f.setEnderecos(enderecos);
        f.setTelefones(telefones);
        funcionarioDAO.salvar(f);
        Log.i("info", "funcionario cadastrado com sucesso");

        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;
        String texto = "Cadastro realizado com sucesso!";
        toast= Toast.makeText(contexto, texto,duracao);
        toast.show();

    }
    
    public setFuncionario(Funcionario funcionario){
		this.funcionario = funcionario;
	}
	
	public getFuncionario(){
		return this.funcionario;
	}
	
	public setEndereco(Endereco endereco){
		this.endereco = endereco;
	}
	
	public getEndereco(){
		return this.endereco;
	}
	
	public setTelefone(Telefone telefone){
		this.telefone = telefone;
	}
	
	public getTelefone(){
		return this.telefone;
	}
}
