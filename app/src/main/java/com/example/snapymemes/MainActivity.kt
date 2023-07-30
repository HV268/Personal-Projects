package com.example.snapymemes

import android.content.Intent
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import androidx.core.view.isVisible as viewIsVisible




class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()

    }

    private fun loadMeme(){
        val circularProgressBar=findViewById<ProgressBar>(R.id.mprogressbar)
//        circularProgressBar.apply {
           circularProgressBar.visibility= View.VISIBLE
//        }

        val memeImage=findViewById<ImageView>(R.id.memeImage)
// Instantiate the RequestQueue.

       // val queue = Volley.newRequestQueue(this)
        val url = "   https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
             currentImageUrl=response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>
                {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        circularProgressBar.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        circularProgressBar.visibility= View.GONE
                        return false
                    }
                }).into(memeImage)

            },
            Response.ErrorListener {

            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun nextmeme(view: View) {
        loadMeme()
    }
    fun sharememe(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type= "text/plain"
       intent.putExtra(Intent.EXTRA_TEXT, "Hey check out this cool meme by Snapymemes $currentImageUrl")
        val chooser= Intent.createChooser(intent, " share this meme using...")
        startActivity(chooser)
    }
}


