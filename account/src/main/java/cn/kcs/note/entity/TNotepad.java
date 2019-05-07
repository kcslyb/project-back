package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TNotepad)实体类
 *
 * @author makejava
 * @since 2018-12-28 14:44:16
 */
public class TNotepad implements Serializable {
    private static final long serialVersionUID = -46214775271099945L;

    private String noteId;

    private String noteCreated;

    private String noteContent;

    private Date noteCreatetime;

    private Date noteModifytime;

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

    public Date getNoteCreatetime() {
        return noteCreatetime;
    }

    public void setNoteCreatetime(Date noteCreatetime) {
        this.noteCreatetime = noteCreatetime;
    }

    public Date getNoteModifytime() {
        return noteModifytime;
    }

    public void setNoteModifytime(Date noteModifytime) {
        this.noteModifytime = noteModifytime;
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