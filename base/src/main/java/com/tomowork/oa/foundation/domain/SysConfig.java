package com.tomowork.oa.foundation.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tomowork.oa.annotation.Lock;
import com.tomowork.oa.domain.IdEntity;

@Cacheable
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "oa_sysconfig")
public class SysConfig extends IdEntity {

	private static final long serialVersionUID = 5705324940568205634L;

	private String title;	//网站标题

	private String keywords; //网站关键字 首页meta处

	private String description; //网店描述 首页meta处

	private String generator; //网站Generator meta处

	private String author; //网站auth meta处

	private String address; //网站网址

	private String copyright; //网站版权说明

	private String uploadFilePath; //图片上传路径

	private String sysLanguage; //系统默认语言

	private int integralRate;

	// TODO 修改表字段 kuangxing
	@Column(name = "smsEnbale")
	private boolean smsEnable; //是否开启短信功能   0 不开启  1 开启

	private String smsURL; /*短信设置没有用到*/

	private String smsUserName; //短信平台用户名

	private String smsPassword; //短信平台密码

	private String smsTest; //短信平台测试内容

	private boolean emailEnable; //是否开启邮件功能  0 不开启  1 开启

	private String emailHost; //邮件SMTP服务器

	private int emailPort; //STMP 端口

	private String emailUser; //收件人

	private String emailUserName; //发件人

	private String emailPws; //邮箱登录密码

	private String emailTest; //邮件发送测试内容

	private String websiteName; //网站名称（将显示在前台顶部欢迎等位置）

	private String hotSearch; //热门搜索

	@Column(columnDefinition = "varchar(255) default 'blue' ")
	private String websiteCss;

	@OneToOne(fetch = FetchType.LAZY)
	private Accessory websiteLogo; //网站logo

	@OneToOne(fetch = FetchType.LAZY)
	private Accessory websiteLoader; //网站加载图

	@Lob
	@Column(columnDefinition = "LongText")
	private String codeStat; //第三方流量统计代码

	private boolean websiteState; //站点状态

	private boolean visitorConsult; //是否允许游客咨询  0 不允许 1 允许

	@Lob
	@Column(columnDefinition = "LongText")
	private String closeReason; //站点关闭原因

	private String securityCodeType; //验证码类型  normal（文字）  voice（语音）

	private boolean securityCodeRegister; //前台注册是否需要验证码   0 不需要  1 需要

	private boolean securityCodeLogin; //前台登录是否需要验证码  0 不需要 1 需要

	private boolean securityCodeConsult; //商品咨询是否需要验证码 0 不需要 1 需要

	private String imageSuffix; //图片扩展名，用于判断上传图片是否为后台允许

	private String imageWebServer; //图片服务器  （可使用ftp）

	private int imageFilesize; //图片文件大小

	private int smallWidth; //商品小图宽度

	private int smallHeight; //商品小图高度

	private int middleWidth; //商品中图宽度

	private int middleHeight; //商品中图高度

	private int bigWidth; //商品大图宽度

	private int bigHeight; //商品大图高度

	private boolean integral; //会员积分管理  为0禁用  为1开启

	private boolean integralStore;

	private boolean voucher;

	private boolean deposit;

	private boolean groupBuy; //是否开启团购

	private boolean groupBuyApply; //是否开启团购申请

	private boolean gold; //是否开启金币功能   0不开启 1开启

	private int goldMarketValue;

	private int memberRegister; //开启积分后  会员注册获赠的积分

	private int memberDayLogin;

	private int indentComment;

	private int consumptionRatio;

	private int everyIndentLimit;

	private String imageSaveType; //图片存放格式设置
	//sidImg  按照文件名存放(例:/店铺id/图片)
	//sidYearImg  按照年份存放(例:/店铺id/年/图片)
	//sidYearMonthImg 按照年月存放(例:/店铺id/年/月/图片)
	//sidYearMonthDayImg 按照年月日存放(例:/店铺id/年/月/日/图片)

	private int complaint_time;

	@OneToOne(cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY)
	private Accessory storeImage; //默认店铺图片

	@OneToOne(cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY)
	private Accessory goodsImage; //默认商品图片

	@OneToOne(cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.LAZY)
	private Accessory memberIcon; //默认会员图片

	private boolean store_allow; //是否允许开店  1 允许   0 不允许

	@Lob
	@Column(columnDefinition = "LongText")
	private String creditrule; //json形式储存卖家的信用评级标准

	@Lob
	@Column(columnDefinition = "LongText")
	private String user_creditrule; //买家信用  json形式储存卖家的信用评级标准

	@Lob
	@Column(columnDefinition = "LongText")
	private String templates; //模版名称

	@Lob
	@Column(columnDefinition = "LongText")
	private String store_payment;

	@Lob
	@Column(columnDefinition = "LongText")
	private String share_code; //百度分享代码

	private boolean ztc_status;

	@Column(columnDefinition = "int default 0")
	private int ztc_goods_view;

	private int ztc_price;

	@Column(columnDefinition = "bit default 0")
	private boolean second_domain_open; //是否开启二级域名 0 不开启 1 开启

	@Column(columnDefinition = "int default 0")
	@Lock
	private int domain_allow_count; //允许修改二级域名的次数

	@Column(columnDefinition = "LongText")
	@Lock
	private String sys_domain; //系统保留的二级域名

	@Column(columnDefinition = "bit default 0")
	private boolean qq_login; //是否启用QQ登录 0不启用  1启用

	private String qq_login_id; //qq登录的id

	private String qq_login_key; //qq登录的key

	@Column(columnDefinition = "LongText")
	private String qq_domain_code; //qq域名验证信息

	@Column(columnDefinition = "bit default 0")
	private boolean sina_login; //是否启用新浪微博登录  0 不启用  1 启用

	private String sina_login_id; //新浪微博登录id

	private String sina_login_key; //新浪微博登录key

	@Column(columnDefinition = "LongText")
	private String sina_domain_code; //新浪微博域名验证信息

	private Date lucene_update;

	@Column(columnDefinition = "int default 0")
	@Lock
	private int alipay_fenrun;

	//1，为参与平台分润。0为不分润
	@Column(columnDefinition = "int default 0")
	@Lock
	private int balance_fenrun;

	private String bargain_title; //特价页标题

	@Column(columnDefinition = "int default 0")
	private int bargain_status; //特价状态 0不开启   1开启

	@Column(columnDefinition = "int default 3")
	private int bargain_validity; //特价商品申请有效期

	@Column(precision = 3, scale = 2)
	private BigDecimal bargain_rebate; //特价折扣

	@Column(columnDefinition = "int default 0")
	private int bargain_maximum; //允许申请特价的最大商品数

	@Column(columnDefinition = "LongText")
	private String bargain_state; //特价说明

	private String delivery_title;

	@Column(columnDefinition = "int default 0")
	private int delivery_status;

	@Column(columnDefinition = "int default 50")
	private int delivery_amount;

	@Column(columnDefinition = "int default 50")
	private int combin_amount;

	@Column(columnDefinition = "int default 3")
	private int combin_count;

	@OneToMany(mappedBy = "config")
	private List<Accessory> login_imgs = new ArrayList<>(); //用户前台登录页左侧图片，可上传4张，每次随机显示一张

	@Column(columnDefinition = "LongText")
	private String service_telphone_list; //平台客服电话（一行一个）

	@Column(columnDefinition = "LongText")
	private String service_qq_list; //平台客服QQ（一行一个）

	@Column(columnDefinition = "bit default 0")
	private boolean uc_bbs;

	private String uc_database = "";

	private String uc_table_preffix = "";

	private String uc_database_url = "";

	private String uc_database_port = "";

	private String uc_database_username = "";

	private String uc_database_pws = "";

	private String uc_api;

	private String uc_ip;

	private String uc_key;

	private String uc_appid;

	@Column(columnDefinition = "int default 3")
	@Lock
	private int auto_order_notice; //自动收货提醒时长（给卖家发送短信，邮件）

	@Column(columnDefinition = "int default 7")
	@Lock
	private int auto_order_confirm; //自动确认收货时长（卖家发货后）

	@Column(columnDefinition = "int default 7")
	@Lock
	private int auto_order_return; //买家申请退货有效时长（买家在该有效时长内需提交退货物流单号及物流公司信息，超过该时长退货申请关闭，订单变为待评价订单）

	@Column(columnDefinition = "int default 7")
	@Lock
	private int auto_order_evaluate; //订单可评价有效时长（到达该有效时长后订单不可评价）

	@Column(columnDefinition = "LongText")
	private String kuaidi_id;

	@Column(columnDefinition = "varchar(255) default '?'")
	private String currency_code;

	@Lock
	@Column(columnDefinition = "bit default 0")
	private boolean weixin_store;

	@Lock
	@Column(columnDefinition = "int default 50")
	private int weixin_amount;

	@Lock
	@Column(columnDefinition = "int default 0")
	private int config_payment_type; // 0店铺支付   1 平台支付

	@OneToOne
	private Accessory weixin_qr_img;

	@Lock
	private String weixin_account;

	@Lock
	private String weixin_token;

	@Lock
	private String weixin_appId;

	@Lock
	private String weixin_appSecret;

	@Lock
	@Column(columnDefinition = "LongText")
	private String weixin_welecome_content;

	@Lock
	@OneToOne(fetch = FetchType.LAZY)
	private Accessory store_weixin_logo;

	@Column(name = "misc_webserver")
	private String miscWebServer; // css / js 等固定类型文件服务器地址

	@Column(name = "chaturl")
	private String chaturl; // css / js 等固定类型文件服务器地址


	public int getConfig_payment_type() {
		return this.config_payment_type;
	}

	public void setConfig_payment_type(int config_payment_type) {
		this.config_payment_type = config_payment_type;
	}

	public Accessory getWeixin_qr_img() {
		return this.weixin_qr_img;
	}

	public void setWeixin_qr_img(Accessory weixin_qr_img) {
		this.weixin_qr_img = weixin_qr_img;
	}

	public String getWeixin_account() {
		return this.weixin_account;
	}

	public void setWeixin_account(String weixin_account) {
		this.weixin_account = weixin_account;
	}

	public String getWeixin_token() {
		return this.weixin_token;
	}

	public void setWeixin_token(String weixin_token) {
		this.weixin_token = weixin_token;
	}

	public String getWeixin_appId() {
		return this.weixin_appId;
	}

	public void setWeixin_appId(String weixin_appId) {
		this.weixin_appId = weixin_appId;
	}

	public String getWeixin_appSecret() {
		return this.weixin_appSecret;
	}

	public void setWeixin_appSecret(String weixin_appSecret) {
		this.weixin_appSecret = weixin_appSecret;
	}

	public String getWeixin_welecome_content() {
		return this.weixin_welecome_content;
	}

	public void setWeixin_welecome_content(String weixin_welecome_content) {
		this.weixin_welecome_content = weixin_welecome_content;
	}

	public Accessory getStore_weixin_logo() {
		return this.store_weixin_logo;
	}

	public void setStore_weixin_logo(Accessory store_weixin_logo) {
		this.store_weixin_logo = store_weixin_logo;
	}

	public int getWeixin_amount() {
		return this.weixin_amount;
	}

	public void setWeixin_amount(int weixin_amount) {
		this.weixin_amount = weixin_amount;
	}

	public boolean isWeixin_store() {
		return this.weixin_store;
	}

	public void setWeixin_store(boolean weixin_store) {
		this.weixin_store = weixin_store;
	}

	public int getAuto_order_return() {
		return this.auto_order_return;
	}

	public void setAuto_order_return(int auto_order_return) {
		this.auto_order_return = auto_order_return;
	}

	public int getAuto_order_evaluate() {
		return this.auto_order_evaluate;
	}

	public void setAuto_order_evaluate(int auto_order_evaluate) {
		this.auto_order_evaluate = auto_order_evaluate;
	}

	public int getZtc_goods_view() {
		return this.ztc_goods_view;
	}

	public void setZtc_goods_view(int ztc_goods_view) {
		this.ztc_goods_view = ztc_goods_view;
	}

	public String getWebsiteCss() {
		return this.websiteCss;
	}

	public void setWebsiteCss(String websiteCss) {
		this.websiteCss = websiteCss;
	}

	public String getCurrency_code() {
		return this.currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public boolean isUc_bbs() {
		return this.uc_bbs;
	}

	public void setUc_bbs(boolean uc_bbs) {
		this.uc_bbs = uc_bbs;
	}

	public List<Accessory> getLogin_imgs() {
		return this.login_imgs;
	}

	public void setLogin_imgs(List<Accessory> login_imgs) {
		this.login_imgs = login_imgs;
	}

	public int getBargain_status() {
		return this.bargain_status;
	}

	public void setBargain_status(int bargain_status) {
		this.bargain_status = bargain_status;
	}

	public int getBargain_validity() {
		return this.bargain_validity;
	}

	public void setBargain_validity(int bargain_validity) {
		this.bargain_validity = bargain_validity;
	}

	public BigDecimal getBargain_rebate() {
		return this.bargain_rebate;
	}

	public void setBargain_rebate(BigDecimal bargain_rebate) {
		this.bargain_rebate = bargain_rebate;
	}

	public int getBargain_maximum() {
		return this.bargain_maximum;
	}

	public void setBargain_maximum(int bargain_maximum) {
		this.bargain_maximum = bargain_maximum;
	}

	public Date getLucene_update() {
		return this.lucene_update;
	}

	public void setLucene_update(Date lucene_update) {
		this.lucene_update = lucene_update;
	}

	public boolean isSina_login() {
		return this.sina_login;
	}

	public void setSina_login(boolean sina_login) {
		this.sina_login = sina_login;
	}

	public String getSina_login_id() {
		return this.sina_login_id;
	}

	public void setSina_login_id(String sina_login_id) {
		this.sina_login_id = sina_login_id;
	}

	public String getSina_login_key() {
		return this.sina_login_key;
	}

	public void setSina_login_key(String sina_login_key) {
		this.sina_login_key = sina_login_key;
	}

	public String getSina_domain_code() {
		return this.sina_domain_code;
	}

	public void setSina_domain_code(String sina_domain_code) {
		this.sina_domain_code = sina_domain_code;
	}

	public boolean isQq_login() {
		return this.qq_login;
	}

	public void setQq_login(boolean qq_login) {
		this.qq_login = qq_login;
	}

	public String getQq_login_id() {
		return this.qq_login_id;
	}

	public void setQq_login_id(String qq_login_id) {
		this.qq_login_id = qq_login_id;
	}

	public String getQq_login_key() {
		return this.qq_login_key;
	}

	public void setQq_login_key(String qq_login_key) {
		this.qq_login_key = qq_login_key;
	}

	public int getDomain_allow_count() {
		return this.domain_allow_count;
	}

	public void setDomain_allow_count(int domain_allow_count) {
		this.domain_allow_count = domain_allow_count;
	}

	public String getSys_domain() {
		return this.sys_domain;
	}

	public void setSys_domain(String sys_domain) {
		this.sys_domain = sys_domain;
	}

	public boolean isZtc_status() {
		return this.ztc_status;
	}

	public void setZtc_status(boolean ztc_status) {
		this.ztc_status = ztc_status;
	}

	public int getZtc_price() {
		return this.ztc_price;
	}

	public void setZtc_price(int ztc_price) {
		this.ztc_price = ztc_price;
	}

	public String getTemplates() {
		return this.templates;
	}

	public void setTemplates(String templates) {
		this.templates = templates;
	}

	public boolean isStore_allow() {
		return this.store_allow;
	}

	public void setStore_allow(boolean store_allow) {
		this.store_allow = store_allow;
	}

	public Accessory getStoreImage() {
		return this.storeImage;
	}

	public void setStoreImage(Accessory storeImage) {
		this.storeImage = storeImage;
	}

	public Accessory getGoodsImage() {
		return this.goodsImage;
	}

	public void setGoodsImage(Accessory goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Accessory getMemberIcon() {
		return this.memberIcon;
	}

	public void setMemberIcon(Accessory memberIcon) {
		this.memberIcon = memberIcon;
	}

	public String getEmailHost() {
		return this.emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public int getEmailPort() {
		return this.emailPort;
	}

	public void setEmailPort(int emailPort) {
		this.emailPort = emailPort;
	}

	public String getEmailUser() {
		return this.emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailUserName() {
		return this.emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getEmailPws() {
		return this.emailPws;
	}

	public void setEmailPws(String emailPws) {
		this.emailPws = emailPws;
	}

	public String getSysLanguage() {
		return this.sysLanguage;
	}

	public void setSysLanguage(String sysLanguage) {
		this.sysLanguage = sysLanguage;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSmsURL() {
		return this.smsURL;
	}

	public void setSmsURL(String smsURL) {
		this.smsURL = smsURL;
	}

	public String getSmsUserName() {
		return this.smsUserName;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public String getSmsPassword() {
		return this.smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getIntegralRate() {
		return this.integralRate;
	}

	public void setIntegralRate(int integralRate) {
		this.integralRate = integralRate;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getWebsiteName() {
		return this.websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getHotSearch() {
		return this.hotSearch;
	}

	public void setHotSearch(String hotSearch) {
		this.hotSearch = hotSearch;
	}

	public Accessory getWebsiteLogo() {
		return this.websiteLogo;
	}

	public void setWebsiteLogo(Accessory websiteLogo) {
		this.websiteLogo = websiteLogo;
	}

	public String getCodeStat() {
		return this.codeStat;
	}

	public void setCodeStat(String codeStat) {
		this.codeStat = codeStat;
	}

	public boolean isWebsiteState() {
		return this.websiteState;
	}

	public void setWebsiteState(boolean websiteState) {
		this.websiteState = websiteState;
	}

	public String getCloseReason() {
		return this.closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public boolean isEmailEnable() {
		return this.emailEnable;
	}

	public void setEmailEnable(boolean emailEnable) {
		this.emailEnable = emailEnable;
	}

	public String getEmailTest() {
		return this.emailTest;
	}

	public void setEmailTest(String emailTest) {
		this.emailTest = emailTest;
	}

	public boolean isSecurityCodeRegister() {
		return this.securityCodeRegister;
	}

	public void setSecurityCodeRegister(boolean securityCodeRegister) {
		this.securityCodeRegister = securityCodeRegister;
	}

	public boolean isSecurityCodeLogin() {
		return this.securityCodeLogin;
	}

	public void setSecurityCodeLogin(boolean securityCodeLogin) {
		this.securityCodeLogin = securityCodeLogin;
	}

	public boolean isSecurityCodeConsult() {
		return this.securityCodeConsult;
	}

	public void setSecurityCodeConsult(boolean securityCodeConsult) {
		this.securityCodeConsult = securityCodeConsult;
	}

	public boolean isVisitorConsult() {
		return this.visitorConsult;
	}

	public void setVisitorConsult(boolean visitorConsult) {
		this.visitorConsult = visitorConsult;
	}

	public String getImageSuffix() {
		return this.imageSuffix;
	}

	public void setImageSuffix(String imageSuffix) {
		this.imageSuffix = imageSuffix;
	}

	public int getImageFilesize() {
		return this.imageFilesize;
	}

	public void setImageFilesize(int imageFilesize) {
		this.imageFilesize = imageFilesize;
	}

	public int getSmallWidth() {
		return this.smallWidth;
	}

	public void setSmallWidth(int smallWidth) {
		this.smallWidth = smallWidth;
	}

	public int getSmallHeight() {
		return this.smallHeight;
	}

	public void setSmallHeight(int smallHeight) {
		this.smallHeight = smallHeight;
	}

	public int getMiddleWidth() {
		return this.middleWidth;
	}

	public void setMiddleWidth(int middleWidth) {
		this.middleWidth = middleWidth;
	}

	public int getMiddleHeight() {
		return this.middleHeight;
	}

	public void setMiddleHeight(int middleHeight) {
		this.middleHeight = middleHeight;
	}

	public int getBigWidth() {
		return this.bigWidth;
	}

	public void setBigWidth(int bigWidth) {
		this.bigWidth = bigWidth;
	}

	public int getBigHeight() {
		return this.bigHeight;
	}

	public void setBigHeight(int bigHeight) {
		this.bigHeight = bigHeight;
	}

	public String getImageSaveType() {
		return this.imageSaveType;
	}

	public void setImageSaveType(String imageSaveType) {
		this.imageSaveType = imageSaveType;
	}

	public String getSecurityCodeType() {
		return this.securityCodeType;
	}

	public void setSecurityCodeType(String securityCodeType) {
		this.securityCodeType = securityCodeType;
	}

	public boolean isIntegral() {
		return this.integral;
	}

	public void setIntegral(boolean integral) {
		this.integral = integral;
	}

	public boolean isIntegralStore() {
		return this.integralStore;
	}

	public void setIntegralStore(boolean integralStore) {
		this.integralStore = integralStore;
	}

	public boolean isVoucher() {
		return this.voucher;
	}

	public void setVoucher(boolean voucher) {
		this.voucher = voucher;
	}

	public boolean isDeposit() {
		return this.deposit;
	}

	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}

	public boolean isGroupBuy() {
		return this.groupBuy;
	}

	public void setGroupBuy(boolean groupBuy) {
		this.groupBuy = groupBuy;
	}

	public boolean isGold() {
		return this.gold;
	}

	public void setGold(boolean gold) {
		this.gold = gold;
	}

	public int getGoldMarketValue() {
		return this.goldMarketValue;
	}

	public void setGoldMarketValue(int goldMarketValue) {
		this.goldMarketValue = goldMarketValue;
	}

	public int getMemberRegister() {
		return this.memberRegister;
	}

	public void setMemberRegister(int memberRegister) {
		this.memberRegister = memberRegister;
	}

	public int getMemberDayLogin() {
		return this.memberDayLogin;
	}

	public void setMemberDayLogin(int memberDayLogin) {
		this.memberDayLogin = memberDayLogin;
	}

	public int getIndentComment() {
		return this.indentComment;
	}

	public void setIndentComment(int indentComment) {
		this.indentComment = indentComment;
	}

	public int getConsumptionRatio() {
		return this.consumptionRatio;
	}

	public void setConsumptionRatio(int consumptionRatio) {
		this.consumptionRatio = consumptionRatio;
	}

	public int getEveryIndentLimit() {
		return this.everyIndentLimit;
	}

	public void setEveryIndentLimit(int everyIndentLimit) {
		this.everyIndentLimit = everyIndentLimit;
	}

	@Deprecated
	public boolean isSmsEnbale() {
		return isSmsEnable();
	}

	@Deprecated
	public void setSmsEnbale(boolean smsEnbale) {
		setSmsEnable(smsEnable);
	}

	public boolean isSmsEnable() {
		return this.smsEnable;
	}

	public void setSmsEnable(boolean smsEnable) {
		this.smsEnable = smsEnable;
	}

	public String getSmsTest() {
		return this.smsTest;
	}

	public void setSmsTest(String smsTest) {
		this.smsTest = smsTest;
	}

	public String getCreditrule() {
		return this.creditrule;
	}

	public void setCreditrule(String creditrule) {
		this.creditrule = creditrule;
	}

	// TODO 上传路径 by hzl 2015/8/15
	public String getUploadFilePath() {
		return this.uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStore_payment() {
		return this.store_payment;
	}

	public void setStore_payment(String store_payment) {
		this.store_payment = store_payment;
	}

	public String getShare_code() {
		return this.share_code;
	}

	public void setShare_code(String share_code) {
		this.share_code = share_code;
	}

	public String getUser_creditrule() {
		return this.user_creditrule;
	}

	public void setUser_creditrule(String user_creditrule) {
		this.user_creditrule = user_creditrule;
	}

	public int getComplaint_time() {
		return this.complaint_time;
	}

	public void setComplaint_time(int complaint_time) {
		this.complaint_time = complaint_time;
	}

	@Deprecated
	public boolean isSecond_domain_open() {
		return this.second_domain_open;
	}

	@Deprecated
	public void setSecond_domain_open(boolean second_domain_open) {
		this.second_domain_open = second_domain_open;
	}

	public String getQq_domain_code() {
		return this.qq_domain_code;
	}

	public void setQq_domain_code(String qq_domain_code) {
		this.qq_domain_code = qq_domain_code;
	}

	public String getImageWebServer() {
		return this.imageWebServer;
	}

	public void setImageWebServer(String imageWebServer) {
		this.imageWebServer = imageWebServer;
	}

	public int getAlipay_fenrun() {
		return this.alipay_fenrun;
	}

	public void setAlipay_fenrun(int alipay_fenrun) {
		this.alipay_fenrun = alipay_fenrun;
	}

	public int getBalance_fenrun() {
		return this.balance_fenrun;
	}

	public void setBalance_fenrun(int balance_fenrun) {
		this.balance_fenrun = balance_fenrun;
	}

	public String getBargain_title() {
		return this.bargain_title;
	}

	public void setBargain_title(String bargain_title) {
		this.bargain_title = bargain_title;
	}

	public String getBargain_state() {
		return this.bargain_state;
	}

	public void setBargain_state(String bargain_state) {
		this.bargain_state = bargain_state;
	}

	public String getDelivery_title() {
		return this.delivery_title;
	}

	public void setDelivery_title(String delivery_title) {
		this.delivery_title = delivery_title;
	}

	public int getDelivery_status() {
		return this.delivery_status;
	}

	public void setDelivery_status(int delivery_status) {
		this.delivery_status = delivery_status;
	}

	public String getService_telphone_list() {
		return this.service_telphone_list;
	}

	public void setService_telphone_list(String service_telphone_list) {
		this.service_telphone_list = service_telphone_list;
	}

	public String getService_qq_list() {
		return this.service_qq_list;
	}

	public void setService_qq_list(String service_qq_list) {
		this.service_qq_list = service_qq_list;
	}

	public int getAuto_order_confirm() {
		return this.auto_order_confirm;
	}

	public void setAuto_order_confirm(int auto_order_confirm) {
		this.auto_order_confirm = auto_order_confirm;
	}

	public int getAuto_order_notice() {
		return this.auto_order_notice;
	}

	public void setAuto_order_notice(int auto_order_notice) {
		this.auto_order_notice = auto_order_notice;
	}

	public String getKuaidi_id() {
		return this.kuaidi_id;
	}

	public void setKuaidi_id(String kuaidi_id) {
		this.kuaidi_id = kuaidi_id;
	}

	public String getUc_database() {
		return this.uc_database;
	}

	public void setUc_database(String uc_database) {
		this.uc_database = uc_database;
	}

	public String getUc_table_preffix() {
		return this.uc_table_preffix;
	}

	public void setUc_table_preffix(String uc_table_preffix) {
		this.uc_table_preffix = uc_table_preffix;
	}

	public String getUc_database_url() {
		return this.uc_database_url;
	}

	public void setUc_database_url(String uc_database_url) {
		this.uc_database_url = uc_database_url;
	}

	public String getUc_database_port() {
		return this.uc_database_port;
	}

	public void setUc_database_port(String uc_database_port) {
		this.uc_database_port = uc_database_port;
	}

	public String getUc_database_username() {
		return this.uc_database_username;
	}

	public void setUc_database_username(String uc_database_username) {
		this.uc_database_username = uc_database_username;
	}

	public String getUc_database_pws() {
		return this.uc_database_pws;
	}

	public void setUc_database_pws(String uc_database_pws) {
		this.uc_database_pws = uc_database_pws;
	}

	public String getUc_api() {
		return this.uc_api;
	}

	public void setUc_api(String uc_api) {
		this.uc_api = uc_api;
	}

	public String getUc_ip() {
		return this.uc_ip;
	}

	public void setUc_ip(String uc_ip) {
		this.uc_ip = uc_ip;
	}

	public String getUc_key() {
		return this.uc_key;
	}

	public void setUc_key(String uc_key) {
		this.uc_key = uc_key;
	}

	public String getUc_appid() {
		return this.uc_appid;
	}

	public void setUc_appid(String uc_appid) {
		this.uc_appid = uc_appid;
	}

	public int getDelivery_amount() {
		return this.delivery_amount;
	}

	public void setDelivery_amount(int delivery_amount) {
		this.delivery_amount = delivery_amount;
	}

	public int getCombin_amount() {
		return this.combin_amount;
	}

	public void setCombin_amount(int combin_amount) {
		this.combin_amount = combin_amount;
	}

	public int getCombin_count() {
		return this.combin_count;
	}

	public void setCombin_count(int combin_count) {
		this.combin_count = combin_count;
	}

	public String getMiscWebServer() {
		return miscWebServer;
	}

	public void setMiscWebServer(String miscPath) {
		this.miscWebServer = miscPath;
	}

	public String getChaturl() {
		return chaturl;
	}

	public void setChaturl(String chaturl) {
		this.chaturl = chaturl;
	}

	public Accessory getWebsiteLoader() {
		return websiteLoader;
	}

	public void setWebsiteLoader(Accessory websiteLoader) {
		this.websiteLoader = websiteLoader;
	}

	public boolean isGroupBuyApply() {
		return groupBuyApply;
	}

	public void setGroupBuyApply(boolean groupBuyApply) {
		this.groupBuyApply = groupBuyApply;
	}
}
