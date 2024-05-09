package mx.edu.iton.potros.practica10

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class menu_grid : AppCompatActivity() {

    private lateinit var items: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_grid)

        val listView: ListView = findViewById(R.id.menu_item_list)

        // Inicializar la lista de items
        items = ArrayList()

        // Obtener referencia a la base de datos de Firebase
        val dbRef = FirebaseDatabase.getInstance().getReference("Item")

        // Agregar un listener para obtener los datos de Firebase
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Limpiar la lista antes de agregar nuevos datos
                items.clear()

                // Iterar sobre los datos obtenidos de Firebase y agregarlos a la lista
                for (data in snapshot.children) {
                    val item = data.getValue(Item::class.java)
                    item?.let { items.add(it) }
                }

                // Crear y asignar adaptador al ListView
                val adaptador = AdaptadorItems(this@menu_grid, items)
                listView.adapter = adaptador
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores de base de datos
            }
        })
    }

    private class AdaptadorItems(private val contexto: Context, private val items: List<Item>) :
        BaseAdapter() {

        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val item = items[position]
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.item, null)

            val nombre = vista.findViewById<TextView>(R.id.producto_nombre)
            val descripcion = vista.findViewById<TextView>(R.id.item_desc)
            val precio = vista.findViewById<TextView>(R.id.item_price)

            nombre.text = item.nombre
            descripcion.text = item.descripcion
            precio.text = "$${item.precio}"

            return vista
        }
    }
}
