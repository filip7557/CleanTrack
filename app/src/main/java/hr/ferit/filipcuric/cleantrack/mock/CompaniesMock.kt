package hr.ferit.filipcuric.cleantrack.mock

import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.model.Location

fun getLocations(): List<Location> = listOf(
    Location(
        id = 0,
        companyId = "asd123",
        name = "Location 1",
        address = "Adresa lokacije 1, Osijek",
        frequency = 5,
    ),
    Location(
        id = 1,
        companyId = "asd123",
        name = "Location 2",
        address = "Adresa lokacije 2, Osijek",
        frequency = 4,
    ),
    Location(
        id = 2,
        companyId = "asd123",
        name = "Location 3",
        address = "Adresa lokacije 3, Osijek",
        frequency = 3,
    ),
    Location(
        id = 3,
        companyId = "yxc456",
        name = "Location 1",
        address = "Adresa lokacije 1, Osijek",
        frequency = 2,
    )

)
