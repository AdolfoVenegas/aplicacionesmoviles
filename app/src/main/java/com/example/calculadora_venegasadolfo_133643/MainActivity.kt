package com.example.calculadora_venegasadolfo_133643

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var oper:Int = 0
    var numero1: Double = 0.0
    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_num1 = findViewById(R.id.tvNumber1)
        tv_num2 = findViewById(R.id.tvNumber2)
        val btnBorrar: Button = findViewById(R.id.delete)
        val btnIgual: Button = findViewById(R.id.result)

        btnIgual.setOnClickListener{
            var numero2: Double = tv_num2.text.toString().toDouble()
            var resp: Double = 0.0

            when(oper){
                1 -> resp = numero1 + numero2
                2 -> resp = numero1 - numero2
                3 -> resp = numero1 * numero2
                4 -> resp = numero1 / numero2
            }
            tv_num2.setText(resp.toString())
            tv_num1.setText("")
        }
        btnBorrar.setOnClickListener{
            tv_num1.setText("")
            tv_num2.setText("")
            numero1 = 0.0
            oper = 0
        }
    }
    fun presionarDigito(view:View){
        //val tv_num2: TextView = findViewById(R.id.tvNumber2)
        var num2: String = tv_num2.text.toString()

        when(view.id){
            R.id.numberCero -> tv_num2.setText(num2 + "0")
            R.id.numberOne -> tv_num2.setText(num2 + "1")
            R.id.numberTwo -> tv_num2.setText(num2 + "2")
            R.id.numberThree -> tv_num2.setText(num2 + "3")
            R.id.numberFour -> tv_num2.setText(num2 + "4")
            R.id.numberFive -> tv_num2.setText(num2 + "5")
            R.id.numberSix -> tv_num2.setText(num2 + "6")
            R.id.numberSeven -> tv_num2.setText(num2 + "7")
            R.id.numberEight -> tv_num2.setText(num2 + "8")
            R.id.numberNine -> tv_num2.setText(num2 + "9")
        }
    }

    fun clickOperation(view: View){
        numero1 = tv_num2.text.toString().toDouble()
        var num2_text: String = tv_num2.text.toString()
        tv_num2.setText("")

        when(view.id){
            R.id.suma -> {
                tv_num1.setText(num2_text + "+")
                oper = 1
            }
            R.id.resta -> {
                tv_num1.setText(num2_text + "-")
                oper = 2
            }
            R.id.multiplica -> {
                tv_num1.setText(num2_text + "*")
                oper = 3
            }
            R.id.divide -> {
                tv_num1.setText(num2_text + "/")
                oper = 4
            }
        }
    }
}