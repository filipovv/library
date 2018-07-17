package app.repositories;

import org.junit.Test;

public class HistoryRepositoryTests {
    @Test(expected = IllegalArgumentException.class)
    public void testIfAddingNullAsHistoryThrowsAnException() {
        HistoryRepository historyRepository = new HistoryRepository();
        historyRepository.addUserHistory(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsUserThrowsAnExceptionInGetHistoryByUser() {
        HistoryRepository historyRepository = new HistoryRepository();
        historyRepository.getHistoryByUser(null);
    }
}
