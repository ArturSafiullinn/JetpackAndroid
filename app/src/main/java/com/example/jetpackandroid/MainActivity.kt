package com.example.jetpackandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackandroid.ui.theme.JetpackAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                }
            }
        }
    }
}


@Composable
fun RoundInitials(initials: String) {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = initials
            )
        }
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun RoundInitialsPreview() {
    RoundInitials("АБ")
}

@Composable
fun ContactCard(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {

            if (contact.imageRes != null) {
                Image(
                    painter = painterResource(contact.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contact.initials(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "${contact.name} ${contact.surname.orEmpty()}".trim(),
                style = MaterialTheme.typography.titleLarge
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = contact.familyName,
                    style = MaterialTheme.typography.headlineSmall
                )

                if (contact.isFavorite) {
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = stringResource(R.string.phone))
                Spacer(Modifier.height(12.dp))
                Text(text = stringResource(R.string.address))
                Spacer(Modifier.height(12.dp))
                Text(text = stringResource(R.string.email))
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = contact.phone)
                Spacer(Modifier.height(12.dp))
                Text(text = contact.address)
                Spacer(Modifier.height(12.dp))
                Text(text = contact.email ?: "—")
            }
        }
    }
}

@Preview
@Composable
fun ContactWithoutImage() {
    ContactCard(
        Contact(
            name = "Артур",
            surname = "Сафиуллин",
            familyName = "Ринатович",
            isFavorite = true,
            phone = "+79845621385",
            address = "ул. Котельников дом 28",
            email = "art22061993@gmail.com"
        )
    )
}

@Preview
@Composable
fun ContactWithImage() {
    ContactCard(
        Contact(
            name = "Артур",
            surname = "Сафиуллин",
            familyName = "Ринатович",
            isFavorite = true,
            phone = "+79845621385",
            address = "ул. Котельников дом 28",
            imageRes = R.drawable.man
        )
    )
}