package com.example.jetpackandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
                val testContact = Contact(
                    name = "Артур",
                    surname = "Сафиуллин",
                    familyName = "Ринатович",
                    isFavorite = true,
                    phone = "+79845621385",
                    address = "ул. Котельников дом 28",
                    email = "art22061993@gmail.com",
                    imageRes = R.drawable.man
                )

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    ContactDetails(
                        contact = testContact,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetails(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ContactAvatar(
                imageRes = contact.imageRes,
                initials = contact.initials()
            )

            Spacer(Modifier.height(12.dp))

            val fullName = buildString {
                append(contact.name)
                append(" ")
                append(contact.surname)
                if (!contact.familyName.isNullOrBlank()) {
                    append(" ")
                    append(contact.familyName)
                }
            }.trim()

            Text(
                text = fullName,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            if (contact.isFavorite) {
                Spacer(Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            DetailRow(label = stringResource(R.string.phone), value = contact.phone)
            Spacer(Modifier.height(12.dp))
            DetailRow(label = stringResource(R.string.address), value = contact.address)

            if (!contact.email.isNullOrBlank()) {
                Spacer(Modifier.height(12.dp))
                DetailRow(label = stringResource(R.string.email), value = contact.email!!)
            }
        }
    }
}

@Composable
private fun ContactAvatar(
    imageRes: Int?,
    initials: String,
) {
    if (imageRes != null) {
        Image(
            painter = painterResource(imageRes),
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
                text = initials,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = value,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(name = "Contact - Full", showSystemUi = true)
@Composable
fun ContactDetailsPreview_Full() {
    JetpackAndroidTheme {
        ContactDetails(
            contact = Contact(
                name = "Артур",
                surname = "Сафиуллин",
                familyName = "Ринатович",
                isFavorite = true,
                phone = "+79845621385",
                address = "ул. Котельников дом 28",
                email = "art22061993@gmail.com",
                imageRes = R.drawable.man
            )
        )
    }
}

@Preview(name = "Contact - Minimal", showSystemUi = true)
@Composable
fun ContactDetailsPreview_Minimal() {
    JetpackAndroidTheme {
        ContactDetails(
            contact = Contact(
                name = "Иван",
                surname = "Петров",
                familyName = null,
                isFavorite = false,
                phone = "+70000000000",
                address = "Москва",
                email = null,
                imageRes = null
            )
        )
    }
}
