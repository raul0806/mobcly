package es.ujaen.rma00035.mobcly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.activity_agenda.*
import kotlinx.android.synthetic.main.recycler_view_agenda.*


class Agenda : AppCompatActivity() {
    private val db = Firebase.database.reference
    private lateinit var tareas: MutableList<Tareas>
    private var email: String? = null
    private var tipo: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)
        setSupportActionBar(findViewById(R.id.toolbar))
        setup()

    }

    fun setup() {
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        email = prefs.getString("email", null)
        tipo = prefs.getString("tipo", "hijo")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                tareas.clear()
                for (d in dataSnapshot.children) {
                    val post = d.getValue<Tareas>()
                    if (post != null) {
                        tareas.add(post)
                    }

                }
                tareas.sortBy { t -> t.date }
                recyclerViewTarea.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Debuffff", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        email?.replace(".", ",")
            ?.let { db.child(it).child("Agenda").addValueEventListener(postListener) }
        recyclerViewTarea.layoutManager = LinearLayoutManager(this)
        tareas = mutableListOf()
        /*
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email",null)
        if (email != null) {
            db.child(email.replace(".",",")).child("Agenda").setValue(tareas)
        }*/
        val adapter = TareaAdapter(tareas,recyclerViewTarea)
        recyclerViewTarea.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerViewTarea)
        if (tipo == "padre"){
            addTarea.setOnClickListener {
                val i = Intent(this, AddTarea::class.java).apply {
                    //putExtra("clave",valor)
                }
                startActivity(i)

            }
    }else {
            addTarea.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /*tareas.removeIf{
            it.date?.before(Date(System.currentTimeMillis()-3600000)) == true
        }
        */
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        email?.let { db.child(it.replace(".", ",")).child("Agenda").setValue(tareas) }
    }


}