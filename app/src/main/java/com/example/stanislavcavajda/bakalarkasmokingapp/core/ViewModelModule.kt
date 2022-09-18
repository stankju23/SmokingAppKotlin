package com.example.stanislavcavajda.bakalarkasmokingapp.core

import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.WalkthroughCigarettesInPackageViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.WalkthroughCigarettesPerDayScreen
import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.WalkthroughWelcome
import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.WalkthroughWelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WalkthroughWelcomeViewModel()
    }

    viewModel {
        WalkthroughCigarettesInPackageViewModel()
    }
}