package com.buurbak.api.data.randomizers;

import com.buurbak.api.security.model.Role;
import lombok.AllArgsConstructor;
import org.jeasy.random.api.Randomizer;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserRoleRandomizer implements Randomizer<List<Role>> {
    private Role role;

    @Override
    public List<Role> getRandomValue() {
        return Collections.singletonList(role);
    }
}