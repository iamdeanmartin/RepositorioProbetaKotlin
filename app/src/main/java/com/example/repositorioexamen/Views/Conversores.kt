package com.example.ejercicio1practica.Views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.Button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon

import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.fastCbrt


@Composable
fun conversores (modifier: Modifier){

    var numero by rememberSaveable { mutableStateOf("") }

    var numeroFinal by rememberSaveable { mutableStateOf(0.0f) }

    var resultado by rememberSaveable { mutableStateOf(0.0f) }

    var expanded by rememberSaveable { mutableStateOf(false) }

    var selectedText by rememberSaveable { mutableStateOf("") }

    var mostrar by rememberSaveable { mutableStateOf(false) }

    val listaConvers = listOf("De polzada a centímetre", "De iarda a metre",
        "De milla a quilòmetre", "De centímetre a polzada", "De metre a iarda",
        "De quilòmetre a milla")

    val cm = 2.54f
    val m = 0.9144f
    val km = 1.60934f

    val p = cm / 2.54f
    val i = m / 0.9144f
    val mill = km / 1.60934f


    //CONVERSIONES


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "APP DE CONVERSIONES",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        TextField (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            value = numero,

            onValueChange = {
                numero = it
                var number = it.toFloatOrNull()
                if (number != null) {
                    numeroFinal = number
                }
            },

            singleLine = true,

            placeholder = {Text("Introduce un número.")},

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
            listaConvers.forEach {
                    i -> DropdownMenuItem(
                text = { Text(text = i) },
                onClick = {
                    expanded = false
                    selectedText = i
                }
            )
            }
        }

        Button (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {
                mostrar = true
            }
        ) {
            Icon (
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Calcular")
        }

        if (mostrar) {
            when (selectedText) {
                "De polzada a centímetre" -> resultado = numeroFinal * cm
                "De iarda a metre" -> resultado = numeroFinal * m
                "De milla a quilòmetre" -> resultado = numeroFinal * km
                "De centímetre a polzada" -> resultado = numeroFinal * p
                "De metre a iarda" -> resultado = numeroFinal * i
                "De quilòmetre a milla" -> resultado = numeroFinal * mill
            }

            Text(
                color = Color.Black,

                fontWeight = FontWeight.SemiBold,

                fontSize = 20.sp,

                textAlign = TextAlign.Center,

                text = "El resultado de la conversion de tipo $selectedText es $resultado."
            )
        }
    }

}