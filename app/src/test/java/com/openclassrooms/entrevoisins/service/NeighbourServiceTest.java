package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        assertTrue(service.getFavoriteNeighbours().isEmpty());
        Neighbour neighbour = service.getNeighbours().get(0);
        Neighbour neighbour2 = service.getNeighbours().get(1);
        neighbour.setFavorite(true);
        neighbour2.setFavorite(true);
        assertEquals(2, service.getFavoriteNeighbours().size());
        assertTrue(service.getFavoriteNeighbours().contains(neighbour));
        assertTrue(service.getFavoriteNeighbours().contains(neighbour2));
    }

    @Test
    public void setFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.setFavoriteNeighbour(neighbour.getId(), true);
        assertTrue(neighbour.getFavorite());
        service.setFavoriteNeighbour(neighbour.getId(), false);
        assertFalse(neighbour.getFavorite());
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        assertFalse(service.getFavoriteNeighbour(neighbour.getId()));
        neighbour.setFavorite(true);
        assertTrue(service.getFavoriteNeighbour(neighbour.getId()));
        neighbour.setFavorite(false);
        assertFalse(service.getFavoriteNeighbour(neighbour.getId()));
    }

    @Test
    public void getNeighbourById() {
        Neighbour neighbourToGet = service.getNeighbours().get(0);
        Neighbour neighbour = service.getNeighbourById(neighbourToGet.getId());
        assertEquals(neighbour, neighbourToGet);
        assertNull(service.getNeighbourById(-1));
    }
}
