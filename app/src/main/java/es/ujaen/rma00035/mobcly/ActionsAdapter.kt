package es.ujaen.rma00035.mobcly

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import es.ujaen.rma00035.mobcly.models.Actions
import kotlinx.android.synthetic.main.recycler_view_itemactions.view.*

class ActionsAdapter(private val tareaLista: List<Actions>) :
        RecyclerView.Adapter<ActionsAdapter.ActionHolder>() {

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


    class ActionHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun render(t: Actions) {
            if (t.imagePath != -1)
                view.imageViewAction.setImageDrawable(getDrawable(view.context, t.imagePath))
            view.nameTextViewAction.text = t.name
            view.setOnClickListener {
                when (t.name) {
                    "Agenda" -> {
                        val i = Intent(view.context, Agenda::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)
                    }
                    "Localizar hijo" -> {
                        val i = Intent(view.context, MapsActivity::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)
                    }
                    "Enviar localizacion" -> {
                        val i = Intent(view.context, ActivityLocalization::class.java).apply {
                            //putExtra("clave",valor)
                        }
                        view.context.startActivity(i)
                    }

                }
            }
            //view.imageView=
        }
    }
}