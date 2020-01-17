package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

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
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> neighboursFavorite = new ArrayList<>();
        for(Neighbour neighbour : neighbours){
            if(neighbour.getFavorite()){
                neighboursFavorite.add(neighbour);
            }
        }
        return neighboursFavorite;
    }

    @Override
    public  void setFavoriteNeighbour(int id, boolean fav){
        neighbours.get(id - 1).setFavorite(fav);
    }
    @Override
    public boolean getFavoriteNeighbour(int id){
        return neighbours.get(id-1).getFavorite();
    }


}
