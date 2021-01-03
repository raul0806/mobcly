package es.ujaen.rma00035.mobcly

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.recycler_view_agenda.*
import java.util.Date


class Agenda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)
        setSupportActionBar(findViewById(R.id.toolbar))
        setup()
        findViewById<FloatingActionButton>(R.id.addTarea).setOnClickListener { view ->
            val i = Intent(this, AddTarea::class.java).apply {
                //putExtra("clave",valor)
            }
            startActivity(i)

        }
    }

    fun setup() {
        recyclerViewTarea.layoutManager = LinearLayoutManager(this)
        var tareas: List<Tareas> = listOf(
            Tareas("Comer", "c", Date(System.currentTimeMillis())),
            Tareas("Come2r", "c", Date(System.currentTimeMillis())),
            Tareas("Come2r", "c", Date(System.currentTimeMillis())),
            Tareas("Come2r", "c", Date(System.currentTimeMillis())),
            Tareas("Come2r", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis())),
            Tareas("Dormir", "c", Date(System.currentTimeMillis()))
        )
        val adapter = TareaAdapter(tareas)
        recyclerViewTarea.adapter = adapter
    }
}