package luna.arturo.calculadora_lunaarturo_210568

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Main2Activity : AppCompatActivity() {
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnPlus: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnEqual: Button
    private lateinit var btnAllClear: Button
    private lateinit var txtViewNumbersResult: TextView
    private var entrada = StringBuilder()
    private var numeroActual = 0.0
    private var operadorActual = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        btn0 = findViewById(R.id.btnZero)
        btn1 = findViewById(R.id.btnOne)
        btn2 = findViewById(R.id.btnTwo)
        btn3 = findViewById(R.id.btnThree)
        btn4 = findViewById(R.id.btnFour)
        btn5 = findViewById(R.id.btnFive)
        btn6 = findViewById(R.id.btnSix)
        btn7 = findViewById(R.id.btnSeven)
        btn8 = findViewById(R.id.btnEight)
        btn9 = findViewById(R.id.btnNine)
        btnPlus = findViewById(R.id.btnPlus)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEqual = findViewById(R.id.btnEqual)
        btnAllClear = findViewById(R.id.btnAllClear)
        txtViewNumbersResult = findViewById(R.id.textViewNumberResult)

        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }

        btnPlus.setOnClickListener { setOperator("+") }
        btnSubtract.setOnClickListener { setOperator("-") }
        btnMultiply.setOnClickListener { setOperator("*") }
        btnDivide.setOnClickListener { setOperator("/") }
        btnEqual.setOnClickListener { calculateResult() }
        btnAllClear.setOnClickListener { clearAll() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun appendNumber(number: String) {
        entrada.append(number)
        updateDisplay()
    }

    private fun setOperator(operator: String) {
        if (entrada.isNotEmpty()) {
            numeroActual = entrada.toString().toDouble()
            operadorActual = operator
            entrada.clear()
            updateDisplay()
        }
    }

    private fun calculateResult() {
        if (entrada.isNotEmpty() && operadorActual.isNotEmpty()) {
            val segundoNumero = entrada.toString().toDouble()
            var resultado = 0.0

            when (operadorActual) {
                "+" -> resultado = numeroActual + segundoNumero
                "-" -> resultado = numeroActual - segundoNumero
                "*" -> resultado = numeroActual * segundoNumero
                "/" -> resultado = if (segundoNumero != 0.0) numeroActual / segundoNumero else Double.NaN
            }
            entrada.clear()
            entrada.append(resultado)
            updateDisplay()
            numeroActual = resultado
            operadorActual = ""
        }
    }

    private fun clearAll() {
        entrada.clear()
        numeroActual = 0.0
        operadorActual = ""
        updateDisplay()
    }

    private fun updateDisplay() {
        txtViewNumbersResult.text = entrada.toString()
    }
}
