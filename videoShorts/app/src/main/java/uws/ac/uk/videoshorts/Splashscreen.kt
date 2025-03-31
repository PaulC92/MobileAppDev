package uws.ac.uk.videoshorts

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.imageSplash)

        // Fade in from 0% to 100% opacity over 5 seconds
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 8000
            fillAfter = true
        }

        logo.startAnimation(fadeIn)

        // Move to MainActivity after the animation
        logo.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }
}