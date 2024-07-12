package org.example.farmmanagementfx.data.repository;

import org.example.farmmanagementfx.data.dao.LandLotDao;
import org.example.farmmanagementfx.data.entity.LandLot;

import java.sql.SQLException;
import java.util.List;

public class LandLotRepository {

    public static List<LandLot> getLandLots() {
        return LandLotDao.getLandLots();
    }

    public static LandLot getLandLotById(int landLotId) {
        return LandLotDao.getLandLotById(landLotId);
    }

    public static LandLot insertLandLot(LandLot toInsert) throws SQLException {
        return LandLotDao.insertLandLot(toInsert);
    }

    public static void updateLandLot(LandLot toUpdate) {
        LandLotDao.updateLandLot(toUpdate);
    }

    public static void deleteLandLot(LandLot toDelete) {
        LandLotDao.deleteLandLot(toDelete);
    }
}
