package com.wordpress.agetechnology.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.wordpress.agetechnology.convidados.service.constants.DataBaseConstants
import com.wordpress.agetechnology.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context) {

    /** Start Singleton **/
    // Connection with the database
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    // Cria um método estático, pq se vc tem uma classe que não pode ser instanceada,
    // então como pegar um método que não estático
    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {                   // Checks if the repository was initialized
                repository = GuestRepository(context)
            }
            return repository
        }
    }
    /** End Singleton **/

    /** Methods CRUD: Create, Read, Update and Delete   **/
    // Create
    fun save(guest: GuestModel): Boolean {
        return try {
            // writableDatabase - Para fazer escrita de dados
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true

        } catch (e: Exception) {
            false
        }
    }

    // Read
    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor =
                db.query(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
                )

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)

                    list.add(guest)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }


    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            // Command via SQL
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1",null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)

                    // Add inside the list
                    list.add(guest)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            // Command via SQL
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0",null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =  cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)

                    // Add inside the list
                    list.add(guest)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    // Update
    fun update(guest: GuestModel): Boolean {
        return try {
            // writableDatabase - Para fazer escrita de dados
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true

        } catch (e: Exception) {
            false
        }
    }

    // Delete
    fun delete(id: Int): Boolean {
        return try {
            // writableDatabase - Para fazer escrita de dados
            val db = mGuestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }


    // Get the guest information
    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            // Filter
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            // Verifica se existem dados no cursor
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }

            cursor?.close()

            guest
        } catch (e: Exception) {
            guest
        }
    }
}