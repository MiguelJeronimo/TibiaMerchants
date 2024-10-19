package com.miguel.tibiamerchants

import Jsoup.Scrapper
import com.miguel.tibiamerchants.data.network.retrofit.ApiClient
import com.miguel.tibiamerchants.data.network.retrofit.RetrofitClient
import com.miguel.tibiamerchants.data.repositories.NPCRepository
import com.miguel.tibiamerchants.data.repositories.NPCRepositoryImp
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsCatalog
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsCatalogImp
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsType
import com.miguel.tibiamerchants.data.repositories.RepositoryItemsTypeImp
import com.miguel.tibiamerchants.data.repositories.RepositorySpells
import com.miguel.tibiamerchants.data.repositories.RepositorySpellsImp
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsCatalog
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsType
import com.miguel.tibiamerchants.domain.usecases.UseCaseNPC
import com.miguel.tibiamerchants.domain.usecases.UseCaseSpellList
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelItems
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelNPC
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModelSpells
import com.miguel.tibiamerchants.presentation.ViewModels.ViewModeltemsType
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsFactory
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelItemsTypeFactory
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelNPCFactory
import com.miguel.tibiamerchants.presentation.viewmodelproviders.ViewModelSpellsFactory
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

        //items catalog
        single<RepositoryItemsCatalog>{
            val url = "https://tibia-merchants-api.onrender.com/"
            val retrofit = RetrofitClient().getRetrofit(url).create(ApiClient::class.java)
            RepositoryItemsCatalogImp(retrofit)
        }

        factory<UseCaseItemsCatalog> {
            UseCaseItemsCatalog(get())
        }

        single {
            ViewModelItemsFactory(get())
        }
        viewModel { ViewModelItems(get()) }

        //items type catalog
        single<RepositoryItemsType> {
            val url = "https://tibia-merchants-api.onrender.com/"
            val retrofit = RetrofitClient().getRetrofit(url).create(ApiClient::class.java)
            RepositoryItemsTypeImp(retrofit)
        }
        factory<UseCaseItemsType> {
            UseCaseItemsType(get())
        }
        single {
            ViewModelItemsTypeFactory(get())
        }
        viewModel { ViewModeltemsType(get()) }

        //spells
        single<RepositorySpells> {
            val url = "https://tibia-merchants-api.onrender.com/"
            val retrofit = RetrofitClient().getRetrofit(url).create(ApiClient::class.java)
            RepositorySpellsImp(retrofit)
        }
        factory<UseCaseSpellList> {
            UseCaseSpellList(get())
        }
        single {
            ViewModelSpellsFactory(get())
        }
        viewModel { ViewModelSpells(get()) }
    }
}