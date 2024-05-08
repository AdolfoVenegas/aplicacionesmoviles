package mx.edu.iton.potros.practica9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEvent
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private val userRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave: Button = findViewById(R.id.save_button)
        btnSave.setOnClickListener { saveMarkFromForm() }

        userRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("MainActivity", "Firebase Error: ${error.message}")
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                try {
                    val usuario = dataSnapshot.getValue(User::class.java)
                    if (usuario != null) {
                        writeMark(usuario)
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error al obtener datos de Firebase: ${e.message}")
                }
            }
        })
    }

    private fun saveMarkFromForm() {
        val name: EditText = findViewById(R.id.et_name)
        val lastName: EditText = findViewById(R.id.et_lastName)
        val age: EditText = findViewById(R.id.et_age)

        val usuario = User(
            firstName = name.text.toString(),
            lastName = lastName.text.toString(),
            age = age.text.toString()
        )
        userRef.push().setValue(usuario)
            .addOnSuccessListener {
                Log.d("MainActivity", "Datos guardados correctamente en Firebase.")
            }
            .addOnFailureListener { e ->
                Log.e("MainActivity", "Error al guardar datos en Firebase: ${e.message}")
            }
    }

    private fun writeMark(mark: User) {
        val listV: TextView = findViewById(R.id.list_textView)
        val text = listV.text.toString() + mark.toString() + "\n"
        listV.text = text
    }
}
