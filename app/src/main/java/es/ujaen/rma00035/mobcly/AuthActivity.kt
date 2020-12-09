package es.ujaen.rma00035.mobcly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
    private fun setup(){
        singUpButton.setOnClickListener{
            if (editTextEmailAddress.text.isNotEmpty()&&editTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    editTextEmailAddress.text.toString(),
                    editTextPassword.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){

                    }else{

                    }
                }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog = builder.create()
        dialog.show()//minuto 11
    }
}