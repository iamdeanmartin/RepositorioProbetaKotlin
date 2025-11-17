package com.example.repositorioexamen.Views.ComponentsSwitchCheckboxTristateRadioButton

// =============================================================
//  APUNTES JETPACK COMPOSE – SWITCH / CHECKBOX / TRISTATE / RADIOBUTTON
// =============================================================
//  TODO LO IMPORTANTE PARA EXAMEN:
//   - Funcionamiento y uso de estado con remember / rememberSaveable
//   - Diferencia: Switch / CheckBox / TriStateCheckBox / RadioButton
//   - Cambios de colores
//   - Cómo agrupar CheckBox y RadioButtons en filas o listas
//   - Ejemplos reales usando onClick / onCheckedChange para modificar UI
//   - Cuándo usar cada uno en diseño UI
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.state.ToggleableState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.ui.text.font.FontWeight


// =============================================================
// 1. SWITCH – Encendido / Apagado
// =============================================================

@Composable
fun SwitchBasico() {

    // Estado del switch (on/off)
    var switchState by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = switchState,                  // si está activado
        onCheckedChange = { switchState = it }  // actualizar estado
    )
}

/*
Switch:
-------
Component tipo interruptor, solo 2 estados:
    - true  → encendido
    - false → apagado

Atributos:
checked            → valor actual
onCheckedChange    → qué hacer cuando el usuario lo activa
colors = SwitchDefaults.colors() → para personalizar estilos
*/


// =============================================================
// 2. SWITCH CON COLORES PERSONALIZADOS
// =============================================================

@Composable
fun SwitchConColores() {

    var switchState by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = switchState,
        onCheckedChange = { switchState = it },

        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Green,     // bolita ON
            uncheckedThumbColor = Color.Red,     // bolita OFF
            checkedTrackColor = Color.LightGray, // fondo ON
            uncheckedTrackColor = Color.DarkGray // fondo OFF
        )
    )
}


// =============================================================
// 3. CHECKBOX – Selección múltiple
// =============================================================

@Composable
fun CheckBoxBasico() {

    var isChecked by rememberSaveable { mutableStateOf(false) }

    Checkbox(
        checked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}

/*
CheckBox:
---------
Selección múltiple. Ideal para listas de opciones.
checked           → si está marcado
onCheckedChange   → acción al marcar/desmarcar

Puedes cambiar colores con:
colors = CheckboxDefaults.colors(...)
*/


// =============================================================
// 4. CHECKBOX CON LABEL AL LADO
// =============================================================

@Composable
fun CheckBoxConTexto() {

    var isChecked by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically   // alinea checkbox y texto
    ) {

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Accept terms")
    }
}


// =============================================================
// 5. CHECKBOX CON COLORES PERSONALIZADOS
// =============================================================

@Composable
fun CheckBoxConColores() {

    var isChecked by rememberSaveable { mutableStateOf(false) }

    Checkbox(
        checked = isChecked,
        onCheckedChange = { isChecked = it },

        colors = CheckboxDefaults.colors(
            checkedColor = Color.Cyan,     // color cuando está marcado
            uncheckedColor = Color.Red,    // cuando está desmarcado
            checkmarkColor = Color.Blue    // ✔ de dentro
        )
    )
}


// =============================================================
// 6. TRISTATE CHECKBOX – 3 ESTADOS (On, Off, Indeterminate)
// =============================================================

@Composable
fun TriStateCheckBoxExample() {

    var state by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    TriStateCheckbox(
        state = state,
        onClick = {
            state = when (state) {
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
                ToggleableState.On -> ToggleableState.Off
            }
        }
    )
}

/*
TriStateCheckbox:
-----------------
Tres estados posibles:
    Off            (false)
    On             (true)
    Indeterminate  (-)

Ideal para listas con subniveles: "Seleccionar todos"
*/


// =============================================================
// 7. RADIOBUTTON – Selección única
// =============================================================

@Composable
fun RadioButtonBasico() {

    var selectedState by rememberSaveable { mutableStateOf(false) }

    RadioButton(
        selected = selectedState,
        onClick = { selectedState = !selectedState }
    )
}

/*
RadioButton:
------------
Solo elegir UNA opción entre varias
Nunca debería ir solo
selected  → si esta opción es la actual
*/


// =============================================================
// 8. GRUPO DE RADIOBUTTONS – Selección única entre varias
// =============================================================

@Composable
fun RadioButtonsGroup() {

    val opciones = listOf("Rojo", "Verde", "Azul")
    var selectedOption by rememberSaveable { mutableStateOf(opciones[0]) }

    Column {
        opciones.forEach { opcion ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .toggleable(
                        value = (opcion == selectedOption),
                        onValueChange = { selectedOption = opcion }
                    )
            ) {

                RadioButton(
                    selected = opcion == selectedOption,
                    onClick = { selectedOption = opcion }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = opcion,
                    fontWeight =
                        if (opcion == selectedOption) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}


// =============================================================
// 9. RESUMEN EXPRESS PARA MEMORIZAR
// =============================================================

/*
SWITCH:
    - 2 estados (Encendido/Apagado)
    - ideal para activar/desactivar funciones
    checked / onCheckedChange
    colores: SwitchDefaults.colors()

CHECKBOX:
    - selección múltiple
    - puede llevar texto al lado
    colors = CheckboxDefaults.colors()

TRISTATECHECKBOX:
    - OFF, ON, INDETERMINADO
    ideal seleccionar subgrupos

RADIOBUTTON:
    - selección única entre varias opciones
    selected / onClick
    agrupar en Column + Row

TRUCO EXAMEN:
    TODOS necesitan estado con rememberSaveable
*/