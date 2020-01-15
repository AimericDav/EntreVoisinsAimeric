package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class AddNeighbourFavoriteEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public AddNeighbourFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
