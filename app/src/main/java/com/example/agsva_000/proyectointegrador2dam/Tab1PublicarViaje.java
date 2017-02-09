package com.example.agsva_000.proyectointegrador2dam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Tab1PublicarViaje extends Fragment {

    private EditText etOrigen;
    private EditText etDestino;
    private EditText etFecha;
    private EditText etHora;
    private EditText etPrecio;
    private Button botonPublicar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1publicarviaje, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etOrigen = (EditText) view.findViewById(R.id.etOrigen);
        etDestino = (EditText) view.findViewById(R.id.etDestino);
        etFecha = (EditText) view.findViewById(R.id.etFecha);
        etHora = (EditText) view.findViewById(R.id.etHora);
        etPrecio = (EditText) view.findViewById(R.id.etPrecio);

        botonPublicar = (Button) view.findViewById(R.id.botonPublicarViaje);

        botonPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Publicando", "Publicando viaje...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();


                String origen = etOrigen.getText().toString();
                String destino = etDestino.getText().toString();
                String fecha = etFecha.getText().toString();
                String hora = etHora.getText().toString();
                String precio = etPrecio.getText().toString();

                boolean esValido = true;

                if(origen == null || origen.length() == 0){
                    etOrigen.setError("Origen debe estar relleno");
                    esValido = false;
                }

                if(destino == null || destino.length() == 0){
                    etDestino.setError("Destino debe estar relleno");
                    esValido = false;
                }

                if(fecha == null || fecha.length() == 0){
                    etFecha.setError("Fecha debe estar relleno");
                    esValido = false;
                }

                if(hora == null || hora.length() == 0){
                    etHora.setError("Hora debe estar relleno");
                    esValido = false;
                }

                if(precio == null || precio.length() == 0){
                    etPrecio.setError("Precio debe estar relleno");
                    esValido = false;
                }

                if(!esValido) {
                    progressDialog.hide();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference travelsData = database.getReference("travels");

                /*travelsData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> childrenIterator = dataSnapshot.getChildren().iterator();
                        while(childrenIterator.hasNext()){
                            Travel travel = childrenIterator.next().getValue(Travel.class);
                            etOrigen.setText(travel.getOrigen());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/



                Travel travel = new Travel();
                travel.setOrigen(origen);
                travel.setDestino(destino);
                travel.setHora(hora);
                travel.setFecha(fecha);
                travel.setPrecio(precio);
                travel.setUserId(MainActivity.loggedUser.getUid());

                travelsData.push().setValue(travel, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        progressDialog.hide();
                        if(databaseError == null && databaseReference != null){
                            Toast.makeText(getContext(), "Viaje publicado correctamente", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), "Error al publicar viaje", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
