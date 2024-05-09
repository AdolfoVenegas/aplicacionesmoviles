package mx.edu.iton.potros.practica10;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class manage_items extends AppCompatActivity{

    private EditText txtid, txtnom, txtPrice, txtDescription;
    private Button btnmod, btnreg, btneli, btnbus;

    private ListView lvDatos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items);

        txtid = (EditText) findViewById(R.id.txtid);
        txtnom = (EditText) findViewById(R.id.txtnom);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnmod);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btneli  = (Button)   findViewById(R.id.btneli);
        lvDatos = (ListView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
    }

    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(manage_items.this, "Type the item ID", Toast.LENGTH_SHORT).show();
                }else{

                    int id = Integer.parseInt(txtid.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Item.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    res = true;
                                    ocultarTeclado();

                                    // Establecer los datos del objeto Item en los campos correspondientes
                                    Item item = x.getValue(Item.class);
                                    txtnom.setText(item.getNombre());
                                    txtPrice.setText(String.valueOf(item.getPrecio()));
                                    txtDescription.setText(item.getDescripcion());
                                    break;
                                }
                            }

                            if(res == false){
                                ocultarTeclado();
                                Toast.makeText(manage_items.this, "ID ("+aux+") Not found", Toast.LENGTH_SHORT).show();
                            }
                            listarItems();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } // Cierra el if/else inicial.

            }
        });
    } // Cierra el método botonBuscar.

    private void botonModificar() {
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()
                        || txtnom.getText().toString().trim().isEmpty()
                        || txtPrice.getText().toString().trim().isEmpty()
                        || txtDescription.getText().toString().trim().isEmpty()) {

                    ocultarTeclado();
                    Toast.makeText(manage_items.this, "Complete all the fields", Toast.LENGTH_SHORT).show();

                } else {

                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    String descripcion = txtDescription.getText().toString();
                    Double precio = Double.parseDouble(txtPrice.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Item.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res2 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("nombre").getValue().toString().equalsIgnoreCase(nom)){
                                    res2 = true;
                                    ocultarTeclado();
                                    Toast.makeText(manage_items.this, "The name ("+nom+") is already registered. Unable to modify.", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }

                            if(res2 == false){
                                String aux = Integer.toString(id);
                                boolean res = false;
                                for(DataSnapshot x : snapshot.getChildren()){
                                    if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                        res = true;
                                        ocultarTeclado();
                                        x.getRef().child("nombre").setValue(nom);
                                        x.getRef().child("descripcion").setValue(descripcion);
                                        x.getRef().child("precio").setValue(precio);
                                        txtid.setText("");
                                        txtnom.setText("");
                                        txtPrice.setText("");
                                        txtDescription.setText("");
                                        listarItems(); // Método para actualizar la lista de elementos
                                        break;
                                    }
                                }

                                if(res == false){
                                    ocultarTeclado();
                                    Toast.makeText(manage_items.this, "ID ("+aux+") not found. Unable to modify", Toast.LENGTH_SHORT).show();
                                    txtid.setText("");
                                    txtnom.setText("");
                                    txtPrice.setText("");
                                    txtDescription.setText("");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Manejar errores aquí
                        }
                    });
                }
            }
        });
    }

    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()
                        || txtnom.getText().toString().trim().isEmpty()
                        || txtDescription.getText().toString().trim().isEmpty()
                        || txtPrice.getText().toString().trim().isEmpty()){

                    ocultarTeclado();
                    Toast.makeText(manage_items.this, "Complete all the fields", Toast.LENGTH_SHORT).show();

                } else {

                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();
                    String descripcion = txtDescription.getText().toString();
                    Double price = Double.parseDouble(txtPrice.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Item.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean idExist = false;
                            boolean nameExist = false;

                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equalsIgnoreCase(aux)){
                                    idExist = true;
                                    break;
                                }
                            }

                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("nombre").getValue().toString().equalsIgnoreCase(nom)){
                                    nameExist = true;
                                    break;
                                }
                            }

                            if(idExist){
                                ocultarTeclado();
                                Toast.makeText(manage_items.this, "Error. The ID ("+aux+") already exists", Toast.LENGTH_SHORT).show();
                            } else if(nameExist){
                                ocultarTeclado();
                                Toast.makeText(manage_items.this, "Error. The name ("+nom+") already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                Item newItem = new Item(id, nom, descripcion, price);
                                dbref.push().setValue(newItem);
                                ocultarTeclado();
                                Toast.makeText(manage_items.this, "Item successfully registered", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtDescription.setText("");
                                txtPrice.setText("");
                                listarItems();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle database error
                        }
                    });

                } // Cierra el if/else inicial.
            }
        });
    } // Cierra el método botonRegistrar.

    private void listarItems(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Item.class.getSimpleName());

        ArrayList<Item> lisluc = new ArrayList <Item> ();
        ArrayAdapter<Item> ada = new ArrayAdapter <Item> (manage_items.this, android.R.layout.simple_list_item_1, lisluc);
        lvDatos.setAdapter(ada);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Item luc = snapshot.getValue(Item.class);
                lisluc.add(luc);
                ada.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ada.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Item luc = lisluc.get(i);
                AlertDialog.Builder a = new AlertDialog.Builder(manage_items.this);
                a.setCancelable(true);
                a.setTitle("Selected item");

                String msg = "ID : " + luc.getId() +"\n\n";
                msg += "Name : " + luc.getNombre() + "\n\n";
                msg += "Price: " + luc.getPrecio() + "\n\n";
                msg += "Description" + luc.getDescripcion() + "\n\n";

                a.setMessage(msg);
                a.show();

            }
        });

    }

    private void botonEliminar(){
        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(manage_items.this, "Type the right ID", Toast.LENGTH_SHORT).show();
                } else {

                    int id = Integer.parseInt(txtid.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Item.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            final boolean[] res = {false};
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){

                                    AlertDialog.Builder a = new AlertDialog.Builder(manage_items.this);
                                    a.setCancelable(false);
                                    a.setTitle("Question");
                                    a.setMessage("¿Are you sure to delete it?");

                                    a.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // Handle exit action
                                        }
                                    });

                                    a.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            res[0] = true;
                                            ocultarTeclado();
                                            x.getRef().removeValue();
                                            listarItems();
                                        }
                                    });

                                    a.show();

                                    break;
                                }
                            }

                            if(res[0] == false){
                                ocultarTeclado();
                                Toast.makeText(manage_items.this, "ID ("+aux+") Not found.\nUnable to delete it", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle database error
                        }
                    });

                } // Cierra el if/else inicial.

            }
        });
    } // Cierra el método botonEliminar.


    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.

}
