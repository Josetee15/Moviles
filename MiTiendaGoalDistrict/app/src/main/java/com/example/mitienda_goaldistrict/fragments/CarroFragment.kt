package com.example.mitienda_goaldistrict.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitienda_goaldistrict.databinding.FragmentCarroBinding
import com.example.mitienda_goaldistrict.model.ItemCarro
import com.example.mitienda_goaldistrict.recycler.CarroAdapter
import com.example.mitienda_goaldistrict.viewmodel.CarroViewModel

class CarroFragment : Fragment() {

    private var _binding: FragmentCarroBinding? = null
    private val binding get() = _binding!!

    private val carroViewModel: CarroViewModel by viewModels()

    private lateinit var carroAdapter: CarroAdapter

    private var token: String = ""

    companion object {
        fun newInstance(token: String): CarroFragment {
            val fragment = CarroFragment()
            val bundle = Bundle()

            // guardamos el token en los argumentos del fragment
            bundle.putString("TOKEN", token)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // recuperamos el token recibido desde la activity principal
        token = arguments?.getString("TOKEN") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecycler()
        configurarObservadores()

        // cargamos desde la api los productos que hay actualmente en el carro
        carroViewModel.cargarCarro(token)
    }

    private fun configurarRecycler() {
        carroAdapter = CarroAdapter(emptyList()) { itemCarro ->
            mostrarDialogoEliminar(itemCarro)
        }

        binding.recyclerCarro.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCarro.adapter = carroAdapter
    }

    private fun configurarObservadores() {
        // observamos el carro recibido desde la api y actualizamos el recyclerview
        carroViewModel.carro.observe(viewLifecycleOwner) { carro ->
            carroAdapter.actualizarCarro(carro.products)
            binding.tvTotalCarro.text = "Total: ${carro.totalAmount} €"
        }

        // si el producto se borra correctamente, avisamos al usuario
        carroViewModel.productoBorrado.observe(viewLifecycleOwner) { borrado ->
            if (borrado) {
                Toast.makeText(requireContext(), "Producto eliminado del carro", Toast.LENGTH_SHORT).show()
            }
        }

        // observamos los errores para mostrarlos al usuario
        carroViewModel.error.observe(viewLifecycleOwner) { mensaje ->
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDialogoEliminar(itemCarro: ItemCarro) {
        // preguntamos al usuario si quiere eliminar el producto seleccionado del carro
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar producto")
            .setMessage("¿Deseas eliminar ${itemCarro.productName} del carro?")
            .setPositiveButton("Sí") { _, _ ->
                // si confirma, llamamos al viewmodel para borrar el producto usando la api
                carroViewModel.deleteProductoCarro(token, itemCarro.productId)
            }
            .setNegativeButton("No") { dialog, _ ->
                // si cancela, cerramos el diálogo sin borrar nada
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // limpiamos el binding cuando se destruye la vista del fragment
        _binding = null
    }
}