package com.example.lytko

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.lytko.MQTT.MQTTconnect
import com.example.lytko.json.jsonDecode
import com.example.lytko.ktor.ktor_rutine
import com.example.lytko.screens.config.init_demo_config
import com.example.lytko.ui.theme.Blue400
import com.example.lytko.ui.theme.DarkGray1000
import com.example.lytko.ui.theme.Grey900
import com.example.lytko.ui.theme.LytkoTheme
import com.siddroid.holi.colors.MaterialColor
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.DelicateCoroutinesApi
import libs.KeepScreenOn

val markdown = "---\n" +
        "__Advertisement :)__\n" +
        "\n" +
        "- __[pica](https://nodeca.github.io/pica/demo/)__ - high quality and fast image\n" +
        "  resize in browser.\n" +
        "- __[babelfish](https://github.com/nodeca/babelfish/)__ - developer friendly\n" +
        "  i18n with plurals support and easy syntax.\n" +
        "\n" +
        "You will like those projects!\n" +
        "\n" +
        "---\n" +
        "\n" +
        "# h1 Heading 8-)\n" +
        "## h2 Heading\n" +
        "### h3 Heading\n" +
        "#### h4 Heading\n" +
        "##### h5 Heading\n" +
        "###### h6 Heading\n" +
        "\n" +
        "\n" +
        "## Horizontal Rules\n" +
        "\n" +
        "___\n" +
        "\n" +
        "---\n" +
        "\n" +
        "***\n" +
        "\n" +
        "\n" +
        "## Typographic replacements\n" +
        "\n" +
        "Enable typographer option to see result.\n" +
        "\n" +
        "(c) (C) (r) (R) (tm) (TM) (p) (P) +-\n" +
        "\n" +
        "test.. test... test..... test?..... test!....\n" +
        "\n" +
        "!!!!!! ???? ,,  -- ---\n" +
        "\n" +
        "\"Smartypants, double quotes\" and 'single quotes'\n" +
        "\n" +
        "\n" +
        "## Emphasis\n" +
        "\n" +
        "**This is bold text**\n" +
        "\n" +
        "__This is bold text__\n" +
        "\n" +
        "*This is italic text*\n" +
        "\n" +
        "_This is italic text_\n" +
        "\n" +
        "~~Strikethrough~~\n" +
        "\n" +
        "\n" +
        "## Blockquotes\n" +
        "\n" +
        "\n" +
        "> Blockquotes can also be nested...\n" +
        ">> ...by using additional greater-than signs right next to each other...\n" +
        "> > > ...or with spaces between arrows.\n" +
        "\n" +
        "\n" +
        "## Lists\n" +
        "\n" +
        "Unordered\n" +
        "\n" +
        "+ Create a list by starting a line with `+`, `-`, or `*`\n" +
        "+ Sub-lists are made by indenting 2 spaces:\n" +
        "  - Marker character change forces new list start:\n" +
        "    * Ac tristique libero volutpat at\n" +
        "    + Facilisis in pretium nisl aliquet\n" +
        "    - Nulla volutpat aliquam velit\n" +
        "+ Very easy!\n" +
        "\n" +
        "Ordered\n" +
        "\n" +
        "1. Lorem ipsum dolor sit amet\n" +
        "2. Consectetur adipiscing elit\n" +
        "3. Integer molestie lorem at massa\n" +
        "\n" +
        "\n" +
        "1. You can use sequential numbers...\n" +
        "1. ...or keep all the numbers as `1.`\n" +
        "\n" +
        "Start numbering with offset:\n" +
        "\n" +
        "57. foo\n" +
        "1. bar\n" +
        "\n" +
        "\n" +
        "## Code\n" +
        "\n" +
        "Inline `code`\n" +
        "\n" +
        "Indented code\n" +
        "\n" +
        "    // Some comments\n" +
        "    line 1 of code\n" +
        "    line 2 of code\n" +
        "    line 3 of code\n" +
        "\n" +
        "\n" +
        "Block code \"fences\"\n" +
        "\n" +
        "```\n" +
        "Sample text here...\n" +
        "```\n" +
        "\n" +
        "Syntax highlighting\n" +
        "\n" +
        "``` js\n" +
        "var foo = function (bar) {\n" +
        "  return bar++;\n" +
        "};\n" +
        "\n" +
        "console.log(foo(5));\n" +
        "```\n" +
        "\n" +
        "## Tables\n" +
        "\n" +
        "| Option | Description |\n" +
        "| ------ | ----------- |\n" +
        "| data   | path to data files to supply the data that will be passed into templates. |\n" +
        "| engine | engine to be used for processing templates. Handlebars is the default. |\n" +
        "| ext    | extension to be used for dest files. |\n" +
        "\n" +
        "Right aligned columns\n" +
        "\n" +
        "| Option | Description |\n" +
        "| ------:| -----------:|\n" +
        "| data   | path to data files to supply the data that will be passed into templates. |\n" +
        "| engine | engine to be used for processing templates. Handlebars is the default. |\n" +
        "| ext    | extension to be used for dest files. |\n" +
        "\n" +
        "\n" +
        "## Links\n" +
        "\n" +
        "[link text](http://dev.nodeca.com)\n" +
        "\n" +
        "[link with title](http://nodeca.github.io/pica/demo/ \"title text!\")\n" +
        "\n" +
        "Autoconverted link https://github.com/nodeca/pica (enable linkify to see)\n" +
        "\n" +
        "\n" +
        "## Images\n" +
        "\n" +
        "![Minion](https://octodex.github.com/images/minion.png)\n" +
        "![Stormtroopocat](https://octodex.github.com/images/stormtroopocat.jpg \"The Stormtroopocat\")\n" +
        "\n" +
        "Like links, Images also have a footnote style syntax\n" +
        "\n" +
        "![Alt text][id]\n" +
        "\n" +
        "With a reference later in the document defining the URL location:\n" +
        "\n" +
        "[id]: https://octodex.github.com/images/dojocat.jpg  \"The Dojocat\"\n" +
        "\n" +
        "\n" +
        "## Plugins\n" +
        "\n" +
        "The killer feature of `markdown-it` is very effective support of\n" +
        "[syntax plugins](https://www.npmjs.org/browse/keyword/markdown-it-plugin).\n" +
        "\n" +
        "\n" +
        "### [Emojies](https://github.com/markdown-it/markdown-it-emoji)\n" +
        "\n" +
        "> Classic markup: :wink: :crush: :cry: :tear: :laughing: :yum:\n" +
        ">\n" +
        "> Shortcuts (emoticons): :-) :-( 8-) ;)\n" +
        "\n" +
        "see [how to change output](https://github.com/markdown-it/markdown-it-emoji#change-output) with twemoji.\n" +
        "\n" +
        "\n" +
        "### [Subscript](https://github.com/markdown-it/markdown-it-sub) / [Superscript](https://github.com/markdown-it/markdown-it-sup)\n" +
        "\n" +
        "- 19^th^\n" +
        "- H~2~O\n" +
        "\n" +
        "\n" +
        "### [\\<ins>](https://github.com/markdown-it/markdown-it-ins)\n" +
        "\n" +
        "++Inserted text++\n" +
        "\n" +
        "\n" +
        "### [\\<mark>](https://github.com/markdown-it/markdown-it-mark)\n" +
        "\n" +
        "==Marked text==\n" +
        "\n" +
        "\n" +
        "### [Footnotes](https://github.com/markdown-it/markdown-it-footnote)\n" +
        "\n" +
        "Footnote 1 link[^first].\n" +
        "\n" +
        "Footnote 2 link[^second].\n" +
        "\n" +
        "Inline footnote^[Text of inline footnote] definition.\n" +
        "\n" +
        "Duplicated footnote reference[^second].\n" +
        "\n" +
        "[^first]: Footnote **can have markup**\n" +
        "\n" +
        "    and multiple paragraphs.\n" +
        "\n" +
        "[^second]: Footnote text.\n" +
        "\n" +
        "\n" +
        "### [Definition lists](https://github.com/markdown-it/markdown-it-deflist)\n" +
        "\n" +
        "Term 1\n" +
        "\n" +
        ":   Definition 1\n" +
        "with lazy continuation.\n" +
        "\n" +
        "Term 2 with *inline markup*\n" +
        "\n" +
        ":   Definition 2\n" +
        "\n" +
        "        { some code, part of Definition 2 }\n" +
        "\n" +
        "    Third paragraph of definition 2.\n" +
        "\n" +
        "_Compact style:_\n" +
        "\n" +
        "Term 1\n" +
        "  ~ Definition 1\n" +
        "\n" +
        "Term 2\n" +
        "  ~ Definition 2a\n" +
        "  ~ Definition 2b\n" +
        "\n" +
        "\n" +
        "### [Abbreviations](https://github.com/markdown-it/markdown-it-abbr)\n" +
        "\n" +
        "This is HTML abbreviation example.\n" +
        "\n" +
        "It converts \"HTML\", but keep intact partial entries like \"xxxHTMLyyy\" and so on.\n" +
        "\n" +
        "*[HTML]: Hyper Text Markup Language\n" +
        "\n" +
        "### [Custom containers](https://github.com/markdown-it/markdown-it-container)\n" +
        "\n" +
        "::: warning\n" +
        "*here be dragons*\n" +
        ":::\n" + "# HTML from now!\n"



class MainActivity : ComponentActivity() {

    override fun onDestroy() {
        //stopService(Intent(this, MyService::class.java))  //Остановка службы
        Toast.makeText(this, "Прирложение onDestroy", Toast.LENGTH_LONG).show()
        println("Прирложение onDestroy")

        super.onDestroy()
    }


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Запуск виджета
        setContentView(R.layout.activity_main)

        //Инициализайия демо списка дял настроек
        init_demo_config()

        //Service(Intent(this, MyService::class.java))  //Запуск службы

        //startService(Intent(this, RandomNumberService::class.java))

        //stopService(Intent(this, MyService::class.java))  //Остановка службы


        //val address: Uri = Uri.parse("http://developer.alexanderklimov.ru")
        //val openLinkIntent : Intent = Intent(Intent.ACTION_VIEW, address)
        //startActivity(openLinkIntent)

        //if (openLinkIntent.resolveActivity(packageManager) != null)
        //{
        //    startActivity(openLinkIntent)
        //}
        //else {
        //    Log.d("Intent", "Не получается обработать намерение!")
        //}


        // Показываем все программы для запуска
        //val intent = Intent(Intent.ACTION_PICK)
        //intent.type = "image/*"
        //startActivityForResult(intent, 1)



        // val response: HttpResponse = client.monitor//get("https://ktor.io/")


        ktor_rutine()

        jsonDecode(device = device0)





        setContent {

            //LaunchedEffect(key1 = true, block = {ktor_rutine()})

            MQTTconnect(LocalContext.current)



            KeepScreenOn()

            var navController = rememberNavController()

            LytkoTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MarkdownText(
                        modifier = Modifier.padding(8.dp).fillMaxSize(),
                        markdown = markdown,
                        fontSize = 12.sp,
                        color = LocalContentColor.current,
                        //maxLines = 30,
                        fontResource = R.font.notosans_regular,
                        style = MaterialTheme.typography.titleMedium,
                        disableLinkMovementMethod = true
                        )


/*
                    NavHost(navController, Destinations.Screen_Dashboard)
                    {

                        composable(Destinations.Screen_Dashboard) {
                            ScreenDashboard(navController)
                        }

                        composable(Destinations.Login) {
                            screenLogin(navController)
                        }

                        composable(Destinations.Onboding) {
                            ScreenAfterLogin(navController)
                        }

                        composable(Destinations.Main) {
                            Greeting("Android")
                        }

                        composable(Destinations.Config_Current) {
                            Screen_Config_Current(navController)
                        }

                    }

*/


                }
            }
        }
    }
}

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState",
    "UnrememberedMutableState"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {

    val strlistState = mutableStateListOf<String>("Лампочка", "Бойлер", "Теплый пол")
    Scaffold(
        bottomBar = {

            Button(
                contentPadding = PaddingValues(start = 0.dp, top = 0.dp, bottom = 0.dp, end = 0.dp),
                shape = RoundedCornerShape(25),
                modifier = Modifier

                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                    .fillMaxWidth()
                    .height(56.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialColor.BLUE_800,
                    contentColor = Color.White
                ), onClick = {
                    strlistState.add(strlistState[strlistState.lastIndex - 2])
                    println("strlistState.value.add(Ванная)")
                }) {

                Text(
                    text = "Подключить", fontSize = 22.sp, fontFamily = FontFamily(
                        Font(R.font.notosans_medium)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(start = 0.dp)
                                //.background(Color.Green)
                                .size(36.dp),
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "",
                            tint = Blue400
                        )

                        Text(
                            textAlign = TextAlign.Start,
                            text = "Обновление",
                            modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                            //.background(Color.Red)
                            ,
                            fontFamily = FontFamily(
                                Font(R.font.notosans_medium)
                            ),
                            fontSize = 28.sp, fontWeight = FontWeight(2)
                        )
                    }

                }, //modifier = Modifier.height(100.dp),

                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Grey900)
            )


        }


    ) { paddingValue ->


        Column(
            Modifier
                .padding(top = paddingValue.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(contentAlignment = Alignment.CenterStart) {
                Text(
                    text = "КУХНЯ",
                    fontFamily = FontFamily(
                        Font(R.font.notosans_regular)
                    ),
                    fontSize = 28.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 6.dp)
                )
            }



            repeat(strlistState.size) { i ->

                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkGray1000),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                ) {

                    Box(contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = strlistState[i % strlistState.size],
                            fontFamily = FontFamily(
                                Font(R.font.notosans_regular)
                            ),
                            fontSize = 28.sp,
                            modifier = Modifier.padding(start = 16.dp, top = 6.dp)
                        )
                    }


                }
            }


        }


    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LytkoTheme {
        Greeting("Android")
    }
}


