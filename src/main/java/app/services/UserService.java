package app.services;

import app.entities.user.HistoryEntry;
import app.entities.user.User;
import app.entities.user.UserHistory;
import app.repositories.HistoryRepository;

import java.util.LinkedList;
import java.util.List;

public class UserService {
    private HistoryRepository historyRepository;

    public UserService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public String getHistoryByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null.");
        }

        UserHistory userHistory = this.historyRepository.getHistoryByUser(user);
        List<HistoryEntry> entries = new LinkedList<>(userHistory.getHistoryEntries());
        StringBuilder sb = new StringBuilder();
        sb.append("-- User History --").append(System.lineSeparator());
        for (HistoryEntry entry : entries) {
            sb.append(String.format("Book name: %s%nStatus: %s%nFrom Date: %s%n",
                    entry.getBook().getTitle(), entry.getStatus(), entry.getEntryDate())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getUserInfo(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null.");
        }

        return user.toString();
    }
}