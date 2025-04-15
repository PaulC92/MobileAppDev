package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val usernameText = view.findViewById<TextView>(R.id.tv_username)
        val emailText = view.findViewById<TextView>(R.id.tv_email)
        val profileImage = view.findViewById<ImageView>(R.id.img_profile)
        val logoutButton = view.findViewById<Button>(R.id.btn_logout_profile)

        // Set username and email using the currently authenticated user's info
        usernameText.text = user?.displayName ?: "Username"
        emailText.text = user?.email ?: "user@example.com"

        // Use the placeholder image already set in fragment_profile.xml
        // No remote image loading is performed here

        // Handle logout functionality
        logoutButton.setOnClickListener {
            auth.signOut()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, LoginFragment())
                .commit()
        }
    }
}
