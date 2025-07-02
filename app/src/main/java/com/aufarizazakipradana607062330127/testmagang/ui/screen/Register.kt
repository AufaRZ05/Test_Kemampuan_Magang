package com.aufarizazakipradana607062330127.testmagang.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.aufarizazakipradana607062330127.testmagang.data.AuthRepository
import com.aufarizazakipradana607062330127.testmagang.data.User
import com.aufarizazakipradana607062330127.testmagang.navigation.Screen
import com.aufarizazakipradana607062330127.testmagang.ui.theme.TestMagangTheme

@Composable
fun Register(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var showUsernameError by remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var showConfirmPasswordError by remember { mutableStateOf(false) }

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
                text = stringResource(id = R.string.register_title),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = hatocaRed,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    showUsernameError = false
                },
                label = { Text(stringResource(id = R.string.username_label)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                isError = showUsernameError,
                singleLine = true
            )
            if (showUsernameError) {
                Text(
                    text = stringResource(id = R.string.username_error_empty_or_taken),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showEmailError = false
                },
                label = { Text(stringResource(id = R.string.email_label)) },
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
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, stringResource(id = R.string.password_label))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isError = showPasswordError,
                singleLine = true
            )
            if (showPasswordError) {
                Text(
                    text = stringResource(id = R.string.password_min_length_error),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    showConfirmPasswordError = false
                },
                label = { Text(stringResource(id = R.string.confirm_password_label)) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, stringResource(id = R.string.confirm_password_label))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                isError = showConfirmPasswordError,
                singleLine = true
            )
            if (showConfirmPasswordError) {
                Text(
                    text = stringResource(id = R.string.confirm_password_mismatch_error),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val isUsernameValid = username.isNotBlank()
                    val isEmailValid = email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    val isPasswordValid = password.length >= 6
                    val isConfirmPasswordValid = password == confirmPassword

                    val isEmailAlreadyRegistered = AuthRepository.registeredUsers.any { it.email == email }
                    val isUsernameAlreadyTaken = AuthRepository.registeredUsers.any { it.username == username }

                    showUsernameError = !isUsernameValid || isUsernameAlreadyTaken
                    showEmailError = !isEmailValid || isEmailAlreadyRegistered
                    showPasswordError = !isPasswordValid
                    showConfirmPasswordError = !isConfirmPasswordValid

                    if (isUsernameValid && !isUsernameAlreadyTaken && isEmailValid && !isEmailAlreadyRegistered && isPasswordValid && isConfirmPasswordValid) {
                        AuthRepository.registeredUsers.add(User(username, email, password))
                        Toast.makeText(context, "Akun berhasil dibuat!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Harap perbaiki kesalahan input.", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = hatocaRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(stringResource(id = R.string.register_button), fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.have_account_text), color = Color.Gray)
                Text(
                    text = stringResource(id = R.string.login_text),
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