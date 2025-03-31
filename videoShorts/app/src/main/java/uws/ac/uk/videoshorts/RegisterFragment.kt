package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import uws.ac.uk.videoshorts.R

class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val registerButton = view.findViewById<Button>(R.id.btn_register)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return view
    }
}
