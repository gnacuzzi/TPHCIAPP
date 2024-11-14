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
fun CardHome(bank: String, number: String, name: String, date: String){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column (modifier = Modifier.size(width = 250.dp, height = 160.dp)){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 5.dp, vertical = 5.dp)
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = name,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
            }
        }
    }
}

