package com.example.repositorioexamen.Views.ComponentsProgressIndicators

// =============================================================
//  APUNTES JETPACK COMPOSE – PROGRESS INDICATORS
// =============================================================
//  Incluye (todo lo que te pueden pedir en examen):
//   - LinearProgressIndicator (horizontal)
//   - CircularProgressIndicator (circular)
//   - Determinados (0f..1f) e indeterminados (infinito)
//   - Atributos importantes: color, trackColor, strokeWidth, strokeCap, modifier
//   - Control de progreso con estados (remember / rememberSaveable)
//   - Mostrar / ocultar con botones
//   - AnimatedVisibility para simular barra de progreso
//   - Ejemplo de pantalla combinando todo
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.alpha


// =============================================================
// 1. LINEAR PROGRESS INDICATOR – BARRA HORIZONTAL
// =============================================================

@Composable
fun LinearProgressBasicoDeterminado() {

    // Progreso entre 0.0f y 1.0f
    var progressStatus by rememberSaveable { mutableStateOf(0.4f) }

    LinearProgressIndicator(
        progress = progressStatus,                 // nivel de progreso (0f..1f)

        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(16.dp),

        color = Color.Black,                      // color de la barra que se llena
        trackColor = Color.LightGray,             // color del fondo de la barra
        strokeCap = StrokeCap.Round               // forma de los extremos (Round, Square, Butt)
    )
}

/*
ATRIBUTOS IMPORTANTE – LinearProgressIndicator:
-----------------------------------------------
progress   → nivel de compleción de 0.0f a 1.0f (ej: 0.25f = 25%)
modifier   → tamaño, posición, padding, etc.
color      → color de la barra que se va llenando
trackColor → color del fondo de la barra
strokeCap  → acabado de los extremos:
              - StrokeCap.Butt   → recto
              - StrokeCap.Round  → redondeado
              - StrokeCap.Square → como Butt pero sobresale un pelín
*/


// =============================================================
// 2. LINEAR PROGRESS – INDETERMINADO (cargando infinito)
// =============================================================

@Composable
fun LinearProgressIndeterminado() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
NOTA:
-----
Si NO pasas el parámetro progress → la barra se anima infinitamente
(indica que "está cargando" sin saber el porcentaje).
*/


// =============================================================
// 3. CONTROLAR EL PROGRESO CON BOTONES (INCREASE / DECREASE)
// =============================================================

@Composable
fun LinearProgressConBotones() {

    var progressStatus by rememberSaveable { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Progreso: ${(progressStatus * 100).toInt()}%",
            fontSize = 18.sp
        )

        Spacer(Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = progressStatus,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            color = Color.Black,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (progressStatus > 0f) {
                        progressStatus -= 0.1f
                        if (progressStatus < 0f) progressStatus = 0f
                    }
                }
            ) {
                Text("Decrease")
            }

            Button(
                onClick = {
                    if (progressStatus < 1f) {
                        progressStatus += 0.1f
                        if (progressStatus > 1f) progressStatus = 1f
                    }
                }
            ) {
                Text("Increase")
            }
        }
    }
}

/*
IDEAS PARA onClick:
-------------------
- Subir o bajar el valor de progressStatus.
- Cuando llega a 1f, puedes mostrar un mensaje de “Completado”.
- Cuando llega a 0f, puedes ocultar la barra.
*/


// =============================================================
// 4. CIRCULAR PROGRESS INDICATOR – CIRCULAR
// =============================================================

@Composable
fun CircularProgressBasicoDeterminado() {

    var progressStatus by rememberSaveable { mutableStateOf(0.5f) }

    CircularProgressIndicator(
        progress = progressStatus,
        modifier = Modifier
            .size(120.dp)
            .padding(16.dp),
        color = Color.Green,             // color del círculo de progreso
        strokeWidth = 10.dp,             // grosor de la línea
        trackColor = Color.LightGray,    // color del fondo
        strokeCap = StrokeCap.Round
    )
}

/*
ATRIBUTOS – CircularProgressIndicator:
--------------------------------------
progress    → nivel de progreso 0.0f..1.0f
modifier    → tamaño, padding, align, etc.
color       → color del trazo que se llena
strokeWidth → grosor del círculo
trackColor  → línea de fondo
strokeCap   → forma del inicio/fin del arco
*/


// =============================================================
// 5. CIRCULAR PROGRESS – INDETERMINADO (cargando infinito)
// =============================================================

@Composable
fun CircularProgressIndeterminado() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 6.dp
        )
    }
}

/*
Al igual que con LinearProgressIndicator:
-----------------------------------------
- Si no pasas progress → animación infinita (cargando).
*/


// =============================================================
// 6. CIRCULAR + BOTONES PARA SUBIR / BAJAR PROGRESO
// =============================================================

@Composable
fun CircularProgressConBotones() {

    var progressStatus by rememberSaveable { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Progreso: ${(progressStatus * 100).toInt()}%",
            fontSize = 18.sp
        )

        Spacer(Modifier.height(12.dp))

        CircularProgressIndicator(
            progress = progressStatus,
            modifier = Modifier.size(120.dp),
            color = Color.Black,
            strokeWidth = 12.dp,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round
        )

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    if (progressStatus > 0f) {
                        progressStatus -= 0.1f
                        if (progressStatus < 0f) progressStatus = 0f
                    }
                }
            ) {
                Text("Decrease")
            }

            Button(
                onClick = {
                    if (progressStatus < 1f) {
                        progressStatus += 0.1f
                        if (progressStatus > 1f) progressStatus = 1f
                    }
                }
            ) {
                Text("Increase")
            }
        }
    }
}


// =============================================================
// 7. ANIMATEDVISIBILITY – SIMULAR PROGRESO VISUAL
// =============================================================

@Composable
fun BarraProgresoConAnimatedVisibility() {

    var progressStatus by rememberSaveable { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Progreso: ${(progressStatus * 100).toInt()}%")

        Spacer(Modifier.height(8.dp))

        // Barra de progreso “custom” que aparece cuando progressStatus > 0.25f
        AnimatedVisibility(
            visible = progressStatus > 0.25f,
            modifier = Modifier.padding(vertical = 16.dp),
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 800)),
            label = "Barra custom con AnimatedVisibility"
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Black)
                    .alpha(progressStatus)  // opacidad ligada al progreso
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    if (progressStatus > 0f) {
                        progressStatus -= 0.1f
                        if (progressStatus < 0f) progressStatus = 0f
                    }
                }
            ) {
                Text("Decrease")
            }

            Button(
                onClick = {
                    if (progressStatus < 1f) {
                        progressStatus += 0.1f
                        if (progressStatus > 1f) progressStatus = 1f
                    }
                }
            ) {
                Text("Increase")
            }
        }
    }
}

/*
AnimatedVisibility:
-------------------
visible = condición → se muestra o se oculta según la condición
enter   → animación de entrada (fadeIn, slideIn...)
exit    → animación de salida (fadeOut, slideOut...)

alpha(progressStatus):
    - Cuanto mayor el progreso, más opaco el fondo.
*/


// =============================================================
// 8. PANTALLA EJEMPLO COMPLETA TIPO EXAMEN
// =============================================================

@Composable
fun PantallaProgresoCompleta() {

    var progressStatus by rememberSaveable { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Ejemplo Completo de Progreso",
            fontSize = 22.sp
        )

        Spacer(Modifier.height(16.dp))

        // Linear determinate
        Text("Linear determinate")
        LinearProgressIndicator(
            progress = progressStatus,
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
                .padding(vertical = 8.dp),
            color = Color.Blue,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round
        )

        Spacer(Modifier.height(16.dp))

        // Circular determinate
        Text("Circular determinate")
        CircularProgressIndicator(
            progress = progressStatus,
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 8.dp),
            color = Color.Green,
            strokeWidth = 10.dp,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Round
        )

        Spacer(Modifier.height(16.dp))

        // Barra custom con AnimatedVisibility
        Text("Barra custom (AnimatedVisibility)")
        AnimatedVisibility(
            visible = progressStatus > 0.25f,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(800)),
            label = "Barra custom"
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Black)
                    .alpha(progressStatus)
            )
        }

        Spacer(Modifier.height(16.dp))

        // Botones de control
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    progressStatus -= 0.1f
                    if (progressStatus < 0f) progressStatus = 0f
                }
            ) {
                Text("Decrease")
            }

            Button(
                onClick = {
                    progressStatus += 0.1f
                    if (progressStatus > 1f) progressStatus = 1f
                }
            ) {
                Text("Increase")
            }

            Button(
                onClick = { progressStatus = 0f }
            ) {
                Text("Reset")
            }
        }
    }
}


// =============================================================
// 9. RESUMEN EXPRESS PARA MEMORIZAR
// =============================================================

/*
LINEARPROGRESSINDICATOR:
    progress (0f..1f) → determinado
    sin progress      → indeterminado
    strokeCap         → Butt / Round / Square
    color / trackColor

CIRCULARPROGRESSINDICATOR:
    igual pero circular + strokeWidth

ESTADO:
    var progressStatus by rememberSaveable { mutableStateOf(0.0f) }

BOTONES:
    Increase / Decrease / Reset modifican progressStatus

ANIMATEDVISIBILITY:
    visible = condición + fadeIn/fadeOut + alpha(progressStatus)
*/