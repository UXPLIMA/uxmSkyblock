package net.cengiz1.skyblock.island;

/**
 * Resolves the shared, built-in roles (those defined in roles.yml). An
 * {@link Island} uses it to turn a stored role id into a {@link RoleData},
 * checking its own custom roles first and falling back to these built-ins.
 */
public interface RoleResolver {

    RoleData builtin(String id);

    RoleData owner();

    RoleData visitor();

    RoleData defaultMember();
}
