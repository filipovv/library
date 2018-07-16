package app.repositories;

import app.entities.user.User;
import app.entities.history.History;

import java.util.HashSet;
import java.util.Set;

/**
 * HistoryRepository class serves as a repository for objects of type History in the application.
 */
public class HistoryRepository {
    private Set<History> historySet;

    /**
     * Constructor for the HistoryRepository.
     */
    public HistoryRepository() {
        this.historySet = new HashSet<>();
    }

    /**
     * Method used to add a history entry into the history repository.
     *
     * @param history Object of type History to be added into the repository.
     */
    public void addUserHistory(History history) {
        if (history == null) {
            throw new IllegalArgumentException("Cannot add null to history repository.");
        }
        this.historySet.add(history);
    }

    /**
     * Method used to find a history entry by a user.
     *
     * @param user Object of type User to be searched with
     * @return Object of type History representing the history of the provided user.
     * Returns null if there is no history for the given user.
     */
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
