package com.ex.iconos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ex.iconos.Modelo.Persona;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

     private ArrayList<Persona> personas=new ArrayList<>();
     private EditText etNombre, etApellido,etCorreo;
     private ListView lvPersonas;
     private ArrayAdapter<Persona> personaArrayAdapter;
     private Persona personaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre=(EditText) findViewById(R.id.etNombrePersona);
        etApellido=(EditText) findViewById(R.id.etApellidoPersona);
        etCorreo=(EditText) findViewById(R.id.etNombrePersona);
        lvPersonas=(ListView) findViewById(R.id.lvDatospersonas);
        lvPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                personaSeleccionada=(Persona) adapterView.getItemAtPosition(i);
                etNombre.setText(personaSeleccionada.getNombre());
                etApellido.setText(personaSeleccionada.getApellido());
                etCorreo.setText(personaSeleccionada.getCorreo());

            }
        });
    }

    private void listarDatos() {
        personaArrayAdapter =new ArrayAdapter<Persona>(
                MainActivity.this, android.R.layout.simple_list_item_1,personas
        );
        lvPersonas.setAdapter(personaArrayAdapter);

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super .onCreateOptionsMenu(menu);
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.icon_add:
                if(validar()) {
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(etNombre.getText().toString());
                    p.setApellido(etApellido.getText().toString());
                    p.setCorreo(etCorreo.getText().toString());
                    personas.add(p);
                    listarDatos();
                    limpiar();
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Ingrese todos los datos",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.icon_delete:
                personas.remove(personaSeleccionada);
                limpiar();
                listarDatos();
                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    private boolean validar()
    {
        if(etNombre.getText().toString().equals(""))
        {
            etNombre.setError("Required");
            return false;

        }
        if(etApellido.getText().equals(""))
        {
            etApellido.setError("Required");
            return false;
        }
        if (etCorreo.getText().toString().equals(""))
        {
            etCorreo.setError("Required");
            return false;
        }
        return true;
    }
    private void limpiar()
    {
        etNombre.setText("");
        etApellido.setText("");
        etCorreo.setText("");
    }

}