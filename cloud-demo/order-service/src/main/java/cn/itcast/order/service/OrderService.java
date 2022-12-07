package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2、查询user
        //3、调用url地址
        //发起调用
//        String url = "http://127.0.0.1:8081/user/" + order.getUserId();
//        String url = "http://userservice/user/" + order.getUserId();
        //利用feign发起http请求，查询用户
        User byId = userClient.findById(order.getUserId());

        //存入order
//        User forObject = restTemplate.getForObject(url, User.class);
        order.setUser(byId);
        // 4.返回
        return order;
    }
}
