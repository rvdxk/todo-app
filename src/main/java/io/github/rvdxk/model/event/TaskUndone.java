package io.github.rvdxk.model.event;

import io.github.rvdxk.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
     TaskUndone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
