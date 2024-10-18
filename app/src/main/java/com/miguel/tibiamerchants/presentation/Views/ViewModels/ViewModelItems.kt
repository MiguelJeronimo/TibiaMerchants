package com.miguel.tibiamerchants.presentation.Views.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.data.repositories.RepositoryItems

class ViewModelItems: ViewModel() {
    private val repository = RepositoryItems()
    private val _items = MutableLiveData<ItemsModels>()
    val items: MutableLiveData<ItemsModels> = _items
    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: MutableLiveData<Boolean> = _isVisibleProgressBar

    private val _isBack = MutableLiveData<Boolean>()
    val isBack: MutableLiveData<Boolean>get() = _isBack

    private val _post = MutableLiveData<PostItemsType>()
    val post: MutableLiveData<PostItemsType> = _post

    init {
        _isVisibleProgressBar.value = true
        setItems()
    }
    fun setProgressBar(state: Boolean){
        _isVisibleProgressBar.value = state
    }
    fun setItems() {
        repository.items(_items)
    }
    fun setBack(status:Boolean){
        _isBack.value = status
    }

    fun setPost(post: PostItemsType){
        _post.value = post
    }

}