package es.ujaen.rma00035.mobcly

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val db = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle = intent.extras
        //bundle?.get("get")
        setup()
        // Guardado de datos
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", "prueba")///cambiar
        prefs.apply()
    }

    private fun setup() {
        title = "Inicio"
        logOutButton.setOnClickListener {
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    private fun notification() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            it.result?.token?.let {
                db.child("users").child(it).setValue("Hola")
            }
        }
        FirebaseMessaging.getInstance().subscribeToTopic("id12345")

        val qrEncoder = QRGEncoder("email", null, QRGContents.Type.TEXT, 100)
        val bitmap = qrEncoder.encodeAsBitmap()
        //qrImage.setImageBitmap(bitmap)
    }
}