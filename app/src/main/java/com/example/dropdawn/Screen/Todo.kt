package com.example.dropdawn.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Lista() {
    var expandedColor by remember { mutableStateOf(false) }
    var expandedTypography by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.Black) }
    var selectedFont by remember { mutableStateOf(FontFamily.Default) }
    var text by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }
    var appliedColor by remember { mutableStateOf(Color.Black) }
    var appliedFont by remember { mutableStateOf(FontFamily.Default) }

    val backgroundGradient = Brush.radialGradient(
        colors = listOf(Color(0xFF8E24AA), Color(0xFFC158DC), Color(0xFFE1BEE7)),
        radius = 500f
    )

    val buttonGradient = Brush.horizontalGradient(listOf(Color(0xFF8E24AA), Color(0xFFC158DC)))

    val colors = mapOf(
        "Rojo" to Color.Red,
        "Verde" to Color.Green,
        "Azul" to Color.Blue,
        "Amarillo" to Color.Yellow,
        "Café" to Color(139, 69, 19)
    )

    val fonts = mapOf(
        "Serif" to FontFamily.Serif,
        "SansSerif" to FontFamily.SansSerif,
        "Monospace" to FontFamily.Monospace,
        "Cursive" to FontFamily.Cursive,
        "Defecto" to FontFamily.Default
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box {
                    GradientButton("Escoger Color", buttonGradient) {
                        expandedColor = true
                    }
                    DropdownMenu(expanded = expandedColor, onDismissRequest = { expandedColor = false }) {
                        colors.forEach { (name, color) ->
                            DropdownMenuItem(
                                text = { Text(name, color = color) },
                                onClick = {
                                    selectedColor = color
                                    expandedColor = false
                                }
                            )
                        }
                    }
                }

                Box {
                    GradientButton("Escoger Tipografía", buttonGradient) {
                        expandedTypography = true
                    }
                    DropdownMenu(expanded = expandedTypography, onDismissRequest = { expandedTypography = false }) {
                        fonts.forEach { (name, font) ->
                            DropdownMenuItem(
                                text = { Text(name) },
                                onClick = {
                                    selectedFont = font
                                    expandedTypography = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Ingrese su texto", color = Color.White) },
                textStyle = TextStyle(color = appliedColor, fontFamily = appliedFont, fontSize = 18.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                singleLine = false
            )

            Spacer(modifier = Modifier.height(20.dp))

            GradientButton("Aplicar", buttonGradient) {
                text = inputText
                appliedColor = selectedColor
                appliedFont = selectedFont
            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Composable
fun GradientButton(text: String, gradient: Brush, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .shadow(6.dp, shape = RoundedCornerShape(20.dp))
            .background(gradient, shape = RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White, fontSize = 16.sp)
    }
}



