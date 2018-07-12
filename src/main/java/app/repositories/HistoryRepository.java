package app.repositories;

import app.entities.user.UserHistory;

import java.util.HashSet;
import java.util.Set;

public class HistoryRepository {
    private Set<UserHistory> historySet;

    public HistoryRepository() {
        this.historySet = new HashSet<>();
    }

    public Set<UserHistory> getHistorySet() {
        return new HashSet<>(historySet);
    }
}
