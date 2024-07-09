package org.example.farmmanagementfx.repository;

import org.example.farmmanagementfx.dao.CultureDao;
import org.example.farmmanagementfx.entity.Culture;

import java.util.List;

public class CultureRepository {

    public static List<Culture> getCultures() {
        return CultureDao.getCultures();
    }
}
