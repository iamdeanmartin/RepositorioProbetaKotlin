package com.example.repositorioexamen.Views.ComponentButton

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT BUTTON (Material3)
// =============================================================
//  Todo lo necesario para examen:
//   - Estructura básica del Button
//   - Estados y acciones dentro de onClick (cambiar valores, navegar, deshabilitar, etc.)
//   - Modifiers: size, align, padding, shape, border
//   - Cambiar colores
//   - Tipos de botón: Button / OutlinedButton / TextButton
//   - Botón con icono
//   - Ejemplos reales de onClick (cambiar estado, mostrar texto, sumar contador)
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment


// =============================================================
// 1. BUTTON BÁSICO – estructura mínima
// =============================================================

@Composable
fun ButtonBasico() {

    Button(
        onClick = { /* acción al pulsar */ }
    ) {
        Text("Push")
    }
}

/*
PARA RECORDAR:
--------------
- Un Button SIEMPRE necesita la función onClick
- Dentro del Button podemos poner: Text, Icon, Row, Column, Box
*/


// =============================================================
// 2. ACCIONES QUE PODEMOS HACER EN onClick
// =============================================================

@Composable
fun ButtonAcciones() {

    var counter by remember { mutableStateOf(0) }
    var mensaje by remember { mutableStateOf("Aún no has hecho click") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Clicks: $counter")
        Text(mensaje)

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                counter++                     // sumar número
                mensaje = "Has hecho click!" // actualizar texto
            }
        ) {
            Text("Click aquí")
        }
    }
}

/*
EJEMPLOS DE ONCLICK REALES:
---------------------------
counter++                        → incrementar contador
counter = 0                      → reset
mensaje = "Texto cambiado"       → actualizar variable del estado
enabled = false                  → deshabilitar botón
mostrarPanel = !mostrarPanel     → alternar visibilidad
*/



// =============================================================
// 3. BUTTON CON COLORES, BORDE, TAMAÑOS Y FORMA
// =============================================================

@Composable
fun ButtonPersonalizado() {

    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,    // fondo del botón
            contentColor = Color.White      // texto e iconos internos
        ),
        border = BorderStroke(3.dp, Color.Green),
        modifier = Modifier
            .fillMaxWidth()                 // ocupa el ancho disponible
            .height(60.dp)                  // alto fijo
            .padding(16.dp)                 // margen exterior
            .align(Alignment.CenterHorizontally) // centrado dentro de Column
    ) {
        Text("Botón personalizado", fontSize = 18.sp)
    }
}

/*
MODIFIERS ÚTILES EN BOTONES:
----------------------------
.fillMaxWidth()                     → botón ancho completo
.width(200.dp) / .height(60.dp)     → tamaño fijo
.padding(16.dp)                     → margen exterior
.align(Alignment.CenterHorizontally)→ centra el botón dentro de Column
.fillMaxSize()                      → rara vez, ocupa toda la pantalla
*/


// =============================================================
// 4. BUTTON ENABLED / DISABLED (desactiva al pulsar)
// =============================================================

@Composable
fun ButtonEnabled() {

    var enabled by remember { mutableStateOf(true) }

    Button(
        onClick = { enabled = false },   // cambia estado al pulsar
        enabled = enabled                // si false aparece gris y no se puede pulsar
    ) {
        Text("Disable me")
    }
}

/*
enabled = false  → deshabilita el botón
-------------------------------------------------------------------------------
Muy usado para formularios donde solo se habilita cuando campos están completos
*/


// =============================================================
// 5. BUTTON CON ICONO
// =============================================================

@Composable
fun ButtonConIcono() {

    Button(
        onClick = { },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
        Spacer(Modifier.width(8.dp))
        Text("Agregar")
    }
}

/*
Button con ícono → útil para acciones rápidas y visuales
*/


// =============================================================
// 6. TIPOS DE BOTONES: Button / OutlinedButton / TextButton
// =============================================================

@Composable
fun TiposBoton() {

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = { }) {
            Text("Button")                      // principal
        }

        Spacer(Modifier.height(10.dp))

        OutlinedButton(onClick = { }) {
            Text("Outlined Button")             // secundario
        }

        Spacer(Modifier.height(10.dp))

        TextButton(onClick = { }) {
            Text("Text Button")                 // enlace / acción ligera
        }
    }
}

/*
DIFERENCIA VISUAL (IMPORTANTE):
-------------------------------------------------------------------
Button         → fondo sólido, destaca más, acción principal
OutlinedButton → borde, fondo transparente, acción secundaria
TextButton     → sólo texto, ideal para "cancelar" o "más info"
-------------------------------------------------------------------
*/


// =============================================================
// 7. RESUMEN EXPRESS PARA EXAMEN
// =============================================================

/*
🔥 SIEMPRE:
Button(onClick = { acciones }) { Text("Push") }

🔥 ACCIONES TÍPICAS EN onClick:
counter++
mensaje = "hola"
enabled = false
visible = !visible

🔥 PERSONALIZACIÓN:
ButtonDefaults.buttonColors(containerColor, contentColor)
BorderStroke(3.dp, Color.Green)
modifier: width(), height(), padding(), fillMaxWidth(), align()

🔥 Tipos de botón:
Button          → principal
OutlinedButton  → secundario
TextButton      → enlace/simple

🔥 Con icono:
Icon(...) + Text(...)
*/