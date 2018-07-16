package app.services;

import app.entities.history.HistoryEntry;
import app.entities.user.User;
import app.entities.history.History;
import app.repositories.HistoryRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * UserService class provides functionality and information about users
 */
public class UserService {
    private HistoryRepository historyRepository;

    /**
     * Constructor for the User Service
     *
     * @param historyRepository The repository for the history
     */
    public UserService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Method used to search for a history by a given user
     *
     * @param user Object of type User to be searched with
     * @return String value representing summed up history for the given user
     */
    public String getHistoryByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null.");
        }

        History history = this.historyRepository.getHistoryByUser(user);
        List<HistoryEntry> entries = new LinkedList<>(history.getHistoryEntries());
        StringBuilder sb = new StringBuilder();
        sb.append("-- User History --").append(System.lineSeparator());
        for (HistoryEntry entry : entries) {
            sb.append(String.format("Book name: %s%nStatus: %s%nFrom Date: %s%n",
                    entry.getBook().getTitle(), entry.getStatus(), entry.getEntryDate())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Method used to provide summed up information about a given user
     *
     * @param user Object of type User to be searched with
     * @return String value representing the information about the given user
     */
    public String getUserInfo(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null.");
        }

        // TODO: use user from repository instead
        return user.toString();
    }
}