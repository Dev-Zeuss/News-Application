package com.zeus.smartnews

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.zeus.smartlearner.chatSection.NetworkConnectionLiveData
import com.zeus.smartnews.adapters.CategoryAdapter
import com.zeus.smartnews.adapters.HomeScreenViewPagerAdapter
import com.zeus.smartnews.adapters.TopHeadlinesRecyclerAdapter
import com.zeus.smartnews.api.ApiUtilities
import com.zeus.smartnews.databinding.ActivityHomeBinding
import com.zeus.smartnews.models.ArticleModel
import com.zeus.smartnews.models.CountriesClass
import com.zeus.smartnews.models.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPref: SharedPref
    lateinit var tabLayout: TabLayout
    lateinit var mGeneral: TabLayout.Tab
    lateinit var mBusiness: TabLayout.Tab
    lateinit var mScience: TabLayout.Tab
    lateinit var mHealth: TabLayout.Tab
    lateinit var mSports: TabLayout.Tab
    lateinit var mTech: TabLayout.Tab
    lateinit var mEntertain: TabLayout.Tab
    lateinit var pagerAdapter: HomeScreenViewPagerAdapter
    lateinit var viewPager: ViewPager
    lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var articleModels: ArrayList<ArticleModel>
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var topHeadlinesRecyclerAdapter: TopHeadlinesRecyclerAdapter
    private var selectedCountry = "ng"
    private lateinit var top_headlines_recyclerView: RecyclerView
    private val api = CountriesClass.apiKey
    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeScreenMenuBar = binding.homeScreenMenuBar
        tabLayout = binding.homeScreenTabLayout
        mGeneral = tabLayout.getTabAt(0)!!
        mBusiness = tabLayout.getTabAt(1)!!
        mScience = tabLayout.getTabAt(2)!!
        mHealth = tabLayout.getTabAt(3)!!
        mSports = tabLayout.getTabAt(4)!!
        mTech = tabLayout.getTabAt(5)!!
        mEntertain = tabLayout.getTabAt(6)!!
        viewPager = binding.homeScreenViewPager
        shimmerLayout = binding.topHeadlinesShimmerLayout
        pagerAdapter = HomeScreenViewPagerAdapter(supportFragmentManager, 7)

        articleModels = ArrayList()
        top_headlines_recyclerView = binding.topHeadlinesRecyclerView
        top_headlines_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        top_headlines_recyclerView.setHasFixedSize(true)
        topHeadlinesRecyclerAdapter = TopHeadlinesRecyclerAdapter(this, articleModels)
        top_headlines_recyclerView.adapter = topHeadlinesRecyclerAdapter
        shimmerLayout.startShimmer()

//        val bundle = intent.extras
//        if (bundle != null) {
//            selectedCountry = bundle.getString("SelectedCountry", "ng")
//        }

        loadChosenCountry()

        val networkConnection = NetworkConnectionLiveData(this)
        networkConnection.observe(this, Observer { isConnected ->
            if (isConnected) {
                findNews()
            } else {
                Toast.makeText(this, "No Internet. Please Check your internet connection", Toast.LENGTH_LONG).show()
            }
        })


        viewPager.adapter = pagerAdapter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                if (tab.position == 0 || tab.position == 1 || tab.position == 2 || tab.position == 3 || tab.position == 4 || tab.position == 5 || tab.position == 6) {
                    pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        homeScreenMenuBar.setOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuAboutUs -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    Animatoo.animateSlideLeft(this)
                }
                R.id.menuOptions -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    Animatoo.animateSlideLeft(this)
                }
                R.id.menuShare -> {
                    shareTextOnly()
                }
            }
            true
        }

    }

    private fun shareTextOnly() {
        val sharebody = "Hi i use smart news to read local and international news from more than 50 countries in different categories, you can download it at -: https://apkpure.com/p/com.zeus.smartnews"
        val intent = Intent(Intent.ACTION_SEND)

        //Setting type of data shared as text
        intent.type = "text/plain"

        //Adding the text to share using put extra
        intent.putExtra(Intent.EXTRA_TEXT, sharebody)
        startActivity(Intent.createChooser(intent, "Share Via"))
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

    private fun findNews() {
        ApiUtilities.getApiInterface().getNews(selectedCountry, 100, api)
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    if (response.isSuccessful) {
                        articleModels.addAll(response.body()!!.articles)
                        topHeadlinesRecyclerAdapter.notifyDataSetChanged()
                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                        top_headlines_recyclerView.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {}
            })
    }

    //Set selected country 2 digits ISO code
    fun loadChosenCountry() {
        val sharedPref = getSharedPreferences("ChosenCountry", Context.MODE_PRIVATE)
        var saveSelectedCountry = sharedPref.getString("ChosenCountryKey", "ng")
        selectedCountry = saveSelectedCountry!!
//        Toast.makeText(this, "$selectedCountry is added", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loadChosenCountry()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
                Animatoo.animateSlideRight(this)
                finish()
                finishAffinity()

            } else {
                //Create an instance of the snackbar
                val snackBar = Snackbar.make(binding.root, "Press Back Again to Exit", Snackbar.LENGTH_SHORT)
                snackBar.show()
            }
        }

        backPressed = System.currentTimeMillis()
    }


}