package com.example.reefev.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.reefev.entity.folders;

import java.util.List;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM folder")
    List<folders> getAll();

    @Query("SELECT * FROM folder where folder_name LIKE  :folderName")
    folders findByName(String folderName);

    @Query("SELECT COUNT(*) from folder")
    int countFolders();

    @Insert
    void insertAll(folders... folders);

    @Delete
    void delete(folders folder);
}