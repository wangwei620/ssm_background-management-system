package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {
    /**
     * 分页查询所有的结果集合
     * @return
     */
    @Select("select * from (select rownum r ,p.* from product p where " +
            "rownum <=#{endIndex}) t where t.r>#{startIndex} ")
    public List<Product> findAllProduct(@Param("startIndex") Integer startIndex,
                                        @Param("endIndex") Integer endIndex);

    /**
     * 添加
     * @param product
     */
    @Insert("insert into product values(com_sequence.nextval," +
            "#{productNum},#{productName},#{cityName}," +
            "#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    /**
     * 修改
     * @param product
     */
    @Update("update product set productName=#{productName},cityName=#{cityName}," +
            "departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc}," +
            "productStatus=#{productStatus} where id = #{id}")
    void updateProduct(Product product);

    /**
     * 通过id找信息
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(Integer id);

    /**
     * 删除
     * @param id
     */

    @Delete("delete from product where id=#{id}")
    void deleteById(Integer id);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(1) from product")
    Integer findTotalCount();
}
