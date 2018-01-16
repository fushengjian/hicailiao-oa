package com.tomowork.oa.foundation.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tomowork.oa.domain.IdEntity;

@Cacheable
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "oa_accessory")
public class Accessory extends IdEntity implements Serializable {

	private static final long serialVersionUID = -9116685587591991837L;

	/**
	 * 图片展示名称，用于搜索图片
	 */
	private String displayName;

	/**
	 * 图片存储名称
	 */
	private String name;

	/**
	 * 图片存储相对路径
	 */
	private String path;

	/**
	 * 图片大小
	 */
	private float size;

	/**
	 * 图片宽度
	 */
	private int width;

	/**
	 * 图片高度
	 */
	private int height;

	/**
	 * 图片类型
	 */
	private String ext;

	private String info;

	/**
	 * 图片所属用户
	 */
	/*@ManyToOne(fetch = FetchType.LAZY)
	private User user;*/

	/**
	 *  所属相册ID
	 */
	/*@ManyToOne(fetch = FetchType.LAZY)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private Album album;*/

	@ManyToOne(fetch = FetchType.LAZY)
	private SysConfig config; //系统配置类

	/*@OneToMany(mappedBy = "goods_main_photo")
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Goods> goods_main_list = new ArrayList<>();

	@OneToMany(mappedBy = "orderform_main_list")
	private List<OrderFormItem> orderform_main_list = new ArrayList<>();

	@ManyToMany(mappedBy = "goods_photos")
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Goods> goods_list = new ArrayList<>();*/

	@Column(name = "url_hash")
	private Integer urlHash;

	/*public List<Goods> getGoods_main_list() {
		return this.goods_main_list;
	}

	public void setGoods_main_list(List<Goods> goods_main_list) {
		this.goods_main_list = goods_main_list;
	}

	public List<Goods> getGoods_list() {
		return this.goods_list;
	}

	public void setGoods_list(List<Goods> goods_list) {
		this.goods_list = goods_list;
	}*/

	/*public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public float getSize() {
		return this.size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	/*public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}*/


	public SysConfig getConfig() {
		return this.config;
	}

	public void setConfig(SysConfig config) {
		this.config = config;
	}

	/*public boolean isCoverAlbum() {
		return album != null && album.getAlbum_cover() != null && getId().equals(album.getAlbum_cover().getId());
	}*/

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/*public List<OrderFormItem> getOrderform_main_list() {
		return orderform_main_list;
	}

	public void setOrderform_main_list(List<OrderFormItem> orderform_main_list) {
		this.orderform_main_list = orderform_main_list;
	}*/

	public Integer getUrlHash() {
		return urlHash;
	}

	public void setUrlHash(Integer urlHash) {
		this.urlHash = urlHash;
	}

	public String getUrl() {
		return path + '/' + name;
	}

	public String getSmallUrl() {
		return getUrl() + "_small." + ext;
	}
}
