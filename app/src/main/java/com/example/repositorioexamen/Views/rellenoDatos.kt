package com.example.businesscard.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.sp
import com.example.businesscard.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Send
@Composable
fun rellenoDatos(modifier: Modifier = Modifier) {

    //Ponemos el nombre y apellido en una sola variable.
    var nombreApellidoUsuario by rememberSaveable { mutableStateOf("") } //Obligatorio

    //La variable de numero de telefono para la esquina izquierda
    var numTel by rememberSaveable { mutableStateOf("") }

    //La variable de empresa será para escribir la empresa donde trabaja el usuario.
    var empresa by rememberSaveable { mutableStateOf("") }

    //La variable de direccion para la parte bottom de la tarjeta
    var direccion by rememberSaveable { mutableStateOf("") }

    //Variable para elegir el fondo de pantalla.
    var fondoPantalla by rememberSaveable { mutableStateOf("") }

    // Checkboxes para controlar la visibilidad
    var incluirEmpresa by rememberSaveable { mutableStateOf(false) }
    var incluirDireccion by rememberSaveable { mutableStateOf(false) }

    //El tri state será para poner si esta looking for a job o hiring y se pondra debajo del número teléfono.
    var tristate by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    //Variables para el darkmode / lightmode
    var darkMode by rememberSaveable { mutableStateOf(false) }
    val backgroundColor = if (darkMode) Color.Black else Color.White
    val primaryTextColor = if (darkMode) Color.White else Color.Black
    val secondaryTextColor = if (darkMode) Color.White else Color.Gray

    val sendButtonColors = if (darkMode) {
        ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.DarkGray,
            disabledContentColor = Color.LightGray
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        )
    }
    //Variable para mostrar la pantalla de relleno de datos o la pantalla de la tarjeta
    var mostrarForm by rememberSaveable { mutableStateOf(true) }

    var totalFields = 2
    if (incluirEmpresa) totalFields++
    if (incluirDireccion) totalFields++
    totalFields++

    var completedFields = 0
    if (nombreApellidoUsuario.isNotBlank()) completedFields++
    if (numTel.isNotBlank()) completedFields++
    if (incluirEmpresa && empresa.isNotBlank()) completedFields++
    if (incluirDireccion && direccion.isNotBlank()) completedFields++
    if (fondoPantalla.isNotBlank()) completedFields++

    val progress = if (totalFields > 0) completedFields.toFloat() / totalFields.toFloat() else 0f

    val opcion = when (fondoPantalla) {
        "montania" -> R.drawable.montania
        "edificios" -> R.drawable.edificios
        "mecanico" -> R.drawable.mecanico
        "paleta" -> R.drawable.paleta
        else -> R.drawable.edificios
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        if (mostrarForm) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .height(100.dp)
                            .width(175.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    Switch(
                        checked = darkMode,
                        onCheckedChange = { darkMode = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color.DarkGray,
                            uncheckedThumbColor = Color.DarkGray,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth(),
                        color = if (darkMode) Color.White else Color.Black,
                        trackColor = if (darkMode) Color.DarkGray else Color.LightGray
                    )
                }

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text("Datos personales",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = primaryTextColor)

                    Spacer(Modifier.padding(top = 6.dp))

                    TextField (
                        value = nombreApellidoUsuario, onValueChange = {
                            if (it.length <= 30) {
                                nombreApellidoUsuario = it
                            }},
                        label={Text("Introduzca su nombre y Apellido")}, singleLine = true
                    )

                    Spacer(Modifier.padding(10.dp))

                    Text("Datos de contacto",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = primaryTextColor)

                    TextField (
                        value = numTel, onValueChange = {
                            if (it.length <= 9) {
                                numTel = it
                            }},
                        label={Text("Introduzca su número de teléfono")},
                        singleLine = true,  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(Modifier.padding(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Estado de trabajo",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = primaryTextColor)

                        TriStateCheckbox(
                            state = tristate,
                            onClick = {
                                tristate = when (tristate) {
                                    ToggleableState.On-> ToggleableState.Off
                                    ToggleableState.Off-> ToggleableState.Indeterminate
                                    ToggleableState.Indeterminate-> ToggleableState.On
                                }
                            },
                            colors = if (darkMode) {
                                CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.DarkGray,
                                    checkmarkColor = Color.Black
                                )
                            } else {
                                CheckboxDefaults.colors()
                            }
                        )

                        Text(
                            text = when (tristate) {
                                ToggleableState.On -> "Buscando Trabajo"
                                ToggleableState.Off -> ""
                                ToggleableState.Indeterminate -> "Contratando"
                            },
                            color = secondaryTextColor
                        )

                    }

                    Spacer(Modifier.padding(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = incluirEmpresa,
                            onCheckedChange = { incluirEmpresa = it },
                            colors = if (darkMode) {
                                CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.DarkGray,
                                    checkmarkColor = Color.Black
                                )
                            } else {
                                CheckboxDefaults.colors()
                            }
                        )
                        Text(
                            "¿Incluir empresa?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = primaryTextColor
                        )
                    }
                    if (incluirEmpresa) {
                        Spacer(Modifier.padding(4.dp))
                        TextField(
                            value = empresa,
                            onValueChange = {
                                if (it.length <= 20) {
                                    empresa = it
                                }
                            },
                            label = { Text("Nombre de la empresa") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = if (darkMode) Color.White else Color.Black,
                                unfocusedIndicatorColor = if (darkMode) Color.White else Color.Gray,
                                cursorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                    }


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = incluirDireccion,
                            onCheckedChange = { incluirDireccion = it },
                            colors = if (darkMode) {
                                CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.DarkGray,
                                    checkmarkColor = Color.Black
                                )
                            } else {
                                CheckboxDefaults.colors()
                            }
                        )
                        Text(
                            "¿Incluir dirección?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = primaryTextColor
                        )
                    }
                    if (incluirDireccion) {
                        Spacer(Modifier.padding(4.dp))
                        TextField(
                            value = direccion,
                            onValueChange = {
                                if (it.length <= 50) {
                                    direccion = it
                                }
                            },
                            label = { Text("Dirección") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = if (darkMode) Color.White else Color.Black,
                                unfocusedIndicatorColor = if (darkMode) Color.White else Color.Gray,
                                cursorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                    }



                    Spacer(Modifier.padding(10.dp))

                    Text(
                        "¿De qué estilo quieres el fondo?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = primaryTextColor
                    )

                    Row(modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                RadioButton(
                                    selected = fondoPantalla == "edificios",
                                    onClick = { fondoPantalla = "edificios" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = if (darkMode) Color.White else Color(0xFF3A4CCB), // azul Android
                                        unselectedColor = if (darkMode) Color.White else Color.Gray
                                    )
                                )
                                Text("🏛️", fontSize = 20.sp)
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                RadioButton(
                                    selected = fondoPantalla == "montania",
                                    onClick = { fondoPantalla = "montania" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = if (darkMode) Color.White else Color(0xFF3A4CCB), // azul Android
                                        unselectedColor = if (darkMode) Color.White else Color.Gray
                                    )
                                )
                                Text("🏕️", fontSize = 20.sp)
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                RadioButton(
                                    selected = fondoPantalla == "mecanico",
                                    onClick = { fondoPantalla = "mecanico" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = if (darkMode) Color.White else Color(0xFF3A4CCB), // azul Android
                                        unselectedColor = if (darkMode) Color.White else Color.Gray
                                    )
                                )
                                Text("🛠️", fontSize = 20.sp)
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                RadioButton(
                                    selected = fondoPantalla == "paleta",
                                    onClick = { fondoPantalla = "paleta" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = if (darkMode) Color.White else Color(0xFF3A4CCB), // azul Android
                                        unselectedColor = if (darkMode) Color.White else Color.Gray
                                    )
                                )
                                Text("🪚", fontSize = 20.sp)
                            }
                        }
                    }


                    Spacer(Modifier.padding(10.dp))

                    Button(
                        onClick = { mostrarForm = false},
                        enabled = progress == 1f, //Solo cuando este completa la barra
                        modifier = Modifier.fillMaxWidth(),
                        colors = sendButtonColors
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Enviar datos y generar tarjeta"
                        )
                    }
                }
            }
        } else {
            val intencionTrabajo = when (tristate) {
                ToggleableState.On -> "Buscando Trabajo"
                ToggleableState.Indeterminate -> "Contratando"
                ToggleableState.Off -> ""
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 8.dp),
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color.Black),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ){

                    Box (Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(opcion),
                            contentDescription = "Fondo tarjeta",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = numTel, color = Color.White,
                                        fontWeight = FontWeight.SemiBold)
                                    Text(text = empresa, color = Color.White,
                                        fontWeight = FontWeight.SemiBold)
                                }

                                Spacer(Modifier.height(6.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = intencionTrabajo, color = Color.White,
                                        fontWeight = FontWeight.SemiBold)
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = nombreApellidoUsuario)
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = direccion, color = Color.White,
                                    fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { mostrarForm = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = sendButtonColors
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Volver al inicio"
                    )

                }
            }
        }
    }
}
