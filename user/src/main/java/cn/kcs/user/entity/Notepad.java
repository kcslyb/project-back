package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TNotepad)实体类
 *
 * @author makejava
 * @since 2018-12-28 14:44:16
 */
public class Notepad implements Serializable {
    private static final long serialVersionUID = -46214775271099945L;

    private String noteId;

    private String noteCreated;

    private String noteContent;

    private Date noteCreateTime;

    private Date noteModifyTime;

    private String noteTitle;

    private String noteType;


    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteCreated() {
        return noteCreated;
    }

    public void setNoteCreated(String noteCreated) {
        this.noteCreated = noteCreated;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getNoteCreateTime() {
        return noteCreateTime;
    }

    public void setNoteCreateTime(Date noteCreateTime) {
        this.noteCreateTime = noteCreateTime;
    }

    public Date getNoteModifyTime() {
        return noteModifyTime;
    }

    public void setNoteModifyTime(Date noteModifyTime) {
        this.noteModifyTime = noteModifyTime;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

}