package app.razar.vincent

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StyleRes
import app.razar.vincent.lifecycle.LifeCycleListener
import kotlin.reflect.KClass

class StylePack(
    val classId: KClass<out Activity>?,
    val theme: List<Style>,
)

class Style(
    @StyleRes val theme: Int,
    val isDark: Boolean = false,
)

//internal variables
internal var currentStylePack: Int = 0
internal val currentStyle: MutableMap<KClass<out Activity>, Int> = mutableMapOf()
private var enableAutoDark: Boolean = false
private var darkStyleIdInternal: Int = -1
private lateinit var availableStylePack: MutableMap<KClass<out Activity>?, StylePack>

/**
 *
 */
fun Application.initVincent(
    vararg styles: StylePack,
    defaultStyle: Int = 0
) {
    if (::availableStylePack.isInitialized)
        throw VincentAlreadyInitialisedException("Already initialised")


    availableStylePack = mutableMapOf<KClass<out Activity>?, StylePack>().apply {
        styles.forEach {
            put(it.classId, it)
        }
    }

    currentStylePack = defaultStyle

    registerActivityLifecycleCallbacks(LifeCycleListener())
}

/**
 *
 */
var Activity.stylePackId: Int
    get() = availableStylePack[this::class]?.theme?.get(0)?.theme ?: -1
    set(value) {
        if (value == -1) {
            //AUTO MODE
            if (isDarkModeSystem) {
                currentStylePack = if (isDarkModeSystem) darkStyleIdInternal else 0
                recreate()
            }
        } else {
            //Set style
            if (currentStylePack == value) return
            currentStylePack = value
            recreate()
        }
    }

/**
 *
 */
fun Activity.enableVincent() {
    if (!::availableStylePack.isInitialized)
        throw VincentNotRegisteredException("Pleas call first initVincent")
    val theme = availableStylePack[this::class]?.theme?.get(currentStylePack)?.theme
        ?: availableStylePack[null]?.theme?.get(currentStylePack)?.theme
        ?: throw VincentNoDefaultTheme("Default theme not found")

    setTheme(theme)
    currentStyle[this::class] = currentStylePack
}

/**
 * @return true if the isDark flag for the style is set to true
 */
val Activity.isDarkMode
    get() = (availableStylePack[this::class]?.theme?.get(currentStylePack)
        ?: availableStylePack[null]?.theme?.get(currentStylePack)
        ?: throw VincentNoDefaultTheme("Default theme not found")).isDark

/**
 * @return true if dark mode currently enable in system
 */
val Context.isDarkModeSystem
    get() = (resources!!.configuration.uiMode
            and Configuration.UI_MODE_NIGHT_MASK) ==
            Configuration.UI_MODE_NIGHT_YES