package org.example.fancy_project.dao;

import org.example.fancy_project.classes.car.CarRent;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentDao extends RentDao<CarRent> {
}
