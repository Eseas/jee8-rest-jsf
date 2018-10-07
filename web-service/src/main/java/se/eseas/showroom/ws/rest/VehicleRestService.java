package se.eseas.showroom.ws.rest;

import se.eseas.showroom.ws.dao.VehicleDAO;
import se.eseas.showroom.ws.entities.Vehicle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ApplicationScoped
@Path("/vehicle-management")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleRestService {

    @Inject
    private EntityManager em;

    @Inject
    private VehicleDAO vehicleDAO;

    @GET
    @Path("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    @GET
    @Path("/vehicles/{vehicleId}")
    public Vehicle find(@PathParam("vehicleId") Integer vehicleId) {
        return em.find(Vehicle.class, vehicleId);
    }

    @POST
    @Path("/vehicles")
    @Transactional(Transactional.TxType.REQUIRED)
    public Response createVehicle(Vehicle vehicle) throws URISyntaxException {
        vehicleDAO.create(vehicle);
        return Response.status(201).contentLocation(new URI("/user-management/vehicles/" + vehicle.getId())).build();
    }

    @PUT
    @Path("/vehicles/{id}")
    @Transactional(Transactional.TxType.REQUIRED)
    public Vehicle replaceVehicle(@PathParam("id") int id, Vehicle vehicle) {
        vehicle.setId(id);
        em.merge(vehicle);
        em.flush();
        return vehicle;
    }

    @DELETE
    @Path("/vehicles/{id}")
    @Transactional(Transactional.TxType.REQUIRED)
    public Response deleteVehicle(@PathParam("id") int id) {
        Vehicle vehicle = em.createNamedQuery("Vehicle.findById", Vehicle.class)
                .setParameter("id", id).getSingleResult();
        em.remove(vehicle);
        em.flush();
        return Response.status(200).build();

    }

}
