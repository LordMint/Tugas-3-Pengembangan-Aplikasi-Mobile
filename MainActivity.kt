import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var pendingOperation = ""
    private var isOperatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { digitPressed((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { operatorPressed("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { operatorPressed("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { operatorPressed("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { operatorPressed("/") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { performOperation() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
    }

    private fun digitPressed(digit: String) {
        if (isOperatorPressed) {
            display.text = digit
            isOperatorPressed = false
        } else {
            display.text = if (display.text == "0") digit else display.text.toString() + digit
        }
    }

    private fun operatorPressed(operator: String) {
        if (!isOperatorPressed) {
            operand1 = display.text.toString().toDouble()
            pendingOperation = operator
            isOperatorPressed = true
        }
    }

    private fun performOperation() {
        operand2 = display.text.toString().toDouble()

        val result = when (pendingOperation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> operand2
        }

        display.text = result.toString()
        isOperatorPressed = true
    }

    private fun clearAll() {
        operand1 = 0.0
        operand2 = 0.0
        display.text = "0"
        isOperatorPressed = false
    }
}
