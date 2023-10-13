package com.example.calculadorajuroscomposto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadorajuroscomposto.ui.theme.CalculadoraJurosCompostoTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraJurosCompostoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JurosComposto()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JurosComposto () {
    var principal by remember { mutableStateOf(0.0) }
    var taxaJurosAnual by remember { mutableStateOf(0.0) }
    var periodoEmAnos by remember { mutableStateOf(0.0) }

    val resultado by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = periodoEmAnos.toString(),
            onValueChange = {principal = it.toDoubleOrNull() ?: 0.0},
            label = { Text("Valor principal") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        TextField(
            value = taxaJurosAnual.toString(),
            onValueChange = { taxaJurosAnual = it.toDoubleOrNull() ?: 0.0},
            label = { Text("Taxa de Juros Anual (%)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
             )
        TextField(
            value = periodoEmAnos.toString(),
            onValueChange = { periodoEmAnos = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Período (Anos)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Button(onClick =  {
            val montanteTotal = principal * (1 + taxaJurosAnual / 100).pow(periodoEmAnos)
            val resultado = "Montante Total: R$ ${"%.2f".format(montanteTotal)}"
        },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calcular")
        }
        Text()
    }
}

fun Text() {

}

@Preview(showBackground = true)
@Composable
fun JurosCompostoApp() {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CalculadoraJurosCompostoTheme {

        }
    }
    CalculadoraJurosCompostoTheme {
        JurosComposto()
    }
}