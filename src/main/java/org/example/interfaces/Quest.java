package org.example.interfaces;

public interface Quest {
    boolean progressKill(QuestSpec questSpec);
    boolean progressCollect(QuestSpec questSpec, int amount);
}
