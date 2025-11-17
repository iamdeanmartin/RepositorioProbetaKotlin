package com.example.repositorioexamen.Views.ComponentsSliders

// =============================================================
//  APUNTES JETPACK COMPOSE – SLIDER & RANGESLIDER (SUPER COMPLETOS)
// =============================================================
//  TODO lo importante para examen y vida real:
//   - Qué es y cómo funciona internamente
//   - value, onValueChange, onValueChangeFinished
//   - valueRange / steps / enabled
//   - Modifiers útiles
//   - Mostrando valores en tiempo real y finales
//   - Ejemplos reales de escenarios de uso
//   - RangeSlider con doble control
//   - Accesibilidad y UX recomendada
//   - Apariencia (colores, track, thumb, labels personalizados)
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color



// =============================================================
// 1. SLIDER BÁSICO – CONTROL DE UN SOLO VALOR
// =============================================================

@Composable
fun SliderBasico() {

    // Estado del valor actual del Slider
    var sliderValue by remember { mutableStateOf(0f) }

    Slider(
        value = sliderValue,      // valor actual mostrado (Float)
        onValueChange = { sliderValue = it }    // mientras arrastramos
    )

    Text(text = sliderValue.toString())
}

/*
EXPLICACIÓN:
------------
- El Slider funciona con valores Float
- Cuando se mueve la bolita, se ejecuta constantemente onValueChange
- Por defecto el rango va de 0f a 1f y los valores tienen decimales
- Siempre necesita un estado para funcionar
*/



// =============================================================
// 2. SLIDER PROFESIONAL – valueRange + steps
// =============================================================

@Composable
fun SliderPro() {

    var value by remember { mutableStateOf(5f) }

    Slider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..10f,   // límites del Slider
        steps = 9               // número de pasos intermedios (no confundir con valores totales)

        /*
        valueRange = 0f..10f → valores permitidos
        steps = 9
          → hace que el Slider tenga puntos exactos donde parar
            siendo número total de valores = límite + 1
            (0..10 = 11 valores → steps = total valores - 2)
         */
    )

    Text("Valor actual: $value")
}

/*
CUÁNDO USAR:
-------------
- Formularios con opciones exactas
- Seleccionar puntuaciones (0-10)
- Brillo / tamaño / zoom en pasos concretos
*/



// =============================================================
// 3. SLIDER – MOSTRAR VALOR FINAL SIN DECIMALES
// =============================================================

@Composable
fun SliderConValorFinal() {

    var sliderValue by remember { mutableStateOf(0f) }
    var finalValue by remember { mutableStateOf("") }

    Slider(
        value = sliderValue,
        onValueChange = { sliderValue = it },

        // Se ejecuta cuando se suelta el control
        onValueChangeFinished = {
            finalValue = sliderValue.toInt().toString()
        },

        valueRange = 0f..10f,
        steps = 9
    )

    Text("Valor seleccionado: $finalValue")
}

/*
POR QUÉ USAR onValueChangeFinished:
-----------------------------------
- Para NO recalcular en cada movimiento
- Para enviar valor a servidor / BD sólo al finalizar
- Para validaciones (ej: botón activar con valor final)
*/



// =============================================================
// 4. RANGESLIDER – CONTROL DE DOS VALORES (INICIO Y FIN)
// =============================================================

@Composable
fun RangeSliderEjemplo() {

    var range by remember { mutableStateOf(2f..8f) }

    RangeSlider(
        value = range,
        onValueChange = { range = it },
        valueRange = 0f..10f
    )

    Text("Rango: ${range.start.toInt()} - ${range.endInclusive.toInt()}")
}

/*
USOS REALES:
------------
- Filtrar precios (ej: 20€ - 120€)
- Rango de edad
- Selección de tamaño mínimo y máximo
- Fechas aproximadas
*/



// =============================================================
// 5. MODIFIERS PARA SLIDER / RANGESLIDER
// =============================================================

/*
Modifier.fillMaxWidth()             → ocupa todo el ancho
Modifier.padding(16.dp)             → separación
Modifier.height(40.dp)              → aumenta el ancho de la barra
Modifier.align(Alignment.CenterHorizontally)
Modifier.alpha(0.5f)                → cambia transparencia
enabled = false                     → deshabilitar
*/



// =============================================================
// 6. CUSTOMIZACIÓN DE COLORES
// =============================================================

@Composable
fun SliderConColores() {

    var value by remember { mutableStateOf(5f) }

    Slider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..10f,

        colors = SliderDefaults.colors(
            thumbColor = Color.Red,            // bolita
            activeTrackColor = Color.Green,    // barra activa
            inactiveTrackColor = Color.Gray,   // barra restante
            activeTickColor = Color.Yellow,    // puntos activos
            inactiveTickColor = Color.Blue
        )
    )
}

/*
CUSTOMIZAR APARIENCIA:
----------------------
- thumbColor: color del circulito arrastrable
- activeTrackColor: porción ya recorrida
- inactiveTrackColor: porción sin recorrer
*/



// =============================================================
// 7. EJEMPLO REAL – SLIDER CONTROLANDO OTRO COMPONENTE
// =============================================================

@Composable
fun SliderConControlDeBoton() {

    var value by remember { mutableStateOf(0f) }

    Column(Modifier.padding(20.dp)) {

        Slider(
            value = value,
            onValueChange = { value = it },
            valueRange = 0f..100f
        )

        Button(
            onClick = {},
            enabled = value >= 50f             // habilitamos cuando llega a 50
        ) {
            Text("Continuar")
        }

        Text("Progreso: ${value.toInt()}%")
    }
}

/*
ESCENARIOS DONDE SE USA:
------------------------
- Aceptar condiciones al mover slider a 100%
- Exámenes tipo confirmación
- Control de volumen / brillo
*/



// =============================================================
// 8. NOTA IMPORTANTE DE UX / ACCESIBILIDAD
// =============================================================

/*
- SIEMPRE mostrar valor en un Text aparte
- SI usas steps, que sean lógicos (no demasiados)
- Evita sliders para valores críticos (usa input numérico)
*/



// =============================================================
// 9. RESUMEN EXPRESS PARA EL EXAMEN
// =============================================================

/*
📌 SLIDER = value + onValueChange + valueRange (+ steps opcional)
📌 onValueChangeFinished → cuando suelto el control
📌 RangeSlider → dos valores (start y endInclusive)
📌 Customización: SliderDefaults.colors(...)
📌 Usos típicos:
    - volumen
    - filtros (precio, edad)
    - calificaciones (0-10)
    - ajustes visuales (zoom, brillo)
📌 No olvidar: mostrar valor con Text
*/