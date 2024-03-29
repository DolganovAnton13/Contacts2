package com.antondolganov.contacts2.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.antondolganov.contacts2.data.model.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: List<Contact>)

    @Update
    fun update(contactEntity: Contact)

    @Query("SELECT * FROM Contact")
    fun getAllContacts(): DataSource.Factory<Int, Contact>

    @Query("DELETE FROM Contact")
    fun deleteAll()

    @Query("SELECT * FROM Contact WHERE id = :id")
    fun getContactById(id: String): LiveData<Contact>

    @Query("SELECT * FROM Contact WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun getContactsByName(query: String): DataSource.Factory<Int, Contact>

    @Query("SELECT * FROM Contact WHERE clearPhone LIKE '%' || :query || '%' ORDER BY name ASC")
    fun getContactsByPhone(query: String): DataSource.Factory<Int, Contact>
}