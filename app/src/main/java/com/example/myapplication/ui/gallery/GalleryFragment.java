package com.example.myapplication.ui.gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    public Button btnCalcular;


    public EditText editPrecoAlcool;
    public EditText editPrecoGasolina;
    public TextView textResultado;

    View vista;


    public GalleryFragment() {
        // Required empty public constructor
    }

    private FragmentGalleryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_gallery, container, false);

        btnCalcular = (Button) vista.findViewById(R.id.btnCalcular);
        editPrecoAlcool = (EditText) vista.findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = (EditText) vista.findViewById(R.id.editPrecoGasolina);
        textResultado = (TextView) vista.findViewById(R.id.textResultado);





        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                //recuperar valores digitados
                //String precoAlcool = editPrecoAlcool.getText().toString();
                String precoAlcool = editPrecoAlcool.getText().toString().replace("," , ".");
                //String precoGasolina = editPrecoGasolina.getText().toString();
                String precoGasolina = editPrecoGasolina.getText().toString().replace("," , ".");




                //Validar os campos digitados
                Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);
                if( camposValidados ){

                    //Convertendo string para números
                    Double valorAlcool = Double.parseDouble( precoAlcool );
                    Double valorGasolina = Double.parseDouble( precoGasolina );

                    /* Fazer cálculo de menor preço
                     * Se (valorAlcool / valorGasolina) >= 0.7 é melhor utilizar gasolina
                     *   senão é melhor utilizar álcool
                     * */
                    double resultado = valorAlcool / valorGasolina;
                    if( resultado >= 0.7 ){
                        textResultado.setText("Melhor utilize Gasolina");
                    }else {
                        textResultado.setText("Melhor utilizar Álcool");
                    }


                }else {
                    //textResultado.setText("Preencha os preços primeiro!");
                    Toast.makeText(getActivity(), "Preencha os preços primeiro!",
                            Toast.LENGTH_LONG).show();

                }
            }

            public Boolean validarCampos( String pAlcool, String pGasolina ){

                boolean camposValidados = true;

                if( pAlcool == null || pAlcool.equals("") ){
                    camposValidados = false;
                }else if( pGasolina == null || pGasolina.equals("") ){
                    camposValidados = false;
                }

                return camposValidados;

            }
        });


        return vista;

    }
}