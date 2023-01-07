package com.zeus.smartnews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.zeus.smartnews.databinding.ActivitySettingsBinding
import com.zeus.smartnews.models.CountriesClass

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding
    lateinit var sharedPref: SharedPref
    lateinit var darkLightModeSwitch: SwitchCompat
    
    lateinit var changeCountryText: TextView                    
    lateinit var countryText: TextView
    lateinit var developerInfoText: TextView
    lateinit var feedbackText: TextView
    lateinit var privacyPolicyText: TextView
    lateinit var termsConditionText: TextView

    private var selectedCountry = "ng"

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        darkLightModeSwitch = binding.darkLightModeSwitch
        changeCountryText = binding.changeCountryText
        countryText = binding.countryText
        developerInfoText = binding.developerInfoText
        privacyPolicyText = binding.privacyPolicyText
        feedbackText = binding.feedBackText
        termsConditionText = binding.termsConditionsText

        val backBtn = binding.toolBarSettings
        setSupportActionBar(backBtn)

        if (sharedPref.loadNightModeState()) {
            darkLightModeSwitch.isChecked = true
        }

        darkLightModeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedPref.setNightModeState(true)
                restartApp()
            } else {
                sharedPref.setNightModeState(false)
                restartApp()
            }
        }

        changeCountryText.setOnClickListener {
            val popUp = PopupMenu(this, changeCountryText)
            popUp.menuInflater.inflate(R.menu.popup_menu, popUp.menu)
            popUp.setOnMenuItemClickListener { myItem ->
                val item = myItem.itemId

                when(item) {
                    R.id.menuIndia -> {
                        selectedCountry = CountriesClass.india
                        countryText.text = "India"
                        saveChosenCountry()
                        Toast.makeText(this, "India Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuUK -> {
                        selectedCountry = CountriesClass.UK
                        countryText.text = "UK"
                        saveChosenCountry()
                        Toast.makeText(this, "UK Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuNigeria -> {
                        selectedCountry = CountriesClass.nigeria
                        countryText.text = "Nigeria"
                        saveChosenCountry()
                        Toast.makeText(this, "Nigeria Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuUAE -> {
                        selectedCountry = CountriesClass.UAE
                        countryText.text = "UAE"
                        saveChosenCountry()
                        Toast.makeText(this, "UAE Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuArgentina -> {
                        selectedCountry = CountriesClass.argentina
                        countryText.text = "Argentina"
                        saveChosenCountry()
                        Toast.makeText(this, "Argentina Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuAustria -> {
                        selectedCountry = CountriesClass.austria
                        countryText.text = "Austria"
                        saveChosenCountry()
                        Toast.makeText(this, "Austria Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuAustralia -> {
                        selectedCountry = CountriesClass.australia
                        countryText.text = "Australia"
                        saveChosenCountry()
                        Toast.makeText(this, "Australia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuBelgium -> {
                        selectedCountry = CountriesClass.belgium
                        countryText.text = "Belgium"
                        saveChosenCountry()
                        Toast.makeText(this, "Belgium Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuBulgaria -> {
                        selectedCountry = CountriesClass.bulgaria
                        countryText.text = "Bulgaria"
                        saveChosenCountry()
                        Toast.makeText(this, "Bulgaria Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuBrazil -> {
                        selectedCountry = CountriesClass.brazil
                        countryText.text = "Brazil"
                        saveChosenCountry()
                        Toast.makeText(this, "Brazil Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuCanada -> {
                        selectedCountry = CountriesClass.canada
                        countryText.text = "Canada"
                        saveChosenCountry()
                        Toast.makeText(this, "Canada Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSwitzerland -> {
                        selectedCountry = CountriesClass.switzerland
                        countryText.text = "Switzerland"
                        saveChosenCountry()
                        Toast.makeText(this, "Switzerland Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuChina -> {
                        selectedCountry = CountriesClass.china
                        countryText.text = "China"
                        saveChosenCountry()
                        Toast.makeText(this, "China Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuColombia -> {
                        selectedCountry = CountriesClass.colombia
                        countryText.text = "Colombia"
                        saveChosenCountry()
                        Toast.makeText(this, "Colombia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuCuba -> {
                        selectedCountry = CountriesClass.cuba
                        countryText.text = "Cuba"
                        saveChosenCountry()
                        Toast.makeText(this, "Cuba Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuCzechia -> {
                        selectedCountry = CountriesClass.czechia
                        countryText.text = "Czechia"
                        saveChosenCountry()
                        Toast.makeText(this, "Czechia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuGermany -> {
                        selectedCountry = CountriesClass.germany
                        countryText.text = "Germany"
                        saveChosenCountry()
                        Toast.makeText(this, "Germany Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuEgypt -> {
                        selectedCountry = CountriesClass.egypt
                        countryText.text = "Egypt"
                        saveChosenCountry()
                        Toast.makeText(this, "Egypt Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuFrance -> {
                        selectedCountry = CountriesClass.france
                        countryText.text = "France"
                        saveChosenCountry()
                        Toast.makeText(this, "France Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuGreece -> {
                        selectedCountry = CountriesClass.greece
                        countryText.text = "Greece"
                        saveChosenCountry()
                        Toast.makeText(this, "Greece Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuHongKong -> {
                        selectedCountry = CountriesClass.hongKong
                        countryText.text = "Hong Kong"
                        saveChosenCountry()
                        Toast.makeText(this, "HongKong Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuHungary -> {
                        selectedCountry = CountriesClass.hungary
                        countryText.text = "Hungary"
                        saveChosenCountry()
                        Toast.makeText(this, "Hungary Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuIndonesia -> {
                        selectedCountry = CountriesClass.indonesia
                        countryText.text = "Indonesia"
                        saveChosenCountry()
                        Toast.makeText(this, "Indonesia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuIreland -> {
                        selectedCountry = CountriesClass.ireland
                        countryText.text = "Ireland"
                        saveChosenCountry()
                        Toast.makeText(this, "Ireland Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuIsrael -> {
                        selectedCountry = CountriesClass.israel
                        countryText.text = "India"
                        saveChosenCountry()
                        Toast.makeText(this, "India Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuItaly -> {
                        selectedCountry = CountriesClass.italy
                        countryText.text = "Italy"
                        saveChosenCountry()
                        Toast.makeText(this, "Italy Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuJapan -> {
                        selectedCountry = CountriesClass.japan
                        countryText.text = "Japan"
                        saveChosenCountry()
                        Toast.makeText(this, "Japan Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuKorea -> {
                        selectedCountry = CountriesClass.korea
                        countryText.text = "Korea"
                        saveChosenCountry()
                        Toast.makeText(this, "Korea Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuLithuania -> {
                        selectedCountry = CountriesClass.lithuania
                        countryText.text = "Lithuania"
                        saveChosenCountry()
                        Toast.makeText(this, "Lithuania Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuLatvia -> {
                        selectedCountry = CountriesClass.latvia
                        countryText.text = "Latvia"
                        saveChosenCountry()
                        Toast.makeText(this, "Latvia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuMorocco -> {
                        selectedCountry = CountriesClass.morocco
                        countryText.text = "Morocco"
                        saveChosenCountry()
                        Toast.makeText(this, "Morocco Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuMexico -> {
                        selectedCountry = CountriesClass.mexico
                        countryText.text = "Mexico"
                        saveChosenCountry()
                        Toast.makeText(this, "Mexico Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuMalaysia -> {
                        selectedCountry = CountriesClass.malaysia
                        countryText.text = "Malaysia"
                        saveChosenCountry()
                        Toast.makeText(this, "Malaysia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuNetherlands -> {
                        selectedCountry = CountriesClass.netherlands
                        countryText.text = "Netherlands"
                        saveChosenCountry()
                        Toast.makeText(this, "Netherlands Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuNorway -> {
                        selectedCountry = CountriesClass.norway
                        countryText.text = "Norway"
                        saveChosenCountry()
                        Toast.makeText(this, "Norway Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuNewZealand -> {
                        selectedCountry = CountriesClass.newZealand
                        countryText.text = "New Zealand"
                        saveChosenCountry()
                        Toast.makeText(this, "NewZealand Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuPhilippines -> {
                        selectedCountry = CountriesClass.philippines
                        countryText.text = "Philippines"
                        saveChosenCountry()
                        Toast.makeText(this, "Philippines Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuPoland -> {
                        selectedCountry = CountriesClass.poland
                        countryText.text = "Poland"
                        saveChosenCountry()
                        Toast.makeText(this, "Poland Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuPortugal -> {
                        selectedCountry = CountriesClass.portugal
                        countryText.text = "Portugal"
                        saveChosenCountry()
                        Toast.makeText(this, "Portugal Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuRomania -> {
                        selectedCountry = CountriesClass.romania
                        countryText.text = "Romania"
                        saveChosenCountry()
                        Toast.makeText(this, "Romania Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSerbia -> {
                        selectedCountry = CountriesClass.serbia
                        countryText.text = "Serbia"
                        saveChosenCountry()
                        Toast.makeText(this, "Serbia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuRussia -> {
                        selectedCountry = CountriesClass.russia
                        countryText.text = "Russia"
                        saveChosenCountry()
                        Toast.makeText(this, "Russia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSaudiArabia -> {
                        selectedCountry = CountriesClass.saudiArabia
                        countryText.text = "Saudi Arabia"
                        saveChosenCountry()
                        Toast.makeText(this, "SaudiArabia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSweden -> {
                        selectedCountry = CountriesClass.sweden
                        countryText.text = "Sweden"
                        saveChosenCountry()
                        Toast.makeText(this, "Sweden Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSingapore -> {
                        selectedCountry = CountriesClass.singapore
                        countryText.text = "Singapore"
                        saveChosenCountry()
                        Toast.makeText(this, "Singapore Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSlovenia -> {
                        selectedCountry = CountriesClass.slovenia
                        countryText.text = "Slovenia"
                        saveChosenCountry()
                        Toast.makeText(this, "Slovenia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSlovakia -> {
                        selectedCountry = CountriesClass.slovakia
                        countryText.text = "Slovakia"
                        saveChosenCountry()
                        Toast.makeText(this, "Slovakia Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuThailand -> {
                        selectedCountry = CountriesClass.thailand
                        countryText.text = "Thailand"
                        saveChosenCountry()
                        Toast.makeText(this, "Thailand Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuTurkey -> {
                        selectedCountry = CountriesClass.turkey
                        countryText.text = "Turkey"
                        saveChosenCountry()
                        Toast.makeText(this, "Turkey Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuTaiwan -> {
                        selectedCountry = CountriesClass.taiwan
                        countryText.text = "Taiwan"
                        saveChosenCountry()
                        Toast.makeText(this, "Taiwan Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuUkraine -> {
                        selectedCountry = CountriesClass.ukraine
                        countryText.text = "Ukraine"
                        saveChosenCountry()
                        Toast.makeText(this, "Ukraine Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuUS -> {
                        selectedCountry = CountriesClass.US
                        countryText.text = "US"
                        saveChosenCountry()
                        Toast.makeText(this, "US Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuVenezuela -> {
                        selectedCountry = CountriesClass.venezuela
                        countryText.text = "Venezuela"
                        saveChosenCountry()
                        Toast.makeText(this, "Venezuela Selected", Toast.LENGTH_SHORT).show()
                    }

                    R.id.menuSouthAfrica -> {
                        selectedCountry = CountriesClass.southAfrica
                        countryText.text = "South Africa"
                        saveChosenCountry()
                        Toast.makeText(this, "SouthAfrica Selected", Toast.LENGTH_SHORT).show()
                    }
                }

                true
            }

            popUp.show()

        }

        countryText.isSelected = true

        val toolbarBtn = backBtn.getChildAt(1)
        toolbarBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            Animatoo.animateSlideRight(this)
        }

        privacyPolicyText.setOnClickListener {
            val url = "https://www.freeprivacypolicy.com/live/82675d9f-c09a-4265-9696-5e05d2850108"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(Intent.createChooser(intent, "Open with"))
        }

        //Open terms and condition link in browser , but first ask user to choose the browser of his or her choice
        termsConditionText.setOnClickListener {
            val url = "https://www.freeprivacypolicy.com/live/c97ab881-23fd-4758-b143-e2f22d7b9e39"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(Intent.createChooser(intent, "Open with"))
        }

        developerInfoText.setOnClickListener {
            startActivity(Intent(this, DeveloperInfoActivity::class.java))
            finish()
            Animatoo.animateSlideLeft(this)
        }

        feedbackText.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
            Animatoo.animateSlideLeft(this)
            finish()
        }

    }

    //Save country selected from popup menu
    fun saveSelectedCountry() {
        val sharedPref = getSharedPreferences("SelectedCountry", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("SelectedCountryKey", countryText.text.toString())
        }.apply()
    }

    //Save selected country ISO 2-digit code
    fun saveChosenCountry() {
        val sharedPref = getSharedPreferences("ChosenCountry", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("ChosenCountryKey", selectedCountry)
        }.apply()

    }

    //Set country selected from popup menu to textview
    fun loadSelectedCountry() {
        val sharedPref = getSharedPreferences("SelectedCountry", Context.MODE_PRIVATE)
        val saveSelectedCountry = sharedPref.getString("SelectedCountryKey", "Nigeria")
        countryText.text = saveSelectedCountry
    }

    override fun onPause() {
        super.onPause()
        saveSelectedCountry()
    }

    override fun onResume() {
        super.onResume()
        loadSelectedCountry()
    }

    private fun restartApp() {
        startActivity(Intent(this, SettingsActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        Animatoo.animateSlideRight(this)
        finish()
    }

}