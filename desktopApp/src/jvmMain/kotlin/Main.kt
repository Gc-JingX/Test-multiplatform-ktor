import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication
import java.awt.Window
import androidx.compose.ui.window.Window

//fun main() {
//    val icon = painterResource("sample.png")
//     singleWindowApplication(
//        title = "仙屁时间显示器",
//        state = WindowState(size = DpSize(500.dp, 100.dp)),
//    ) {
//        MainView()
//    }
//}
fun main() = application {
    val icon = painterResource("stock1.jpg")
    Window(
        onCloseRequest = ::exitApplication,
        icon = icon,
        state = WindowState(size = DpSize(500.dp, 100.dp)),
        title = "仙屁时间显示器",
    ) {
        MainView()
    }
}