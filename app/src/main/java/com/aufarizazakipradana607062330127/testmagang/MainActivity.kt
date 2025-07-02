package com.aufarizazakipradana607062330127.testmagang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aufarizazakipradana607062330127.testmagang.navigation.SetupNavGraph
import com.aufarizazakipradana607062330127.testmagang.ui.theme.TestMagangTheme

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestMagangTheme {
                SetupNavGraph()
            }
        }
    }
}

