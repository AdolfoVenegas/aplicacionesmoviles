package mx.edu.iton.potros.practica10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import mx.edu.iton.potros.practica10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val menuBtn : Button = findViewById(R.id.btn_seeMenu)

        menuBtn.setOnClickListener{
            var intent: Intent = Intent(this, menu_grid::class.java)
            startActivity(intent)
        }

        val itemsBtn : Button = findViewById(R.id.btn_addItem)

        itemsBtn.setOnClickListener{
            var intent : Intent = Intent(this, manage_items::class.java)
            startActivity(intent)
        }
    }
}