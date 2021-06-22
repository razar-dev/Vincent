package app.razar.vincent_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.razar.vincent.enableVincent

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableVincent()
        setContentView(R.layout.activity_main2)
    }
}