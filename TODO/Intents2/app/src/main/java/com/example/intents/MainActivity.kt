package com.example.intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var miLanzador : ActivityResultLauncher<Intent>

    private lateinit var miContrato : ActivityResultContracts.StartActivityForResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        miLanzador = registerForActivityResult(miContrato) {
            when(it.resultCode){
                RESULT_OK -> binding.txtV.text = it.data!!.getStringExtra("mensajeVuelta")
                RESULT_CANCELED ->  binding.txtV.text = "Sin mensaje de vuelta"
            }
        }
        binding.btn1.setOnClickListener {
            var myIntent = Intent(this, SecondActivity::class.java)
            myIntent.putExtra("mensaje", binding.edit.text.toString())
            startActivity(myIntent)
        }

        binding.btn2.setOnClickListener {
            var myIntent3 = Intent(this, SecondActivity::class.java)
            myIntent3.putExtra("mensaje", binding.edit)
        }



        binding.btn3.setOnClickListener {
            var myIntent2 = Intent(Intent.ACTION_DIAL, "tel: +34666555444".toUri())
            startActivity(myIntent2)
        }
    }
}