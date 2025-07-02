package com.aufarizazakipradana607062330127.testmagang.ui.screen

import android.widget.Toast // Import untuk Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext // Import untuk Context (digunakan Toast)
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.testmagang.navigation.Screen
import com.aufarizazakipradana607062330127.testmagang.ui.theme.TestMagangTheme
import com.aufarizazakipradana607062330127.testmagang.data.AuthRepository // Import AuthRepository
import com.aufarizazakipradana607062330127.testmagang.data.User // Import User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController) {
    var username by remember { mutableStateOf("") } // State untuk username
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var showUsernameError by remember { mutableStateOf(false) } // State error username
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var showConfirmPasswordError by remember { mutableStateOf(false) }

    val context = LocalContext.current // Mendapatkan context untuk Toast
    val hatocaRed = Color(0xFFC73030)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { paddingValues ->
        // Menghilangkan warna merah di samping, hanya Column di tengah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp), // Padding konten utama
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "REGISTER",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = hatocaRed,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Input Username
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    showUsernameError = false // Reset error saat ada input
                },
                label = { Text("Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                isError = showUsernameError,
                singleLine = true
            )
            if (showUsernameError) {
                Text(
                    text = "Username tidak boleh kosong.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input Email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showEmailError = false
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                isError = showEmailError,
                singleLine = true
            )
            if (showEmailError) {
                Text(
                    text = "Email tidak boleh kosong atau sudah terdaftar.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    showPasswordError = false
                },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, "Toggle password visibility")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isError = showPasswordError,
                singleLine = true
            )
            if (showPasswordError) {
                Text(
                    text = "Password minimal 6 karakter.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    showConfirmPasswordError = false
                },
                label = { Text("Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, "Toggle confirm password visibility")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isError = showConfirmPasswordError,
                singleLine = true
            )
            if (showConfirmPasswordError) {
                Text(
                    text = "Password tidak cocok.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    // Sanity Check Register (semua field)
                    val isUsernameValid = username.isNotBlank()
                    val isEmailValid = email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    val isPasswordValid = password.length >= 6
                    val isConfirmPasswordValid = password == confirmPassword

                    // Cek apakah email atau username sudah terdaftar (simulasi)
                    val isEmailAlreadyRegistered = AuthRepository.registeredUsers.any { it.email == email }
                    val isUsernameAlreadyTaken = AuthRepository.registeredUsers.any { it.username == username }

                    showUsernameError = !isUsernameValid || isUsernameAlreadyTaken
                    showEmailError = !isEmailValid || isEmailAlreadyRegistered
                    showPasswordError = !isPasswordValid
                    showConfirmPasswordError = !isConfirmPasswordValid

                    // Jika semua validasi sukses
                    if (isUsernameValid && !isUsernameAlreadyTaken && isEmailValid && !isEmailAlreadyRegistered && isPasswordValid && isConfirmPasswordValid) {
                        // Simpan user (simulasi, dalam memori)
                        AuthRepository.registeredUsers.add(User(username, email, password)) // Password TIDAK AMAN, HANYA UNTUK DEMO
                        Toast.makeText(context, "Akun berhasil dibuat!", Toast.LENGTH_SHORT).show() // Tampilkan Toast
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        // Jika ada error, tampilkan Toast umum (atau biarkan error messages individual)
                        Toast.makeText(context, "Harap perbaiki kesalahan input.", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = hatocaRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Already have an account? ", color = Color.Gray)
                Text(
                    text = "Login",
                    color = hatocaRed,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RegisterPreview() {
    TestMagangTheme {
        Register(navController = rememberNavController())
    }
}