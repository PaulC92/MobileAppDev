package uws.ac.uk.videoshorts

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoViewFragment : Fragment() {

    private lateinit var videoView: VideoView

    // bundle video URL and load it into the view
    companion object {
        private const val ARG_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String): VideoViewFragment {
            val fragment = VideoViewFragment()
            val bundle = Bundle()
            bundle.putString(ARG_VIDEO_URL, videoUrl)
            fragment.arguments = bundle
            return fragment // Return a view with the selected video loaded
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView = view.findViewById(R.id.videoView)

        // Retrieve the video URL from arguments.
        val videoUrl = arguments?.getString(ARG_VIDEO_URL)
        if (!videoUrl.isNullOrEmpty()) {
            val videoUri = Uri.parse(videoUrl)
            videoView.setVideoURI(videoUri)
            videoView.start()
        }

        // Set up play/pause
        videoView.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                Toast.makeText(requireContext(), "Video paused", Toast.LENGTH_SHORT).show()
            } else {
                videoView.start()
                Toast.makeText(requireContext(), "Video resumed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        videoView.pause() // Pause the video if the app is in background
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoView.stopPlayback()
    }
}
