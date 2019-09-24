package com.example.reefev.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class folders {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "folder_name")
    private String folderName;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String firstName) {
        this.folderName = folderName;
    }

}