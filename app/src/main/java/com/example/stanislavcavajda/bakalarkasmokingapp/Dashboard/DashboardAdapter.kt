package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MoneySaved.MoneySavedViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.HealthprogressDashboardItemNewBinding
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.MainProgressBinding
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.MoneySavedDashboardItemBinding
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.WishManagerDashboardItemBinding

class DashboardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var list:ArrayList<Object>
    var context:Context

    var layoutInflater: LayoutInflater? = null

    constructor(list:ArrayList<Object>,context:Context) {
        this.list = ArrayList()
        this.list.addAll(list)
        this.context = context
    }

    override fun getItemViewType(position: Int): Int {

        when(position) {
            Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE -> return R.layout.main_progress
            Constants.viewTypes.HEALTH_PROGRESS_VIEW_TYPE -> return R.layout.healthprogress_dashboard_item_new
            Constants.viewTypes.WISHES_MANAGER_VIEW_TYPE ->return R.layout.wish_manager_dashboard_item
            Constants.viewTypes.MONEY_SAVED_VIEW_TYPE ->return R.layout.money_saved_dashboard_item
            else ->return R.layout.wish_manager_dashboard_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(parent.context)
        }

        when(viewType) {
            R.layout.main_progress -> {
                var mainProgressBinding = DataBindingUtil.inflate<MainProgressBinding>(this.layoutInflater!!,R.layout.main_progress,parent,false)
                return MainProgressViewHolder(mainProgressBinding)
            }
            R.layout.healthprogress_dashboard_item_new -> {
                var healthProgressBinding = DataBindingUtil.inflate<HealthprogressDashboardItemNewBinding>(this.layoutInflater!!,R.layout.healthprogress_dashboard_item_new,parent,false)
                return HealthProgressViewHolder(healthProgressBinding)
            }
            R.layout.wish_manager_dashboard_item -> {
                var wishBinding = DataBindingUtil.inflate<WishManagerDashboardItemBinding>(this.layoutInflater!!,R.layout.wish_manager_dashboard_item,parent,false)
                return WishViewHolder(wishBinding)
            }
            R.layout.money_saved_dashboard_item -> {
                var moneySavedBinding = DataBindingUtil.inflate<MoneySavedDashboardItemBinding>(this.layoutInflater!!,R.layout.money_saved_dashboard_item,parent,false)
                return MoneyViewHolder(moneySavedBinding)
            }
            else -> {
                var mainProgressBinding = DataBindingUtil.inflate<MainProgressBinding>(this.layoutInflater!!,R.layout.main_progress,parent,false)
                return MainProgressViewHolder(mainProgressBinding)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainProgressViewHolder) {
            holder.mainProgressBinding.viewModel = this.list[0] as MainProgressViewModel
        }
        if (holder is HealthProgressViewHolder) {
            holder.healthProgressBinding.viewModel = this.list[1] as HealthProgressListViewModel
        }
        if (holder is WishViewHolder) {
            holder.wishBinding.viewModel = this.list[2] as WishListViewModel
        }
        if (holder is MoneyViewHolder) {
            holder.moneySavedBinding.viewModel = this.list[3] as MoneySavedViewModel
        }

    }


    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder != null) {
            if (holder is MainProgressViewHolder) {
                holder.mainProgressBinding.viewModel = null
            }
            if (holder is HealthProgressViewHolder) {
                holder.healthProgressBinding.viewModel = null
            }
            if (holder is WishViewHolder) {
                holder.wishBinding.viewModel = null
            }
            if (holder is MoneyViewHolder) {
                holder.moneySavedBinding.viewModel = null
            }
        }

        super.onViewRecycled(holder)
    }




    inner class MainProgressViewHolder : RecyclerView.ViewHolder {

        var mainProgressBinding: MainProgressBinding

        constructor(mpBinding:MainProgressBinding):super(mpBinding.root) {
            this.mainProgressBinding = mpBinding
        }
    }

    inner class HealthProgressViewHolder : RecyclerView.ViewHolder {

        var healthProgressBinding: HealthprogressDashboardItemNewBinding

        constructor(healthProgressBinding:HealthprogressDashboardItemNewBinding):super(healthProgressBinding.root) {
            this.healthProgressBinding = healthProgressBinding
        }
    }

    inner class WishViewHolder : RecyclerView.ViewHolder {

        var wishBinding: WishManagerDashboardItemBinding

        constructor(wishBinding:WishManagerDashboardItemBinding):super(wishBinding.root) {
            this.wishBinding = wishBinding
        }
    }

    inner class MoneyViewHolder : RecyclerView.ViewHolder {

        var moneySavedBinding: MoneySavedDashboardItemBinding

        constructor(moneySavedBinding:MoneySavedDashboardItemBinding):super(moneySavedBinding.root) {
            this.moneySavedBinding = moneySavedBinding
        }
    }


}