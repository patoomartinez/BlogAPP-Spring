package com.bloggingapp.bootcampjava.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




import com.bloggingapp.bootcampjava.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
