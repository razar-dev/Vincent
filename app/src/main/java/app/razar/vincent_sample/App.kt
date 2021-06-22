package app.razar.vincent_sample

import android.app.Application
import app.razar.vincent.Style
import app.razar.vincent.StylePack
import app.razar.vincent.initVincent

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val setting = getSharedPreferences("app.razar.vincent_sample_preferences", MODE_PRIVATE)
        initVincent(
            //Default style pack (MainActivity, SettingsActivity)
            StylePack(null, listOf(
                Style(R.style.Theme_VincentLight),
                Style(R.style.Theme_VincentDark, true)
            )),
            //Override style pack for MainActivity2
            StylePack(MainActivity2::class, listOf(
                Style(R.style.Theme_Vincent2Light),
                Style(R.style.Theme_Vincent2Dark, true)
            )),
            defaultStyle = setting.getString("style", "0")?.toInt() ?: 0
        )
    }
}