package com.smile.shirosmiledemo.service.impl;

import com.smile.shirosmiledemo.dto.UserDto;
import com.smile.shirosmiledemo.entity.User;
import com.smile.shirosmiledemo.enumeration.UserStatus;
import com.smile.shirosmiledemo.repository.UserRepository;
import com.smile.shirosmiledemo.service.UserService;
import com.smile.shirosmiledemo.utils.EmialUtils;
import com.smile.shirosmiledemo.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 9:28
 * @description：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public long addUser(UserDto userDto) {
        //校验数据
        if (StringUtils.isAllBlank(userDto.getUserName())) {
            throw new AccountException("请输入用户名!");
        }
        if (StringUtils.isAllBlank(userDto.getUserPassword())) {
            throw new AccountException("请输入密码!");
        }
        if (StringUtils.isAllBlank(userDto.getUserEmail()) || EmialUtils.verify(userDto.getUserEmail())) {
            throw new AccountException("请输入正确的邮箱地址!");
        }

        Date now = new Date();
        String userName = userDto.getUserName();
        List<User> userList = userRepository.findByUserName(userName);
        if (!(null == userList || userList.size() == 0)) {
            throw new AccountException("用户名已存在!");
        }
        String salt = UUID.randomUUID().toString().substring(0, 5);
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setStatus(UserStatus.EFFECTIVE.getCode());
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setSalt(salt);
        user.setUserPassword(Md5Utils.md5Encrypt(user.getUserPassword() + user.getSalt()));
        User userSave = userRepository.save(user);
        return userSave.getId();
    }
}
