package com.example.mitienda_goaldistrict

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mitienda_goaldistrict.databinding.ActivityLoginBinding
import com.example.mitienda_goaldistrict.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val usuario = binding.etUsuario.text.toString()
            val password = binding.etPassword.text.toString()

            if (usuario.isNotEmpty() && password.isNotEmpty()) {
                // llamamos al viewmodel para hacer login contra la api
                loginViewModel.login(usuario, password)
            } else {
                Toast.makeText(this, "Introduce usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // observamos el token; cuando llega, significa que el login ha sido correcto
        loginViewModel.token.observe(this) { token ->
            val intent = Intent(this, MainActivity::class.java)

            // pasamos el token a la pantalla principal para usarlo en el resto de llamadas api
            intent.putExtra("TOKEN", token)

            // pasamos también el nombre de usuario para mostrarlo en la toolbar
            intent.putExtra("USUARIO", binding.etUsuario.text.toString())

            startActivity(intent)
            finish()
        }

        // observamos los errores para mostrarlos al usuario
        loginViewModel.error.observe(this) { mensaje ->
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }

        // mostramos u ocultamos el progressbar mientras se está haciendo la llamada a la api
        loginViewModel.cargando.observe(this) { cargando ->
            if (cargando) {
                binding.progressLogin.visibility = View.VISIBLE
                binding.btnLogin.isEnabled = false
            } else {
                binding.progressLogin.visibility = View.GONE
                binding.btnLogin.isEnabled = true
            }
        }
    }
}