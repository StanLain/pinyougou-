package cn.itcast.mapper;

import cn.itcast.pojo.Member;

public interface MemberMapper {

    /**
     * 根据订单的memberId 查询会员信息
     * @param mid
     * @return
     */
    Member findByMid(String mid);
}
