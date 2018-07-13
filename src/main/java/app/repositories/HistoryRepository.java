package app.repositories;

import app.entities.user.User;
import app.entities.user.UserHistory;

import java.util.HashSet;
import java.util.Set;

public class HistoryRepository {
    private Set<UserHistory> historySet;

    public HistoryRepository() {
        this.historySet = new HashSet<>();
    }

    public void addUserHistory(UserHistory history) {
        if (history == null) {
            throw new IllegalArgumentException("Cannot add null to history repository.");
        }
        this.historySet.add(history);
    }

    public UserHistory getHistoryByUser(User user) {
        UserHistory entry = null;

        for (UserHistory userHistory : historySet) {
            if (userHistory.getUser().equals(user)) {
                entry = userHistory;
                break;
            }
        }
        if (entry == null) {
            throw new IllegalArgumentException("User does not have history yet.");
        }
        return entry;
    }

    public Set<UserHistory> getHistorySet() {
        return this.historySet;
    }
}
