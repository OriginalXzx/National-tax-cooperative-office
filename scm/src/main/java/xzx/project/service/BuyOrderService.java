package xzx.project.service;

import xzx.project.base.service.BaseService;
import xzx.project.entity.BuyOrder;

public interface BuyOrderService extends BaseService<BuyOrder> {
       public Integer insertBuyOrder(BuyOrder buyOrder) throws Exception;
}
