package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class VideoBrowserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_browser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle logout
        val logoutButton = view.findViewById<Button>(R.id.btn_logout)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, LoginFragment())
                .commit()
        }

        // Set up video list
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerVideoBrowser)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

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
