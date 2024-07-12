package org.example.farmmanagementfx.data.repository;

import org.example.farmmanagementfx.data.dao.FarmerDao;
import org.example.farmmanagementfx.data.entity.Farmer;

import java.sql.SQLException;
import java.util.List;

public class FarmerRepository {

    public static List<Farmer> getFarmers() {
        return FarmerDao.getFarmers();
    }

    public static Farmer getFarmerById(int farmerId) {
        return FarmerDao.getFarmerById(farmerId);
    }

    public static Farmer insertFarmer(Farmer toInsert) throws SQLException {
        return FarmerDao.insertFarmer(toInsert);
    }

    public static void updateFarmer(Farmer toUpdate) {
        FarmerDao.updateFarmer(toUpdate);
    }

    public static void deleteFarmer(Farmer toDelete) {
        FarmerDao.deleteFarmer(toDelete);
    }
}
