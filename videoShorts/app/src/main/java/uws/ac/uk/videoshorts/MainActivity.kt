package uws.ac.uk.videoshorts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Load the nav bar fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.navBarFragmentContainer, NavBarFragment())
                .commit()

            // Load the video browser fragment (Replace with home once built)
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, VideoBrowserFragment())
                .commit()
        }
    }
}
