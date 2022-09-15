package com.example.stanislavcavajda.bakalarkasmokingapp.feedback

import androidx.databinding.BaseObservable

class FeedbackViewModel : BaseObservable {

    var title:String
    var hint:String

    constructor(title:String, hint:String){
        this.title = title
        this.hint = hint
    }
}