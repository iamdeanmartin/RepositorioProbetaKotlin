package com.example.ejercicio1practica.Views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.style.TextAlign

@Composable
fun tresPantallas (modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        var pasos by rememberSaveable { mutableStateOf(1) }

        var nombre by rememberSaveable { mutableStateOf("") }

        val context = LocalContext.current

        var hola by rememberSaveable { mutableStateOf(false) }

        var adeu by rememberSaveable { mutableStateOf(false) }

        var sliderValue by rememberSaveable { mutableStateOf(0f) }
        var sliderText by rememberSaveable { mutableStateOf("") }

        when (pasos) {
            1 -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField (
                        value = nombre,

                        onValueChange = {
                            nombre = it
                        },

                        singleLine = true,

                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,   // Fondo cuando no está enfocado
                            focusedContainerColor = Color.Transparent,     // Fondo cuando está enfocado
                            unfocusedIndicatorColor = Color.LightGray,     // Línea sin focus
                            focusedIndicatorColor = Color.Cyan,            // Línea con focus
                            cursorColor = Color.Cyan                       // Palito de escritura
                        )
                    )
                }
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CD3C2),   // color de fondo normal (turquesa)
                        contentColor = Color.White,           // color del texto/icono
                        disabledContainerColor = Color.Gray,  // fondo cuando está deshabilitado
                        disabledContentColor = Color.LightGray
                    ),

                    shape = RoundedCornerShape(0.dp),

                    onClick = {
                        if (nombre.isBlank()) {
                            Toast.makeText(
                                context,
                                "El nombre no puede ir vacío.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            pasos = 2
                        }
                    }
                ) {
                    Text("NEXT STEP")
                }
            }
            2 -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){

                        RadioButton(
                            selected = hola,
                            onClick = { hola = !hola },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF4CD3C2),
                                unselectedColor = Color.Black
                            )
                        )

                        Text(
                            text = "Hola"
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        RadioButton(
                            selected = adeu,
                            onClick = { adeu = !adeu },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF4CD3C2),
                                unselectedColor = Color.Black
                            )
                        )

                        Text(
                            text = "Adéu"
                        )

                    }

                    Slider(
                        value=sliderValue,
                        onValueChange = {sliderValue = it},
                        onValueChangeFinished = {sliderText = sliderValue.toInt().toString()}, //Como lo mostramos en texto hemos de hacerle los castings
                        valueRange = 0f..100f,
                        steps = 0,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF4CD3C2),          // circulito
                            activeTrackColor = Color(0xFF4CD3C2),    // parte “rellena”
                            inactiveTrackColor = Color(0xFFDDDDDD)   // parte gris
                        )
                    )

                    Text(
                        text = sliderText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CD3C2),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CD3C2),   // color de fondo normal (turquesa)
                        contentColor = Color.White,           // color del texto/icono
                        disabledContainerColor = Color.Gray,  // fondo cuando está deshabilitado
                        disabledContentColor = Color.LightGray
                    ),

                    shape = RoundedCornerShape(0.dp),

                    onClick = {
                        if ((hola == false) && (adeu == false)) {
                            Toast.makeText(
                                context,
                                "Debes seleccionar una opcíon.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            pasos = 3
                        }
                    }
                ) {
                    Text("NEXT STEP")
                }
            }
            3 -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var texto by rememberSaveable { mutableStateOf("") }

                    var mostrarElemento by rememberSaveable { mutableStateOf(false) }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CD3C2),   // color de fondo normal (turquesa)
                            contentColor = Color.White,           // color del texto/icono
                            disabledContainerColor = Color.Gray,  // fondo cuando está deshabilitado
                            disabledContentColor = Color.LightGray
                        ),

                        shape = RoundedCornerShape(0.dp),

                        onClick = {
                            if (hola) {
                                texto = "Hola $nombre, com portes aquests $sliderText anys?"
                            } else if (adeu){
                                val edadMostrar = sliderValue.toInt() + 1
                                texto = "Espero tornar a veure’t $nombre, abans que facis $edadMostrar anys"
                            }

                            mostrarElemento = !mostrarElemento
                        }
                    ) {
                        Text("SHOW")
                    }

                    if (mostrarElemento) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = texto,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}