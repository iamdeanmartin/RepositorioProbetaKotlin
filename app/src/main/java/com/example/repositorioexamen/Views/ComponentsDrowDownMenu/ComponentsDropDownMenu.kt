package com.example.repositorioexamen.Views.ComponentsDrowDownMenu

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT DROPDOWNMENU
// =============================================================
//  TODO lo importante para el examen:
//   - Qué es un DropdownMenu y para qué sirve
//   - Variables necesarias: seleccionado, expanded y lista
//   - OutlinedTextField para mostrar selección
//   - DropdownMenu + DropdownMenuItem
//   - Cómo abrir/cerrar el menú
//   - Ejemplos con modificaciones de estilo
//   - Situaciones típicas de uso (formularios / filtros / selección única)
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape


// =============================================================
// 1. DROPDOWNMENU BÁSICO – ESTRUCTURA COMPLETA
// =============================================================

@Composable
fun MyDropdownMenuBasico() {

    // Estado para guardar la opción seleccionada
    var selectedText: String by remember { mutableStateOf("") }

    // Estado para saber si el menú está desplegado o cerrado
    var expanded: Boolean by remember { mutableStateOf(false) }

    // Lista de elementos (puede venir de un ArrayList dinámico)
    val hobbies = listOf("Play music", "Practice sport", "Programming", "Reading", "Other")

    Column(Modifier.padding(20.dp, 80.dp)) {

        // OutlinedTextField (solo muestra información, no editable)
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },  // aunque no se escriba seguimos el protocolo
            enabled = false,
            readOnly = true,                        // bloqueo de escritura
            modifier = Modifier
                .clickable { expanded = true }      // abre el menú al pulsar
                .fillMaxWidth()
        )

        // Menú desplegable
        DropdownMenu(
            expanded = expanded,                     // si true → visible, si false → oculto
            onDismissRequest = { expanded = false }, // cerrar cuando toca fuera
            modifier = Modifier.fillMaxWidth()
        ) {

            hobbies.forEach { hobby ->

                DropdownMenuItem(
                    text = { Text(text = hobby) },   // contenido del item
                    onClick = {
                        selectedText = hobby         // actualizamos selección
                        expanded = false             // cerramos menú
                    }
                )
            }
        }
    }
}

/*
IDEA PRINCIPAL:
---------------
DropdownMenu siempre necesita:
    - selectedText
    - expanded
    - una lista de opciones a mostrar

OutlinedTextField sirve como visualización del seleccionado.
*/



// =============================================================
// 2. DROPDOWNMENU CON ESTILO Y BORDE
// =============================================================

@Composable
fun MyDropdownMenuConEstilo() {

    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val hobbies = listOf("Music", "Sports", "Programming", "Gaming", "Reading")

    Column(Modifier.padding(20.dp)) {

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

            hobbies.forEach { hobby ->
                DropdownMenuItem(
                    text = { Text(hobby) },
                    onClick = {
                        selectedText = hobby
                        expanded = false
                    }
                )
            }
        }
    }
}

/*
MODIFICACIONES IMPORTANTES:
---------------------------
modifier.border()       → borde personalizado
RoundedCornerShape()    → esquinas redondeadas
fillMaxWidth()          → ocupar todo el ancho
clickable { expanded = true } → mostrar menú
*/



// =============================================================
// 3. DROPDOWNMENU – ESCENARIO TIPO EXAMEN (CONTROL DE ERRORES)
// =============================================================

@Composable
fun DropdownMenuExamen() {

    var selectedFruit by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val fruits = listOf("Apple", "Banana", "Orange", "Kiwi")

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = selectedFruit,
            onValueChange = { selectedFruit = it },
            enabled = false,
            readOnly = true,
            label = { Text("Select fruit") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        if (errorMessage != null) {
            Text(text = errorMessage ?: "", color = Color.Red)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            fruits.forEach { fruit ->

                DropdownMenuItem(
                    text = { Text(text = fruit) },
                    onClick = {
                        selectedFruit = fruit
                        expanded = false
                        errorMessage = null              // limpiar error
                    }
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            if (selectedFruit.isEmpty()) {
                errorMessage = "Selecciona una fruta"
            }
        }) {
            Text("Confirm")
        }
    }
}

/*
ESCENARIO REAL:
----------------
Botón confirma que hay una selección válida.
Si no hay → mensaje de error
*/


// =============================================================
// 4. RESUMEN EXPRESS PARA EXAMEN
// =============================================================

/*
📌 VARIABLES NECESARIAS
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val lista = listOf("uno", "dos", ...)

📌 COMPONENTES
    OutlinedTextField → visualiza selección (disabled/readOnly)
    DropdownMenu(expanded, onDismissRequest)
    DropdownMenuItem(text, onClick)

📌 FUNCIONAMIENTO
    click en OutlinedTextField → expanded = true
    seleccionar item → expanded = false + actualizar selectedText

📌 SITUACIONES TÍPICAS DE USO
    - Formularios (provincia, país, género, profesión…)
    - Filtros de búsqueda
    - Seleccionar categorías
    - Configuración de usuario

⚠ FRASE PARA RECORDAR
    "DropdownMenu = Expanded + Selección + Lista"
*/
// =============================================================