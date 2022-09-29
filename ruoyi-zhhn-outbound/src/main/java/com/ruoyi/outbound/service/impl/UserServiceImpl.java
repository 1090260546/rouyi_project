package com.ruoyi.outbound.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.outbound.entity.User;
import com.ruoyi.outbound.mapper.UserMapper;
import com.ruoyi.outbound.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void updateUser(String phone, String province, String city, String content, String carrierCode) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().
            eq("phone",phone)
        );
        User user =null;
        if(null != userList && !userList.isEmpty()){
            user = userList.get(0);
            user.setUpdateTime(new Date());
            user.setCarrier(getUserCarrier(carrierCode));
            if(!StringUtils.isEmpty(content)&& StringUtils.isEmpty(user.getAddress()) && StringUtils.isEmpty(user.getName())){
                JSONObject jsonObject = JSONObject.parseObject(content);
                user.setName(jsonObject.getString("姓名"));
                user.setAddress(jsonObject.getString("住址"));
                userMapper.updateById(user);
            }else{
                user.setAddress(province+city);
                userMapper.updateById(user);
            }
        }
    }

    @Override
    public User saveUser(String phone, String province, String city, String content, String carrierCode) {
        if(phone.length()>20){
            return null;
        }
        User user = new User();
        user.setPhone(phone);
        user.setCreateTime(new Date());
        user.setIsDelete(0);
        user.setCarrier(getUserCarrier(carrierCode));
        if(!StringUtils.isEmpty(content)){
            JSONObject jsonObject = JSONObject.parseObject(content);
            String reservedField1 = jsonObject.getString("人员ID");
            if(StringUtils.isEmpty(reservedField1)){
                reservedField1 = jsonObject.getString("人员id");
            }else if(StringUtils.isEmpty(reservedField1)){
                reservedField1 = jsonObject.getString("用户ID");
            }
            user.setReservedField1(reservedField1);
            user.setName(jsonObject.getString("姓名"));
            String address =jsonObject.getString("住址");
            if(StringUtils.isEmpty(address)){
                address = province+city;
            }
            user.setAddress(address);
        }else {
            user.setAddress(province + city);
        }
        userMapper.insert(user);
        return user;
    }


    private String getUserCarrier(String carrierCode){
        if(carrierCode.equals("1")){
            return "移动";
        }else if(carrierCode.equals("2")){
            return "联通";
        }else if(carrierCode.equals("3")){
            return "电信";
        }
        return "其他";
    }
}
