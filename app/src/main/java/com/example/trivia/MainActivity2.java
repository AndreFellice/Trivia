package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trivia.databinding.ActivityMain2Binding;
import com.example.trivia.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    // Declaración de vistas

    private ActivityMain2Binding binding;
    private TextView textv3, pregunta;
    private Button respuesta;
   String nombreUsuario;
    private Fragment ganasteFragment;
    private Fragment perdisteFragment;
   // private boolean isFragmentShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el nombre del usuario de MainActivity
        Intent intent = getIntent();
        nombreUsuario = intent.getStringExtra("nombre_usuario");

        // Mostrar el saludo
        binding.textv3.setText(getString(R.string.textv3) + " " + nombreUsuario);


        // Configurar la pregunta
        binding.question.setText(getString(R.string.question));


        // Configurar el botón de respuesta
        binding.btn2.setText(getString(R.string.btn2));
        binding.btn2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View v){
        // Verificar la respuesta del usuario
        boolean respuestaCorrecta = verificarRespuesta();
        // Mostrar el fragmento correspondiente
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (respuestaCorrecta) {
            showGanasteFragment(ft);
            //ft.replace(R.id.contenedor, new Ganaste());
        } else {
           // ft.replace(R.id.contenedor, new Perdiste());
            showPerdisteFragment(ft);
        }
        ft.commit();
            /* Llamar a los métodos para abrir o cerrar el fragmento
            if (respuestaCorrecta) {
                openFragment();
            } else {
                closeFragment();
            }*/
        }
    });
        // Inicializar los fragmentos
        ganasteFragment = new Ganaste();
        perdisteFragment = new Perdiste();
}


    private boolean verificarRespuesta() {
        // Obtener el ID del RadioButton seleccionado
        int radioButtonId = binding.radioGroup.getCheckedRadioButtonId();

        // Verificar si el RadioButton seleccionado es el correcto (RadioButton5)
        return radioButtonId == R.id.radioButton5;
    }


    private void showGanasteFragment(FragmentTransaction ft) {
        if (ganasteFragment.isAdded()) {
            ft.show(ganasteFragment);
        } else {
            ft.add(R.id.contenedor, ganasteFragment, Ganaste.class.getSimpleName());
        }

        if (perdisteFragment.isAdded()) {
            ft.hide(perdisteFragment);
        }
    }

        private void showPerdisteFragment (FragmentTransaction ft){
            if (perdisteFragment.isAdded()) {
                ft.show(perdisteFragment);
            } else {
                ft.add(R.id.contenedor, perdisteFragment, Perdiste.class.getSimpleName());
            }

            if (ganasteFragment.isAdded()) {
                ft.hide(ganasteFragment);
            }
        }

    }

    // Inicialización de vistas
 /*   private void openFragment() {
        Ganaste ganaste = new Ganaste();
       getSupportFragmentManager().beginTransaction().replace(binding.contenedor
               .getId(), ganaste, Ganaste.class.getSimpleName()).commit();*/

       //FragmentTransaction transaction = manager.beginTransaction()
               // .replace(winner.contenedor.getId(), ganaste,
                      //  Ganaste.class.getSimpleName());

       // winner.btn2.setText("ABRIR");
        //isFragmentShow = true;  }




   /* private void closeFragment(){
        FragmentManager manager = getSupportFragmentManager();
        Perdiste perdiste = (Perdiste) manager.findFragmentById(R.id.contenedor);

        if (perdiste != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(perdiste).commit();
            //winner.btn2.setText("CERRAR");
            // isFragmentShow = false;
        }

}*/

