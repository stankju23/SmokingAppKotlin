package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WalkthroughWelcomeViewModel : ViewModel() {
    val moveAction = MutableStateFlow<MoveAction>(MoveAction.StartState)

    fun resetInitState() {
        if (moveAction.value != MoveAction.StartState) {
            moveAction.value = MoveAction.StartState
            Log.d("Reset init state", "called")
        }
    }

    fun goToCigarettesPerDay() {
        moveAction.value = MoveAction.WalkthroughCigarettesPerDay
    }
}

sealed class MoveAction{
    object StartState: MoveAction()
    object WalkthroughCigarettesPerDay : MoveAction()
}
