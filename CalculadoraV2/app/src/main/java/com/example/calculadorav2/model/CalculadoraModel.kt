package com.example.calculadorav2.model

import com.example.calculadorav2.model.Datos

class CalculadoraModel {

    var estadoActual = Datos("", "", "0", false)

    private var primerNumero: Double = 0.0
    private var hayPrimerNumero = false

    private var operacion: String? = null
    private var esperandoSegundo = false
    private var segundoEmpezado = false

    private fun ponerEstado(estado: String, acumulado: String, numero: String, resultado: Boolean) {
        estadoActual = Datos(estado, acumulado, numero, resultado)
    }

    fun pulsarNumero(digito: Int): Datos {

        if (estadoActual.calcularResultado) {
            limpiar()
        }

        if (esperandoSegundo) {
            esperandoSegundo = false
            segundoEmpezado = true
        }

        val actual = estadoActual.numero
        val nuevo = if (actual == "0") digito.toString() else actual + digito.toString()

        ponerEstado(
            estado = "",
            acumulado = estadoActual.acumulado,
            numero = nuevo,
            resultado = false
        )

        if (hayPrimerNumero && operacion != null && !esperandoSegundo) {
            ponerEstado(
                estado = "",
                acumulado = "${formatear(primerNumero)} $operacion $nuevo",
                numero = nuevo,
                resultado = false
            )
        }

        return estadoActual
    }

    fun limpiar(): Datos {
        primerNumero = 0.0
        hayPrimerNumero = false
        operacion = null
        esperandoSegundo = false
        segundoEmpezado = false

        ponerEstado("", "", "0", false)
        return estadoActual
    }

    fun pulsarOperacion(op: String): Datos {

        if (operacion != null && esperandoSegundo && !segundoEmpezado) {
            ponerEstado(
                estado = "NO SE PUEDEN DOS SIMBOLOS SEGUIDOS!!!",
                acumulado = estadoActual.acumulado,
                numero = estadoActual.numero,
                resultado = false
            )
            return estadoActual
        }

        if (estadoActual.calcularResultado) {
            primerNumero = estadoActual.numero.toDouble()
            hayPrimerNumero = true
        }

        val numeroActual = estadoActual.numero.toDouble()

        if (!hayPrimerNumero) {
            primerNumero = numeroActual
            hayPrimerNumero = true
        } else {
            if (operacion != null && segundoEmpezado) {
                primerNumero = when (operacion) {
                    "+" -> primerNumero + numeroActual
                    "-" -> primerNumero - numeroActual
                    "*" -> primerNumero * numeroActual
                    "/" -> primerNumero / numeroActual
                    else -> primerNumero
                }
            }
        }

        operacion = op
        esperandoSegundo = true
        segundoEmpezado = false

        ponerEstado(
            estado = "",
            acumulado = "${formatear(primerNumero)} $op",
            numero = "0",
            resultado = false
        )

        return estadoActual
    }

    fun calcular(): Datos {

        if (!hayPrimerNumero || operacion == null || (esperandoSegundo && !segundoEmpezado)) {
            return estadoActual
        }

        val segundoNumero = estadoActual.numero.toDouble()

        val resultado = when (operacion) {
            "+" -> primerNumero + segundoNumero
            "-" -> primerNumero - segundoNumero
            "*" -> primerNumero * segundoNumero
            "/" -> primerNumero / segundoNumero
            else -> primerNumero
        }

        ponerEstado(
            estado = "",
            acumulado = "${formatear(primerNumero)} $operacion $segundoNumero =",
            numero = formatear(resultado),
            resultado = true
        )


        primerNumero = 0.0
        hayPrimerNumero = false
        operacion = null
        esperandoSegundo = false
        segundoEmpezado = false

        return estadoActual
    }

    private fun formatear(n: Double): String {
        val entero = n.toLong()
        return if (n == entero.toDouble()) entero.toString() else n.toString()
    }
}
