package com.example.examenu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity<Bundle> : AppCompatActivity() {


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temperaturaC:EditText=findViewById(R.id.etC)
        val temperaturaF:EditText=findViewById(R.id.etF)
        val calculaC:Button=findViewById(R.id.btnC)
        val calculaF:Button=findViewById(R.id.btnGF)

        calculaC.setOnClickListener {
            var temperatura: Float = 0f

            try {
                temperatura = temperaturaC.text.toString().toFloat()
            } catch (e: java.lang.Exception) {
                temperaturaC.setText("Debes ingresar valores reales")
                print(e)
            }
            val resultado = calculaTC(temperatura)
            val formattedNumber = "%.2f".format(resultado)
            temperaturaF.setText(formattedNumber)
        }

        calculaF.setOnClickListener {
            var temperatura: Float = 0f

            try {
                temperatura = temperaturaF.text.toString().toFloat()
            } catch (e: java.lang.Exception) {
                temperaturaF.setText("Debes ingresar valores reales")
                print(e)
            }
            val resultado = calculaTF(temperatura)
            val formattedNumber = "%.2f".format(resultado)
            temperaturaC.setText(formattedNumber)
        }

    }
    fun calculaTC(temperaturaF: Float): Float {
        return (temperaturaF - 32) * 5 / 9
    }
    fun calculaTF(temperaturaC: Float): Float {
        return (temperaturaC * 9 / 5) + 32
    }
}