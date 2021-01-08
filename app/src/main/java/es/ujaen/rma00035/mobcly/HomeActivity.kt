package es.ujaen.rma00035.mobcly

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import es.ujaen.rma00035.mobcly.models.Actions
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.recycler_view_actions.*

class HomeActivity : AppCompatActivity() {
    private lateinit var tipo: String
    private var email:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle = intent.extras
        email = bundle?.getString("email")
        tipo = bundle?.getString("tipo") ?: "padre"
        setup()
        // Guardado de datos
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("tipo", tipo)
        prefs.apply()

    }


    private fun setup() {
        title = "Inicio"
        email?.let { FirebaseMessaging.getInstance().subscribeToTopic(it) }
        logOutButton.setOnClickListener {
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val authIntent: Intent
            if (tipo == "padre")
                authIntent = Intent(this, AuthActivity::class.java)
            else
                authIntent = Intent(this, MainActivity::class.java)
            authIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(authIntent)
        }

        val lista: List<Actions>
        if (tipo == "padre") {
            lista = listOf(
                Actions("Agenda", R.drawable.agenda),
                Actions("Localizar hijo", R.drawable.mapa),
                Actions("Enviar localizacion", R.drawable.mapa)
            )
            addHijo.setOnClickListener {

                val qrEncoder = QRGEncoder("email:$email", null, QRGContents.Type.TEXT, 500)
                val bitmap = qrEncoder.encodeAsBitmap()
                //qrImage.setImageBitmap(bitmap)
                val m = ImageView(this)
                m.setImageBitmap(bitmap)
                val dialog = Dialog(this)
                dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
                dialog.window?.setContentView(m)
                dialog.show()

            }
            // email?.let { db.child(it).child("solicitarLocalizacion").setValue("False") }
        } else {
            lista = listOf(
                Actions("Agenda", R.drawable.agenda),
                //Actions("Localizar hijo", ""),
                Actions("Enviar localizacion", R.drawable.mapa)
            )
            addHijo.hide()
            //  email?.let { db.child(it).child("enviarLocalizacion").addOn }
        }
        recyclerViewAction.layoutManager = LinearLayoutManager(this)

        val adapter = ActionsAdapter(lista)
        recyclerViewAction.adapter = adapter

    }

}