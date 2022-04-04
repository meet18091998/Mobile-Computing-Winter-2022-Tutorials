package com.mc2022.template

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class DownloadMusic(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    val TAG = "Worker : DownloadMusic"

    override fun doWork(): Result {
        Log.d(TAG, "HERE")
        return if ( downloadSong() )
            Result.success()
        else Result.retry()
    }

    private fun downloadSong() : Boolean {

        Log.d(TAG, "HERE")
        var downloader : InputStream? = null
        var writer : OutputStream? = null
        var connection : HttpURLConnection? = null
        connection?.requestMethod = "GET"


        val songUrl = URL(applicationContext.resources.getString(R.string.song_url))

        try {
            connection = songUrl.openConnection() as HttpURLConnection
            connection.connect()

            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                Log.v(TAG, "Server returned ${connection.responseCode}")
                return false
            }

            downloader = connection.inputStream
            var f = File(applicationContext.filesDir, applicationContext.resources.getString(R.string.downloaded_song_name))
            if (!f.exists()) {
                f.createNewFile()
            }
            writer = FileOutputStream(f)

            var data = ByteArray(1024)
            var copied : Long = 0

            var count : Int = 0
            var filelength = connection.contentLength

            count = downloader.read(data)
            while( count != -1) {
                Log.v(TAG, "Copied- $copied of $filelength")
                copied += count
                writer.write(data, 0, count)
                count = downloader.read(data)
            }

            MainActivity.mediaPlayer = MediaPlayer.create(MainActivity.self, Uri.fromFile(f))
        } catch (e : IOException) {
            e.printStackTrace()
            return false
        } catch (e : Exception) {
            e.printStackTrace()
        } finally {
            writer?.close()
            downloader?.close()
            connection?.disconnect()

            return true
        }
    }
}