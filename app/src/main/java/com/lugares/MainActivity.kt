package com.lugares

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(context:this)
        auth = Firebase.auth

        //se define el metodo login

        binding.bt_login.setOnClickListener(){ it:View!
                hacerlogin();

        }
        //se define el metodo registrar

        binding.bt_register.setOnClickListener(){ it:view!
            hacerRegistro();

        }
    }

    private fun hacerRegistro() {
        var email = binding.etEmail.text.toString()
        var clave = binding.et_Clave.text.toString()

        //Se hace el registro
        auth.createUserWithEmaiLAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d( "creando usuanio", "Registrado")
                    val user = auth.currentUser
                    if (user !=null) {
                        actualiza(user)
                    }

                }  else{
                    Log.d( "creando usuanio",  "Fallo")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    actualiza(null)

                }
    }
    }

    private fun actualiza(user: FirebaseUser?): Any {
       if (user != null ){
           val intent = Intent (this,Principal ::class.java)
           startActivity(intent)
       }
    }
// esto haria que una vez autentificado no pida más a menos que se cierre la sesión
    public override fun onStart() {
        super.onStart()
        val usuario= auth.currentUser
        actualiza(usuario)
    }
    }


    private fun hacerlogin() {
        var email = binding.etEmail.text.toString()
        var clave = binding.et_Clave.text.toString()

        //Se hace el login
        val addOnCompleteListener = auth.SingInWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Autenticando", "Autenticado")
                    val user = auth.currentUser
                    actualiza(user)

                } else {
                    Log.d("Autenticando" "Fallo")
                    toast.makeText(baseContext, "Fallo", toast.LENGTH_LONG).show
                    actualiza(null)

                }
                override fun onOptionsItemSelected(item: MenuItem): Boolean {
                    return when (item.itemId) {
                    R.id.action_logoff -> {
                        Firebase.auth.signOut()
                        finish()
                        true
                    } else -> super.onOptionsItemSelected(item)
                }
            }

    }
}

fun actualiza(nothing: Nothing?) {

}

