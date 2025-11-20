package com.example.llimonadedean.composables

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.R.string
import androidx.compose.foundation.Image
import com.example.llimonadedean.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun proyectoLimonero(modifier: Modifier = Modifier) {
    var nombreUsuario by rememberSaveable { mutableStateOf("") }
    var nombreEnviado by rememberSaveable { mutableStateOf("") }

    var pedirNombre by rememberSaveable { mutableStateOf(true) }
    var cogerLimon by rememberSaveable { mutableStateOf(false) }
    var exprimirLimon by rememberSaveable { mutableStateOf(false) }
    var beberLimon by rememberSaveable { mutableStateOf(false) }
    var valoracion by rememberSaveable { mutableStateOf(false) }
    var sinZumo by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    if (pedirNombre) {
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
        {
            TextField( value = nombreUsuario, onValueChange = { nombreUsuario = it }, label = { Text("Introduzca su nombre") })
            Button(
                onClick = {
                    if (nombreUsuario.isNotEmpty()) {
                        nombreEnviado = nombreUsuario
                        pedirNombre = false
                        cogerLimon = true
                        Toast.makeText(context, "Benvingut/da $nombreUsuario 🍋", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "¡Es obligatori posar un nom!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) { Text("Acceder al juego") }
        }
    } else if (cogerLimon) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lemon_tree),
                contentDescription = "Imagen del árbol limonero.",
                modifier = Modifier.size(320.dp).clickable { cogerLimon = false
                    exprimirLimon = true }
            )
            Text(
                text = "Agafa una llimona $nombreUsuario"
            )
        }
    } else if (exprimirLimon) {
        var contador by rememberSaveable { mutableStateOf(0) }
        val totalClicks = remember { (1..10).random() }

        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lemon_squeeze), contentDescription = "Limón para hacer zumo.",
                modifier = Modifier.size(320.dp).clickable {
                    contador++
                    if (contador >= totalClicks) {
                        exprimirLimon = false
                        beberLimon = true
                        contador = 0
                    }
                }
            )
            Text("Esprem la llimona $nombreEnviado")
        }
    } else if (beberLimon) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lemon_drink), contentDescription = "Limonada recién exprimida del árbol",
                modifier = Modifier.size(320.dp).clickable {
                    beberLimon = false
                    valoracion = true
                }
            )
            Text("Beu-te-la $nombreEnviado")
        }
    } else if (valoracion) {
        var valoracionUsuario by rememberSaveable { mutableStateOf("") }
        var cantidadEstrellas by rememberSaveable { mutableStateOf(0) }

        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Valora la llimonada que has pres $nombreEnviado 🍋")

            TextField(
                value = valoracionUsuario, onValueChange = { valoracionUsuario = it }, label = { Text("Número entre 1 - 5") },
                singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    val numero = valoracionUsuario.toIntOrNull()
                    if (numero != null && numero in 1..5) {
                        cantidadEstrellas = numero
                    } else {
                        Toast.makeText(context, "¡Escriu un número entre 1-5!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Enviar valoració")
            }

            if (cantidadEstrellas > 0) {
                val estrellas = "⭐".repeat(cantidadEstrellas)
                Text(
                    text = "La teva valoració ha sigut de $estrellas",
                )
                Button(
                    onClick = {
                        valoracion = false
                        sinZumo = true
                    },
                ) {
                    Text("Continuar")
                }
            }
        }
    } else if (sinZumo) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lemon_restart),
                contentDescription = "Ja no hi ha res de suc...",
                modifier = Modifier.size(320.dp) .clickable {
                    nombreUsuario = ""
                    nombreEnviado = ""
                    pedirNombre = true
                    sinZumo = false
                }
            )
            Text(text = "Comença de nou $nombreEnviado")
        }
    }
}
