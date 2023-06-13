import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.M
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lt.compose_views.banner.Banner
import com.lt.compose_views.banner.rememberBannerState
import com.lt.compose_views.image_banner.ImageBanner
import com.lt.compose_views.image_viewer.ImageViewer
import com.lt.compose_views.other.FpsText
import com.lt.compose_views.other.VerticalSpace
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.RefreshLayout
import com.lt.compose_views.refresh_layout.RefreshLayoutState
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import com.lt.compose_views.text_field.GoodTextField
import com.lt.compose_views.util.ComposePosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import net.Greeting
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimeAppWithScaffold(displayTextField: Boolean = true) {
    TimeTheme {
        Scaffold( ) {
            TimeApp(displayTextField = displayTextField)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TimeApp(displayTextField: Boolean = true) {
    val state by store.stateFlow.collectAsState()
    TimeTheme {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Medium Bold",
                    style = typography.body1.copy(fontWeight = FontWeight.Medium),
                )
            }
        }
    }
}


private val orientation = mutableStateOf(Orientation.Horizontal)

@Composable
fun TimeTheme(content: @Composable () -> Unit) {

    var currentTime by remember { mutableStateOf("") }
    LaunchedEffect("") {
        while (true) {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
            currentTime = sdf.format(Calendar.getInstance().time)
            delay(100) //每秒更新一次时间
        }
    }
    val lis:MutableList<String> = arrayListOf()
    lis.add("1")
    lis.add("2")
    lis.add("3")
    lis.add("4")
    lis.add("5")
    val bannerState = rememberBannerState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
      Column {
          Text(
              text = currentTime,
              style = MaterialTheme.typography.h4.copy(
                  fontWeight = FontWeight.Bold
              ),
              textAlign = TextAlign.Center,
              modifier = Modifier.padding(horizontal = 16.dp)
          )
          Button(onClick = {
              Greeting().greeting()

          }, modifier = Modifier.fillMaxSize()) {
              Text(text = "哈哈哈哈", fontSize = 30.sp)
          }
          Banner(
              lis.size,
              Modifier
                  .height(100.dp).fillMaxWidth(),
              bannerState = bannerState,
              autoScrollTime = 1000,
              orientation = orientation.value,
          ) {
              Box(
                  modifier = Modifier
                      .height(100.dp).fillMaxWidth().background( Color.Black)
              ) {
                  Button(onClick = {

                  }, modifier = Modifier.align(Alignment.Center)) {
                      Text(text = this@Banner.index.toString(), fontSize = 30.sp)
                  }
              }
          }
      }
    }
//    MaterialTheme(
//        colors = lightColors(
//            surface = Color(ChatColors.SURFACE),
//            background = Color(ChatColors.TOP_GRADIENT.last()),
//        ),
//    ) {
//        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
//            content()
//        }
//    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TopRefreshLayout(refreshState: RefreshLayoutState) {
    RefreshLayout(
        {
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = "下拉刷新",
                    modifier = Modifier
                        .background(Color.Red)
                        .align(Alignment.Center)
                )
            }
        },
        refreshLayoutState = refreshState,
        composePosition = ComposePosition.Top,
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(Color.Gray)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "内容区域1")
            Text(text = "内容区域2")
            Text(text = "内容区域3")
            Text(text = "内容区域4")
            Text(text = "内容区域5")
            Text(text = "内容区域6")
            Text(text = "内容区域7")
            Text(text = "内容区域8")
            Text(text = "内容区域9")
        }
    }
}
@Composable
private fun createState() = rememberRefreshLayoutState {
    coroutineScope.run {

        setRefreshState(RefreshContentStateEnum.Stop)
    }
}
