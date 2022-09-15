package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityWishesBinding

class WishesActivity : AppCompatActivity() {

    var binding: ActivityWishesBinding? = null
    var viewModel: WishListViewModel? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }

        ThemeManager.setTheme(this,Data.actualTheme)

        binding = ActivityWishesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.wishesRecycler?.layoutManager =
            LinearLayoutManager(this)
        viewModel = WishListViewModel(Data.wishList,this)

        binding?.viewModel = viewModel

        setSupportActionBar(binding?.wishesListToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = resources.getString(R.string.wishes_manager_title)



        binding?.wishesRecycler?.setHasFixedSize(true)
        binding?.wishesRecycler?.itemAnimator = null



        binding?.wishesRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy<0 && binding?.addWish?.isShown() == true) {
                    binding?.addWish?.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    binding?.addWish?.show()
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

    override fun onResume() {

        val list = Data.wishList.sortedWith(compareBy(Wish::price))
        for (i in 0..Data.wishList.size - 1) {
            Data.wishList[i] = list[i]
        }

        this.viewModel?.updateWishList(Data.wishList)
        super.onResume()
    }

    override fun onRestart() {

        super.onRestart()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                } else {
                    this.finish()
                }
            }
        }
        return
    }
}
