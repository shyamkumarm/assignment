package com.yelp.search.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.Duration

abstract class BaseActivity : AppCompatActivity() {


    abstract fun getContentLayout(): View
    abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         onCreate()
        setContentView(getContentLayout())
    }



    protected fun showToast(msg: String?,duration:Int =  Toast.LENGTH_SHORT) {
        Toast.makeText(baseContext, msg, duration).show()
    }
}