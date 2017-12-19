use scm;
drop table if exists goods;

drop table if exists store_house;
/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
/*
goods_Id         商品编号
   goods_name           商品名称
   goods_unit          商品单价
   goods_type           类型
   goods_color          颜色,
   goods_store          库存下限,
   goods_limit          有效的修改时间,
   goods_commission     提成,
   goods_producer       生产商
   goods_remark         备注,
   goods_sel_price        售价
   goods_buy_price         进价
                                               */
/*==============================================================*/
create table goods
(
   goods_Id             varchar(36) not null,
   goods_name           varchar(20),
   goods_unit           varchar(10),
   goods_type           varchar(10),
   goods_color          varchar(10),
   goods_store          int,
   goods_limit          int,
   goods_commission     decimal(2,2),
   goods_producer       varchar(50),
   goods_remark         varchar(100),
   goods_sel_price      decimal(12,2),
   goods_buy_price      decimal(12,2),
   primary key (goods_Id)
);
/*==============================================================*/
/* Table: store_house                                           */
/*
   sh_id                仓库编号,
   sh_name              仓库名称,
   sh_responsible       责任人,
   sh_phone             联系电话,
   sh_address           联系地址,
   sh_type              仓库类型,
   sh_remark            备注,
*/
/*==============================================================*/
create table store_house
(
   sh_id                varchar(10) not null,
   sh_name              varchar(20),
   sh_responsible       varchar(20),
   sh_phone             varchar(11),
   sh_address           varchar(50),
   sh_type              varchar(10),
   sh_remark            varchar(100),
   primary key (sh_id)
);
/* 新增功能所需的系统表*/
drop table if exists sys_param;

/*==============================================================*/
/* Table: sys_param                                             */
/*==============================================================*/
create table sys_param
(
   sys_param_id         bigint auto_increment,
   sys_param_field      varchar(50),
   sys_param_value      varchar(50),
   sys_param_text       varchar(50),
   primary key (sys_param_id)
);
/*====================进货模块==========================================*/
/* Table: account_records   
编号		ar_id,
供货商编号	sup_id，
日期		ar_date，
单号(不同类型单号不一样）ad_order_id，
类型(业务类型）ar_bus_type，
应付		ar_payable，
实付		ar_paid，
欠款		ar_arrears，
优惠金额	ar_discount，
经办人		ar_attn，
操作员		ar_operator。
备注		ar_remark
                                    */
/*==============================================================*/
create table account_records
(
   ar_id                varchar(36) not null,
   sup_id               int,
   ar_date              date,
   ar_order_id          varchar(36),
   ar_bus_type          varchar(10),
   ar_payable           decimal(12,2),
   ar_paid              decimal(12,2),
   ar_arrears           decimal(12,2),
   ar_discount          decimal(12,2),
   ar_attn              varchar(20),
   ar_operator          int,
   ar_remark            varchar(100),
   primary key (ar_id)
);

/*==============================================================*/
/* Table: buy_order  
单号     	bo_id，
供货商  	sup_id，
仓库      	sh_id，
收货日期  	bo_date，
应付（实付+欠款+优惠）bo_payable，
实付      	bo_paid，
欠款		bo_Arrears，
原始单号	bo_original_id，
备注		bo_remark，
经办人		bo_attn，
操作员		operator

                                           */
/*==============================================================*/
create table buy_order
(
   bo_id                varchar(36) not null,
   sup_id               int,
   sh_id                varchar(10),
   bo_date              date,
   bo_payable           decimal(12,2),
   bo_paid              decimal(12,2),
   bo_arrears           decimal(12,2),
   bo_original          varchar(20),
   bo_remark            varchar(100),
   bo_attn              varchar(20),
   bo_operator          int,
   primary key (bo_id)
);

/*==============================================================*/
/* Table: buy_order_detail     
编号		bod_id：
商品ID	goods_id，备用
 goods_name           varchar(20),
   goods_type           varchar(10),
   goods_color          varchar(10),
单位		goods_unit，
数量 		bod_amount，
进价		bod_buy_price，
总金额（可无）bod_total_price，
 采购单号	bo_id，
手机串号列表（##分隔）bod_IMEI_list
                                 */
/*==============================================================*/
create table buy_order_detail
(
   bod_id               varchar(36) not null,
   goods_id             varchar(36),
   goods_name           varchar(20),
   goods_unit           varchar(10),
   goods_type           varchar(10),
   goods_color          varchar(10),
   bod_amount           int,
   bod_buy_price        decimal(12,2),
   bod_total_price     decimal(12,2),
   bo_id                varchar(36),
   bod_imei_list        text,
   primary key (bod_id)
);

/*==============================================================*/
/* Table: return_order   
单号		roId，
供货商	sup_id，
仓库		sh_id，
退货日期	ro_date，
应退金额	ro_payable，
实退金额	ro_Paid，
备注，	ro_remark,
经办人	ro_attn，
操作员	ro_operator


                                       */
/*==============================================================*/
create table return_order
(
   ro_id                varchar(36) not null,
   sup_id               int,
   sh_id                varchar(10),
   ro_date              date,
   ro_payable           decimal(12,2),
   ro_paid              decimal(12,2),
   ro_remark            varchar(100),
   ro_attn              varchar(20),
   ro_operator          int,
   primary key (ro_id)
);

/*==============================================================*/
/* Table: return_order_deatil      
编号		rod_id：
商品		goods_id，//备用
 goods_name           varchar(20),
   goods_type           varchar(10),
   goods_color          varchar(10),
单位		goods_unit，
数量		rod_amount，
退货单价	rod_return_price，
总金额（可无）rod_total_price，
 手机串号列表（##分隔）rod_imei_list
                             */
/*==============================================================*/
create table return_order_detail
(
   rod_id              varchar(36) not null,
   goods_id             varchar(36),
   goods_unit           varchar(10),
   goods_name           varchar(20),
   goods_type           varchar(10),
   goods_color          varchar(10),
   rod_amount           int,
   rod_return_price     decimal(12,2),
   rod_total_price      decimal(12,2),
   rod_imei_list        text,
   ro_id                varchar(36),
   primary key (rod_id)
);
/*===================== 库存表 ============================================*/  
/*==

  stock_id  varchar(40) not null       库存编号 , 主键  
  sh_id      varchar(10),              所在仓库,     外键 ( 参照 SOTRE_HOUSE 表 )      
  goods_id    varchar(36),             商品编号,     外键 ( 参照 goods 表 )      
  stock_firstdate   date,               此种商品第一次入库时间    
  stock_lastdate    date,               此种商品最后一次出库时间,为NULL 时,此种商品从来没有卖过   
  stock_amount         int,              所存数量   
  stock_price          decimal(12,2),    
        加权价 ,加权价简单可理解为平均价， 分为一次平均加权和移动平均加权。计算如下：一次平均加权为月底一次计算库存的平均采购成本：
         公式=（月初成本+本月实际入库成本）/（月初数量+本月入库数量），移动平均加权为每次入库时系统都自动计算加权平均成本。
         公式=（本次入库前的库存成本+ 本次入库实际成本）/ (入库前数量+本次入库数量）
          即一次加权成本是每月计算一次， 而移动加权成本是每次入库都计算一次的。  
=================================================================================*/
create table stock  
(    
  stock_id   varchar(40) not null, 
  sh_id      varchar(10),     
  goods_id    varchar(36),     
  stock_firstdate   date, 
  stock_lastdate    date, 
  stock_amount         int, 
  stock_price          decimal(12,2),
   primary key (stock_id)
);


