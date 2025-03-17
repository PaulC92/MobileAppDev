package uws.ac.uk.videoshorts

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class VideoBrowserAdapter(
    private val videoList: List<VideoItem>,
    private val onVideoClick: (VideoItem) -> Unit
) : RecyclerView.Adapter<VideoBrowserAdapter.VideoViewHolder>() {

    // Create a CoroutineScope tied to this adapter
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        // Job to track the coroutine for thumbnail extraction
        var job: Job? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoList[position]

        // Cancel any previous job for this holder (if recycling)
        holder.job?.cancel()

        // Clear any previous image
        holder.thumbnailImageView.setImageDrawable(null)

        // Launch a coroutine to extract the thumbnail.
        holder.job = scope.launch {
            val bitmap = retrieveFrame(videoItem.videoUrl)
            withContext(Dispatchers.Main) {
                // Update the ImageView with the thumbnail if available.
                bitmap?.let {
                    holder.thumbnailImageView.setImageBitmap(it)
                }
            }
        }

        holder.itemView.setOnClickListener {
            onVideoClick(videoItem)
        }
    }

    override fun getItemCount(): Int = videoList.size

    // Cancel any running job if the view is recycled
    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.job?.cancel()
    }

    // Clean up the coroutine scope
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        scope.cancel()
    }

    // Function to retrieve a frame from the video
    private fun retrieveFrame(videoUrl: String): Bitmap? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(videoUrl, HashMap())
            // Extract a frame at 1 second (1,000,000 microseconds)
            retriever.getFrameAtTime(1_000_000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            retriever.release()
        }
    }
}
