package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> map = new HashMap<>();

    private long idCounter;

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        if (timeEntryToCreate.getId() == 0) {
            timeEntryToCreate.setId(++idCounter);
        }
        TimeEntry timeEntry = new TimeEntry(timeEntryToCreate.getId(), timeEntryToCreate.getProjectId(),
                timeEntryToCreate.getUserId(), timeEntryToCreate.getDate(), timeEntryToCreate.getHours());
        map.put(timeEntryToCreate.getId(), timeEntry);
        return timeEntryToCreate;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return map.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(map.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = find(timeEntryId);
        if (timeEntry != null) {
            timeEntry = new TimeEntry(timeEntryId, timeEntryToUpdate.getProjectId(), timeEntryToUpdate.getUserId(),
                    timeEntryToUpdate.getDate(), timeEntryToUpdate.getHours());
            map.put(timeEntryId, timeEntry);
            return timeEntry;
        } else {
            return null;
        }
    }

    @Override
    public void delete(long timeEntryId) {
        if (timeEntryId != 0) {
            map.remove(timeEntryId);
        }
    }

}
