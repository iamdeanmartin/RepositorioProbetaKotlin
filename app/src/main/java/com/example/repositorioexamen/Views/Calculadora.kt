package com.example.ejercicio1practica.Views

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Redo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.example.ejercicio1practica.R

@Composable
fun calcularPropina(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White), // y si fuera con código de color sería con ( )

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var boolean by rememberSaveable { mutableStateOf(false) }

        var propinaText by rememberSaveable { mutableStateOf("") }
        var precioText by rememberSaveable { mutableStateOf("") }

        var propina by rememberSaveable { mutableStateOf(0) }
        var precio by rememberSaveable { mutableStateOf(0)}

        val cantidadPropina = precio * propina / 100
        val totalPago = precio + cantidadPropina

        if (!boolean) {
            Text("Calculador de Propina",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                color = Color.DarkGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                singleLine = true,

                value = precioText,
                onValueChange = {
                    precioText = it
                    var number = it.toIntOrNull()
                    if (number != null) {
                        precio = number
                    }
                },
                placeholder = { Text("Introduce el precio del menú.")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                singleLine = true,

                value = propinaText,
                onValueChange = {
                    propinaText = it
                    var number = it.toIntOrNull()
                    if (number != null) {
                        propina = number
                    }
                },
                placeholder = { Text("Introduce el porcentaje de propina que quieres paga.")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            val botonHabilitado = precioText.toIntOrNull() != null && propinaText.toIntOrNull() != null
            //Lo metemos aquí para el scope y que solo exista aqui de manera que se pueda poner en otras partes

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,// color de fondo normal
                    contentColor = Color.White,// color del texto/ícono
                    disabledContainerColor = Color.Gray,// fondo cuando está deshabilitado
                    disabledContentColor = Color.LightGray// texto cuando está deshabilitado
                ),

                enabled = botonHabilitado,

                onClick = {
                    boolean = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Enviar")
            }
        } else {
            Box (modifier = Modifier
                .fillMaxSize()
            ) {
                Image (
                    painter = painterResource(id = R.drawable.confeti),
                    contentDescription = "Confeti",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column (modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "FELICIDADES",
                        color = Color.Black,

                        fontSize = 40.sp,

                        fontFamily = FontFamily.Cursive,

                        fontWeight = FontWeight.ExtraBold
                    )

                    Text("El total a pagar és $totalPago$.")

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),

                        onClick = {
                            //Reinciamos todas las variables
                            boolean = false
                            precioText = ""
                            propinaText = ""
                            precio = 0
                            propina = 0
                        }

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Redo,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Volver a calcular")
                    }
                }
            }
        }
    }
}