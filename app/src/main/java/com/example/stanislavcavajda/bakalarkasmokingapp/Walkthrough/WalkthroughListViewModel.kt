package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.OnItemBind

class WalkthroughListViewModel: BaseObservable {

    var walkthroughList:ObservableArrayList<Object> = ObservableArrayList()
    var context: Context
    var itemBinding: OnItemBind<Object>

    constructor(walkthroughList:ArrayList<Object>,context: Context) {
        this.walkthroughList.addAll(walkthroughList)
        this.context = context
        this.itemBinding = OnItemBind { itemBinding, position, item ->
            itemBinding.set(BR.viewModel,
                when(position) {
                    0 -> R.layout.walkthrough_welcome_item
                    1 -> R.layout.walkthrough_cigarettes_info_item
                    2 -> R.layout.walkthrough_cigarettes_info_item
                    3 ->R.layout.walkthrough_currency_item
                    4-> R.layout.walkthrough_currency_item
                    else -> R.layout.walkthrough_cigarettes_info_item
                })
        }
    }
}