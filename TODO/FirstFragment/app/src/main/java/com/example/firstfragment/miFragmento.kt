package com.example.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [miFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class miFragmento : Fragment() {
    // TODO: Rename and change types of parameters
    private var texto: String? = null
    private var num: Int? = null

    private lateinit var miVista: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            texto = it.getString(ARG_PARAM1)
            num = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        miVista = inflater.inflate(R.layout.fragment_mi_fragmento, container, false)
        return miVista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miVista.findViewById<TextView>(R.id.txt1).text = texto

        miVista.findViewById<Button>(R.id.miBtn).setOnClickListener {
            miVista.findViewById<TextView>(R.id.txt1).text = "Trabajando con fragmentos"
            Toast.makeText(miVista.context, "El numero era el $num", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment miFragmento.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(texto: String, num: Int) =
            miFragmento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, texto)
                    putInt(ARG_PARAM2, num)
                }
            }
    }
}