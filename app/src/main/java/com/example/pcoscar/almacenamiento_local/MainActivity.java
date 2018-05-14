package com.example.pcoscar.almacenamiento_local;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText txtnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnota=findViewById(R.id.editText);
        String archivos[]= fileList();

        if (isArchivoExiste(archivos , "bitacora.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br=new BufferedReader(archivo);
                String linea=br.readLine();
                String bitacoracompleta ="";
                while(linea!=null){
                    bitacoracompleta=bitacoracompleta+linea + "\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                txtnota.setText(bitacoracompleta);

            }
            catch (IOException e){

            }

        }

    }

    private boolean isArchivoExiste(String archivos[] , String  NombreArchivo) {
        for (int i=0 ;i<archivos.length;i++)
            if (NombreArchivo.equals(archivos[i]))
                return true ;
        return false;
    }

    public void Guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(txtnota.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }

        Toast.makeText(this,"Bitacora guardada correctamente" , Toast.LENGTH_SHORT).show();
        finish();

    }


}
