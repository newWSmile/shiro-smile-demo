package com.smile.shirosmiledemo.repository;

import com.smile.shirosmiledemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/22 11:06
 * @description：
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUserName(String userName);

}