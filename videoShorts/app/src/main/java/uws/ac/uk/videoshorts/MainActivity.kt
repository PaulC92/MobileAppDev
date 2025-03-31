package uws.ac.uk.videoshorts

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // 👇 Optional: Only use this during development to force logout on every launch
        // auth.signOut()

        if (savedInstanceState == null) {
            // Always load the nav bar
            supportFragmentManager.beginTransaction()
                .replace(R.id.navBarFragmentContainer, NavBarFragment())
                .commit()

            // Show login if user is not authenticated
            val startFragment = if (auth.currentUser != null) {
                VideoBrowserFragment() // Already logged in
            } else {
                LoginFragment() // Needs to login first
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, startFragment)
                .commit()
        }
    }
}