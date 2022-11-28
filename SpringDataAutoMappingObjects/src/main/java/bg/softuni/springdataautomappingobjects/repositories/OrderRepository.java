package bg.softuni.springdataautomappingobjects.repositories;

import bg.softuni.springdataautomappingobjects.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
