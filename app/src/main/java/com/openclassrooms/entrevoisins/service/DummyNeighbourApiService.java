package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbours = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) { neighbours.remove(neighbour); }

    @Override
    public List<Neighbour> getFavoriteNeighbours() { return favoriteNeighbours; }

    @Override
    public void addNeighbourFavorite(Neighbour neighbour) { favoriteNeighbours.add(neighbour); }

    @Override
    public void deleteNeighbourFavorite(Neighbour neighbour) { favoriteNeighbours.remove(neighbour); }

}
