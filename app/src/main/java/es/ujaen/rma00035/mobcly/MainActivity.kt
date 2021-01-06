package es.ujaen.rma00035.mobcly

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()
        initLayout.visibility = View.VISIBLE
    }

    private fun setup() {
        Firebase.database.setPersistenceEnabled(true)
        buttonProfilePadre.setOnClickListener {
            val homeIntent = Intent(this, AuthActivity::class.java).apply {
                //putExtra("clave",valor)
            }
            startActivity(homeIntent)
        }
        buttonProfileHijo.setOnClickListener {
            val homeIntent = Intent(this, QrReader::class.java).apply {
                //putExtra("clave",valor)
            }
            startActivity(homeIntent)
        }

    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", "None")
        if (email != null && provider != null) {
            initLayout.visibility = View.INVISIBLE
            showHome(email)
        }
    }


    private fun showHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(homeIntent)
    }
}