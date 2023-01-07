package com.zeus.smartnews

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar

class FeedbackActivity : AppCompatActivity() {

    lateinit var myEmail: EditText
    lateinit var emailSubject: EditText
    lateinit var emailMessage: EditText
    lateinit var sendFeedbackBtn: Button
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)


        myEmail = findViewById(R.id.myEmail)
        emailSubject = findViewById(R.id.emailSubject)
        emailMessage = findViewById(R.id.emailMessage)
        sendFeedbackBtn = findViewById(R.id.sendFeedbackBtn)

        val toolBar: Toolbar = findViewById(R.id.toolBarFeedback)
        setSupportActionBar(toolBar)

        val logoView = toolBar.getChildAt(1)
        logoView.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
            Animatoo.animateSlideRight(this)
        }

        //code to direct users to email app to send email to me on contact button click
        if (checkForInternet(this)) {
            try {
                sendFeedbackBtn.setOnClickListener {
                    val animation = AnimationUtils.loadAnimation(this, R.anim.click)
                    sendFeedbackBtn.startAnimation(animation)

                    if (TextUtils.isEmpty(emailSubject.text.toString())) {
                        emailSubject.error = "Please enter Subject"
                        return@setOnClickListener

                    } else if (TextUtils.isEmpty(emailMessage.text.toString())) {
                        emailMessage.error = "Please enter Message"
                        return@setOnClickListener
                    }

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.data = Uri.parse("Email")
                    val myEmail = "ztech890@gmail.com"
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(myEmail))
                    intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject.text.toString())
                    intent.putExtra(Intent.EXTRA_TEXT, emailMessage.text.toString())
                    intent.type = "message/rfc822"
                    try {
                        val chooser = Intent.createChooser(intent, "Send Email")
                        startActivity(chooser)
                    } catch (e: Exception) {
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            sendFeedbackBtn.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(this, R.anim.click)
                sendFeedbackBtn.startAnimation(animation)

                //Create an instance of the snackbar
                val snackBar = Snackbar.make(it, "No Internet Connection", Snackbar.LENGTH_LONG)
                snackBar.setAction("DISMISS", View.OnClickListener {
                    snackBar.dismiss()
                })
                snackBar.show()

            }
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
        super.onBackPressed()
        startActivity(Intent(this, SettingsActivity::class.java))
        finish()
        Animatoo.animateSlideRight(this)
    }
}