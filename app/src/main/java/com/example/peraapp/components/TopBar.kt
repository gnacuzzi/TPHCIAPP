package com.example.peraapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(@StringRes titleTopBar: Int) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoinicio),
                    contentDescription = stringResource(R.string.logoinicio),
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = stringResource(titleTopBar),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTablet(@StringRes name: Int) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.logoinicio),
                        contentDescription = stringResource(R.string.logoinicio),
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        stringResource(R.string.app_name),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.displayMedium)
                }
                Text(
                    text = stringResource(name),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    )
}