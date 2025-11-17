package com.example.repositorioexamen.Views.ComponentTextField

// =============================================================
//  APUNTES JETPACK COMPOSE – COMPONENT TEXTFIELD & OUTLINEDTEXTFIELD
// =============================================================
//  TODO lo importante para el examen:
//   - Cómo funciona TextField y su estado
//   - value / onValueChange bien entendidos
//   - label, placeholder
//   - keyboardOptions (número, password, etc.)
//   - filtros con Regex
//   - password con icono de visibilidad
//   - OutlinedTextField + colors
//   - Modifiers típicos que puedes usar
// =============================================================


// ========================== IMPORTS ===========================

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff


// =============================================================
// 1. TEXTFIELD BÁSICO – SIEMPRE CON ESTADO
// =============================================================

@Composable
fun TextFieldBasico() {

    // Estado que guarda el texto actual del TextField
    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,                    // lo que se muestra dentro del TextField
        onValueChange = { myText = it }    // qué pasa cuando el usuario escribe

        // value y onValueChange SON OBLIGATORIOS
    )
}

/*
EXPLICACIÓN:
-----------
value:
    - El valor actual que se está mostrando.
    - Tiene que venir de un estado (remember { mutableStateOf("") } o rememberSaveable).

onValueChange:
    - Función lambda que recibe el nuevo texto como "it".
    - Dentro SIEMPRE hay que actualizar el estado: myText = it
*/


// =============================================================
// 2. TEXTFIELD CON LABEL (texto que indica para qué sirve)
// =============================================================

@Composable
fun TextFieldConLabel() {

    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = { myText = it },

        // label → texto que se muestra flotando
        label = { Text(text = "Enter your name") },

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
label:
    - Le dice al usuario qué debe escribir ahí.
    - Se posiciona encima del borde (Material Design).

placeholder:
    - También existe placeholder = { Text("Ejemplo...") }
    - Es un texto gris dentro del campo cuando está vacío.
*/


// =============================================================
// 3. TEXTFIELD SOLO NÚMEROS – keyboardOptions + KeyboardType.Number
// =============================================================

@Composable
fun TextFieldSoloNumeros() {

    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = { myText = it },

        label = { Text(text = "Enter your year of birth") },

        // keyboardOptions → tipo de teclado que aparece
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number    // teclado numérico
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
keyboardOptions → configuración del teclado
-------------------------------------------
KeyboardType.Text          → texto normal
KeyboardType.Number        → teclado numérico
KeyboardType.Email         → teclado con @ y .com
KeyboardType.Phone         → teclado de teléfono
KeyboardType.Password      → teclado adaptado a password
KeyboardType.Decimal       → números con decimal

Se usa SIEMPRE junto a TextField cuando quieres un teclado específico.
*/


// =============================================================
// 4. TEXTFIELD CON FILTRO (solo números usando Regex)
// =============================================================

@Composable
fun TextFieldSoloNumerosConFiltro() {

    var myText by remember { mutableStateOf("") }

    // Regex: solo dígitos (^\d+$) →  uno o más números
    val pattern = remember { Regex("^\\d+\$") }

    TextField(
        value = myText,
        onValueChange = { nuevoTexto ->
            // Solo aceptamos vacío o texto que cumpla el patrón
            if (nuevoTexto.isEmpty() || nuevoTexto.matches(pattern)) {
                myText = nuevoTexto
            }
        },
        label = { Text(text = "Enter your year of birth") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
TRUCO:
------
onValueChange = { nuevoTexto ->
    if (nuevoTexto.isEmpty() || nuevoTexto.matches(pattern)) {
        myText = nuevoTexto
    }
}

Así evitas que el estado se actualice con valores no válidos.
*/


// =============================================================
// 5. TEXTFIELD DE PASSWORD – ejemplo completo con icono de visibilidad
// =============================================================

@Composable
fun PasswordField() {

    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },

        label = { Text("Enter your password") },

        maxLines = 1,
        singleLine = true,

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),

        // Icono al final del campo (trailingIcon)
        trailingIcon = {

            // Elegimos el icono según si la password es visible o no
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                }
            ) {
                Icon(
                    imageVector = image,
                    contentDescription = "Password visibility"
                )
            }
        },

        // visualTransformation → cómo se dibuja el texto
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None                 // se ve el texto real
        } else {
            PasswordVisualTransformation()            // se ocultan los caracteres
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
RESUMEN PASSWORD FIELD:
-----------------------
- password: estado del texto
- passwordVisibility: estado de si se ve o no
- keyboardType = Password
- trailingIcon: icono para mostrar/ocultar
- visualTransformation:
      PasswordVisualTransformation() → oculta
      VisualTransformation.None      → muestra
*/


// =============================================================
// 6. OUTLINEDTEXTFIELD – Versión con borde alrededor
// =============================================================

@Composable
fun OutlinedTextFieldBasico() {

    var myText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        label = { Text("Enter your name") },

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
OutlinedTextField:
------------------
- Funciona IGUAL que TextField.
- Diferencia visual: tiene una línea/borde alrededor del campo.
- Muy común en Material3 y en diseños de formularios.
*/


// =============================================================
// 7. OUTLINEDTEXTFIELD CON COLORES PERSONALIZADOS
// =============================================================

@Composable
fun OutlinedTextFieldConColores() {

    var myText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        label = { Text("Enter your name") },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,   // cuando el TextField tiene foco
            unfocusedBorderColor = Color.Black  // cuando NO tiene foco
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

/*
colors en OutlinedTextField:
----------------------------
TextFieldDefaults.outlinedTextFieldColors(
    focusedBorderColor = ...,
    unfocusedBorderColor = ...,
    textColor = ...,
    cursorColor = ...,
    focusedLabelColor = ...,
    ...
)

Para examen:
    - recuerda especialmente focusedBorderColor y unfocusedBorderColor.
*/


// =============================================================
// 8. MODIFIERS QUE PUEDES USAR CON TEXTFIELD / OUTLINEDTEXTFIELD
// =============================================================

/*
MODIFIER → PARA EL COMPONENTE:
-------------------------------
Modifier.fillMaxWidth()
    → el campo ocupa todo el ancho de su contenedor.

Modifier.padding(16.dp)
    → separación respecto a bordes y otros elementos.

Modifier.width(200.dp) / height(60.dp)
    → tamaño fijo del TextField.

Modifier.align(Alignment.CenterHorizontally)
    → solo funciona dentro de Column, Row, Box,
      para posicionar el TextField (por ejemplo, centrado).

Modifier.fillMaxSize()
    → muy poco común en TextField, pero existe.


PROPIEDADES IMPORTANTES DEL TEXTFIELD:
--------------------------------------
value                 → texto actual
onValueChange         → actualización del estado
label                 → texto flotante
placeholder           → texto gris de ejemplo cuando está vacío
singleLine            → si el TextField solo puede tener una línea
maxLines              → número máximo de líneas
keyboardOptions       → tipo de teclado (Number, Password, Email, etc.)
enabled               → true/false, si está activo
readOnly              → true → se ve pero no deja escribir
trailingIcon          → componente al final (icono de ojo, borrar, etc.)
leadingIcon           → componente al principio del campo
visualTransformation  → cómo se dibuja (password, mayúsculas visuales, etc.)
*/


// =============================================================
// 9. EJEMPLO COMPLETO TIPO EXAMEN – Formulario pequeño
// =============================================================

@Composable
fun FormularioEjemplo() {

    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var errorEdad by remember { mutableStateOf<String?>(null) }

    val soloNumeros = remember { Regex("^\\d+\$") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { nuevaEdad ->
                if (nuevaEdad.isEmpty() || nuevaEdad.matches(soloNumeros)) {
                    edad = nuevaEdad
                    errorEdad = null
                } else {
                    errorEdad = "Solo números"
                }
            },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorEdad != null) {
            Text(
                text = errorEdad ?: "",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
    }
}


// =============================================================
// 10. RESUMEN EXPRESS PARA EXAMEN – TEXTFIELD
// =============================================================

/*
✅ TextField / OutlinedTextField:
    - SIEMPRE: var texto by remember { mutableStateOf("") }
    - value = texto
    - onValueChange = { texto = it }

✅ Atributos clave:
    - label = { Text("...") }
    - placeholder = { Text("...") }
    - singleLine, maxLines
    - keyboardOptions = KeyboardOptions(keyboardType = ...)
    - trailingIcon / leadingIcon
    - visualTransformation (Password)
    - colors (en OutlinedTextField)

✅ Modifiers:
    - fillMaxWidth()
    - padding(...)
    - align(...)
    - width()/height()

⚠ Frase para memorizar:
    TEXTFIELD = ESTADO + value + onValueChange
    SIN ESTO → NO FUNCIONA.
*/