package com.example.jetpackandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ContactColumn(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Имя: ${contact.name}")
            Text("Отчество: ${contact.surname.orEmpty()}")
            Text("Фамилия: ${contact.familyName}")
            Text("Мобильный телефон: ${contact.phone}")
            Text("Адрес: ${contact.address}")
        }
        if (contact.isFavorite) Image(
            modifier = Modifier.padding(start = 16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = android.R.drawable.star_big_on),
            contentDescription = null
        )
    }
}

@Composable
fun Contacts(items: List<Contact>) {
    Column { items.forEach { ContactColumn(it) } }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ListPreview() {
    Contacts(
        listOf(
            Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                isFavorite = true
            ),
            Contact(
                name = "Василий",
                surname = "Егорович",
                familyName = "Кузякин",
                phone = " --- ",
                address = "Ивановская область, дер. Крутово, д. 4"
            ),
            Contact(
                name = "Людмила",
                surname = "Прокофьевна",
                familyName = "Калугина",
                phone = "+7 495 788 78 78",
                address = "Москва, Большая Никитская, д. 43, кв. 290"
            )
        )
    )
}