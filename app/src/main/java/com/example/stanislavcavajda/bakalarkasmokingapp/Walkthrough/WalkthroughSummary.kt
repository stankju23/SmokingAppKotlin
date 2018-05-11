package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

class WalkthroughSummary {

    var title: String
    var cigarettesPerDay: String
    var cigarettesInPacket: String
    var currency: String
    var buttonText: String

    constructor(title: String, cigarettesPerDay: String, cigarettesInPacket:String, currency:String, buttonText:String) {
        this.title = title
        this.cigarettesPerDay = cigarettesPerDay
        this.cigarettesInPacket = cigarettesInPacket
        this.currency = currency
        this.buttonText = buttonText
    }
}