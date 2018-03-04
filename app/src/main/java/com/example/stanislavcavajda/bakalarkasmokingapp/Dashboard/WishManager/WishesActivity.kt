package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityWishesBinding
import kotlinx.android.synthetic.main.activity_wishes.*

class WishesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityWishesBinding = DataBindingUtil.setContentView(this,R.layout.activity_wishes)

        var viewModel = WishListViewModel(Data.wishList,this)

        binding.viewModel = viewModel

        setSupportActionBar(wishes_list_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        supportActionBar?.title = resources.getString(R.string.wishes_manager_title)

        wishes_recycler_view.setHasFixedSize(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
