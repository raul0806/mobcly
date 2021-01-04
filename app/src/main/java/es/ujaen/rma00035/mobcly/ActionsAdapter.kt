package es.ujaen.rma00035.mobcly

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import es.ujaen.rma00035.mobcly.models.Actions
import kotlinx.android.synthetic.main.recycler_view_itemagenda.view.*
import java.util.jar.Manifest

class ActionsAdapter(val tareaLista: List<Actions>) :
    RecyclerView.Adapter<ActionsAdapter.ActionHolder>() {
    private lateinit var locationManager: LocationManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActionHolder(
            layoutInflater.inflate(
                R.layout.recycler_view_itemactions,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActionHolder, position: Int) {
        holder.render(tareaLista[position])
    }

    override fun getItemCount(): Int = tareaLista.size


    class ActionHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(t: Actions) {

            view.nameTextView.text = t.name
            view.setOnClickListener {
                when (t.name) {
                    "Agenda" -> {
                        val i = Intent(view.context, Agenda::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)
                    }
                    "Localizar hijo" -> {
                        val i = Intent(view.context, ActivityLocalization::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)
                    }
                    "Enviar localizacion" -> {
                        /* TODO
                        val i = Intent(view.context, AddTarea::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)

                         */
                    }
                    "Establecer hijo" -> {
                        /* TODO
                        val i = Intent(view.context, AddTarea::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)

                         */
                    }
                }
            }
            //view.imageView=
        }
    }
}