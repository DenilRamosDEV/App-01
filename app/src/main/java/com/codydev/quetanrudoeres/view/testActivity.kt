package com.codydev.quetanrudoeres.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codydev.quetanrudoeres.R
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class testActivity : AppCompatActivity() {
    private var correctAnswers = 0
    lateinit var progressBar: ProgressBar
    lateinit var tvConversation: TextView
    lateinit var btnNext: Button
    lateinit var btnHome: ImageView
    var cont = 0

    private val questions = arrayOf(
        Question(
            "¿Cuál es el lenguaje de programación más popular?",
            arrayOf("Python", "JavaScript", "Java", "C++"),
            "Python"
        ),
        Question(
            "¿Cuál es el mejor framework para el desarrollo de aplicaciones Android?",
            arrayOf("Kotlin", "Flutter", "Xamarin.Forms", "React Native"),
            "Kotlin"
        ),
        Question(
            "¿Cuál es el sistema de gestión de bases de datos más popular?",
            arrayOf("PostgreSQL", "Oracle Database", "MongoDB", "MySQL"),
            "MySQL"
        ),
        Question(
            "¿Cuál es el tipo de datos más básico en Kotlin?",
            arrayOf("Int", "Long", "Float", "Double"),
            "Int"
        ),
        Question(
            "¿Qué es un `null` en Kotlin?",
            arrayOf(
                "puede ser nulo",
                "siempre es 0",
                "es un valor no definido",
                "es un valor de tipo String"
            ),
            "puede ser nulo"
        ),
        Question(
            "¿Cuál es el operador de asignación en Kotlin?",
            arrayOf("===", "==", "->", "="),
            "="
        ),
        Question(
            "¿Cuál no es un tipo de dato primitivo en el lenguaje de programación Java?",
            arrayOf("int", "float", "string", "char"),
            "string"
        ),
        Question(
            "¿Se utiliza para repetir un bloque de código mientras se cumple una condición?",
            arrayOf("if-else", "try-catch", "switch", "for"),
            "for"
        ),
        Question(
            "¿Lenguaje de programación utiliza para desarrollar aplicaciones móviles en iOS?",
            arrayOf("Java", "Python", "C#", "Swift"),
            "Swift"
        ),
        Question(
            "¿Qué es un \"puntero\" en programación?",
            arrayOf("Un tipo de variable que almacena números enteros.", "Un operador utilizado para multiplicar dos valores.", "Una variable que almacena direcciones de memoria.", "Un tipo de bucle en programación."),
            "Una variable que almacena direcciones de memoria."
        )
    )
    val conversationList: List<String> = listOf(
        "Salty: \n\nQue lejos as llegado",
        "Salty: \n\nTerminó el interrogatorio",
        "Salty: \n\n¿Estas listo para ver Puntuacio?",
        "Salty: \n\ntiene una nota final de:"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var currentQuestion = 0
        // Inicializa vistas
        tvConversation = findViewById(R.id.tvConversation)
        btnNext = findViewById(R.id.btnNext)
        btnHome = findViewById(R.id.btnHome)

        //descativar btn Home
        btnHome.isEnabled = false
        // Ocultar el botón
        btnHome.visibility = View.INVISIBLE

        //en el clik
        btnNext.setOnClickListener {
            if (cont < conversationList.size - 1) {
                cont++
                tvConversation.text = conversation(cont)
                if (cont == conversationList.size - 1) {
                    // Cambia el texto del botón cuando llegues al penúltimo elemento
                    btnNext.text = "Ver mi puntaje"
                    showResultDialog()
                }
            } else if (cont == conversationList.size - 1 && btnNext.text == "¡Sí!") {
                // Redirige a otro Activity cuando el botón ya esté en "¡Sí!"
                //val intent = Intent(this, testActivity::class.java)
                //startActivity(intent)
                //showResultDialog()
                btnNext.text = "Ver mi puntaje"
                //showResultDialog()
            }
        }

        // Mostrar la primera pregunta.
        showQuestion(currentQuestion)
    }

    private fun showQuestion(questionIndex: Int) {
        progressBar = findViewById(R.id.progressBar)
        val question = questions[questionIndex]

        val dialog = AlertDialog.Builder(this)
            .setTitle(question.text)
            .setSingleChoiceItems(question.options, -1, null)
            .setPositiveButton("Probar suerte") { dialog, which ->
                val selectedOptionIndex = (dialog as AlertDialog).listView.checkedItemPosition
                val selectedOption = question.options[selectedOptionIndex]
                // Verificar si la opción seleccionada es la respuesta correcta.
                if (selectedOption == question.correctAnswer) {
                    // Respuesta correcta.
                    correctAnswers++
                }
                // Actualizar el progreso en la ProgressBar.
                progressBar.progress = questionIndex + 1

                // Mostrar la próxima pregunta o finalizar si no hay más preguntas.
                if (questionIndex < questions.size - 1) {
                    showQuestion(questionIndex + 1)
                } else {
                    // No hay más preguntas, puedes finalizar la actividad o hacer otra acción.
                    //showResultDialog()
                    dialog.dismiss()
                }
            }
            .setNegativeButton("Me rindo") { dialog, _ ->
                // Ir a la pantalla de resultados si el usuario se rinde.
                showResultDialog()
            }
            .create()
        dialog.show()
    }

    private fun showResultDialog() {
        val resultDialog = AlertDialog.Builder(this)
            .setTitle("Puntuacion")
            .setMessage("Preguntas correctas: $correctAnswers/10") // Muestra la puntuación
            .setPositiveButton("Cerrar") { dialog, _ ->
                tvConversation.text = "Tienes $correctAnswers puntos"
                // Desactivar el botón
                btnNext.isEnabled = false
                btnHome.isEnabled = true

                // Ocultar el botón
                btnNext.visibility = View.INVISIBLE
                btnHome.visibility = View.VISIBLE

                btnHome.setOnClickListener {
                    val intent: Intent = when (correctAnswers) {
                        in 0..4 -> Intent(this, CasaSuperPequeActivity::class.java)
                        in 5..8 -> Intent(this, CasaPequeActivity::class.java)
                        in 9..10 -> Intent(this, CasaRudoActivity::class.java)
                        else -> {
                            // Si la puntuación no está en ninguno de los rangos, te lleva al inicio
                            Intent(this, homeActivity::class.java)
                        }
                    }
                    // Inicia la actividad correspondiente
                    startActivity(intent)
                }
                dialog.dismiss()
            }
            .create()

        resultDialog.show()
    }

    fun conversation(numero: Int): String {
        return conversationList[numero]
    }
}

class Question(
    val text: String,
    val options: Array<String>,
    val correctAnswer: String
)
