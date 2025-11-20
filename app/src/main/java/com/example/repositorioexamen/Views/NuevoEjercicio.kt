package com.example.myapplication.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


import com.example.repositorioexamen.R

import androidx.compose.ui.res.painterResource

import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox


import androidx.compose.foundation.R
import androidx.compose.ui.res.painterResource

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun nuevoEjercicio (modifier: Modifier = Modifier) {

    var numeroTexto by rememberSaveable { mutableStateOf("") }
    var numero by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text="MI PRIMERA APP",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                BadgedBox(
                    badge = { Badge(
                        containerColor = Color.Red,
                        contentColor = Color.Black
                    ) { Text("3") } }
                ) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(10.dp)
                        .padding(horizontal = 8.dp),
                    thickness = 2.dp,
                    color = Color.Black
                )

                BadgedBox(
                    badge = { Badge(
                        containerColor = Color.Red,
                        contentColor = Color.Black
                    ) { Text("3") } }
                ) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .height(10.dp)
                        .padding(horizontal = 8.dp),
                    thickness = 2.dp,
                    color = Color.Black
                )

                BadgedBox(
                    badge= { Badge(
                        containerColor = Color.Red,
                        contentColor = Color.Black
                    ) {
                        Text("3")
                    }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 2.dp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image (
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Foto",

                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)

            )

            TextField(
                value = numeroTexto,
                onValueChange = {numeroTexto = it
                    val number = it.toIntOrNull()
                    if (number != null) {
                        numero = number
                    }
                },

                singleLine = true,
                placeholder = {Text("Escribe un número")},

                )

        }
    }
}