package org.example.farmmanagementfx.data.repository;

import org.example.farmmanagementfx.data.dao.CultureDao;
import org.example.farmmanagementfx.data.entity.Culture;

import java.sql.SQLException;
import java.util.List;

public class CultureRepository {

    public static List<Culture> getCultures() {
        return CultureDao.getCultures();
    }

    public static Culture getCultureById(int cultureId) {
        return CultureDao.getCultureById(cultureId);
    }

    public static Culture insertCulture(Culture toInsert) throws SQLException {
        return CultureDao.insertCulture(toInsert);
    }

    public static void updateCulture(Culture toUpdate) {
        CultureDao.updateCulture(toUpdate);
    }

    public static void deleteCulture(Culture toDelete) {
        CultureDao.deleteCulture(toDelete);
    }
}
