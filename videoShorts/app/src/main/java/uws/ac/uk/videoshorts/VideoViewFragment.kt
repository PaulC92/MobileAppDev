package uws.ac.uk.videoshorts

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoViewFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var resumeButton: ImageButton

    companion object {
        private const val ARG_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String?): VideoViewFragment {
            val fragment = VideoViewFragment()
            val bundle = Bundle()
            bundle.putString(ARG_VIDEO_URL, videoUrl ?: "") // ✅ Prevents null crashes
            fragment.arguments = bundle
            return fragment
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
        resumeButton = view.findViewById(R.id.play_button)

        val videoUrl = arguments?.getString(ARG_VIDEO_URL)

        if (!videoUrl.isNullOrEmpty()) {
            val videoUri = Uri.parse(videoUrl)
            videoView.setVideoURI(videoUri)
            videoView.start()
        } else {
            Toast.makeText(requireContext(), "Error: No video URL found!", Toast.LENGTH_SHORT).show()
        }

        // Handle pause/play
        videoView.setOnClickListener {
            videoView.pause()
            Toast.makeText(requireContext(), "Video paused", Toast.LENGTH_SHORT).show()
            resumeButton.visibility = VISIBLE
        }

        resumeButton.setOnClickListener {
            videoView.start()
            Toast.makeText(requireContext(), "Video resumed", Toast.LENGTH_SHORT).show()
            resumeButton.visibility = INVISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoView.stopPlayback()
    }
}
