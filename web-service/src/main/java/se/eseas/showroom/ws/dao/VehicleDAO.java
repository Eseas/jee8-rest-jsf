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

    public void delete(Vehicle vehicle) {
        em.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return em.createNamedQuery("Vehicle.findAll", Vehicle.class).getResultList();
    }

    public Vehicle selectByLicence(String licence) {
        return em.createNamedQuery("Vehicle.findByLicence", Vehicle.class)
                .setParameter("licence", licence.toLowerCase()).getSingleResult();
    }


}
