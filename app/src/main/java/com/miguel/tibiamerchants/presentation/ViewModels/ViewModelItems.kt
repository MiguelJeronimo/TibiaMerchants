package com.miguel.tibiamerchants.presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.tibiamerchants.domain.models.ItemsModels
import com.miguel.tibiamerchants.domain.models.PostItemsType
import com.miguel.tibiamerchants.domain.usecases.UseCaseItemsCatalog
import kotlinx.coroutines.launch

class ViewModelItems(private val useCaseItemsCatalog: UseCaseItemsCatalog) : ViewModel() {
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
        viewModelScope.launch {
            _items.value = useCaseItemsCatalog.items()
        }
    }
    fun setProgressBar(state: Boolean){
        _isVisibleProgressBar.value = state
    }
    fun setItems() {

    }
    fun setBack(status:Boolean){
        _isBack.value = status
    }

    fun setPost(post: PostItemsType){
        _post.value = post
    }

}