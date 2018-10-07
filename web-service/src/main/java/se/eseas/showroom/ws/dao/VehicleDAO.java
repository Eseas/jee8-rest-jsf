package se.eseas.showroom.ws.dao;

import se.eseas.showroom.ws.entities.Vehicle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class VehicleDAO {

    @Inject
    private EntityManager em;

    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    public void delete(Integer id) {
        Vehicle vehicle = em.createNamedQuery("Vehicle.findById", Vehicle.class)
                .setParameter("id", id).getSingleResult();
        em.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return em.createNamedQuery("Vehicle.findAll", Vehicle.class).getResultList();
    }

    public Vehicle getById(Integer id) {
        return em.find(Vehicle.class, id);
    }

    public Vehicle update(Vehicle vehicle) {
        em.merge(vehicle);
        em.flush();
        return vehicle;
    }
}
