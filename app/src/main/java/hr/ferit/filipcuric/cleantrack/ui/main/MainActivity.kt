package hr.ferit.filipcuric.cleantrack.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import hr.ferit.filipcuric.cleantrack.ui.theme.CleanTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            CleanTrackTheme {
                    MainScreen()
            }
        }
    }
}
