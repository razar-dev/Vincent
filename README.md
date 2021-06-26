# Vincent

Tiny lightweight Kotlin Android library to change theme at runtime. 

- Very simple to use
- Tiny
- Automatically updates activity themes
- Fast integration
- Support different styles for different activities

## Used By:
<a href ="https://github.com/razar-dev/PowerTube">
  <img width="72" src="https://raw.githubusercontent.com/razar-dev/Vincent/master/other/img/powertube.png">
</a>

## Setting your App

In your Android Application class `onCreate` call the library function to set your style 

```kotlin
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
            ))
        )
```

Then from your Activities simply call `enableVincent` before `setContentView`.

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableVincent()
        setContentView(R.layout.activity_main)
    }
```

For change theme use `stylePackId`
```kotlin
    stylePackId = id
```
Vincent will apply the new theme to all the activities in which enableVincent is called
