package com.example.ejercicio1practica.Views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

// BADGE & DIVIDERS
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider

// DROPDOWN
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api

// SWITCH, CHECKBOX, RADIO, TRI-STATE
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.ui.state.ToggleableState

// SLIDERS
import androidx.compose.material3.Slider
import androidx.compose.material3.RangeSlider

// PROGRESS
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.StrokeCap

import androidx.compose.foundation.clickable

// CARD
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import com.example.myapplication.R

import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun EJERCICIOCOMPLETO(modifier: Modifier = Modifier) {

    // -------- ESTADOS BÁSICOS --------
    var numeroTexto by rememberSaveable { mutableStateOf("") }
    var numero by rememberSaveable { mutableStateOf(0) }

    // Dropdown
    var expanded by rememberSaveable { mutableStateOf(false) }
    val opciones = listOf("Opción A", "Opción B", "Opción C")
    var opcionSeleccionada by rememberSaveable { mutableStateOf(opciones[0]) }

    // Switch, Checkbox, Radio, TriState
    var switchOn by rememberSaveable { mutableStateOf(false) }
    var checkAvanzado by rememberSaveable { mutableStateOf(false) }

    val radioOpciones = listOf("Rojo", "Verde", "Azul")
    var radioSeleccionado by rememberSaveable { mutableStateOf(radioOpciones[0]) }

    var triState by rememberSaveable { mutableStateOf(ToggleableState.Indeterminate) }

    // Sliders
    var sliderValue by rememberSaveable { mutableStateOf(50f) }               // 0..100
    var rangeValues by rememberSaveable { mutableStateOf(20f..80f) }          // 0..100

    // Progress
    var progress by rememberSaveable { mutableStateOf(0.4f) }                 // 0f..1f

    // Card visible al pulsar ENVIAR
    var showCard by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E88E5))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // ----------- TÍTULO -----------
            Text(
                text = "MI PRIMERA APP",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ----------- BADGEDBOXES + DIVIDERS -----------
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.White
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                BadgedBox(
                    badge = { Badge { Text("10") } }
                ) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(horizontal = 6.dp),
                    thickness = 2.dp,
                    color = Color.White
                )

                BadgedBox(
                    badge = { Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) { Text("3") } }
                ) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(horizontal = 6.dp),
                    thickness = 2.dp,
                    color = Color.White
                )

                BadgedBox(
                    badge = { Badge { Text("7") } }
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ----------- IMAGEN -----------
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Imagen de ejemplo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ----------- TEXTFIELD NUMÉRICO -----------
            TextField(
                value = numeroTexto,
                onValueChange = { valor ->
                    numeroTexto = valor
                    valor.toIntOrNull()?.let { numero = it }
                },
                singleLine = true,
                placeholder = { Text("Introduce un número") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (numeroTexto.isBlank()) {
                            Toast.makeText(
                                context,
                                "Introduce un número válido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.2f),
                    focusedContainerColor = Color.White.copy(alpha = 0.1f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Yellow,
                    cursorColor = Color.Yellow,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // -------- DROPDOWNMENU (OutlinedTextField) --------

            OutlinedTextField(
                value = opcionSeleccionada,
                onValueChange = { opcionSeleccionada = it},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.15f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.10f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Yellow,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            opcionSeleccionada = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -------- SWITCH --------
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Modo especial:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Switch(
                checked = switchOn,
                onCheckedChange = { switchOn = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Yellow,
                    checkedTrackColor = Color(0xFF00C853)
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // -------- CHECKBOX CON CONTENIDO EXTRA --------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkAvanzado,
                onCheckedChange = { checkAvanzado = it }
            )
            Text(
                text = "Mostrar opciones avanzadas",
                color = Color.White
            )
        }

        if (checkAvanzado) {
            Text(
                text = "Opciones avanzadas activadas ✅",
                color = Color.Yellow,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // -------- RADIO BUTTONS --------
        Text(
            text = "Elige un color:",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )

        val radioOpciones = listOf("Rojo", "Verde", "Azul")
        radioOpciones.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = (opcion == radioSeleccionado),
                    onClick = { radioSeleccionado = opcion },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Yellow,
                        unselectedColor = Color.White
                    )
                )
                Text(
                    text = opcion,
                    color = Color.White,
                    fontWeight = if (opcion == radioSeleccionado)
                        FontWeight.Bold else FontWeight.Normal
                )
            }
        }

        // -------- TRI-STATE CHECKBOX --------
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriStateCheckbox(
                state = triState,
                onClick = {
                    triState = when (triState) {
                        ToggleableState.Off -> ToggleableState.Indeterminate
                        ToggleableState.Indeterminate -> ToggleableState.On
                        ToggleableState.On -> ToggleableState.Off
                    }
                }
            )
            Text(
                text = "Estado tri-state",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -------- SLIDER SIMPLE --------
        Text(
            text = "Valor slider: ${sliderValue.toInt()}",
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // -------- RANGE SLIDER --------
        Text(
            text = "Rango: ${rangeValues.start.toInt()} - ${rangeValues.endInclusive.toInt()}",
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        RangeSlider(
            value = rangeValues,
            onValueChange = { rangeValues = it },
            valueRange = 0f..100f
        )

        Spacer(modifier = Modifier.height(12.dp))

        // -------- PROGRESS INDICATORS --------
        Text(
            text = "Progreso: ${(progress * 100).toInt()}%",
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.Black,
            trackColor = Color.White.copy(alpha = 0.3f),
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(8.dp))

        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(60.dp),
            color = Color.Black,
            strokeWidth = 6.dp,
            trackColor = Color.White.copy(alpha = 0.3f),
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    progress = (progress - 0.1f).coerceIn(0f, 1f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("- 10%")
            }
            Button(
                onClick = {
                    progress = (progress + 0.1f).coerceIn(0f, 1f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("+ 10%")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ----------- BOTÓN ENVIAR -----------
        Button(
            onClick = {
                if (numeroTexto.isNotBlank()) {
                    Toast.makeText(
                        context,
                        "Número válido: $numero",
                        Toast.LENGTH_LONG
                    ).show()
                    showCard = true   // mostramos la Card
                }
            },
            enabled = numeroTexto.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(55.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(
                text = "ENVIAR",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        // ----------- CARD DE RESUMEN (MEJOR ALINEADA) -----------
        if (showCard) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "Resumen de tu configuración",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Cada dato en una Row, etiqueta a la izquierda, valor a la derecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Número introducido:")
                        Text(numero.toString(), fontWeight = FontWeight.SemiBold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Opción seleccionada:")
                        Text(opcionSeleccionada, fontWeight = FontWeight.SemiBold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Modo especial:")
                        Text(
                            if (switchOn) "Activado" else "Desactivado",
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Color elegido:")
                        Text(radioSeleccionado, fontWeight = FontWeight.SemiBold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Slider:")
                        Text("${sliderValue.toInt()}", fontWeight = FontWeight.SemiBold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Rango:")
                        Text(
                            "${rangeValues.start.toInt()} - ${rangeValues.endInclusive.toInt()}",
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Progreso:")
                        Text(
                            "${(progress * 100).toInt()}%",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
