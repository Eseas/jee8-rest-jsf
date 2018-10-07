package se.eseas.showroom.client.businesslogic;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class VehicleLogic {

    private static List<String> makeList = Arrays.asList(
            "TOYOTA",
            "HOLDEN",
            "FORD",
            "NISSAN",
            "BMW",
            "MAZDA",
            "MERCEDES-BENZ",
            "VOLKSWAGEN",
            "AUDI",
            "KIA",
            "PEUGEOT",
            "HYUNDAI",
            "ABARTH",
            "ALFA ROMEO",
            "ASTON MARTIN",
            "AUSTIN",
            "BEDFORD",
            "BENTLEY",
            "BOLWELL",
            "BUFORI",
            "CADILLAC",
            "CATERHAM",
            "CHERY",
            "CHEVROLET",
            "CHRYSLER",
            "CITROEN",
            "DAEWOO",
            "DAIHATSU",
            "DODGE",
            "FIAT",
            "GEELY",
            "GREAT WALL",
            "HINO",
            "HUMMER",
            "INFINITI",
            "ISUZU",
            "JAGUAR",
            "JEEP",
            "LAND ROVER",
            "LEXUS",
            "LOTUS",
            "MINI",
            "MITSUBISHI",
            "OPEL",
            "PORSCHE",
            "PROTON",
            "RANGE ROVER",
            "RENAULT",
            "SAAB",
            "SKODA",
            "SSANGYONG",
            "SUBARU",
            "SUZUKI",
            "TATA",
            "VOLVO"
    );

    public boolean isMakeValid(String make) {
        for(String allowedMake: makeList) {
            if(make.toLowerCase().equals(allowedMake.toLowerCase()))
                return true;
        }
        return false;
    }
}

