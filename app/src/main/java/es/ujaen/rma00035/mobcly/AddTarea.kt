package es.ujaen.rma00035.mobcly

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import com.google.firebase.crashlytics.internal.common.CommonUtils.hideKeyboard
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.activity_add_tarea.*
import java.util.*

class AddTarea : AppCompatActivity() {
    val LOG_TAG = "MAINACTIVITY CLASS"
    var dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var mes = Calendar.getInstance().get(Calendar.MONTH) // OJO: De 0 a 11
    var anio = Calendar.getInstance().get(Calendar.YEAR)
    var hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    var minuto = Calendar.getInstance().get(Calendar.MINUTE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tarea)
        setup()
    }

    private fun setup() {
        title = "Nueva Tarea"
        buttonCancel.setOnClickListener {
            onBackPressed()
        }
        buttonSave.setOnClickListener {
            if ((editTextTitle.text.isNotEmpty()) and (dateInput.text == getString(R.string.fecha)) and (timeInput.text == getString(
                    R.string.hora
                ))
            ) {
                val cal = Calendar.getInstance()
                cal.set(anio, mes, dia, hora, minuto)
                Tareas(editTextTitle.text.toString(), "", cal.time)
                //TODO save
                onBackPressed()
            }
        }
    }

    fun citahora(view: View) {
        hideKeyboard(this, view)
        val time_picker = TimePickerDialog(
            this,
            { _, h, m ->
                hora = h
                minuto = m
                Log.d(LOG_TAG, "OnTimeSetListener $h:$m")
                timeInput.setText("$hora:$minuto")
                if (dateInput.text == getString(R.string.fecha)) {
                    citadia(view)
                }
            },
            hora,
            minuto,
            is24HourFormat(this)
        )
        time_picker.setCancelable(false)
        time_picker.show()

    }

    fun citadia(view: View) {

        Log.d(LOG_TAG, "$dia de $mes de $anio")
        val date_picker = DatePickerDialog(
            this,
            { _, y, m, d ->
                anio = y
                mes = m + 1
                dia = d
                Log.d(LOG_TAG, "OnDateSetListener $dia/$mes/$anio")
                dateInput.setText("$dia/$mes/$anio")
                if (timeInput.text == getString(R.string.hora)) {
                    citahora(view)
                }
            },
            anio,
            mes,
            dia
        )
        date_picker.setCancelable(false)
        date_picker.show()
    }
}