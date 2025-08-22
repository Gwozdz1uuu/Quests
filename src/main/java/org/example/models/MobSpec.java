package org.example.models;

import org.example.interfaces.QuestSpec;

public enum MobSpec implements QuestSpec {
    ZOMBIE,
    SKELETON,
    SPIDER;

    @Override
    public String getName() {
        return this.name();
    }
}
