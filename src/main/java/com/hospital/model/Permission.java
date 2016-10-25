package com.hospital.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Permissions is the authorization to allow the users for specific actions.
 */
@Entity
@Table(name = "permissions")
@SuppressWarnings("serial")
public class Permission implements Serializable {

    /** Id of the Permission. */
    @Id
    @GeneratedValue
    @Column(name = "permission_id")
    private Integer permissionId;

    /** Action of the Permission. */
    @Column(name = "action", nullable = false)
    private String action;

    /** Action key of the Permission. */
    @Column(name = "action_key", nullable = false)
    private String actionKey;

    /** Description of the Permission. */
    @Column(name = "permission_description")
    private String permissionDescription;

    /** Assign module category. */
    @Column(name = "module")
    private Module module;

    /**
     * Default constructor.
     */
    public Permission() {
        super();
    }

    /**
     * Get the id of the permission.
     *
     * @return the permissionId
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * Set the id of the permission.
     *
     * @param permissionId the id to set
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * Get the action of the permission.
     *
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the action of the permission.
     *
     * @param action action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Get the actionKeyof the permission.
     *
     * @return the actionKey
     */
    public String getActionKey() {
        return actionKey;
    }

    /**
     * Set the actionKey of the permission.
     *
     * @param actionKey the actionKey to set
     */
    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }


    /**
	 * Get the permissionDescription of Permission.
	 *
	 * @return the permissionDescription
	 */
	public String getPermissionDescription() {
		return permissionDescription;
	}

	/**
	 * Set the permissionDescription of Permission.
	 *
	 * @param permissionDescription the permissionDescription to set
	 */
	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	/**
     * Get the module of the permission.
     *
     * @return the module
     */
    public Module getModule() {
        return module;
    }

    /**
     * Set the module of the permission.
     *
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Enumeration module list.
     */
    public enum Module {

        Laboratory,
        Diet,
        Emr
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
