package com.example.repositorioexamen.Views.Layouts

// =============================================================
//  APUNTES JETPACK COMPOSE – LAYOUTS, ALIGNMENTS, SCROLL, WEIGHT, MODIFIERS
//  Todo explicado con comentarios dentro del código para usarlo de chuleta.
// =============================================================


// ========================== IMPORTS ===========================
// IMPORTA SOLO LOS QUE NECESITES, NO TODOS DE GOLPE.
// Aquí están comentados para que recuerdes qué hace cada uno.

// Necesario para cualquier función de UI en Compose (@Composable)
import androidx.compose.runtime.Composable               // Define una función de interfaz

// Layouts (Box, Column, Row) y funciones como fillMaxSize, padding, size, weight, etc.
import androidx.compose.foundation.layout.*               // Contiene Column, Row, Box y modifiers de layout

// Para poder aplicar Modifier.xxx a los composables
import androidx.compose.ui.Modifier                      // Base para encadenar modificadores

// Para escribir texto en la pantalla
import androidx.compose.material3.Text                   // Composable de texto

// Para usar colores como Color.Red, Color.Green, etc.
import androidx.compose.ui.graphics.Color                // Colores para fondos, etc.

// Para trabajar con unidades dp (tamaños, paddings, separaciones...)
import androidx.compose.ui.unit.dp                       // Unidades independientes de densidad

// Para alineaciones: Alignment.Center, TopStart, BottomEnd, etc.
import androidx.compose.ui.Alignment                     // Alineaciones para Box, Column y Row

// Scroll vertical para Column, Box, etc.
import androidx.compose.foundation.verticalScroll         // Habilita scroll vertical

// Scroll horizontal para Row o Box
import androidx.compose.foundation.horizontalScroll       // Habilita scroll horizontal

// Estado que recuerda la posición del scroll (necesario para verticalScroll/horizontalScroll)
import androidx.compose.foundation.rememberScrollState    // ScrollState recordable

// Para poner background(Color.X) en un Modifier
import androidx.compose.foundation.background             // Fondo de colores en elementos

// Otros helpers de foundation (clickable, border, etc.) si los necesitas
import androidx.compose.foundation.*                      // Extra (usar solo si hace falta)


// =============================================================
// MAPA DE EJES Y ALIGNMENTS (MUY IMPORTANTE EN EXAMEN)
// =============================================================

/*
COLUMN  →  eje principal VERTICAL (↑↓), eje cruzado HORIZONTAL (↔)
    - verticalArrangement → controla cómo se reparte el espacio entre elementos en vertical.
    - horizontalAlignment → alinea los hijos en horizontal (izquierda/centro/derecha).

ROW     → eje principal HORIZONTAL (↔), eje cruzado VERTICAL (↑↓)
    - horizontalArrangement → distribuye espacio horizontal entre elementos.
    - verticalAlignment     → alinea los hijos en vertical (arriba/centro/abajo).

BOX     → contenedor 2D con mapa 3x3.
    - contentAlignment → alineación general de todos los hijos.
    - Modifier.align() → alineación específica de un hijo concreto.

MAPA 3x3 VISUAL DE ALIGNMENTS:

    TopStart      TopCenter      TopEnd
    CenterStart   Center         CenterEnd
    BottomStart   BottomCenter   BottomEnd
*/


// =============================================================
// 📦 BOX – Contenedor libre y superposición de elementos
// =============================================================

@Composable
fun EjemploBox() {
    Box(
        modifier = Modifier
            .size(200.dp)                      // Tamaño del Box
            .background(Color.LightGray),      // Fondo para ver el contorno
        contentAlignment = Alignment.Center    // Alineación GLOBAL de los hijos
    ) {
        // Hijo 1 centrado por el contentAlignment del Box
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Red)
        )

        // Hijo 2 alineado individualmente, rompe el Center global y se va a abajo-derecha
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Blue)
                .align(Alignment.BottomEnd)    // Alineación personalizada para ESTE hijo
        )
    }
}


// =============================================================
// 🟧 COLUMN – Orden vertical (elementos uno debajo del otro)
// =============================================================

@Composable
fun EjemploColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()                             // Ocupa toda la pantalla
            .padding(16.dp),                           // Margen respecto al borde de la pantalla
        verticalArrangement = Arrangement.SpaceEvenly, // Reparto de espacio en VERTICAL
        horizontalAlignment = Alignment.CenterHorizontally // Alineación en HORIZONTAL
    ) {
        Text("Elemento 1", Modifier.background(Color.Yellow))
        Text("Elemento 2", Modifier.background(Color.Cyan))
        Text("Elemento 3", Modifier.background(Color.Green))
    }
}

/*
💡 POR QUÉ EN COLUMN USAMOS fillMaxSize() (EXPLICACIÓN PARA EXAMEN):
    - La Column tiene el eje principal VERTICAL.
    - Si usamos verticalArrangement (SpaceEvenly, SpaceBetween, etc.),
      necesitamos que la Column tenga ALTURA suficiente para poder repartir.
    - fillMaxSize() hace que la Column ocupe todo el ALTO de la pantalla,
      así el arrangement tiene "espacio" para jugar.
    - Si la Column fuera wrapContentHeight (sin fillMaxSize/Height),
      SpaceBetween / SpaceEvenly casi no se notarían, porque no habría espacio libre.
COLUMN – LISTA COMPLETA DE ALIGNMENTS Y ARRANGEMENTS

verticalArrangement (eje principal VERTICAL)
--------------------------------------------
- Arrangement.Top
    Todos los hijos se apilan hacia arriba.

- Arrangement.Center
    Todo el bloque de hijos se centra verticalmente.

- Arrangement.Bottom
    Todos los hijos se apilan hacia abajo.

- Arrangement.spacedBy(XX.dp)
    Mismo espacio constante entre cada hijo, por ejemplo 8.dp.

Explicación visual de los que reparten espacio (con 3 elementos):

- Arrangement.SpaceBetween:
    |Elem1|----------ESPACIO----------|Elem2|----------ESPACIO----------|Elem3|
    Primer hijo pegado arriba, último abajo, y el espacio se reparte SOLO entre medias.

- Arrangement.SpaceAround:
    --esp/2--|Elem1|--esp--|Elem2|--esp--|Elem3|--esp/2--
    El margen de fuera es la mitad que el interior. Queda "aire" antes y después.

- Arrangement.SpaceEvenly:
    --esp--|Elem1|--esp--|Elem2|--esp--|Elem3|--esp--
    Todo el espacio es igual: mismo margen arriba, entre ellos y abajo.


horizontalAlignment (eje cruzado HORIZONTAL)
--------------------------------------------
- Alignment.Start              → Hijos alineados a la izquierda.
- Alignment.CenterHorizontally → Hijos centrados horizontalmente.
- Alignment.End                → Hijos alineados a la derecha.

⚠ IMPORTANTE:
    Un HIJO puede romper el horizontalAlignment del padre usando:
        Modifier.align(Alignment.Start / CenterHorizontally / End)

💭 QUÉ PASA SI VOY AÑADIENDO MÁS ELEMENTOS A LA COLUMN:
    - Si usas verticalArrangement = Center:
        * El grupo entero de hijos (1, 2, 3, 4...) siempre se queda CENTRADO en el alto,
          aunque al añadir más se vayan compactando dentro de esa zona.
    - Si usas Top:
        * A medida que añades más elementos, se apilan desde arriba hacia abajo.
    - Si usas SpaceEvenly:
        * Al añadir más elementos, cada uno sigue separado por espacios iguales;
          el bloque total se reajusta, pero se respetan esos "espacios" iguales.
*/


// COLUMN CON PESOS – reparte la ALTURA entre hijos
@Composable
fun ColumnConPesos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            "Arriba",
            modifier = Modifier
                .weight(1f)                 // Ocupa 1/3 de la altura (3 elementos con mismo peso)
                .fillMaxWidth()
                .background(Color.Red)
        )

        Text(
            "Centro",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Blue)
        )

        Text(
            "Abajo",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Green)
        )
    }
}

/*
NOTA SOBRE weight:
    - En Column → reparte ALTURA.
    - En Row    → reparte ANCHURA.
    - Siempre va en el Modifier del HIJO, nunca en el padre.
*/


// =============================================================
// 🟥 ROW – Orden horizontal (elementos en fila)
// =============================================================

@Composable
fun EjemploRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()                             // Para que pueda repartir bien el espacio
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,  // Reparto en el eje HORIZONTAL
        verticalAlignment = Alignment.CenterVertically     // Alineación en el eje VERTICAL
    ) {
        Text("A", Modifier.background(Color.Yellow))
        Text("B", Modifier.background(Color.Cyan))
        Text("C", Modifier.background(Color.Magenta))
    }
}

/*
💡 POR QUÉ EN ROW USAMOS fillMaxWidth() (EXPLICACIÓN PARA EXAMEN):
    - La Row tiene su eje principal en HORIZONTAL.
    - Para que horizontalArrangement (SpaceBetween, SpaceEvenly, etc.)
      funcione de verdad, necesitamos ANCHO libre que repartir.
    - fillMaxWidth() hace que la Row ocupe todo el ancho disponible de la pantalla.
    - Si la Row fuera wrapContentWidth (sin fillMaxWidth),
      solo mediría lo que ocupan sus hijos y casi no habría "espacio extra" que repartir,
      así que SpaceBetween / SpaceAround / SpaceEvenly parecerían inútiles.
ROW – ALIGNMENTS Y ARRANGEMENTS COMPLETOS

horizontalArrangement (eje principal HORIZONTAL)
-------------------------------------------------
- Arrangement.Start
    Todos los hijos pegados a la izquierda.

- Arrangement.Center
    Todo el bloque de hijos centrado horizontalmente.

- Arrangement.End
    Todos los hijos pegados a la derecha.

- Arrangement.spacedBy(XX.dp)
    Espacio fijo entre hijos. Ej: 8.dp siempre entre cada elemento.


Explicaciones visuales (con 3 elementos A, B, C):

- Arrangement.SpaceBetween:
    |A|----------------------ESP----------------------|B|----------------------ESP----------------------|C|
    A pegado a la izquierda, C pegado a la derecha, y B repartido en medio.

- Arrangement.SpaceAround:
    --esp/2--|A|--esp--|B|--esp--|C|--esp/2--
    Hay medio espacio a los lados y espacio completo entre elementos. Parecen "rodeados" de márgenes.

- Arrangement.SpaceEvenly:
    --esp--|A|--esp--|B|--esp--|C|--esp--
    Todo absolutamente simétrico. Mismo espacio entre bordes y entre hijos.


verticalAlignment (eje cruzado VERTICAL)
----------------------------------------
- Alignment.Top               → Hijos pegados a la parte superior de la Row.
- Alignment.CenterVertically  → Hijos centrados verticalmente.
- Alignment.Bottom            → Hijos pegados a la parte inferior.

⚠ ACLARACIÓN MUY IMPORTANTE:
    - Un hijo SÍ puede romper la ALIGNMENT (alineación) del padre con Modifier.align(...).
    - Un hijo NO puede romper el ARRANGEMENT del padre.
      El arrangement es un cálculo GLOBAL del padre para distribuir el espacio del eje principal,
      y no se puede cambiar desde un único hijo.
*/


// =============================================================
// 📜 SCROLL – Vertical y horizontal correctamente hecho
// =============================================================

@Composable
fun ColumnScroll() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)                         // Separación del borde de la pantalla
            .verticalScroll(rememberScrollState())  // Habilita scroll vertical
    ) {
        repeat(25) { index ->
            Text(
                text = "Elemento $index",
                modifier = Modifier
                    .padding(vertical = 6.dp)       // Separación entre elementos
                    .background(Color.LightGray)
            )
        }
    }
}

@Composable
fun RowScroll() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()) // Habilita scroll horizontal
    ) {
        repeat(10) { index ->
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp)            // Separación entre cajas
                    .background(Color.Green)
            )
        }
    }
}

/*
REGLAS IMPORTANTES PARA SCROLL:
    - padding en el PADRE:
        → para que el contenido no quede pegado al borde de la pantalla.

    - padding en cada HIJO o Arrangement.spacedBy():
        → para separar los elementos entre sí.

    - verticalScroll/horizontalScroll:
        → siempre se ponen como Modifier del layout padre (Column, Row, Box).
*/


// =============================================================
// 🧱 COMBINAR LAYOUTS (estructura típica de examen)
// =============================================================

@Composable
fun LayoutCombinado() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Parte superior: Box con algo visual (imagen/logo/título grande)
        Box(
            modifier = Modifier
                .weight(2f)                       // 2/3 de la altura de la pantalla
                .fillMaxWidth()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("AQUÍ UNA IMAGEN O LOGO")
        }

        // Parte inferior: fila con dos elementos repartidos
        Row(
            modifier = Modifier
                .weight(1f)                       // 1/3 de la pantalla
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Blue)
            )
        }
    }
}

/*
COMBINACIONES ÚTILES EN EXAMEN:
    - Column + Row:
        Formularios, secciones (título arriba, filas de contenido abajo).

    - Box + Column:
        Fondo (color/imagen) con contenido de texto y botones encima.

    - Column + Scroll:
        Listas verticales largas (información, tarjetas, etc.).

    - Row + Scroll:
        Carrusel horizontal (imágenes, tarjetas, iconos…).

    - Column + Box:
        Pantallas separadas en zona visual superior y acciones abajo.
*/


// =============================================================
// 🆘 TIPS EXPRESS PARA NO LIARSE EN EXAMEN
// =============================================================

/*
1) SOBRE Row Y fillMaxWidth:
    - Si NO usas .fillMaxWidth() en una Row:
        → solo ocupa lo que miden sus hijos (wrap content),
          por lo que Arrangement.SpaceBetween / SpaceEvenly / SpaceAround
          parecen "no hacer nada" porque casi no hay espacio libre.
    - CONSEJO: en la mayoría de filas principales usa:
        Row(Modifier.fillMaxWidth()) { ... }

2) SOBRE ALIGNMENT DEL PADRE VS HIJO:
    - El padre (Column, Row, Box) marca una alineación general.
    - Un hijo puede ROMPER esa alineación usando Modifier.align(...).

      Ejemplo en Column:
        Column(horizontalAlignment = Alignment.Start) {
            Text("Izq por defecto")
            Text("Centrado", Modifier.align(Alignment.CenterHorizontally))
        }

      Ejemplo en Row:
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Centro")
            Text("Arriba", Modifier.align(Alignment.Top))
        }

3) SOBRE ARRANGEMENT:
    - El arrangement SIEMPRE lo controla el padre.
    - Ningún hijo puede cambiar cómo se reparte el espacio del eje principal.
    - Esto aplica tanto a Column (verticalArrangement) como a Row (horizontalArrangement).

4) SOBRE EJES:
    Column →
        main axis   → vertical
        cross axis  → horizontal

    Row →
        main axis   → horizontal
        cross axis  → vertical

5) SOBRE PADDING:
    - Padre:
        .padding(...)   → margen con respecto a bordes o otros contenedores
    - Hijo:
        .padding(...)   → aire alrededor de ese elemento dentro del layout padre

6) SOBRE WEIGHT:
    - Column + weight   → repartir alturas
    - Row + weight      → repartir anchos
*/


// =============================================================
// RESUMEN EN UNA FRASE PARA RECORDAR:
// =============================================================

/*
👉 El padre controla el ARRANGEMENT (reparto de espacio).
👉 El hijo solo puede cambiar su ALIGNMENT (posición dentro del espacio).
*/