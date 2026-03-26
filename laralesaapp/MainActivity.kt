package com.example.LesalaraApp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "screen_a"
    ) {
        composable("screen_a") {
            ScreenA(navController)
        }

        composable("screen_b/{message}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message")
            ScreenB(navController, message)
        }

        composable("screen_b_sem_mensagem") {
            ScreenB(navController, null)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    val texto =
        "Looks luxuosos com vestidos, couro, salto alto e acessórios elegantes"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lesa girl fashion",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8B1E3F)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Tendências do mundo das meninas",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("screen_b/${Uri.encode(texto)}")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8BAC4)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ver tendências", color = Color(0xFF8B1E3F))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("screen_b_sem_mensagem")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8BAC4)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ver sem mensagem", color = Color(0xFF8B1E3F))
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sinta-se estilosa",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8B1E3F)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "A moda é sobre se sentir bem e expressar seu próprio estilo.",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (message != null) {
                    Text(
                        text = message,
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF8BAC4)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Voltar", color = Color(0xFF8B1E3F))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    MaterialTheme {
        ScreenA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    MaterialTheme {
        ScreenB(
            navController = rememberNavController(),
            message = "No nosso site você encontra inspiração fashion com maquiagens leves, roupas em tons suaves e penteados simples que estão em alta."
        )
    }
}