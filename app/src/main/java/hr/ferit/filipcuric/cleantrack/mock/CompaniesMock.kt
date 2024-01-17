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

fun getCompanies(): List<Company> = listOf(
    Company(
        id = "asd123",
        name = "Cleaning Company 1",
        position = "Manager",
        imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
    ),
    Company(
        id = "yxc456",
        name = "Cleaning Company 2",
        position = "Worker",
        imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
    ),
    Company(
        id = "qwe678",
        name = "Cleaning Company 3",
        position = "Worker",
        imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
    ),
    Company(
        id = "vbn890",
        name = "Cleaning Company 4",
        position = "Worker",
        imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
    ),
    Company(
        id = "fgh123",
        name = "Cleaning Company 5",
        position = "Worker",
        imageUrl = "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/cleaning-service-company-logo-design-template-db40091848b1283994ec71ae58270d9f_screen.jpg?ts=1669998818",
    )

)
