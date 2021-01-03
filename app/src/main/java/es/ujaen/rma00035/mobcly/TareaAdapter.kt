package es.ujaen.rma00035.mobcly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.recycler_view_itemagenda.view.*
import java.text.SimpleDateFormat

class TareaAdapter(val tareaLista: List<Tareas>) :
    RecyclerView.Adapter<TareaAdapter.TareaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TareaHolder(layoutInflater.inflate(R.layout.recycler_view_itemagenda, parent, false))
    }

    override fun onBindViewHolder(holder: TareaHolder, position: Int) {
        holder.render(tareaLista[position])
    }

    override fun getItemCount(): Int= tareaLista.size


    class TareaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(t: Tareas) {
            view.hourTextView.text = SimpleDateFormat("HH:mm").format(t.date)
            view.nameTextView.text = t.name
            //view.imageView=
        }
    }
}