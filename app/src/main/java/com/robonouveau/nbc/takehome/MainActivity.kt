package com.robonouveau.nbc.takehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.robonouveau.nbc.takehome.compose.HomepageScreen
import com.robonouveau.nbc.takehome.ui.theme.BottomGradiant
import com.robonouveau.nbc.takehome.ui.theme.NbcTakeHomeTheme
import com.robonouveau.nbc.takehome.ui.theme.TopGradiant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbcTakeHomeTheme {
                val gradient = Brush.verticalGradient(
                    listOf(TopGradiant, BottomGradiant),
                    startY = 0.0f,
                    endY = Float.POSITIVE_INFINITY
                )
                Box(
                    modifier = Modifier.fillMaxSize().background(gradient),
                ) {
                    HomepageScreen()
                }
            }
        }
    }
}
