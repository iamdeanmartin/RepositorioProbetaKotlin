📘 APUNTES BASE PARA EL EXAMEN DE JETPACK COMPOSE

0. Objetivo general
	•	Tener el proyecto preparado para que en el examen solo tengas que replicar la pantalla en papel.
	•	Estructura limpia:
	•	MainActivity → se encarga del tema, Scaffold e innerPadding.
	•	Carpeta views → aquí van las pantallas reales (ExamenApp, PantallaExamen, etc.).
	•	Previews listos para ir viendo lo que haces.

1. Estructura del proyecto
app/
└── java/com.example.repositorioexamen/
    ├── MainActivity.kt
    └── views/
        ├── ExamenApp.kt
        └── (otras pantallas que necesite el examen)
•	Siempre que el examen pida una pantalla, la UI principal va a ir en la carpeta views.

2. Cambios en MainActivity: de plantilla de Android Studio a versión examen

2.1. Código típico inicial de Android Studio (ejemplo)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepositorioExamenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
2.2. ¿Qué CAMBIOS hacemos y por qué?
CAMBIO 1 – Borrar Greeting y cualquier composable de ejemplo
	•	Quitamos Greeting, GreetingPreview, etc. de MainActivity.kt.
	•	Razón: no queremos lógica ni pantallas en MainActivity; las pantallas irán en views.
CAMBIO 2 – Sustituir Greeting(...) por nuestra vista raíz ExamenApp(...)
Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    ExamenApp(
        modifier = Modifier.padding(innerPadding)
    )
}
•	Razón: queremos que MainActivity solo monte el tema + scaffold y luego llame a tu “app de examen”: ExamenApp.

CAMBIO 3 – Mantener Scaffold solo en MainActivity
	•	Scaffold se queda en MainActivity.
	•	Dentro de views (pantallas reales) vamos a usar Column, Row, Box, que es lo que habéis dado en clase.

3. Scaffold e innerPadding: qué son y por qué solo ahí
3.1. Idea rápida
   	•	Scaffold = estructura base de pantalla (sirve para topBar, bottomBar, etc.).
	•	innerPadding = espacio que te da Scaffold para que tu contenido no se solape con barras.
Por eso usamos:
ExamenApp(
    modifier = Modifier.padding(innerPadding)
)
4. Composable raíz: ExamenApp (en views)

Archivo: views/ExamenApp.kt

4.1. ¿Qué hace?
	•	Recibe el modifier desde MainActivity (con el innerPadding ya aplicado).
	•	Gestiona qué “pantalla” mostrar usando un remember { mutableStateOf() }.
	•	Muestra el contenido con Column, Row, Box.

4.2. Código para README
package com.example.repositorioexamen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun ExamenApp(modifier: Modifier = Modifier) {

    // Estado para saber qué "pantalla" mostrar (simulación de navegación)
    val (pantallaActual, setPantallaActual) = remember { mutableStateOf(1) }

    Column(modifier = modifier) {

        when (pantallaActual) {
            1 -> PantallaExamen()
            // 2 -> OtraPantalla()
            // 3 -> OtraPantallaMas()
        }
    }
}

@Composable
fun PantallaExamen() {
    Text(text = "Aquí va la UI del examen")
}

🖼 5. PREVIEW — cómo ver la pantalla sin ejecutar la app

📍 Punto clave:

Si ejecutas la app en el emulador con ▶️ NO necesitas Preview
Preview es para ver una versión estática dentro de Android Studio

Preview
Run App
Necesita @Preview
No lo necesita
Se ve dentro de Android Studio
Se ve en el emulador o móvil real
No soporta toasts, clicks, estados dinámicos
Sí soporta todo
Más rápido para maquetar
Mejor para probar funcionalidad


Código Preview correcto:

6. Imágenes en drawable y uso en Compose

6.1. Reglas de nombres
	•	Carpeta: app/src/main/res/drawable
	•	Nombres:
	•	todo minúsculas
	•	sin espacios
	•	sin acentos
Ejemplos: fondo_login.png, icono_usuario.png

6.2. Uso en un composable
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.repositorioexamen.R

@Composable
fun ImagenEjemplo() {
    Image(
        painter = painterResource(id = R.drawable.fondo_login),
        contentDescription = "Fondo pantalla login",
        modifier = Modifier,
        contentScale = ContentScale.Crop
    )
}
# 🧠 CHULETA RÁPIDA

- MainActivity:
  - Usa Scaffold y innerPadding.
  - Llama a ExamenApp(modifier = Modifier.padding(innerPadding)).

- Carpeta /views:
  - Aquí van las pantallas reales.
  - Se construyen con Column, Row, Box, Image, Button, etc.

- Navegación sin Navigation:
  - Usar remember { mutableStateOf(1) } para pantallaActual.
  - Cambiar pantalla con setPantallaActual(2).
  - Mostrar con when(pantallaActual) { ... }.

- Preview:
  - Siempre sobre el composable de la pantalla, no MainActivity.
  - Envolverlo en RepositorioExamenTheme.

- Imágenes:
  - Van en res/drawable.
  - Nombres en minúsculas, sin espacios ni acentos.
  - Se usan con painterResource(R.drawable.nombre_imagen).
