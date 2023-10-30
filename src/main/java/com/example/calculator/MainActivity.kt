package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_0=findViewById<TextView>(R.id.btn_0)
        val one=findViewById<TextView>(R.id.one)
        val two=findViewById<TextView>(R.id.two)
        val three=findViewById<TextView>(R.id.three)
        val four=findViewById<TextView>(R.id.four)
        val eight=findViewById<TextView>(R.id.eight)
        val five=findViewById<TextView>(R.id.five)
        val six=findViewById<TextView>(R.id.six)
        val seven=findViewById<TextView>(R.id.seven)
        val nine=findViewById<TextView>(R.id.nine)
        val min=findViewById<TextView>(R.id.min)
        val sum=findViewById<TextView>(R.id.sum)
        val mult=findViewById<TextView>(R.id.mult)
        val div=findViewById<TextView>(R.id.divide)
        val l_bracket=findViewById<TextView>(R.id.l_bracket)
        val r_bracket=findViewById<TextView>(R.id.r_bracket)
        val clear=findViewById<TextView>(R.id.ac)
        val result=findViewById<TextView>(R.id.mathsresult)
        val mathoper=findViewById<TextView>(R.id.mathsoper)
        val back=findViewById<TextView>(R.id.del)
        val equal=findViewById<Button>(R.id.equal)

        btn_0.setOnClickListener { textField("0") }
        one.setOnClickListener { textField("1") }
        two.setOnClickListener { textField("2") }
        three.setOnClickListener { textField("3") }
        four.setOnClickListener { textField("4") }
        five.setOnClickListener { textField("5") }
        six.setOnClickListener { textField("6") }
        seven.setOnClickListener { textField("7") }
        eight.setOnClickListener { textField("8") }
        nine.setOnClickListener { textField("9") }
        min.setOnClickListener { textField("-") }
        sum.setOnClickListener { textField("+") }
        mult.setOnClickListener { textField("*") }
        div.setOnClickListener { textField("/") }
        l_bracket.setOnClickListener { textField("(") }
        r_bracket.setOnClickListener { textField(")") }

        clear.setOnClickListener {
            mathoper.text=""
            result.text=""
        }
        back.setOnClickListener {
            val str=mathoper.text.toString()
            if (str.isNotEmpty()){
                mathoper.text=str.substring(0, str.length-1)
                result.text=""
            }
        }
        equal.setOnClickListener {
            try {
                val exc= ExpressionBuilder(mathoper.text.toString()).build() //приводим к выражению
                val res = exc.evaluate()  //конечный результат с дробной частью

                val longResult = res.toLong()  //int тоже самое
                if (res==longResult.toDouble()){
                    result.text=longResult.toString() //челое число
                } else{
                    result.text=res.toString() //иначе выводим с павающей точкой
                }


            } catch(e:Exception){
                result.text="Error"
            }
        }

    }
    fun textField(str:String){
        val result=findViewById<TextView>(R.id.mathsresult)
        val mathoper=findViewById<TextView>(R.id.mathsoper)

        if (result.text != ""){
            mathoper.text=result.text
            result.text=""
        }
        mathoper.append(str)
    }
}