package com.example.stanislavcavajda.bakalarkasmokingapp.helper

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
object Constants {

    object viewTypes {
        const val MAIN_PROGRESS_VIEW_TYPE = 0
        const val HEALTH_PROGRESS_VIEW_TYPE = 1
        const val WISHES_MANAGER_VIEW_TYPE = 2
        const val MONEY_SAVED_VIEW_TYPE = 3
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
        const val EXTRA_OBJ_ID = "objectiveId"
    }

    object preferences {
        const val DATE_PREFERENCES = "date"
        const val FIRST_TIME = "firstTime"
    }

    object Wish {
        const val TITLE = "wish_title"
        const val PRICE = "wish_price"
        const val IMAGE = "wish_image"
        const val DESC = "wish_desc"
    }

    object actualState {
        const val CIGARETTES_PER_DAY = "cigarettes_per_day"
        const val CIGARETTES_IN_PACKAGE = "cigarettes_in_package"
        const val PACKAGE_PRICE = "package_price"
        const val CURRENCY = "currency"
        const val ACTUAL_DATE = "actual_date"
        const val THEME = "actual_theme"
    }

    object Themes {
        const val pastel = 0
        const val blueOcean = 1
        const val wine = 2
        const val banana = 3
    }

    object Notification{
        const val title = "notificationTitle"
        const val desc = "notificationDesc"
        const val image = "notificationImage"
        const val id = "id"
    }

    object Feedback {
        const val RATE_APP = 0
        const val LIKE = 1
        const val TIPS = 2
        const val APP_CHANGES = 3
        const val SUBMIT = 4
    }
}