package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (Genealogy)实体类
 *
 * @author kcs
 * @since 2019-09-30 10:28:30
 */
public class Genealogy implements Serializable {
    private static final long serialVersionUID = -61429050110090504L;

    private String genealogyId;

    private String genealogyName;

    private String genealogyWord;

    private String genealogyRegion;

    private Integer genealogyNumber;

    private String genealogyProverbs;

    private String genealogyDescription;

    private String genealogyReserved;


    public String getGenealogyId() {
        return genealogyId;
    }

    public void setGenealogyId(String genealogyId) {
        this.genealogyId = genealogyId;
    }

    public String getGenealogyName() {
        return genealogyName;
    }

    public void setGenealogyName(String genealogyName) {
        this.genealogyName = genealogyName;
    }

    public String getGenealogyWord() {
        return genealogyWord;
    }

    public void setGenealogyWord(String genealogyWord) {
        this.genealogyWord = genealogyWord;
    }

    public String getGenealogyRegion() {
        return genealogyRegion;
    }

    public void setGenealogyRegion(String genealogyRegion) {
        this.genealogyRegion = genealogyRegion;
    }

    public Integer getGenealogyNumber() {
        return genealogyNumber;
    }

    public void setGenealogyNumber(Integer genealogyNumber) {
        this.genealogyNumber = genealogyNumber;
    }

    public String getGenealogyProverbs() {
        return genealogyProverbs;
    }

    public void setGenealogyProverbs(String genealogyProverbs) {
        this.genealogyProverbs = genealogyProverbs;
    }

    public String getGenealogyDescription() {
        return genealogyDescription;
    }

    public void setGenealogyDescription(String genealogyDescription) {
        this.genealogyDescription = genealogyDescription;
    }

    public String getGenealogyReserved() {
        return genealogyReserved;
    }

    public void setGenealogyReserved(String genealogyReserved) {
        this.genealogyReserved = genealogyReserved;
    }

}