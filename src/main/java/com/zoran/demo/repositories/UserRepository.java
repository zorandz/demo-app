package com.zoran.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zoran.demo.dto.Orders;
import com.zoran.demo.dto.UsersListDTO;
import com.zoran.demo.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
       
    @Query(nativeQuery=true, value="SELECT user_id as userID, first_name as firstName, last_name as lastName, username, " + 
    		"email, last_login_date as lastLoginDate, last_login_date_display lastLoginDateDisplay, join_date as joinDate, profile_image_url as profileImageUrl, " +
    		"is_active as isActive, role, is_not_locked as isNotLocked, " +
    		"authorities FROM `full-stack-ecommerce`.user;")
    public List<UsersListDTO> findSiteUsers();
    
    @Query(nativeQuery=true, value="SELECT orders.id as orderId, orders.total_price as price, orders.date_created as orderDate, order_item.image_url as imageURL, order_item.product_id as productId, product.name as productName "
    		+ "FROM orders "
    		+ "INNER JOIN user ON orders.user_id = user.id "
    		+ "INNER JOIN order_item ON orders.id = order_item.order_id "
    		+ "INNER JOIN product ON order_item.product_id = product.id "
    		+ "WHERE user.username =:username "
    		+ "ORDER BY orders.id ASC;")
    public List<Orders> getOrders(String username);
    
  
}
