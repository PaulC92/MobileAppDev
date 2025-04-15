package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set personalized welcome message using the current user
        val welcomeText = view.findViewById<TextView>(R.id.tv_welcome)
        // val user = FirebaseAuth.getInstance().currentUser
        /* welcomeText.text = if (user != null && user.displayName != null) {
            "Hello, ${user.displayName}!"
        } else {
            "Hello!"
        }*/
        welcomeText.text = "Hello, Username!"

        // Set up video grid
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerHomeVideos)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Use the same video URLs as in the video browser
        val videos = listOf(
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(4).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(3).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(2).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(1).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design.mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(4).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(3).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(2).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design%20(1).mp4"),
            VideoItem("https://clydesidewebdev.co.uk/videoshorts/Untitled%20design.mp4")
        )

        recyclerView.adapter = VideoBrowserAdapter(videos) { videoItem ->
            val videoFragment = VideoViewFragment.newInstance(videoItem.videoUrl)
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, videoFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
