package com.example.stanislavcavajda.bakalarkasmokingapp.journal

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase.RealmDB

class Journal:BaseObservable {

    var id:String
    var title:ObservableField<String> = ObservableField()
    var desc:ObservableField<String> = ObservableField()
    var timestamp:Long
    var locked:Boolean = true
    var written:ObservableBoolean = ObservableBoolean()
    var date:String
    var context:Context
    var dateConverter = DateConverter()

    constructor(id:String, title:String,desc:String,timestamp:Long,context: Context){
        this.id = id
        this.title.set(title)
        this.desc.set(desc)
        this.timestamp = timestamp

        if (this.timestamp < dateConverter.getCurrentTimestamp()) {
            this.locked = false
        } else {
            this.locked = true
        }
        if (title.length == 0 && desc.length == 0) {
            this.written.set(false)
        } else {
            this.written.set(true)
        }

        this.date = dateConverter.getDate(this.timestamp)
        this.context = context
    }

    fun updateJournal(currentTimestamp:Long) {
        this.locked = this.timestamp >= currentTimestamp
    }

    fun updateJournalInfo(title:String,desc:String) {

        if (title.length == 0 || desc.length == 0) {
            this.title.set(title)
            this.desc.set(desc)
            this.written.set(false)
            RealmDB.updateJournal(this)
        } else {
            this.title.set(title)
            this.desc.set(desc)
            this.written.set(true)
            RealmDB.updateJournal(this)
        }
    }
}