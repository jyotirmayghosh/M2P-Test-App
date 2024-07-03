package com.jyotirmayg.testapplication.viewModel

import android.content.ContentResolver
import androidx.lifecycle.*
import com.jyotirmayg.testapplication.ContactData
import com.jyotirmayg.testapplication.repo.ContactRepo
import kotlinx.coroutines.launch

/**
 * @author jyoti
 * @created on 03-07-2024
 */
class ContactViewModel: ViewModel() {

    private val _mLiveData: MutableLiveData<List<ContactData>> = MutableLiveData()
    val liveData: LiveData<List<ContactData>> = _mLiveData
    fun getContactDetails(contentResolver: ContentResolver) {
        val repo = ContactRepo()
        viewModelScope.launch {
            val result = repo.fetchContacts(contentResolver)
            _mLiveData.postValue(result)
        }
    }
}