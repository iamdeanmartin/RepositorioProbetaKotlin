package com.example.repositorioexamen.Views.ComponentText

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT TEXT & TEXTFIELD
// =============================================================
//  Incluye:
//   - Cómo usar Text (básico y avanzado)
//   - TextStyle reutilizable
//   - TextField / OutlinedTextField con estado
//   - Modifiers típicos
//   - Detalle importante de TextAlign (tipos y cómo se comporta)
//   - Recordatorio sp vs dp
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// =============================================================
// 📌 COMPONENT TEXT – Básico
// =============================================================

@Composable
fun EjemploTextBasico() {
    Text("Este es mi texto de ejemplo")
}


// =============================================================
// 📌 COMPONENT TEXT – Con atributos completos
// =============================================================

@Composable
fun EjemploTextAvanzado() {

    Text(
        text = "Texto con estilo",
        modifier = Modifier
            .padding(16.dp)             // espacio alrededor del Text
            .fillMaxWidth(),            // ocupa todo el ancho disponible

        // 🎨 Propiedades visuales
        color = Color.Red,              // color de la fuente
        fontSize = 30.sp,               // tamaño de texto → SIEMPRE sp (NO dp)
        fontWeight = FontWeight.Bold,   // grosor (Light, Normal, Medium, Bold...)
        fontStyle = FontStyle.Italic,   // cursiva
        fontFamily = FontFamily.Cursive,// familia tipográfica

        // ✨ Extras de formato
        textDecoration = TextDecoration.Underline,   // subrayado

        // 📐 Alineación del texto DENTRO del propio Text (no del layout)
        textAlign = TextAlign.Center,

        // 🧱 Líneas máximas y comportamiento al cortar
        maxLines = 2,
        overflow = TextOverflow.Ellipsis             // si se corta → "..."
    )
}

/*
⚠ IMPORTANTE: sp vs dp (para el examen)
----------------------------------------
- PARA TEXTO → fontSize = X.sp
    sp = "scale-independent pixels": respeta la configuración de tamaño de letra
    del usuario (accesibilidad, letra grande, etc.).

- PARA LAYOUTS (padding, width, height, size...) → X.dp

Usar dp en texto se considera mala práctica, porque NO respeta la accesibilidad
del sistema.


MODIFIERS típicos en Text:
--------------------------
Modifier.padding(16.dp)         → separa el texto de otros elementos
Modifier.fillMaxWidth()         → el Text ocupa todo el ancho
Modifier.fillMaxSize()          → ocupar ancho y alto (se usa menos en Text)
Modifier.width()/height()/size()→ tamaños concretos
Modifier.align(...)             → alinear el Text dentro de Row/Column/Box


📍 DIFERENCIA entre textAlign y Modifier.align:
-----------------------------------------------
- textAlign (TextAlign.Start, Center, End, Justify, etc.):
    → Alinea el TEXTO dentro del rectángulo del propio Text.
    → Afecta al contenido interno.

- Modifier.align(Alignment.XXX):
    → Alinea el COMPONENTE Text dentro de su contenedor (Column, Row, Box).
    → Afecta a la posición del Text en la pantalla, no a las letras.


TEXTALIGN – TIPOS PRINCIPALES Y QUÉ HACEN:
------------------------------------------
TextAlign.Start
    - Alinea el texto al "inicio" del rectángulo.
    - En idiomas LTR (izquierda a derecha, como español/inglés):
        Start = izquierda.
    - Útil para textos normales alineados a la izquierda.

TextAlign.Center
    - Centra el texto dentro del ancho del Text.
    - El bloque de texto queda centrado horizontalmente.

TextAlign.End
    - Alinea el texto al "final" del rectángulo.
    - En LTR (es/es, en/en):
        End = derecha.
    - Útil para alinear textos numéricos o valores a la derecha.

TextAlign.Justify
    - Intenta que cada línea de texto ocupe todo el ancho disponible,
      añadiendo espacios entre palabras para que la izquierda y la
      derecha queden alineadas (como en periódicos).
    - Solo se aprecia con textos largos (varias líneas).

Resumiendo para examen:
    - Start  → izquierda
    - Center → centro
    - End    → derecha
    - Justify→ texto “en bloque” (alineado a ambos lados)
*/


// =============================================================
// 🎨 TEXTSTYLE – Definir estilos reutilizables
// =============================================================

val EstiloTitulo = TextStyle(
    color = Color.Magenta,
    fontSize = 26.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun EjemploTextStyle() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Título 1", style = EstiloTitulo)
        Text("Título 2", style = EstiloTitulo)
    }
}


// =============================================================
// 🖊 COMPONENT TEXTFIELD – Entrada de texto editable
// =============================================================

@Composable
fun EjemploTextField() {

    // Estado del contenido que escribe el usuario
    var textoUsuario by remember { mutableStateOf("") }

    TextField(
        value = textoUsuario,                    // texto actual
        onValueChange = { textoUsuario = it },   // se llama cada vez que el usuario escribe

        modifier = Modifier
            .fillMaxWidth()                     // que no quede pequeño
            .padding(16.dp),

        label = { Text("Tu nombre") },              // texto flotante encima
        placeholder = { Text("Escribe aquí...") },  // texto gris cuando está vacío
        singleLine = true,                          // solo una línea
        maxLines = 1                                // por si acaso
    )
}

/*
IMPORTANTE:
    - Text NO necesita estado.
    - TextField SIEMPRE necesita estado (value + onValueChange).

PROPIEDADES CLAVE DE TEXTFIELD:
-------------------------------
value             → texto actual
onValueChange     → función que actualiza el estado
label             → etiqueta flotante
placeholder       → texto guía gris dentro del campo
singleLine        → si quieres solo una línea
maxLines          → máximo de líneas si es multilinea
enabled           → si está activo o no
readOnly          → se muestra pero no deja escribir
keyboardOptions   → tipo de teclado (número, email, password, etc.)

MODIFIERS útiles en TextField:
------------------------------
.fillMaxWidth()   → ocupa todo el ancho
.padding(...)     → separarlo del borde o de otros elementos
.width()/height() → tamaño a medida
.align(...)       → colocación dentro de Column/Row/Box
*/


// =============================================================
// 🖊 OUTLINED TEXTFIELD – Versión con borde (muy típica en diseños)
// =============================================================

@Composable
fun EjemploOutlinedTextField() {

    var nombre by remember { mutableStateOf("") }

    OutlinedTextField(
        value = nombre,
        onValueChange = { nombre = it },
        label = { Text("Nombre") },
        placeholder = { Text("Introduce tu nombre") },

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),

        singleLine = true
    )
}


// =============================================================
// 📍 EJEMPLO COMPLETO: TextField + Text de saludo
// =============================================================

@Composable
fun AppNombreEjemplo() {

    var nombre by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Introduce tu nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (nombre.isBlank()) "Escribe tu nombre arriba"
            else "Hola $nombre 👋",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,          // centrado dentro del ancho
            modifier = Modifier.fillMaxWidth()     // para que el Center tenga efecto
        )
    }
}


// =============================================================
// 🧠 RESUMEN EXPRESS PARA EXAMEN
// =============================================================

/*
TEXT:
    - Atributos clave:
        text, color, fontSize (sp), fontWeight, fontStyle, fontFamily,
        textDecoration, textAlign, maxLines, overflow, style, modifier

    - sp para fuentes, dp para tamaños/paddings.
    - textAlign:
        Start  → izquierda (en español)
        Center → centrado
        End    → derecha
        Justify→ texto en bloque

TEXTFIELD / OUTLINEDTEXTFIELD:
    - value + onValueChange → siempre necesarios
    - label, placeholder, singleLine, maxLines, modifier, keyboardOptions, enabled, readOnly

MODIFIERS IMPORTANTES:
    - padding()         → margen del componente
    - fillMaxWidth()    → evitar que quede enano
    - align()           → alineación dentro del layout
    - background()      → si quieres resaltar un Text

REGLA DE ORO:
    - Text: mostrador → NO necesita estado.
    - TextField: editable → SIEMPRE con estado remember { mutableStateOf("") } o rememberSaveable.
*/