package com.example.fragmentosynav.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentosynav.databinding.FragDivisibleBinding
import com.example.fragmentosynav.model.EstadoRespuesta
import com.example.fragmentosynav.viewmodel.MainViewModel

class DivisibleFrag : Fragment() {

    private var _binding: FragDivisibleBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragDivisibleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.btnValidarDivisible.setOnClickListener {
            viewModel.validarDivisible(
                binding.cb2.isChecked,
                binding.cb3.isChecked,
                binding.cb5.isChecked,
                binding.cb10.isChecked,
                binding.cbNinguno.isChecked
            )
        }

        viewModel.resultadoDivisible.observe(viewLifecycleOwner) { estado ->
            when (estado) {
                EstadoRespuesta.PENDIENTE -> {
                    binding.cb2.isChecked = false
                    binding.cb3.isChecked = false
                    binding.cb5.isChecked = false
                    binding.cb10.isChecked = false
                    binding.cbNinguno.isChecked = false
                    binding.tvResultadoDivisible.text = "Pendiente"
                    binding.tvResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_blue_dark, null))

                }
                EstadoRespuesta.CORRECTO -> {
                    binding.tvResultadoDivisible.text = "Correcto!!!"
                    binding.tvResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_green_light, null))

                }
                EstadoRespuesta.INCORRECTO -> {
                    binding.tvResultadoDivisible.text = "Incorrecto :("
                    binding.tvResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_red_light, null))

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}