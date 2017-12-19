package xzx.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2601777964576485529L;

	private String goodsId; //商品编号

    private String goodsName; //商品名称

    private String goodsUnit; //商品单价

    private String goodsType; //类型

    private String goodsColor; // 颜色
 
    private Integer goodsStore; // 库存下限

    private Integer goodsLimit; // 有效的修改时间

    private BigDecimal goodsCommission; // 提成

    private String goodsProducer;    // 生产商

    private String goodsRemark;  // 备注

    private BigDecimal goodsSelPrice;  // 售价

    private BigDecimal goodsBuyPrice;  // 进价

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public Integer getGoodsStore() {
        return goodsStore;
    }

    public void setGoodsStore(Integer goodsStore) {
        this.goodsStore = goodsStore;
    }

    public Integer getGoodsLimit() {
        return goodsLimit;
    }

    public void setGoodsLimit(Integer goodsLimit) {
        this.goodsLimit = goodsLimit;
    }

    public BigDecimal getGoodsCommission() {
        return goodsCommission;
    }

    public void setGoodsCommission(BigDecimal goodsCommission) {
        this.goodsCommission = goodsCommission;
    }

    public String getGoodsProducer() {
        return goodsProducer;
    }

    public void setGoodsProducer(String goodsProducer) {
        this.goodsProducer = goodsProducer;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public BigDecimal getGoodsSelPrice() {
        return goodsSelPrice;
    }

    public void setGoodsSelPrice(BigDecimal goodsSelPrice) {
        this.goodsSelPrice = goodsSelPrice;
    }

    public BigDecimal getGoodsBuyPrice() {
        return goodsBuyPrice;
    }

    public void setGoodsBuyPrice(BigDecimal goodsBuyPrice) {
        this.goodsBuyPrice = goodsBuyPrice;
    }

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsUnit=" + goodsUnit + ", goodsType="
				+ goodsType + ", goodsColor=" + goodsColor + ", goodsStore=" + goodsStore + ", goodsLimit=" + goodsLimit
				+ ", goodsCommission=" + goodsCommission + ", goodsProducer=" + goodsProducer + ", goodsRemark="
				+ goodsRemark + ", goodsSelPrice=" + goodsSelPrice + ", goodsBuyPrice=" + goodsBuyPrice + "]";
	}
    
}