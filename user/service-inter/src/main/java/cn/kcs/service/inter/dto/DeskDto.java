
package cn.kcs.service.inter.dto;

import java.io.Serializable;

/**
 * <!-- begin-user-doc --> T_DESK * <!-- end-user-doc -->
 */
public class DeskDto implements Serializable {

    /**
     * <!-- begin-user-doc --> DESK_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String deskId;

    /**
     * <!-- begin-user-doc --> DESK_NUMBER * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String deskNumber;

    /**
     * <!-- begin-user-doc --> DESK_STATUS * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String deskStatus;

    /**
     * <!-- begin-user-doc --> DESK_SIZE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String deskSize;

    /**
     * <!-- begin-user-doc --> DESK_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getDeskId() {
        return deskId;
    }

    /**
     * <!-- begin-user-doc --> DESK_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    /**
     * <!-- begin-user-doc --> DESK_NUMBER * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getDeskNumber() {
        return deskNumber;
    }

    /**
     * <!-- begin-user-doc --> DESK_NUMBER * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    /**
     * <!-- begin-user-doc --> DESK_STATUS * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getDeskStatus() {
        return deskStatus;
    }

    /**
     * <!-- begin-user-doc --> DESK_STATUS * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setDeskStatus(String deskStatus) {
        this.deskStatus = deskStatus;
    }

    /**
     * <!-- begin-user-doc --> DESK_SIZE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getDeskSize() {
        return deskSize;
    }

    /**
     * <!-- begin-user-doc --> DESK_SIZE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setDeskSize(String deskSize) {
        this.deskSize = deskSize;
    }

}
