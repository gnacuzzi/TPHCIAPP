package com.example.peraapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

class Cards {
}

@Composable
fun Card(){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
    ){
        Column (modifier = Modifier.size(width = 400.dp, height = 200.dp)){
            Text(
                text = "Santander",
                modifier = Modifier.align(Alignment.End).padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Text(
                text = "1234 2123 2323 2343",
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 20.dp, vertical = 20.dp)
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = "Samanta Jones",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                )
                Text(
                    text = "12/28",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    PeraAppTheme {
        Card()
    }
}

