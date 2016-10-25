package com.hospital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Roles are categorize the departments with different permissions. We restrict the user based on the permission and the
 * permission assigned with Role and give access based on the assigned permissions.
 */
@Entity
@Table(name = "roles")
@SuppressWarnings("serial")
public class Role implements Serializable {

    /** Id of the Role. */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer roleId;

    /** Name of the Role. */
    @Column(name = "role_name", nullable = false)
    private String roleName;

    /** Description of the Role. */
    @Column(name = "role_description")
    private String roleDescription;

    /** Permission list of the role. */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissionList;

    /**
     * Default constructor.
     */
    public Role() {
        super();
    }

    /**
     * Get the id of the role.
     *
     * @return the id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Set the id of the role.
     *
     * @param roleId the id to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    /**
	 * Get the roleName of Role.
	 *
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}


	/**
	 * Set the roleName of Role.
	 *
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	/**
	 * Get the roleDescription of Role.
	 *
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}


	/**
	 * Set the roleDescription of Role.
	 *
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


	/**
     * Get the permissionList of the role.
     *
     * @return the permissionList.
     */
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    /**
     * Set the permissionList of the role.
     *
     * @param permissionList the permissionList to set.
     */
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

}
