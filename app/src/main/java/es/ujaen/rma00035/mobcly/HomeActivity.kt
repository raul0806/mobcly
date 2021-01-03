package es.ujaen.rma00035.mobcly

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import es.ujaen.rma00035.mobcly.models.Actions
import es.ujaen.rma00035.mobcly.models.Tareas
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.recycler_view_actions.*
import kotlinx.android.synthetic.main.recycler_view_agenda.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val db = Firebase.database.reference
    private lateinit var tipo: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle = intent.extras
        val email = bundle?.getString("email")
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
        logOutButton.setOnClickListener {
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val authIntent = Intent(this, AuthActivity::class.java)
            authIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(authIntent)
        }
        if (tipo == "padre") {
            addHijo.setOnClickListener {
                val prefs =
                    getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
                val email = prefs.getString("email",null)
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
        }
        recyclerViewAction.layoutManager = LinearLayoutManager(this)
        val lista: List<Actions> = listOf(
            Actions("Agenda", ""),
            Actions("Localizar hijo", ""),
            Actions("Enviar Localizacion", ""),
            Actions("Establecer hijo", "")
        )
        val adapter = ActionsAdapter(lista)
        recyclerViewAction.adapter = adapter
    }
    
}