package com.miguel.tibiamerchants.presentation.viewmodelproviders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.tibiamerchants.domain.usecases.UseCaseIItemProfile
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItemProfile

class ViewModelItemProfileFactory(private val useCaseIItemProfile: UseCaseIItemProfile, private val savableStateHandle: SavedStateHandle): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelItemProfile::class.java)) {
            return ViewModelItemProfile(useCaseIItemProfile, savableStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModelItemProfile class")
    }
}