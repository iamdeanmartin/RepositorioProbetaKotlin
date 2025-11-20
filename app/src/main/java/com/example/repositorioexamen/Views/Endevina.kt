package com.example.ejercicio1practica.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.ejercicio1practica.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Redo
import androidx.compose.ui.text.font.FontFamily
import kotlin.random.Random
import androidx.compose.ui.text.style.TextAlign

@Composable
fun endevina(modifier: Modifier) {

    var primerCheck by rememberSaveable { mutableStateOf(false) }
    var segundoCheck by rememberSaveable { mutableStateOf(false) }
    var tercerCheck by rememberSaveable { mutableStateOf(false) }

    var numero by rememberSaveable { mutableStateOf("") }
    var numeroRandomInt by rememberSaveable { mutableStateOf(0) }

    var boolean by rememberSaveable { mutableStateOf(false) }

    var numeroAleatorio by rememberSaveable { mutableStateOf(Random.nextInt(1, 11)) }

    var iteracion by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.random),
            contentDescription = "Random foto of ShinChan",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "ENDEVINA EL NUMERET",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Marcalo si tienes más de 18 años",
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )

                    Checkbox(
                        checked = primerCheck,
                        onCheckedChange = { primerCheck = it }
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "¿Te gustan las cosas random?",
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )

                    Checkbox(
                        checked = segundoCheck,
                        onCheckedChange = { segundoCheck = it }
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "¿Vas a intentar adivinarlo?",
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )

                    Checkbox(
                        checked = tercerCheck,
                        onCheckedChange = { tercerCheck = it }
                    )
                }
            }

            val verdadero = primerCheck && segundoCheck && tercerCheck

            // 1) Zona del TextField + botón (solo si ha marcado los 3 checks y aún no ha enviado)
            if (verdadero && !boolean) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    value = numero,
                    onValueChange = {
                        numero = it
                        val numeroConvertido = it.toIntOrNull()
                        if (numeroConvertido != null) {
                            numeroRandomInt = numeroConvertido
                        }
                    },
                    placeholder = { Text("Introduce un número al azar.") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                val botonHabilitado =
                    numero.toIntOrNull() != null && numero.toIntOrNull()!! in 1..10

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    ),
                    enabled = botonHabilitado,
                    onClick = {
                        boolean = true
                        numero = ""
                        iteracion++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Enviar")
                }
            }

            // 2) Zona de resultado (solo cuando boolean == true)
            if (boolean) {

                if (numeroRandomInt < numeroAleatorio) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        text = "El númmero es más grande."
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = {
                            boolean = false
                            numero = ""
                            numeroRandomInt = 0
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Redo,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Volver a adivinar")
                    }
                } else if (numeroRandomInt > numeroAleatorio) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        text = "El númmero es más pequeño."
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = {
                            boolean = false
                            numero = ""
                            numeroRandomInt = 0
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Redo,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Volver a adivinar")
                    }
                } else { // numeroRandomInt == numeroAleatorio
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        text = "Lo has adivinado en $iteracion rondas. FELICIDADES"
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = {
                            boolean = false
                            numero = ""
                            numeroRandomInt = 0
                            numeroAleatorio = Random.nextInt(1, 11)
                            primerCheck = false
                            segundoCheck = false
                            tercerCheck = false
                            iteracion = 0
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Redo,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Jugar de nuevo")
                    }
                }
            }
        }
    }
}