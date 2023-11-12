package com.codydev.quetanrudoeres.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.codydev.quetanrudoeres.R

class homeActivity : AppCompatActivity() {

    //variables
    lateinit var tvConversation:TextView
    lateinit var btnNext:Button
    lateinit var btnSaltar:Button
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializa vistas
        tvConversation = findViewById(R.id.tvConversation)
        btnNext = findViewById(R.id.btnNext)
        btnSaltar=findViewById(R.id.btnSaltar)

        // Asigna el texto inicial desde la conversación
        tvConversation.text = conversation(cont)

        // Agrega un listener al botón para avanzar en la conversación
        btnNext.setOnClickListener {
            if (cont < conversationList.size - 1) {
                cont++
                tvConversation.text = conversation(cont)
                if (cont == conversationList.size - 1) {
                    // Cambia el texto del botón cuando llegues al penúltimo elemento
                    btnNext.text = "¡Sí!"
                }
            }else if (cont == conversationList.size - 1 && btnNext.text == "¡Sí!") {
                // Redirige a otro Activity cuando el botón ya esté en "¡Sí!"
                val intent = Intent(this, testActivity::class.java)
                startActivity(intent)
            }
        }
        btnSaltar.setOnClickListener {
            val intent = Intent(this, testActivity::class.java)
            startActivity(intent)
        }

    }

    fun conversation(numero: Int): String {
        return conversationList[numero]
    }

    // Lista de conversación
        val conversationList:List<String> = listOf(
            "Patricio Estrella: \n\n¿Has oído hablar de la nueva aplicación de aprendizaje de programación?",
            "Me: \n\nNo, ¿de qué se trata?",
            "Patricio Estrella: \n\nSe llama \"QUE TAN RUDO ERES\". Es una aplicación de aprendizaje de programación que utiliza un enfoque lúdico.",
            "Me: \n\n¿Suena interesante. ¿Cómo funciona?",
            "Patricio Estrella: \n\nLa aplicación presenta una serie de preguntas de programación de nivel progresivo.",
            "Patricio Estrella: \n\nDe acuerdo con las respuestas del usuario, la aplicación le asigna a una de las \"CASAS\".",
            "Me: \n\n¿\"CASAS\"?..........¿Y qué casas hay?",
            "Patricio Estrella: \n\nHay tres casas: la casa de los superpequeñines , la casa de los pequeñines y la casa del rudo.",
            "Me: \n\nmmmmm......¿Y qué hay encada casa?",
            "Patricio Estrella: \n\n1: La casa de los superpequeñines es para los usuarios que están aprendiendo los fundamentos de la programación (Nivel Basico).",
            "Patricio Estrella: \n\n2: La casa de los pequeñines es para los usuarios que tienen un buen conocimiento de los fundamentos de la programación  (Nivel Intermedio).",
            "Patricio Estrella: \n\n3: La casa del rudo es para los usuarios que son expertos en programación (Nivel Avanzado).",
            "Me: \n\nQue emocion, ya quiero empezar!!!",
            "Patricio Estrella: \n\n¿Estas listo?"
        )

}