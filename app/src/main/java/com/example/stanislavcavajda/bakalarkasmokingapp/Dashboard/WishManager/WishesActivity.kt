package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityWishesBinding
import kotlinx.android.synthetic.main.activity_wishes.*

class WishesActivity : AppCompatActivity() {

    var binding: ActivityWishesBinding? = null
    var viewModel: WishListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_wishes)

        viewModel = WishListViewModel(Data.wishList,this)

        binding?.viewModel = viewModel

        setSupportActionBar(wishes_list_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        supportActionBar?.title = resources.getString(R.string.wishes_manager_title)


        wishes_recycler.setHasFixedSize(true)
        wishes_recycler.itemAnimator = null



        wishes_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0 || dy<0 && add_wish.isShown()) {
                    add_wish.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    add_wish.show()
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        })


        var dividerDecoration = DividerItemDecoration(this,LinearLayoutManager.VERTICAL)
        dividerDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
        wishes_recycler.addItemDecoration(dividerDecoration)
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
}
