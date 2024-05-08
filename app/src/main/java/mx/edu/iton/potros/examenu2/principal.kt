package mx.edu.iton.potros.examenu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val detallesBtn : Button = findViewById(R.id.btnDetalles)
        val globosBtn : Button = findViewById(R.id.btnGlobos)
        val peluchesBtn : Button = findViewById(R.id.btnPeluches)
        val regalosBtn : Button = findViewById(R.id.btnRegalos)
        val tazasBtn : Button = findViewById(R.id.btnTazas)

        detallesBtn.setOnClickListener {
            startRegalosActivity("detalles")
        }
        globosBtn.setOnClickListener {
            startRegalosActivity("globos")
        }
        peluchesBtn.setOnClickListener {
            startRegalosActivity("peluches")
        }
        regalosBtn.setOnClickListener {
            startRegalosActivity("regalos")
        }
        tazasBtn.setOnClickListener {
            startRegalosActivity("tazas")
        }
    }

    private fun startRegalosActivity(categoria: String) {
        val intent = Intent(this, regalos::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}
