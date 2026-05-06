package com.example.mitienda_goaldistrict.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.mitienda_goaldistrict.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // configuramos el webview para cargar la web dentro de la app
        binding.webViewTienda.webViewClient = WebViewClient()

        // activamos javascript porque la web de la tienda puede necesitarlo para funcionar correctamente
        binding.webViewTienda.settings.javaScriptEnabled = true

        // cargamos la web de la tienda desde el servidor local
        binding.webViewTienda.loadUrl("http://10.0.2.2:8080")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // limpiamos el binding cuando se destruye la vista del fragment
        _binding = null
    }
}