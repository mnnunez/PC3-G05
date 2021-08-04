package com.example.actividadpstg5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

//Se crea la clase Metodos para modularizar el código del desarrollo de la app y tener el codigo
// más organizado

public class Metodos extends MainActivity {

    //Creamos el metodo registro el cual servirá para guardar la información en la base de datos
    public void registro(View view){
        BaseDeDatos admin= new BaseDeDatos(this,
                "administracion", null, 1); //Instancia de la base de datos
        SQLiteDatabase bd= admin.getWritableDatabase();
        //Obteniendo el contenido de cada uno de los EditText
        String matriculaText= matricula.getText().toString();
        String nombresText= nombres.getText().toString();
        String apellidosText= apellidos.getText().toString();
        String generoText= genero.getText().toString();

        if(!matriculaText.isEmpty()||!nombresText.isEmpty()||!apellidosText.isEmpty()||
                !generoText.isEmpty()){
            bd.execSQL("insert into usuarios(id_usuario, nombres, apellidos, matricula, genero)"
                    +"values("+matriculaText+",'"+nombresText+"','"+apellidosText+"',"
                    +generoText+"')"); //Ingresando la información a la base de datos
            bd.close();
            //Fijamos los EditText como vacíos para un nuevo ingreso de datos
            matricula.setText("");
            nombres.setText("");
            apellidos.setText("");
            genero.setText("");
            //Enviamos un mensaje de confirmacion de que los datos fueron ingresados correctamente
            Toast.makeText(this, "Estimado usuario, sus datos fueron ingresados satisfactoriamente",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Estimado usuario, por favor llene todos los campos",
                    Toast.LENGTH_SHORT).show(); //En caso que el usuario no llene todos los campos se mostrará este mensaje
        }
    }

    //Metodo para consultar al usuario por su numero de matricula
    public void consultaMatricula(View view){
        BaseDeDatos admin = new BaseDeDatos(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase(); //Obtenemos la base de datos para poder leerla
        String matriculaText = matricula.getText().toString();
        if(!matriculaText.isEmpty()){
            //Con el métido rawQuery podremos realizar consultas a la base de datos
            Cursor fila = bd.rawQuery(
                    "select nombres, apellidos, genero from usuarios where " +
                            "id_usuario=" + matriculaText, null); //Con select, from, where recorremos el cursor para obtener los datos
            if(fila.moveToFirst()){
                nombres.setText(fila.getString(0));
                apellidos.setText(fila.getString(1));
                genero.setText(fila.getString(2));
                Toast.makeText(this, "Consulta exitosa", //Mensaje de confirmación para una consulta exitosa
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Estimado usuario, el número de matrícula consultado no existe",
                        Toast.LENGTH_SHORT).show(); //Mensaje de alerta en caso que el ID del usuario no exista
            }
            bd.close();
        }else{
            Toast.makeText(this, "Estimado usuario, ingrese un número de matrícula",
                    Toast.LENGTH_SHORT).show(); //Mensaje para que el usuario ingrese otro ID y realizar la consulta
        }
        bd.close();
    }

    //Método para modificar la información de un usuario
    public void modificarInformacion(View v){
        BaseDeDatos admin= new BaseDeDatos(this,
                "administracion",null,1);

        SQLiteDatabase bd= admin.getWritableDatabase(); //Obtenemos la base de datos de manera  Writable para que podamos escribir los datos
        String matriculaText= matricula.getText().toString();
        String nombresText= nombres.getText().toString();
        String apellidosText= apellidos.getText().toString();
        String generoText= genero.getText().toString();

        if(!matriculaText.isEmpty()){
            if(nombresText.isEmpty()||apellidosText.isEmpty()||
                    generoText.isEmpty()){
                Toast.makeText(this, "Estimado usuario, ingrese todos los datos.",
                        Toast.LENGTH_SHORT).show();
            }
            //Usamos el método execSQL para escribir en la base de datos
            bd.execSQL("update usuarios set id_usuario="+matriculaText+",nombres='"
                    +nombresText+"',apellidos='"+apellidosText+"',genero="+generoText+"' where id_usuario="+matriculaText);
            matricula.setText("");
            nombres.setText("");
            apellidos.setText("");
            genero.setText("");
            bd.close();
            Toast.makeText(this, "Estimado usuario, los datos han sido modificados.",
                    Toast.LENGTH_SHORT).show(); //Mensaje de confirmacion para los datos modificados
        }else{
            Toast.makeText(this, "Estimado usuario, ingrese un número de matrícula.",
                    Toast.LENGTH_SHORT).show(); //Mensaje para ingresar otro ID
        }
    }

    //Método para eliminar un usuario
    public void eliminarUsuario(View v){
        BaseDeDatos admin = new BaseDeDatos(this, "administracion", null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String matriculaText= matricula.getText().toString();

        if(!matriculaText.isEmpty()){
            bd.execSQL("delete from usuarios where id_usuario"+ matriculaText);
            bd.close();
            matricula.setText("");
            nombres.setText("");
            apellidos.setText("");
            genero.setText("");
            Toast.makeText(this, "Usuario eliminado de la base de datos.",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Estimado usuario, ingrese un número de matrícula.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
