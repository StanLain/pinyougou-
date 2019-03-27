package cn.itcast.mapper;

import cn.itcast.pojo.Traveller;

import java.util.List;

public interface TravellerMapper {

    /**
     * 根据orders的id 查询旅客信息
     * @param oid
     * @return
     */
    List<Traveller> findByOid(String oid);
}
