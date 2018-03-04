package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
object Constants {

    object viewTypes {
        const val MAIN_PROGRESS_VIEW_TYPE = 0
        const val HEALTH_PROGRESS_VIEW_TYPE = 1
        const val WISHES_MANAGER_VIEW_TYPE = 2
    }

    object timeConst {
        const val twentyMinutes =   1200L
        const val oneHour =         3600L
        const val twoHours =        7200L
        const val twelveHours =     43200L
        const val oneDay =          86400L
        const val twoDays =         172800L
        const val seventyTwoHours = 259200L
        const val eightDays =       691200L
        const val twoWeeks =        1209600L
        const val twentyOneDays =   1814400L
        const val fourWeeks =       2419200L
        const val eightWeeks =      4838400L
        const val treeMonths =      7776000L
        const val nineMonths =      23328000L
        const val oneYear =         31104000L
        const val fiveYears =       155520000L
        const val tenYears =        311040000L
        const val thirteenYears =   404352000L
        const val fifteenYears =    466560000L
        const val twentyYears =     622080000L
    }

    object extras {
        const val EXTRA_TITLE = "title"
        const val EXTRA_DESC = "desc"
        const val EXTRA_DATE = "date"
        const val EXTRA_ITEM_ID = "itemId"
    }

    object preferences {
        const val DATE_PREFERENCES = "date"
    }
}