package se.eseas.showroom.client.usecases;

import lombok.Getter;
import se.eseas.showroom.client.businesslogic.VehicleLogic;
import se.eseas.showroom.ws.entities.Vehicle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VehicleManagementController implements Serializable {
    @Getter
    private Vehicle vehicle = new Vehicle();

    @Inject
    private VehicleLogic vehicleLogic;

    @Getter private List<Vehicle> vehicles;

    @PostConstruct
    public void loadData() {
        Client client = ClientBuilder.newClient();
        Response response =
                client.target("http://localhost:8080/rest/vehicle-management/vehicles")
                        .request(MediaType.APPLICATION_JSON)
                        .get(Response.class);
        vehicles = response.readEntity(new GenericType<List<Vehicle>>(){});
    }

    public void createVehicle() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest/vehicle-management/vehicles");

        if (vehicleLogic.isMakeValid(vehicle.getMake())) {
            Response response = target
                    .request()
                    .post(Entity.entity(vehicle, MediaType.APPLICATION_JSON));
            if (response.getStatus() == 201) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully created."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error occurred while creating vehicle!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid vehicle make."));
        }
        vehicle = new Vehicle();
        loadData();
    }

    public void updateVehicle(Vehicle vehicle) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/rest/vehicle-management/vehicles");
        Response response = target
                .path(vehicle.getId().toString())
                .request()
                .put(Entity.entity(vehicle, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully updated."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error occurred while updating vehicle!"));
        }
    }

    public void deleteVehicle(Vehicle vehicle) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest/vehicle-management/vehicles");
        Response response = target
                .path(vehicle.getId().toString())
                .request()
                .delete();
        loadData();
        if (response.getStatus() == 200) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully deleted."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error occurred while deleting vehicle!"));
        }
    }

}