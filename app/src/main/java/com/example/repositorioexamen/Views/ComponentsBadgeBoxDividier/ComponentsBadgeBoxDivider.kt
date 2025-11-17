package com.example.repositorioexamen.Views.ComponentsBadgeBoxDividier

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT BADGEDBOX & DIVIDERS
// =============================================================
//  TODO lo importante para el examen:
//   - Qué es BadgedBox y cuándo se usa
//   - Cómo crear badges con números (notificaciones)
//   - Cómo customizar colores y contenido del badge
//   - BadgedBox con Icon (muy típico en navBars, toolbars…)
//   - HorizontalDivider y VerticalDivider (separadores visuales)
//   - Atributos: thickness, color, modifier
//   - Ejemplos de uso real
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// =============================================================
// 1. BADGEDBOX BÁSICO – ICONO CON NOTIFICACIÓN
// =============================================================

@Composable
fun BadgedBoxBasico() {

    BadgedBox(
        modifier = Modifier.padding(20.dp),   // padding opcional
        badge = { Badge { Text("10") } }      // contenido del badge (obligatorio)
    ) {

        // Contenido principal (normalmente una Icon o Image)
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email"
        )
    }
}

/*
IDEA CLAVE:
-----------
BadgedBox agrupa un Icon (o imagen) con un Badge encima.
Muy usado para notificaciones (mensajes pendientes, carrito…)
*/



// =============================================================
// 2. BADGEDBOX CUSTOMIZADO – COLORES, CONTENIDO, ESTILO
// =============================================================

@Composable
fun BadgedBoxColores() {

    BadgedBox(
        modifier = Modifier.padding(20.dp),

        // Badge personalizado
        badge = {
            Badge(
                containerColor = Color.Green,
                contentColor = Color.Red
            ) {
                Text("99+")   // Contenido dentro del badge
            }
        }
    ) {

        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email"
        )
    }
}

/*
ATRIBUTOS IMPORTANTES DEL BADGE:
--------------------------------
containerColor → color de fondo del Badge
contentColor   → color del texto/dibujo dentro del Badge
content        → contenido (Text, Icon…) habitualmente números
*/



// =============================================================
// 3. BADGEDBOX CON CONTENT ALTERNATIVO (OTRA FORMA)
// =============================================================

@Composable
fun BadgedBoxConContentParameter() {

    BadgedBox(
        badge = {
            Badge(
                containerColor = Color.Magenta,
                contentColor = Color.White,
                content = { Text("7") }   // alternativa a escribir {} fuera
            )
        },
        content = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email"
            )
        }
    )
}

/*
NOTA:
-----
badge = {} es obligatorio
content = {} es opcional (Si NO hay content, se usa el trailing lambda por defecto)
*/


// =============================================================
// 4. HORIZONTAL DIVIDER – SEPARADOR HORIZONTAL
// =============================================================

@Composable
fun EjemploHorizontalDivider() {

    Column(modifier = Modifier.fillMaxSize()) {

        Text("Elemento superior")

        HorizontalDivider(
            modifier = Modifier
                .padding(0.dp, 10.dp),
            thickness = 3.dp,      // Grosor
            color = Color.Blue     // Color de la línea
        )

        Text("Elemento inferior")
    }
}

/*
ATRIBUTOS DEL HORIZONTAL DIVIDER:
---------------------------------
modifier   → tamaño, padding, align…
thickness  → grosor de la línea (dp)
color      → color del divisor
*/



// =============================================================
// 5. VERTICAL DIVIDER – SEPARADOR VERTICAL
// =============================================================

@Composable
fun EjemploVerticalDivider() {

    Row(modifier = Modifier.fillMaxWidth()) {

        Text("Left")

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp, 0.dp),
            thickness = DividerDefaults.Thickness,
            color = Color.Red
        )

        Text("Right")
    }
}

/*
IDEA:
-----
VerticalDivider separa contenido en dirección horizontal
Usado en menus, listados, layouts complejos con filas
*/


// =============================================================
// 6. EJEMPLO COMPLETO – BADGEDBOX + DIVIDERS + CONTENIDO
// =============================================================

@Composable
fun EjemploCompletoBadgedBoxDividers() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text("Notificaciones:")

        BadgedBox(
            badge = { Badge { Text("5") } }
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email notifications"
            )
        }

        HorizontalDivider(thickness = 2.dp, color = Color.Gray)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Text("Left")

            VerticalDivider(
                thickness = 3.dp,
                color = Color.Blue
            )

            Text("Right")
        }
    }
}


// =============================================================
// 7. RESUMEN EXPRESS PARA EXAMEN – BADGEDBOX / DIVIDER
// =============================================================

/*
🎯 BADGEDBOX:
    - badge = { Badge { Text("10") } }   → obligatorio
    - content → Icon normalmente
    - containerColor / contentColor customizables
    - muy usado en barras de navegación / toolbars / notificaciones

🎯 DIVIDERS:
    HorizontalDivider() → separa filas verticales de contenido
    VerticalDivider()   → separa columnas (Row)

PROPIEDADES CLAVE:
    thickness  → grosor
    color      → color
    modifier   → padding, size, align…

TRUCO EXAMEN:
    Si ves una barra de notificaciones o contador → BADGEDBOX
    Si ves separadores orgánicos entre elementos → DIVIDER
*/
// =============================================================