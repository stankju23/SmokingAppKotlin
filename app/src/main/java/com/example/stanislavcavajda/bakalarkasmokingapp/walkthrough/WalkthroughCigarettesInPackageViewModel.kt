package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WalkthroughCigarettesInPackageViewModel : ViewModel() {

    val numberOfCigarettesInPackage = MutableStateFlow("")
}