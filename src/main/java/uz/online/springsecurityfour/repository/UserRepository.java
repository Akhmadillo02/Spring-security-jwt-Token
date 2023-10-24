package uz.online.springsecurityfour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.online.springsecurityfour.domein.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
    @Query("select u from User u where u.userName = :userName")
    User findByLogin(@Param("userName") String userName);

    Boolean existsByUserName(String userName);
}
