package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products(title,price) VALUES(?,?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getPrice());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            List<Product> entities = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                Product product = new Product(title, price);
                entities.add(new Product(title, price));
            }
            return entities;
        }
    }

    public static Optional<Product> find(long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id=?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                int price = rs.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    // END
}
