package com.yelp.search.view

import android.content.res.Configuration
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yelp.search.R
import com.yelp.search.base.BaseActivity
import com.yelp.search.databinding.ActivityMainBinding
import com.yelp.search.model.Data
import com.yelp.search.network.response.Status
import com.yelp.search.viewmodels.BusinessViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var viewAdapter: ViewAdapter


    private val viewModel: BusinessViewModel by viewModel()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).root
    }

    override fun getContentLayout(): View = binding
    override fun onCreate() {


        initView()


        initObserver()


    }

    private fun initView() {
        setSupportActionBar(binding.tool_bar)
        binding.recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            viewAdapter = ViewAdapter(arrayListOf())
            binding.recycler_view.adapter = viewAdapter

        }


    }

    private fun initObserver() {
        viewModel.resData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.caption_title.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    it.data?.let { viewAdapter.run { setData(it.data as ArrayList<Data>) } }
                    binding.recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.caption_title.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                    binding.recycler_view.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.caption_title.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                    showToast(it.message, Toast.LENGTH_LONG)
                }
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }




    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }


}


