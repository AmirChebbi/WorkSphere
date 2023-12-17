package com.WorkSphere.WorkSphere.Services.role;


import com.WorkSphere.WorkSphere.DAOs.Role.Role;

public interface RoleService {

    public Role fetchRoleByName(final String roleName);
}
