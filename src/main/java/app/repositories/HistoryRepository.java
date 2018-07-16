package app.repositories;

import app.entities.user.User;
import app.entities.history.History;

import java.util.HashSet;
import java.util.Set;

public class HistoryRepository {
    private Set<History> historySet;

    public HistoryRepository() {
        this.historySet = new HashSet<>();
    }

    public void addUserHistory(History history) {
        if (history == null) {
            throw new IllegalArgumentException("Cannot add null to history repository.");
        }
        this.historySet.add(history);
    }

    public History getHistoryByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot search history for null user.");
        }

        History entry = null;

        for (History history : historySet) {
            if (history.getUser().equals(user)) {
                entry = history;
                break;
            }
        }

        return entry;
    }

    public Set<History> getHistorySet() {
        return this.historySet;
    }
}
