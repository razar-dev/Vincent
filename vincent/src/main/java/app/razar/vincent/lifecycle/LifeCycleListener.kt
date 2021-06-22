package app.razar.vincent.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import app.razar.vincent.currentStyle
import app.razar.vincent.currentStylePack

internal class LifeCycleListener : Application.ActivityLifecycleCallbacks{
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
        val style = currentStyle[activity::class] ?: return //Disable
        if (currentStylePack != style) activity.recreate()
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}