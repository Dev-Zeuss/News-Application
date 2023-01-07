package com.zeus.smartnews

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import com.zeus.smartnews.databinding.ActivityWebPageBinding

class WebPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebPageBinding

    lateinit var webView: WebView

    lateinit var loadingAnimation: LottieAnimationView

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var noInternetText: TextView
    lateinit var noInternetAnimation: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webPageView
        loadingAnimation = binding.loadingAnimation
        noInternetAnimation = binding.noInternetAnimation
        noInternetText = binding.noInternetText
        swipeRefreshLayout = binding.swipeRefreshView

        //Add this to avoid lottie animation error
        noInternetAnimation.imageAssetsFolder = "images"

        val intent = intent
        val url = intent.getStringExtra("url")

        try {
            if (checkForInternet(this)) {
                webView.loadUrl(url!!)
                val webSettings = webView.settings
                webSettings.allowFileAccess = false
                webSettings.allowFileAccessFromFileURLs = false
                webSettings.allowUniversalAccessFromFileURLs = false
                webSettings.javaScriptEnabled = true //to prevent cross site scripting attacks
                webView.webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        loadingAnimation.visibility =
                            View.VISIBLE //show loading Animation when webpage is not loaded
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        loadingAnimation.visibility =
                            View.GONE // hide loading animation after webpage has loaded
                    }
                }
                noInternetAnimation.visibility = View.GONE
                noInternetText.visibility = View.GONE
                webView.visibility = View.VISIBLE
            } else {
                webView.visibility = View.GONE
                noInternetText.visibility = View.VISIBLE
                noInternetAnimation.visibility =
                    View.VISIBLE //show no Internet Animation when there is no internet connection
                //Create an instance of the snackbar
                val snackBar =
                    Snackbar.make(webView, "Swipe down to refresh the page", Snackbar.LENGTH_LONG)
                snackBar.show()
            }

            swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
                override fun onRefresh() {
                    swipeRefreshLayout.isRefreshing = true
                    Handler().postDelayed({
                        swipeRefreshLayout.isRefreshing = false
                        if (checkForInternet(this@WebPageActivity)) {
                            webView.loadUrl(url!!)
                            val webSettings = webView.settings
                            webSettings.allowFileAccess = false
                            webSettings.allowFileAccessFromFileURLs = false
                            webSettings.allowUniversalAccessFromFileURLs = false
                            webSettings.javaScriptEnabled =
                                false //to prevent cross site scripting attacks
                            webView.webViewClient = object : WebViewClient() {

                                override fun onPageStarted(
                                    view: WebView?,
                                    url: String?,
                                    favicon: Bitmap?
                                ) {
                                    super.onPageStarted(view, url, favicon)
                                    loadingAnimation.visibility =
                                        View.VISIBLE //show loading Animation when webpage is not loaded
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)
                                    loadingAnimation.visibility =
                                        View.GONE // hide loading animation after webpage has loaded
                                }
                            }
                            noInternetAnimation.visibility = View.GONE
                            noInternetText.visibility = View.GONE
                            webView.visibility = View.VISIBLE
                        } else {
                            webView.visibility = View.GONE
                            noInternetText.visibility = View.VISIBLE
                            noInternetAnimation.visibility =
                                View.VISIBLE //show no Internet Animation when there is no internet connection
                            //Create an instance of the snackbar
                            val snackBar = Snackbar.make(
                                webView,
                                "Swipe down to refresh the page",
                                Snackbar.LENGTH_LONG
                            )
                            snackBar.show()
                        }
                    }, 3000)
                }

            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //Check for internet connection in App
    private fun checkForInternet(context: Context): Boolean {
        //Register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //If the android version is equa to 'M' or higher, we need to use the network capabilities to check what type of network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Returns a network object corresponding to the currently active default data network
            val network = connectivityManager.activeNetwork ?: return false

            //Representation of the capabilities of an active network
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                //Indicates this network uses a wi-fi transport or wi-fi has network capabilities
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                //Indicates this network has a cellular network or cellular has network capabilities
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            //If android version is below 'M'

            @Suppress("DEPRECIATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false

            @Suppress("DEPRECIATION")
            return networkInfo.isConnected
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            startActivity(Intent(this, HomeActivity::class.java))
            Animatoo.animateSlideRight(this)
        }
    }
}
