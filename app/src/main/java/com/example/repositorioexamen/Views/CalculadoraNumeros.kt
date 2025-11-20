package com.example.ejercicio1practica.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.foundation.border
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon

import androidx.compose.foundation.layout.Spacer

import android.widget.Toast

@Composable
fun calculadoraNumerso (modifier: Modifier) {

    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val operaciones = listOf("+", "-", "x", "/")

    var resultado by rememberSaveable { mutableStateOf(0) }

    var numero1 by rememberSaveable { mutableStateOf(1000) }
    var numero2 by rememberSaveable { mutableStateOf(1000) }

    var numero1Texto by rememberSaveable { mutableStateOf("") }
    var numero2Texto by rememberSaveable { mutableStateOf("") }

    var mostrado by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            value = numero1Texto,

            onValueChange = {
                numero1Texto = it
                val number = it.toIntOrNull()
                if (number != null) {
                    numero1 = number
                }
            },

            placeholder = { Text("Pon un número") },   // placeholder siempre es un composable

            singleLine = true,

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(

            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            operaciones.forEach {
                    i -> DropdownMenuItem(
                text = { Text(text = i) },
                onClick = {
                    expanded = false
                    selectedText = i
                }
            )
            }
        }

        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            value = numero2Texto,

            onValueChange = {
                numero2Texto = it
                val number = it.toIntOrNull()
                if (number != null) {
                    numero2 = number
                }
            },

            placeholder = { Text("Pon otro número") },

            singleLine = true,

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button (
            onClick = {
                if (numero1Texto.isBlank() || numero2Texto.isBlank() || selectedText.isBlank()) {
                    Toast.makeText(
                        context, "Rellena todos los campos.",
                        Toast.LENGTH_LONG
                    ).show()
                } else if ((numero2Texto == "0" && selectedText == "/") || (numero1Texto == "0" && selectedText == "/")) {
                    Toast.makeText(
                        context, "No se puede dividir por 0.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    mostrado = true
                }
            }
        ) {
            Icon (
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text (
                text = "Calcular"
            )
        }

        if (mostrado) {

            when (selectedText) {
                "+" -> resultado = numero1 + numero2
                "-" -> resultado = numero1 - numero2
                "x" -> resultado = numero1 * numero2
                "/" -> resultado = numero1 / numero2
            }

            Text(
                text = "El resultado es $resultado.",

                color = Color.Black,

                fontSize = 20.sp,

                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}