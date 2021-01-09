package es.ujaen.rma00035.mobcly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.recycler_view_itemagenda.view.*
import java.text.SimpleDateFormat


class TareaAdapter(val tareaLista: MutableList<Tareas>, val view: RecyclerView) :
        RecyclerView.Adapter<TareaAdapter.TareaHolder>() {
    private lateinit var ultimaTarea: Tareas
    private var ultimaPosicion = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_view_itemagenda, parent, false)
        return TareaHolder(view)
    }

    override fun onBindViewHolder(holder: TareaHolder, position: Int) {
        holder.render(tareaLista[position])
    }

    override fun getItemCount(): Int = tareaLista.size


    fun deleteItem(position: Int) {
        ultimaTarea = tareaLista.get(position)
        ultimaPosicion = position
        tareaLista.removeAt(position)
        notifyItemRemoved(position)
        showUndoSnackbar()
    }

    private fun showUndoSnackbar() {

        val snackbar = Snackbar.make(view, R.string.snack_bar_text, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.snack_bar_undo) { undoDelete() }
        snackbar.show()
    }

    private fun undoDelete() {
        tareaLista.add(ultimaPosicion, ultimaTarea)
        notifyItemInserted(ultimaPosicion)
    }

    class TareaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(t: Tareas) {
            view.hourTextView.text = SimpleDateFormat("HH:mm").format(t.date)
            view.nameTextViewTarea.text = t.name
            if ((t.imagePath != -1)and (t.imagePath!=R.drawable.ninguna))
                view.imageViewTarea.setImageDrawable(getDrawable(view.context,t.imagePath))
        }
    }
}