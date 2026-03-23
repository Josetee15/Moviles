package com.example.fragmentosynav.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentosynav.R
import com.example.fragmentosynav.databinding.FragBisiestoBinding
import com.example.fragmentosynav.model.EstadoRespuesta
import com.example.fragmentosynav.viewmodel.MainViewModel

class BisiestoFrag : Fragment() {

    private var _binding: FragBisiestoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragBisiestoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.btnValidarBisiesto.setOnClickListener {
            val idMarcado = binding.rgBisiesto.checkedRadioButtonId

            if (idMarcado == -1) {
                Toast.makeText(requireContext(), "Selecciona una respuesta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val respuestaUsuario = idMarcado == R.id.rbSi
            viewModel.validarBisiesto(respuestaUsuario)
        }

        viewModel.resultadoBisiesto.observe(viewLifecycleOwner) { estado ->
            when (estado) {
                EstadoRespuesta.PENDIENTE -> {
                    binding.rgBisiesto.clearCheck()
                    binding.tvResultadoBisiesto.text = "Pendiente"
                }
                EstadoRespuesta.CORRECTO -> {
                    binding.tvResultadoBisiesto.text = "Correcto!!!"
                }
                EstadoRespuesta.INCORRECTO -> {
                    binding.tvResultadoBisiesto.text = "Incorrecto :("
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}