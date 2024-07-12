package org.example.farmmanagementfx.data.dao;

import org.example.farmmanagementfx.data.entity.LandLot;
import org.example.farmmanagementfx.data.util.DateUtil;
import org.example.farmmanagementfx.data.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandLotDao {

    public static List<LandLot> getLandLots() {
        var connection = DbUtil.getConnection();

        var landLots = new ArrayList<LandLot>();
        var call = "{ call get_all_land_lots() }";

        try (var statement = connection.prepareCall(call)) {
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var landLot = convertToEntity(resultSet);
                landLots.add(landLot);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return landLots;
    }

    public static LandLot getLandLotById(int landLotId) {
        var connection = DbUtil.getConnection();

        var call = "{ call get_land_lot_by_id(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, landLotId);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return convertToEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }

        return null;
    }

    public static LandLot insertLandLot(LandLot toInsert) throws SQLException {
        var connection = DbUtil.getConnection();

        var call = "{ ? = call insert_land_lot(?, ?, ?, ?, ?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.setString(2, toInsert.getLocation());
            statement.setDouble(3, toInsert.getArea());
            statement.setDate(4, DateUtil.convertToSqlDate(toInsert.getPlantingDate()));
            statement.setDate(5, DateUtil.convertToSqlDate(toInsert.getHarvestDate()));
            statement.setInt(6, toInsert.getPlantedCulture().getId());

            statement.execute();

            var id = statement.getInt(1);
            toInsert.setId(id);

            return toInsert;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw e;
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    public static void updateLandLot(LandLot toUpdate) {
        var connection = DbUtil.getConnection();

        var call = "{ call update_land_lot(?, ?, ?, ?, ?, ?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toUpdate.getId());
            statement.setString(2, toUpdate.getLocation());
            statement.setDouble(3, toUpdate.getArea());
            statement.setDate(4, DateUtil.convertToSqlDate(toUpdate.getPlantingDate()));
            statement.setDate(5, DateUtil.convertToSqlDate(toUpdate.getHarvestDate()));
            statement.setInt(6, toUpdate.getPlantedCulture().getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    public static void deleteLandLot(LandLot toDelete) {
        var connection = DbUtil.getConnection();

        var call = "{ call delete_land_lot(?) }";

        try (var statement = connection.prepareCall(call)) {
            statement.setInt(1, toDelete.getId());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    private static LandLot convertToEntity(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("land_lot_id");
        var location = resultSet.getString("location");
        var area = resultSet.getDouble("area");
        var plantingDate = DateUtil.convertToDate(resultSet.getDate("planting_date"));
        var harvestDate = DateUtil.convertToDate(resultSet.getDate("harvest_date"));
        var plantedCultureId = resultSet.getInt("planted_culture_id");
        var isActive = resultSet.getBoolean("is_active");

        var plantedCulture = CultureDao.getCultureById(plantedCultureId);

        return new LandLot(id, location, area, plantingDate, harvestDate, plantedCulture, isActive);
    }
}
