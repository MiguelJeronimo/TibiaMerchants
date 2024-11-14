package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseIItemProfile
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItemProfile

class ViewModelItemProfileFactory(private val useCaseIItemProfile: UseCaseIItemProfile): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelItemProfile::class.java)) {
            return ViewModelItemProfile(useCaseIItemProfile) as T
        }
        throw IllegalArgumentException("Unknown ViewModelItemProfile class")
    }
}