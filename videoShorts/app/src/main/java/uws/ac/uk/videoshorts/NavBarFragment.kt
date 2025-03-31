package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController



class NavBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create values for the buttons
        val buttonHome = view.findViewById<ImageButton>(R.id.buttonHome)
        val buttonBrowse = view.findViewById<ImageButton>(R.id.buttonBrowse)
        val buttonProfile = view.findViewById<ImageButton>(R.id.buttonProfile)

        /* buttonHome.setOnClickListener {
            // Replace content fragment with a home fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, HomeFragment())
                .commit()
        } */ // NEED TO CREATE A HOME FRAGMENT

        buttonBrowse.setOnClickListener {
            // Replace content fragment with the video browser fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, VideoBrowserFragment())
                .commit()
        }

        /* buttonProfile.setOnClickListener {
            // Replace content fragment with a profile fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, ProfileFragment())
                .commit()
        } */ // NEED TO CREATE A PROFILE FRAGMENT
    }
}