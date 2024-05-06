package mx.edu.iton.potros.practica6


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.boton)

        button.setOnClickListener(){
            var intent: Intent = Intent(this, catalago::class.java)
            startActivity(intent)
        }
    }
}