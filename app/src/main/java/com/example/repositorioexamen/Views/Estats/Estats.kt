package com.example.repositorioexamen.Views.Estats

// =============================================================
//  APUNTES JETPACK COMPOSE – ESTADOS (STATE), REMEMBER, REMEMBERSAVEABLE
// =============================================================
//  Explicación completa con ejemplos y comentarios para examen.
//  ENTENDER ESTO = CONTROLAR EL 80% DE EXAMENES DE COMPOSE.
// =============================================================


// ========================== IMPORTS ===========================
// IMPORTA SOLO LOS QUE NECESITES, NO TODOS DE GOLPE.

import androidx.compose.runtime.Composable               // Para funciones de UI
import androidx.compose.runtime.mutableStateOf           // Crea un estado observable
import androidx.compose.runtime.remember                 // Mantiene el estado entre recomposiciones
import androidx.compose.runtime.saveable.rememberSaveable // Mantiene valor al girar dispositivo
import androidx.compose.runtime.getValue                 // Para usar "by"
import androidx.compose.runtime.setValue                 // Para usar "by"

import androidx.compose.foundation.layout.*              // Column, fillMaxSize, etc.
import androidx.compose.material3.Button                // Botón
import androidx.compose.material3.Text                  // Texto
import androidx.compose.ui.Alignment                    // Alineaciones
import androidx.compose.ui.Modifier                     // Modifiers
import androidx.compose.ui.unit.dp                      // dp


// =============================================================
// 1. QUÉ ES EL ESTADO
// =============================================================

/*
“El estado es cualquier valor que puede cambiar con el tiempo.”
La UI está ligada a un estado ⇒ si el estado cambia, la UI se actualiza sola.

Compose es DECLARATIVO:
    No cambiamos la UI directamente,
    cambiamos el estado → Compose redibuja lo necesario (recomposición).
*/


// =============================================================
// 2. EJEMPLO QUE NO FUNCIONA (sin estado real)
// =============================================================

@Composable
fun MyStateExample_MAL() {

    var counter = 0 // ❌ Variable normal, NO observable por Compose

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text("Push")
        }
        Text("Clicks: $counter") // ❌ No se actualiza nunca
    }
}


// =============================================================
// 3. mutableStateOf PERO SIN REMEMBER (todavía mal)
// =============================================================

@Composable
fun MyStateExample_MutableStatePeroMAL() {

    // mutableStateOf crea estado observable,
    // PERO se reinicia en cada recomposición
    var counter = mutableStateOf(0)

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter.value++ }) { Text("Push") }
        Text("${counter.value} times clicked")
    }
}


// =============================================================
// 4. remember + mutableStateOf (solución correcta excepto rotaciones)
// =============================================================

@Composable
fun MyStateExample_Remember() {

    // Se crea SOLO 1 vez, luego se recuerda
    var counter = remember { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter.value++ }) { Text("Push") }
        Text("${counter.value} times clicked")
    }
}


// =============================================================
// 5. rememberSaveable (solución final REAL) – mantiene valor al rotar
// =============================================================

@Composable
fun MyStateExample_RememberSaveable() {
    var counter = rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter.value++ }) { Text("Push") }
        Text("${counter.value} times clicked")
    }
}


// =============================================================
// 6. Versión final elegante usando "by"
// =============================================================

@Composable
fun MyStateExample_Final() {

    var counter by rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { counter++ }) { Text("Push") }
        Text("$counter times clicked")
    }
}

/*
Ventajas de usar "by":
    - Escribes menos: counter en lugar de counter.value
    - Se lee como una variable normal
*/


// =============================================================
// 7. TIPOS QUE PUEDO GUARDAR EN mutableStateOf (LISTA COMPLETA)
// =============================================================

/*
Tipos básicos:
--------------
Int        mutableStateOf(0)
Long       mutableStateOf(123L)
Float      mutableStateOf(3.14f)
Double     mutableStateOf(12.40)
Boolean    mutableStateOf(false)
String     mutableStateOf("Hola")
Char       mutableStateOf('A')

Nullable:
---------
mutableStateOf<String?>(null)

Listas y colecciones:
---------------------
mutableStateOf(listOf("a","b","c"))       // Lista inmutable (recomendada)
mutableStateOf(mutableListOf("a","b","c"))// Se puede modificar, pero peor para recomposición
mutableStateOf(setOf(1,2,3))
mutableStateOf(mapOf("a" to 1, "b" to 2))

Data class:
-----------
data class Usuario(val nombre: String, val edad: Int)
mutableStateOf(Usuario("Ana", 20))

Enums (muy usado para cambiar pantallas):
-----------------------------------------
enum class Pantalla { HOME, LOGIN }
mutableStateOf(Pantalla.HOME)


⭐ REGLA DE ORO:
    - Si guardas listas o data class, lo ideal es crear COPIAS nuevas al actualizar,
      porque Compose detecta mejor el cambio:
        lista = lista + "nuevo elemento"
        usuario = usuario.copy(nombre = "Nuevo")
*/


// =============================================================
// 8. EJEMPLO con diferentes tipos de estado
// =============================================================

@Composable
fun EstadosMultiples() {

    var clicks by rememberSaveable { mutableStateOf(0) }
    var nombre by rememberSaveable { mutableStateOf("") }
    var cargado by rememberSaveable { mutableStateOf(false) }
    var notas by rememberSaveable { mutableStateOf(listOf(5, 8, 9)) } // LISTA
    var usuario by rememberSaveable { mutableStateOf(UsuarioEjemplo("Dean", 19)) }
    var pantalla by rememberSaveable { mutableStateOf(PantallaExamenEstado.INICIO) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Clicks: $clicks")
        Text("Usuario: $nombre")
        Text("Notas: $notas")
        Text("Pantalla actual: $pantalla")
    }
}

data class UsuarioEjemplo(val nombre: String, val edad: Int)
enum class PantallaExamenEstado { INICIO, DETALLE }


// =============================================================
// 9. PATRÓN PARA NAVEGACIÓN SIN NAVIGATION (con when y estado)
// =============================================================

@Composable
fun NavegacionPorEstado() {

    var pantalla by rememberSaveable { mutableStateOf(PantallaExamenEstado.INICIO) }

    when (pantalla) {
        PantallaExamenEstado.INICIO -> {
            Text("Pantalla inicio")
            Button(onClick = { pantalla = PantallaExamenEstado.DETALLE }) { Text("Ir") }
        }
        PantallaExamenEstado.DETALLE -> {
            Text("Pantalla detalle")
            Button(onClick = { pantalla = PantallaExamenEstado.INICIO }) { Text("Volver") }
        }
    }
}


// =============================================================
// 10. RESUMEN EXPRESS PARA EXAMEN
// =============================================================

/*
❌ var normal en Composable
    → NO actualiza UI

⚠ mutableStateOf
    → crea estado observable, PERO se reinicia sin remember/

⭐ remember
    → conserva valor en recomposición (no en rotación)

🔥 rememberSaveable
    → conserva valor incluso si se gira la pantalla

💎 by
    → accedes sin escribir .value

💡 Tipos permitidos:
    Int, String, Boolean, Float, Double, Char
    List, MutableList, Set, Map
    data class
    enums
    nullables
*/