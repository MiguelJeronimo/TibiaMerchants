package com.miguel.tibiamerchants

import Jsoup.Scrapper
import com.miguel.tibiamerchants.data.repositories.NPCRepository
import com.miguel.tibiamerchants.data.repositories.NPCRepositoryImp
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import com.miguel.tibiamerchants.presentation.Views.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.presentation.Views.viewmodelproviders.ViewModelNPCFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class Di {
    val appModule = module {
        //Main NPCs.
        single<NPCRepository> {
            val scrapper = Scrapper()
            NPCRepositoryImp(scrapper)
        }
        factory<UseCaseNPC> {
            UseCaseNPC(get())
        }
        single {
            ViewModelNPCFactory(get())
        }
        viewModel { ViewModelNPC(get()) }
    }
}