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
            itemBinding.set(BR.viewModel, R.layout.walkthrough_money_item)
        }
    }
}