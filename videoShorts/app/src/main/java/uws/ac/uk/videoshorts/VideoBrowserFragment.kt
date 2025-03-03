package uws.ac.uk.videoshorts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoBrowserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_browser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerVideoBrowser)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // List of videos (NEED TO UPDATE WITH OUR OWN VIDEOS)
        val videos = listOf(
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"),
            VideoItem("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
        )

        recyclerView.adapter = VideoBrowserAdapter(videos) { videoItem ->
            // Switch to video view when a thumbnail is clicked
            val videoFragment = VideoViewFragment.newInstance(videoItem.videoUrl)
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainContentFragmentContainer, videoFragment)
                .commit()
        }
    }
}
