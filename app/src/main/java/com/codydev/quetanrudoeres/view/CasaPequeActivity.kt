package com.codydev.quetanrudoeres.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.codydev.quetanrudoeres.R

class CasaPequeActivity : AppCompatActivity() {
    //variables
    lateinit var cbWeb: CheckBox
    lateinit var cbIa: CheckBox
    lateinit var cbMble: CheckBox
    lateinit var cbBack: CheckBox
    lateinit var cbFron: CheckBox

    lateinit var cvHtml: CardView
    lateinit var cvPy: CardView
    lateinit var cvKt: CardView
    lateinit var cvJv: CardView

    // Contador para rastrear la cantidad de toques en el botón Atrás
    private var backButtonPressedCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casa_peque)

        //inicializar las vistas
        cbWeb = findViewById(R.id.cbWeb)
        cbIa = findViewById(R.id.cbIa)
        cbMble = findViewById(R.id.cbMble)
        cbBack = findViewById(R.id.cbBack)
        cbFron = findViewById(R.id.cbFron)

        cvHtml = findViewById(R.id.cvHtml)
        cvPy = findViewById(R.id.cvPy)
        cvKt = findViewById(R.id.cvKt)
        cvJv = findViewById(R.id.cvJv)

        // Escuchar los cambios en los CheckBox
        cbWeb.setOnCheckedChangeListener { _, _ -> updateFilter() }
        cbIa.setOnCheckedChangeListener { _, _ -> updateFilter() }
        cbMble.setOnCheckedChangeListener { _, _ -> updateFilter() }
        cbBack.setOnCheckedChangeListener { _, _ -> updateFilter() }
        cbFron.setOnCheckedChangeListener { _, _ -> updateFilter() }

        // Agregar clic listeners a los CardView
        cvHtml.setOnClickListener { showCourseInfoDialog("Curso HTML Intermedio, disponible próximamente") }
        cvPy.setOnClickListener { showCourseInfoDialog("Curso Python Intermedio, disponible próximamente") }
        cvKt.setOnClickListener { showCourseInfoDialog("Curso Kotlin Intermedio, disponible próximamente") }
        cvJv.setOnClickListener { showCourseInfoDialog("Curso Java Intermedio, disponible próximamente") }
    }
    private fun updateFilter() {
        // Obtener el estado actual de los CheckBox
        val isWebSelected = cbWeb.isChecked
        val isIaSelected = cbIa.isChecked
        val isMbleSelected = cbMble.isChecked
        val isBackSelected = cbBack.isChecked
        val isFronSelected = cbFron.isChecked

        // Mostrar u ocultar los cursos según los CheckBox seleccionados
        cvHtml.visibility = if (isWebSelected || isFronSelected) View.VISIBLE else View.GONE
        cvPy.visibility = if (isWebSelected || isIaSelected || isBackSelected) View.VISIBLE else View.GONE
        cvKt.visibility = if (isMbleSelected) View.VISIBLE else View.GONE
        cvJv.visibility = if (isBackSelected) View.VISIBLE else View.GONE

        // Verificar si todos los CheckBox están desactivados
        if (!isWebSelected && !isIaSelected && !isMbleSelected && !isBackSelected && !isFronSelected) {
            // Si todos los CheckBox están desactivados, mostrar todos los cursos
            cvHtml.visibility = View.VISIBLE
            cvPy.visibility = View.VISIBLE
            cvKt.visibility = View.VISIBLE
            cvJv.visibility = View.VISIBLE
        }
    }

    // Función para mostrar los diálogos
    private fun showCourseInfoDialog(message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Información del Curso")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    // Controlar el botón de retroceso
    override fun onBackPressed() {
        if (backButtonPressedCount >= 1) {
            // Si se presiona nuevamente el botón Atrás, cierra la aplicación
            super.onBackPressed() // Cierra la actividad actual
            finishAffinity() // Cierra todas las actividades en la aplicación
            System.exit(0) // Cierra la aplicación por completo
        } else {
            // Muestra un Toast indicando que se necesita presionar dos veces para salir
            Toast.makeText(this, "Presione nuevamente para salir", Toast.LENGTH_SHORT).show()
            backButtonPressedCount++
        }
    }
}