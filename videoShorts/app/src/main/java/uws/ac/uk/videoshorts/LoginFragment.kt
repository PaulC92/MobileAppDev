package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val loginButton = view.findViewById<Button>(R.id.btn_login)
        val registerLink = view.findViewById<TextView>(R.id.tv_register)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

                            // Replace with video browser manually
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.mainContentFragmentContainer, VideoBrowserFragment())
                                .commit()
                        } else {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        registerLink.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
