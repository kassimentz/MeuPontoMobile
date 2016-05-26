package com.edm.kassimentz.meupontomobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnderecoFragment extends Fragment {


    public EnderecoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_endereco, container, false);

        Spinner spinnerCidade = (Spinner) v.findViewById(R.id.spinCidade);
        ArrayAdapter<CharSequence> adapterCidade = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.principaisCidades, android.R.layout.simple_spinner_item);
        adapterCidade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCidade.setAdapter(adapterCidade);

        Spinner spinnerEstado = (Spinner) v.findViewById(R.id.spinEstado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.estados, android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerEstado.setAdapter(adapterEstado);

        Spinner spinnerPais = (Spinner) v.findViewById(R.id.spinPais);
        ArrayAdapter<CharSequence> adapterPais = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.paisesLatinos, android.R.layout.simple_spinner_item);
        adapterPais.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPais.setAdapter(adapterPais);


        return v;


    }

}
