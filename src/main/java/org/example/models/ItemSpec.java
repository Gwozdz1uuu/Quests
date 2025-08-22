package org.example.models;

import org.example.interfaces.QuestSpec;

public enum ItemSpec implements QuestSpec {
    DIAMOND,
    WHEAT,
    IRON;

    @Override
    public String getName() {
        return this.name();
    }
}
