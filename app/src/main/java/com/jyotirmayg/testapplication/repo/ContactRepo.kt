package com.jyotirmayg.testapplication.repo

import android.content.ContentResolver
import android.provider.ContactsContract
import com.jyotirmayg.testapplication.ContactData

/**
 * @author jyoti
 * @created on 03-07-2024
 */
class ContactRepo {

    suspend fun fetchContacts(contentResolver: ContentResolver): MutableList<ContactData> {
        val contactsList = mutableListOf<ContactData>()

        val contactsCursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        if (contactsCursor != null && contactsCursor.count > 0) {
            val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            while (contactsCursor.moveToNext()) {
                val id = contactsCursor.getString(idIndex)
                val name = contactsCursor.getString(nameIndex)
                if (name != null) {
                    contactsList.add(ContactData(name, ""))
                }
            }
            contactsCursor.close()
        }

        return contactsList
    }
}