package com.aufarizazakipradana607062330127.testmagang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.testmagang.R
import com.aufarizazakipradana607062330127.testmagang.navigation.Screen
import com.aufarizazakipradana607062330127.testmagang.data.AuthRepository
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.aufarizazakipradana607062330127.testmagang.ui.theme.TestMagangTheme


@Composable
fun Login(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val hatocaRed = Color(0xFFC73030)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.hatoca_title),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = hatocaRed,
                modifier = Modifier.padding(bottom = 64.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showEmailError = false
                },
                label = { Text(stringResource(id = R.string.email_label))  },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                isError = showEmailError,
                singleLine = true
            )
            if (showEmailError) {
                Text(
                    text = stringResource(id = R.string.email_error_empty_or_invalid),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    showPasswordError = false
                },
                label = { Text(stringResource(id = R.string.password_label)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isError = showPasswordError,
                singleLine = true
            )
            if (showPasswordError) {
                Text(
                    text = stringResource(id = R.string.password_error_empty),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                color = hatocaRed,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {  }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val isEmailValid = email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        val isPasswordValid = password.isNotBlank()

                        showEmailError = !isEmailValid
                        showPasswordError = !isPasswordValid

                        if (isEmailValid && isPasswordValid) {
                            val user = AuthRepository.registeredUsers.find { it.email == email && it.passwordHash == password }
                            if (user != null) {
                                AuthRepository.loggedInUser = user
                                navController.navigate(Screen.Features.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Login Gagal: Email atau password salah.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Harap lengkapi semua isian.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = hatocaRed),
                    modifier = Modifier
                        .weight(0.7f)
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text("Login", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = hatocaRed),
                    modifier = Modifier
                        .weight(0.3f)
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_fingerprint_24),
                        contentDescription = stringResource(id = R.string.fingerprint_login_desc),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.no_account_text), color = Color.Gray)
                Text(
                    text = stringResource(id = R.string.signup_text),
                    color = hatocaRed,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun LoginPreview() {
    TestMagangTheme {
        Login(navController = rememberNavController())
    }
}