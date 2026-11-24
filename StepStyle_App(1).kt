import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

private val Fondo = Color(0xFFF7F3EE)
private val Cafe = Color(0xFF8B6B4F)
private val Negro = Color(0xFF171717)
private val Gris = Color(0xFF666666)
private val Borde = Color(0xFFE3E3E3)

private data class Producto(
    val categoria: String,
    val nombre: String,
    val precio: String,
    val imagen: String
)

private val productos = listOf(
    Producto(
        "Deportivo",
        "Nike Air Force 1",
        "$189.900",
        "https://upload.wikimedia.org/wikipedia/commons/5/5c/Air_Force_1.JPG"
    ),
    Producto(
        "Clásico",
        "adidas Grand Court",
        "$209.900",
        "https://assets.adidas.com/images/w_600%2Cf_auto%2Cq_auto/e7d77d715f17473ba1f3aa48011dc53d_9366/Tenis_Grand_Court_UNISEX_Blanco_EF0101_01_standard.jpg"
    ),
    Producto(
        "Elegante",
        "Vizzano Tacón Alto",
        "$239.900",
        "https://static.dafiti.com.co/p/vizzano-4974-7642772-1-cart.jpg"
    ),
    Producto(
        "Casual",
        "Puma Suede",
        "$219.900",
        "https://upload.wikimedia.org/wikipedia/commons/f/f3/Puma_Suede.jpg"
    )
)

@Composable
fun App() {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }
    val categorias = listOf("Todos", "Deportivo", "Clásico", "Elegante", "Casual")

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Encabezado()

            Inicio()

            Catalogo(
                categorias = categorias,
                categoriaSeleccionada = categoriaSeleccionada,
                onCategoriaSeleccionada = { categoriaSeleccionada = it },
                productos = productos.filter {
                    categoriaSeleccionada == "Todos" ||
                        it.categoria == categoriaSeleccionada
                }
            )

            Sistema()

            Nosotros()

            PieDePagina()
        }
    }
}

@Composable
private fun Encabezado() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Fondo)
            .padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        if (maxWidth < 600.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Logo()
                MenuNavegacion()
                BotonOscuro("Ver zapatos", onClick = {})
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Logo()
                MenuNavegacion()
                BotonOscuro("Ver zapatos", onClick = {})
            }
        }
    }
}

@Composable
private fun Logo() {
    Text(
        text = "StepStyle",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Negro
    )
}

@Composable
private fun MenuNavegacion() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("Inicio", "Catálogo", "Sistema", "Nosotros").forEach { opcion ->
            Text(
                text = opcion,
                color = Color(0xFF333333),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun Inicio() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Fondo)
            .padding(horizontal = 24.dp, vertical = 55.dp)
    ) {
        val apilado = maxWidth < 650.dp

        if (apilado) {
            Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
                TextoInicio()
                TarjetaPrincipal(modifier = Modifier.fillMaxWidth())
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    TextoInicio()
                }
                Spacer(Modifier.width(32.dp))
                TarjetaPrincipal(modifier = Modifier.width(310.dp))
            }
        }
    }
}

@Composable
private fun TextoInicio() {
    Column {
        TextoPequeno("CATÁLOGO DE CALZADO")
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Encuentra el calzado que va contigo.",
            fontSize = 42.sp,
            lineHeight = 49.sp,
            fontWeight = FontWeight.Bold,
            color = Negro
        )
        Spacer(Modifier.height(18.dp))
        Text(
            text = "Conoce nuestros zapatos y consulta su modelo, marca, categoría, talla, color, precio y stock.",
            color = Gris,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(25.dp))
        BotonOscuro("Ver catálogo", onClick = {})
    }
}

@Composable
private fun TarjetaPrincipal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFE9E2D9), RoundedCornerShape(12.dp))
            .padding(24.dp)
            .height(172.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "👟", fontSize = 75.sp)
        Text(
            text = "STEPSTYLE",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp,
            color = Negro
        )
        Text(
            text = "Calzado para cada estilo",
            fontSize = 13.sp,
            color = Gris
        )
    }
}

@Composable
private fun Catalogo(
    categorias: List<String>,
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit,
    productos: List<Producto>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 45.dp)
    ) {
        TextoPequeno("NUESTRO CATÁLOGO")
        TituloSeccion("Elige tu estilo")

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            categorias.forEach { categoria ->
                if (categoria == categoriaSeleccionada) {
                    Button(
                        onClick = { onCategoriaSeleccionada(categoria) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Negro,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(categoria, fontSize = 13.sp)
                    }
                } else {
                    OutlinedButton(
                        onClick = { onCategoriaSeleccionada(categoria) },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(categoria, fontSize = 13.sp, color = Color(0xFF333333))
                    }
                }
            }
        }

        Spacer(Modifier.height(18.dp))
        Text(
            text = "Fotografías de referencia. Los precios y el stock mostrados son datos de ejemplo del proyecto.",
            color = Color(0xFF777777),
            fontSize = 12.sp
        )
        Spacer(Modifier.height(18.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val espacio = 18.dp
            val anchoTarjeta: Dp = when {
                maxWidth >= 1000.dp -> (maxWidth - espacio * 3) / 4
                maxWidth >= 600.dp -> (maxWidth - espacio) / 2
                else -> maxWidth
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(espacio),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                productos.forEach { producto ->
                    TarjetaProducto(
                        producto = producto,
                        modifier = Modifier.width(anchoTarjeta)
                    )
                }
            }
        }
    }
}

@Composable
private fun TarjetaProducto(
    producto: Producto,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        AsyncImage(
            model = producto.imagen,
            contentDescription = producto.nombre,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .background(Color(0xFFF1F1F1), RoundedCornerShape(9.dp))
        )

        Spacer(Modifier.height(12.dp))
        Text(
            text = producto.categoria,
            color = Color(0xFF777777),
            fontSize = 13.sp
        )
        Text(
            text = producto.nombre,
            color = Negro,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(7.dp))
        Text(
            text = producto.precio,
            color = Negro,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Negro,
                contentColor = Color.White
            )
        ) {
            Text("Ver información", fontSize = 13.sp)
        }
    }
}

@Composable
private fun Sistema() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Fondo)
            .padding(horizontal = 24.dp, vertical = 45.dp)
    ) {
        val elementos = listOf(
            "Registrar productos y sus características",
            "Consultar precio y disponibilidad",
            "Organizar productos por categoría",
            "Controlar el stock de zapatos"
        )

        if (maxWidth < 650.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                TextoSistema()
                ListaSistema(elementos)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    TextoSistema()
                }
                Box(modifier = Modifier.weight(1f)) {
                    ListaSistema(elementos)
                }
            }
        }
    }
}

@Composable
private fun TextoSistema() {
    Column {
        TextoPequeno("SISTEMA")
        TituloSeccion("Organiza la información de tu tienda.")
        Text(
            text = "StepStyle representa la información principal que puede manejar una tienda de zapatos.",
            color = Gris
        )
    }
}

@Composable
private fun ListaSistema(elementos: List<String>) {
    Column {
        elementos.forEachIndexed { index, texto ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "0${index + 1}",
                    color = Cafe,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(18.dp))
                Text(texto, color = Color(0xFF333333))
            }
            Surface(
                modifier = Modifier.fillMaxWidth().height(1.dp),
                color = Color(0xFFDED8D1)
            ) {}
        }
    }
}

@Composable
private fun Nosotros() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 45.dp)
    ) {
        if (maxWidth < 650.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                TextoNosotrosTitulo()
                TextoNosotrosDescripcion()
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    TextoNosotrosTitulo()
                }
                Box(modifier = Modifier.weight(1f)) {
                    TextoNosotrosDescripcion()
                }
            }
        }
    }
}

@Composable
private fun TextoNosotrosTitulo() {
    Column {
        TextoPequeno("SOBRE STEPSTYLE")
        TituloSeccion("Una forma sencilla de mostrar tu catálogo.")
    }
}

@Composable
private fun TextoNosotrosDescripcion() {
    Column {
        Text(
            text = "Este proyecto presenta un catálogo web para una tienda de zapatos, usando una estructura sencilla de HTML y CSS.",
            color = Gris
        )
        Spacer(Modifier.height(15.dp))
        listOf(
            "1. Selecciona una categoría.",
            "2. Elige un zapato.",
            "3. Consulta su información."
        ).forEach { paso ->
            Text(
                text = paso,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 7.dp)
            )
        }
    }
}

@Composable
private fun PieDePagina() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Negro)
            .padding(horizontal = 15.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "StepStyle",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Catálogo de zapatos.",
            color = Color(0xFFCCCCCC),
            fontSize = 13.sp
        )
    }
}

@Composable
private fun TextoPequeno(texto: String) {
    Text(
        text = texto,
        color = Cafe,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.sp
    )
}

@Composable
private fun TituloSeccion(texto: String) {
    Text(
        text = texto,
        color = Negro,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 22.dp)
    )
}

@Composable
private fun BotonOscuro(
    texto: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Negro,
            contentColor = Color.White
        )
    ) {
        Text(texto, fontSize = 14.sp)
    }
}
