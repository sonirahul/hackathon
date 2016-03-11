package com.donateknowledge.service;

import com.donateknowledge.model.State;
import java.util.List;

public interface StateService {
    public void saveState(State state);
    public List<State> listState();
    public void removeState(Integer stateNo);
    public State getStateById(Integer stateNo);
}
