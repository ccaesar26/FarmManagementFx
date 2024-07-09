package org.example.farmmanagementfx.dao;

import org.example.farmmanagementfx.data.util.DbUtil;
import org.example.farmmanagementfx.entity.Culture;

import java.util.ArrayList;
import java.util.List;

public class CultureDao {

    public static List<Culture> getCultures() {
        var connection = DbUtil.getConnection();

        var cultures = new ArrayList<Culture>();
        var call = "{ call get_all_cultures() }";

        try (var statement = connection.prepareCall(call)) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var culture = new Culture(
                        resultSet.getInt("culture_id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("is_active")
                );

                cultures.add(culture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConnection(connection);
        }

        return cultures;
    }
}
