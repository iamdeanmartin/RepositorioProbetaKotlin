package com.example.repositorioexamen.Views.ComponentImage

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT IMAGE & ICON
// =============================================================
//  Todo lo importante para el examen:
//   - painterResource y contentDescription
//   - alpha (transparencia)
//   - clip (RoundedCornerShape / CircleShape)
//   - border en imágenes
//   - contentScale (Fit, Crop, FillBounds…)
//   - Imagen ocupando toda la pantalla
//   - Modifiers importantes (size, fillMaxWidth, align, padding…)
//   - Imagen clickable
//   - Icon y tint
//   - Colección completa de iconos
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.foundation.Image                  // Componente Image
import androidx.compose.foundation.border                // Para border()
import androidx.compose.foundation.clickable             // Para hacer la imagen clickable
import androidx.compose.foundation.layout.*              // Column, Row, Box, fillMaxSize, etc.
import androidx.compose.foundation.shape.CircleShape     // Forma circular
import androidx.compose.foundation.shape.RoundedCornerShape // Esquinas redondeadas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale            // Cómo se adapta la imagen
import androidx.compose.ui.res.painterResource           // Para cargar drawables
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

import androidx.compose.material3.Icon                   // Componente Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star       // Ejemplo de icono de estrella


// =============================================================
// 1. IMAGE BÁSICA – estructura mínima
// =============================================================

@Composable
fun ImagenBasica() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),   // imagen en res/drawable
        contentDescription = "Descripción imagen"               // para accesibilidad
    )
}

/*
CONTENT DESCRIPTION:
--------------------
✔ Obligatorio siempre (menos en imágenes decorativas)
✔ Describe la imagen para lectores de pantalla
⚠ Si es solo decorativa → contentDescription = null
*/


// =============================================================
// 2. TRANSPARENCIA – alpha
// =============================================================

@Composable
fun ImagenConAlpha() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),
        contentDescription = "Descripción imagen semitransparente",
        alpha = 0.5f              // 1f opaco, 0f invisible
    )
}


// =============================================================
// 3. CLIP – esquinas redondeadas y forma circular
// =============================================================

@Composable
fun ImagenRedondeada() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),
        contentDescription = "Imagen con esquinas redondeadas",
        modifier = Modifier.clip(RoundedCornerShape(25f))
    )
}

@Composable
fun ImagenCircular() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),
        contentDescription = "Imagen circular",
        modifier = Modifier.clip(CircleShape)
    )
}


// =============================================================
// 4. BORDER – borde alrededor de la imagen
// =============================================================

@Composable
fun ImagenConBorde() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),
        contentDescription = "Imagen con borde",
        modifier = Modifier
            .clip(CircleShape)                         // primero recortamos forma circular
            .border(4.dp, Color.Red, CircleShape)      // mismo shape para la imagen
    )
}

/*
IMPORTANTE:
-----------
La forma del borde (CircleShape / RoundedCornerShape)
debe coincidir con la forma aplicada en clip()
*/


// =============================================================
// 5. CONTENT SCALE – cómo se adapta la imagen al espacio
// =============================================================

@Composable
fun ImagenConEscala() {
    Image(
        painter = painterResource(id = R.drawable.mi_imagen),
        contentDescription = "Imagen ajustada",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}

/*
CONTENT SCALE (muy importante para examen):
-------------------------------------------
ContentScale.FillBounds → estira la imagen para llenar el espacio, aunque se deforme
ContentScale.Crop       → recorta la imagen para llenar sin deformar (muy usado en fondos)
ContentScale.Fit        → ajusta la imagen entera dentro del espacio, puede dejar bordes
ContentScale.FillWidth  → llena el ancho, altura ajustada
ContentScale.FillHeight → llena la altura, ancho ajustado
*/


// =============================================================
// 6. IMAGEN COMO FONDO DE PANTALLA COMPLETA
// =============================================================

@Composable
fun ImagenFondoPantalla() {
    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo (por detrás)
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido encima de la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Aquí se pueden poner Text, Buttons, etc.
            // Text("Contenido sobre el fondo")
        }
    }
}


// =============================================================
// 7. IMAGEN CLICKABLE – detectar clicks sobre la imagen
// =============================================================

@Composable
fun ImagenClickable() {

    var pulsada by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.mi_imagen),
            contentDescription = "Imagen clickable",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    // ACCIONES AL HACER CLICK EN LA IMAGEN
                    pulsada = !pulsada
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (pulsada) {
            // Ejemplo: mostramos un texto si se ha pulsado
            androidx.compose.material3.Text(text = "Has pulsado la imagen ✅")
        } else {
            androidx.compose.material3.Text(text = "Pulsa la imagen 👆")
        }
    }
}

/*
Imagen clickable:
-----------------
modifier = Modifier.clickable { ... }

Acciones típicas en el click:
    - cambiar un estado (boolean, contador, texto…)
    - navegar a otra pantalla (cuando uses NavController)
    - mostrar/ocultar más contenido
*/


// =============================================================
// 8. MODIFIERS ÚTILES CON IMAGEN
// =============================================================

/*
MODIFIERS FRECUENTES EN IMAGEN:
-------------------------------
Modifier.fillMaxSize()                 → ocupa toda la pantalla
Modifier.fillMaxWidth()                → ancho completo
Modifier.size(120.dp)                  → tamaño cuadrado fijo
Modifier.width(120.dp) / height(80.dp) → tamaño personalizado
Modifier.padding(16.dp)                → margen exterior
Modifier.align(Alignment.CenterHorizontally) → centro en Column
Modifier.clip(CircleShape)             → recorte en forma
Modifier.border(4.dp, Color.Red)       → borde
Modifier.clickable { ... }             → clickable
*/


// =============================================================
// 9. ICON – para iconos vectoriales pequeños
// =============================================================

@Composable
fun IconoBasico() {

    Icon(
        imageVector = Icons.Default.Star,        // icono incluido en Material Icons
        contentDescription = "Icono estrella",
        tint = Color.Yellow                      // color del icono
    )
}

/*
ICON vs IMAGE:
--------------
Icon → vectorial, pequeño (24x24dp típico), se puede cambiar color con tint
Image → fotos/recursos, no cambia color tan fácilmente
*/


// =============================================================
// 10. ICON EN BOTÓN O EN ROW
// =============================================================

@Composable
fun BotonConIcono() {
    androidx.compose.material3.Button(
        onClick = { },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Icono en botón"
        )
        Spacer(Modifier.width(8.dp))
        androidx.compose.material3.Text("Favorito")
    }
}


// =============================================================
// 11. COLECCIÓN COMPLETA DE ICONOS
// =============================================================

/*
Página oficial de iconos de Google:
-----------------------------------
https://fonts.google.com/icons

Para usar TODOS los iconos:
---------------------------
En build.gradle (Module: app) añade:

implementation("androidx.compose.material:material-icons-extended:1.5.0")

(ajusta la versión a la que uses para Compose)
*/


// =============================================================
// 12. RESUMEN EXPRESS PARA EXAMEN
// =============================================================

/*
IMAGE:
Image(
    painter = painterResource(id = R.drawable.mi_imagen),
    contentDescription = "..."
)

Extras:
    alpha = 0.5f
    modifier = Modifier
        .clip(CircleShape)
        .border(4.dp, Color.Red, CircleShape)
        .clickable { ... }
        .fillMaxWidth()
        .size(120.dp)

CONTENT SCALE:
    FillBounds / Crop / Fit / FillWidth / FillHeight

ICON:
Icon(Icons.Default.Star, contentDescription = "...", tint = Color.Yellow)
*/