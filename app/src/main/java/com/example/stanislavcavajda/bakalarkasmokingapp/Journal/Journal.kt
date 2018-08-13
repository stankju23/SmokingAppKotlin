package com.example.stanislavcavajda.bakalarkasmokingapp.Journal

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB

class Journal:BaseObservable {

    var id:String
    var title:ObservableField<String> = ObservableField()
    var desc:ObservableField<String> = ObservableField()
    var timestamp:Long
    var locked:ObservableBoolean = ObservableBoolean()
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
            this.locked.set(false)
        } else {
            this.locked.set(true)
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
        if (this.timestamp < currentTimestamp) {
            this.locked.set(false)
        } else {
            this.locked.set(true)
        }
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