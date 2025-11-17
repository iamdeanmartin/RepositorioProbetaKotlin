package com.example.repositorioexamen.Views.ComponentCard

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT CARD (Material3)
// =============================================================
//  TODO lo que te puede caer en el examen y algo más:
//   - Qué es una Card y para qué sirve
//   - Parámetros importantes: modifier, shape, colors, elevation, border
//   - Cómo organizar el contenido dentro (Column, Row, Image, Text…)
//   - Card clickable (cambiar estado / seleccionar elemento)
//   - Variantes: Card, ElevatedCard, OutlinedCard (Material3)
//   - Modifiers típicos (fillMaxWidth, padding, align, clickable, etc.)
//   - Ejemplos tipo “ficha de usuario / producto”
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// =============================================================
// 1. CARD BÁSICA – CONTENEDOR SENCILLO
// =============================================================

@Composable
fun CardBasica() {

    Card(
        modifier = Modifier
            .fillMaxWidth()      // ocupa todo el ancho disponible
            .padding(16.dp)      // margen exterior de la Card
    ) {
        // Contenido de la Card
        Column(
            modifier = Modifier.padding(12.dp)  // margen interior
        ) {
            Text(
                text = "Título",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Subtítulo",
                fontSize = 18.sp
            )
            Text(
                text = "Texto de ejemplo de la tarjeta...",
                maxLines = 3
            )
        }
    }
}

/*
IDEA CLAVE:
-----------
Card = CONTENEDOR visual con esquinas redondeadas y sombra.
Siempre se usa con más contenido dentro (Column, Row, etc).
*/


// =============================================================
// 2. PARÁMETROS IMPORTANTES DE CARD (shape, colors, elevation, border)
// =============================================================

@Composable
fun CardConParametrosCustom() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        // Forma de la Card (esquinas)
        shape = RoundedCornerShape(16.dp),
        // También puedes usar:
        // shape = MaterialTheme.shapes.small / medium / large

        // Colores de la Card
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,    // fondo de la Card
            contentColor = Color.Red        // color por defecto del contenido (Text, Icon…)
        ),

        // Borde alrededor de la Card
        border = BorderStroke(
            width = 3.dp,
            color = Color.Yellow
        ),

        // Elevación = cantidad de sombra (profundidad)
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(modifier = Modifier.padding(12.dp)) {
            Text("Card personalizada", fontWeight = FontWeight.Bold)
            Text("Con shape + colors + border + elevation.")
        }
    }
}

/*
PARÁMETROS DE CARD:
-------------------
modifier → tamaño, padding, clickable...
shape    → forma/esquinas (RoundedCornerShape, MaterialTheme.shapes.X)
colors   → colores de fondo y contenido (CardDefaults.cardColors)
border   → grosor y color del borde (BorderStroke)
elevation→ sombra, da sensación de profundidad
*/


// =============================================================
// 3. CARD CLICKABLE – SELECCIONABLE (CAMBIO DE ESTADO)
// =============================================================

@Composable
fun CardSeleccionable() {

    var seleccionado by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                // Acción cuando pulsamos la Card
                seleccionado = !seleccionado
            },

        colors = CardDefaults.cardColors(
            containerColor = if (seleccionado) Color(0xFFB2FF59) else Color.White,
            contentColor = Color.Black
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = if (seleccionado) 12.dp else 4.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Card seleccionable",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = if (seleccionado) "Estado: SELECCIONADA ✅" else "Estado: NO seleccionada",
                fontSize = 16.sp
            )
        }
    }
}

/*
CASOS DE USO PARA CARD CLICKABLE:
---------------------------------
✔ Lista de elementos seleccionables (usuarios, productos…)
✔ Tarjeta que abre detalle al tocarla (navigation)
✔ Cambiar estilo visual según esté seleccionada o no
*/


// =============================================================
// 4. CARD DE PERFIL – EJEMPLO TIPO EXAMEN
// =============================================================

@Composable
fun CardPerfil() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        border = BorderStroke(2.dp, Color.DarkGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bart Simpson",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Android Developer",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Desenvolupador d'Android amb 5 anys d'experiència a les més grans multinacionals.",
                maxLines = 3,
                fontSize = 14.sp
            )
        }
    }
}

/*
ESTRUCTURA TÍPICA DE CARD TIPO PERFIL:
--------------------------------------
Title (nombre)      → Text grande, bold, underline
Subtitle (rol)      → Text algo más pequeño
Descripción         → Uno o varios Text con maxLines
*/


// =============================================================
// 5. CARD + IMAGEN + TEXTO (CARD “DE PRODUCTO”)
// =============================================================

@Composable
fun CardConImagenYTexto() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Imagen a la izquierda
            Image(
                painter = painterResource(id = R.drawable.mi_imagen), // pon el nombre real
                contentDescription = "Imagen de producto",
                modifier = Modifier
                    .size(64.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Texto a la derecha
            Column {
                Text(
                    text = "Nombre del producto",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Descripción corta del producto...",
                    maxLines = 2,
                    fontSize = 14.sp
                )
                Text(
                    text = "Precio: 19,99€",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

/*
MUY TÍPICO EN EXAMEN:
---------------------
Card con Row:
    - Izquierda: Image / Icon
    - Derecha: Column con Title + Subtitle + Info
*/


// =============================================================
// 6. VARIANTES DE CARD EN MATERIAL3 (Card, ElevatedCard, OutlinedCard)
// =============================================================

@Composable
fun VariantesDeCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // CARD normal
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Card normal",
                modifier = Modifier.padding(12.dp)
            )
        }

        // ElevatedCard → con más sombra por defecto
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ElevatedCard (más sombra por defecto)",
                modifier = Modifier.padding(12.dp)
            )
        }

        // OutlinedCard → con borde, muy similar a Card + border
        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "OutlinedCard (con borde por defecto)",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

/*
DIFERENCIAS RÁPIDAS:
--------------------
Card          → base, sombra configurable con elevation
ElevatedCard  → más “levantada”, sombra más marcada por defecto
OutlinedCard  → borde fino alrededor, sin fondo muy marcado
*/


// =============================================================
// 7. MODIFIERS TÍPICOS CON CARD Y CÓMO AFECTAN
// =============================================================

/*
MODIFIERS QUE VAS A USAR SIEMPRE:
---------------------------------
Modifier.fillMaxWidth()
    → la Card ocupa todo el ancho posible del padre.
    Útil en listas verticales (Column con varias Cards una debajo de otra).

Modifier.padding(16.dp)
    → separa la Card de los bordes de la pantalla o de otras Cards.

Modifier.wrapContentSize()
    → la Card solo ocupa lo que necesite (más raro en examen).

Modifier.clickable { ... }
    → convierte toda la Card en interactiva (selección o navegación).

Modifier.height(x.dp) / width(x.dp)
    → si quieres Card con tamaño fijo (por ejemplo, tarjetas pequeñas).

A NIVEL DE CONTENIDO (DENTRO DE Card):
--------------------------------------
Column / Row con:
    verticalArrangement = ...
    horizontalAlignment = ...
para alinear y separar elementos.
*/


// =============================================================
// 8. CARD EN UNA LISTA (EJEMPLO CON VARIAS TARJETAS)
// =============================================================

@Composable
fun ListaDeCards() {

    val items = listOf("Elemento 1", "Elemento 2", "Elemento 3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { texto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = texto,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

/*
ESTO ES MUY TIPO EXAMEN:
------------------------
Una Column con varias Cards, cada una representando un ítem.
*/


// =============================================================
// 9. RESUMEN EXPRESS PARA MEMORIZAR (CARD)
// =============================================================

/*
CARD:
    - Es un contenedor visual con:
        shape (forma/esquinas)
        colors (containerColor, contentColor)
        elevation (sombra)
        border (borde)
        modifier (tamaño, padding, clickable…)

ESCENARIOS TÍPICOS:
    ✔ Tarjeta de perfil (nombre + rol + descripción)
    ✔ Tarjeta de producto (imagen + info)
    ✔ Elemento de lista clickable (seleccionable)

TRUCO EXAMEN:
    - Siempre mete una Column o Row dentro de la Card.
    - Usa fillMaxWidth() + padding() en el modifier de la Card.
    - Para hacerla clickable: modifier.clickable { ... }.
*/