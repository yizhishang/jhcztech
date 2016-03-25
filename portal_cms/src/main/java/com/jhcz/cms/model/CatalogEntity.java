package com.jhcz.cms.model;

public class CatalogEntity {
    /**
     * column JHCMS.T_CATALOG.CATALOG_ID
     */
    private Long catalogId;

    /**
     * column JHCMS.T_CATALOG.PARENT_ID
     */
    private Long parentId;

    /**
     * column JHCMS.T_CATALOG.NAME
     */
    private String name;

    /**
     * column JHCMS.T_CATALOG.CATALOG_NO
     */
    private String catalogNo;

    /**
     * column JHCMS.T_CATALOG.SITENO
     */
    private String siteno;

    /**
     * column JHCMS.T_CATALOG.ORDERLINE
     */
    private Long orderline;

    /**
     * column JHCMS.T_CATALOG.LAYER
     */
    private Long layer;

    /**
     * column JHCMS.T_CATALOG.ROUTE
     */
    private String route;

    /**
     * column JHCMS.T_CATALOG.STATE
     */
    private Long state;

    /**
     * column JHCMS.T_CATALOG.NEED_PUBLISH
     */
    private Long needPublish;

    /**
     * column JHCMS.T_CATALOG.NEED_APPROVE
     */
    private Long needApprove;

    /**
     * column JHCMS.T_CATALOG.IS_ROOT
     */
    private Long isRoot;

    /**
     * column JHCMS.T_CATALOG.IS_SYSTEM
     */
    private Long isSystem;

    /**
     * column JHCMS.T_CATALOG.PAGE_TYPE
     */
    private Long pageType;

    /**
     * column JHCMS.T_CATALOG.LINK_URL
     */
    private String linkUrl;

    /**
     * column JHCMS.T_CATALOG.PUBLISH_PATH
     */
    private String publishPath;

    /**
     * column JHCMS.T_CATALOG.FILE_TYPE
     */
    private String fileType;

    /**
     * column JHCMS.T_CATALOG.SMALL_IMAGE
     */
    private String smallImage;

    /**
     * column JHCMS.T_CATALOG.LARGE_IMAGE
     */
    private String largeImage;

    /**
     * column JHCMS.T_CATALOG.TYPE
     */
    private Long type;

    /**
     * column JHCMS.T_CATALOG.CREATE_BY
     */
    private String createBy;

    /**
     * column JHCMS.T_CATALOG.CREATE_DATE
     */
    private String createDate;

    /**
     * column JHCMS.T_CATALOG.MODIFIED_BY
     */
    private String modifiedBy;

    /**
     * column JHCMS.T_CATALOG.MODIFIED_DATE
     */
    private String modifiedDate;

    /**
     * column JHCMS.T_CATALOG.USER_RIGHT
     */
    private Long userRight;

    /**
     * column JHCMS.T_CATALOG.SPECIAL_URL
     */
    private String specialUrl;

    /**
     * column JHCMS.T_CATALOG.COLUMN_LEVEL
     */
    private Long columnLevel;

    /**
     * column JHCMS.T_CATALOG.CHILDRENNUM
     */
    private Long childrennum;

    /**
     * column JHCMS.T_CATALOG.INHERIT_MODE
     */
    private Long inheritMode;

    /**
     * column JHCMS.T_CATALOG.INHERIT_FIELD
     */
    private Long inheritField;

    /**
     * column JHCMS.T_CATALOG.ATTRIBUTE
     */
    private Long attribute;

    /**
     * column JHCMS.T_CATALOG.SOURCE_ID
     */
    private Long sourceId;

    /**
     * column JHCMS.T_CATALOG.DESCRIPTION
     */
    private String description;

    /**
     * This method returns JHCMS.T_CATALOG.CATALOG_ID
     @return JHCMS.T_CATALOG.CATALOG_ID
     */
    public Long getCatalogId() {
        return catalogId;
    }

    /**
     * This method sets JHCMS.T_CATALOG.CATALOG_ID
     @param catalogId the value for JHCMS.T_CATALOG.CATALOG_ID
     */
    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * This method returns JHCMS.T_CATALOG.PARENT_ID
     @return JHCMS.T_CATALOG.PARENT_ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method sets JHCMS.T_CATALOG.PARENT_ID
     @param parentId the value for JHCMS.T_CATALOG.PARENT_ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method returns JHCMS.T_CATALOG.NAME
     @return JHCMS.T_CATALOG.NAME
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets JHCMS.T_CATALOG.NAME
     @param name the value for JHCMS.T_CATALOG.NAME
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.CATALOG_NO
     @return JHCMS.T_CATALOG.CATALOG_NO
     */
    public String getCatalogNo() {
        return catalogNo;
    }

    /**
     * This method sets JHCMS.T_CATALOG.CATALOG_NO
     @param catalogNo the value for JHCMS.T_CATALOG.CATALOG_NO
     */
    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo == null ? null : catalogNo.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.SITENO
     @return JHCMS.T_CATALOG.SITENO
     */
    public String getSiteno() {
        return siteno;
    }

    /**
     * This method sets JHCMS.T_CATALOG.SITENO
     @param siteno the value for JHCMS.T_CATALOG.SITENO
     */
    public void setSiteno(String siteno) {
        this.siteno = siteno == null ? null : siteno.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.ORDERLINE
     @return JHCMS.T_CATALOG.ORDERLINE
     */
    public Long getOrderline() {
        return orderline;
    }

    /**
     * This method sets JHCMS.T_CATALOG.ORDERLINE
     @param orderline the value for JHCMS.T_CATALOG.ORDERLINE
     */
    public void setOrderline(Long orderline) {
        this.orderline = orderline;
    }

    /**
     * This method returns JHCMS.T_CATALOG.LAYER
     @return JHCMS.T_CATALOG.LAYER
     */
    public Long getLayer() {
        return layer;
    }

    /**
     * This method sets JHCMS.T_CATALOG.LAYER
     @param layer the value for JHCMS.T_CATALOG.LAYER
     */
    public void setLayer(Long layer) {
        this.layer = layer;
    }

    /**
     * This method returns JHCMS.T_CATALOG.ROUTE
     @return JHCMS.T_CATALOG.ROUTE
     */
    public String getRoute() {
        return route;
    }

    /**
     * This method sets JHCMS.T_CATALOG.ROUTE
     @param route the value for JHCMS.T_CATALOG.ROUTE
     */
    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.STATE
     @return JHCMS.T_CATALOG.STATE
     */
    public Long getState() {
        return state;
    }

    /**
     * This method sets JHCMS.T_CATALOG.STATE
     @param state the value for JHCMS.T_CATALOG.STATE
     */
    public void setState(Long state) {
        this.state = state;
    }

    /**
     * This method returns JHCMS.T_CATALOG.NEED_PUBLISH
     @return JHCMS.T_CATALOG.NEED_PUBLISH
     */
    public Long getNeedPublish() {
        return needPublish;
    }

    /**
     * This method sets JHCMS.T_CATALOG.NEED_PUBLISH
     @param needPublish the value for JHCMS.T_CATALOG.NEED_PUBLISH
     */
    public void setNeedPublish(Long needPublish) {
        this.needPublish = needPublish;
    }

    /**
     * This method returns JHCMS.T_CATALOG.NEED_APPROVE
     @return JHCMS.T_CATALOG.NEED_APPROVE
     */
    public Long getNeedApprove() {
        return needApprove;
    }

    /**
     * This method sets JHCMS.T_CATALOG.NEED_APPROVE
     @param needApprove the value for JHCMS.T_CATALOG.NEED_APPROVE
     */
    public void setNeedApprove(Long needApprove) {
        this.needApprove = needApprove;
    }

    /**
     * This method returns JHCMS.T_CATALOG.IS_ROOT
     @return JHCMS.T_CATALOG.IS_ROOT
     */
    public Long getIsRoot() {
        return isRoot;
    }

    /**
     * This method sets JHCMS.T_CATALOG.IS_ROOT
     @param isRoot the value for JHCMS.T_CATALOG.IS_ROOT
     */
    public void setIsRoot(Long isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * This method returns JHCMS.T_CATALOG.IS_SYSTEM
     @return JHCMS.T_CATALOG.IS_SYSTEM
     */
    public Long getIsSystem() {
        return isSystem;
    }

    /**
     * This method sets JHCMS.T_CATALOG.IS_SYSTEM
     @param isSystem the value for JHCMS.T_CATALOG.IS_SYSTEM
     */
    public void setIsSystem(Long isSystem) {
        this.isSystem = isSystem;
    }

    /**
     * This method returns JHCMS.T_CATALOG.PAGE_TYPE
     @return JHCMS.T_CATALOG.PAGE_TYPE
     */
    public Long getPageType() {
        return pageType;
    }

    /**
     * This method sets JHCMS.T_CATALOG.PAGE_TYPE
     @param pageType the value for JHCMS.T_CATALOG.PAGE_TYPE
     */
    public void setPageType(Long pageType) {
        this.pageType = pageType;
    }

    /**
     * This method returns JHCMS.T_CATALOG.LINK_URL
     @return JHCMS.T_CATALOG.LINK_URL
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * This method sets JHCMS.T_CATALOG.LINK_URL
     @param linkUrl the value for JHCMS.T_CATALOG.LINK_URL
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.PUBLISH_PATH
     @return JHCMS.T_CATALOG.PUBLISH_PATH
     */
    public String getPublishPath() {
        return publishPath;
    }

    /**
     * This method sets JHCMS.T_CATALOG.PUBLISH_PATH
     @param publishPath the value for JHCMS.T_CATALOG.PUBLISH_PATH
     */
    public void setPublishPath(String publishPath) {
        this.publishPath = publishPath == null ? null : publishPath.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.FILE_TYPE
     @return JHCMS.T_CATALOG.FILE_TYPE
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * This method sets JHCMS.T_CATALOG.FILE_TYPE
     @param fileType the value for JHCMS.T_CATALOG.FILE_TYPE
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.SMALL_IMAGE
     @return JHCMS.T_CATALOG.SMALL_IMAGE
     */
    public String getSmallImage() {
        return smallImage;
    }

    /**
     * This method sets JHCMS.T_CATALOG.SMALL_IMAGE
     @param smallImage the value for JHCMS.T_CATALOG.SMALL_IMAGE
     */
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage == null ? null : smallImage.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.LARGE_IMAGE
     @return JHCMS.T_CATALOG.LARGE_IMAGE
     */
    public String getLargeImage() {
        return largeImage;
    }

    /**
     * This method sets JHCMS.T_CATALOG.LARGE_IMAGE
     @param largeImage the value for JHCMS.T_CATALOG.LARGE_IMAGE
     */
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage == null ? null : largeImage.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.TYPE
     @return JHCMS.T_CATALOG.TYPE
     */
    public Long getType() {
        return type;
    }

    /**
     * This method sets JHCMS.T_CATALOG.TYPE
     @param type the value for JHCMS.T_CATALOG.TYPE
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * This method returns JHCMS.T_CATALOG.CREATE_BY
     @return JHCMS.T_CATALOG.CREATE_BY
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method sets JHCMS.T_CATALOG.CREATE_BY
     @param createBy the value for JHCMS.T_CATALOG.CREATE_BY
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.CREATE_DATE
     @return JHCMS.T_CATALOG.CREATE_DATE
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method sets JHCMS.T_CATALOG.CREATE_DATE
     @param createDate the value for JHCMS.T_CATALOG.CREATE_DATE
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.MODIFIED_BY
     @return JHCMS.T_CATALOG.MODIFIED_BY
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * This method sets JHCMS.T_CATALOG.MODIFIED_BY
     @param modifiedBy the value for JHCMS.T_CATALOG.MODIFIED_BY
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.MODIFIED_DATE
     @return JHCMS.T_CATALOG.MODIFIED_DATE
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * This method sets JHCMS.T_CATALOG.MODIFIED_DATE
     @param modifiedDate the value for JHCMS.T_CATALOG.MODIFIED_DATE
     */
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : modifiedDate.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.USER_RIGHT
     @return JHCMS.T_CATALOG.USER_RIGHT
     */
    public Long getUserRight() {
        return userRight;
    }

    /**
     * This method sets JHCMS.T_CATALOG.USER_RIGHT
     @param userRight the value for JHCMS.T_CATALOG.USER_RIGHT
     */
    public void setUserRight(Long userRight) {
        this.userRight = userRight;
    }

    /**
     * This method returns JHCMS.T_CATALOG.SPECIAL_URL
     @return JHCMS.T_CATALOG.SPECIAL_URL
     */
    public String getSpecialUrl() {
        return specialUrl;
    }

    /**
     * This method sets JHCMS.T_CATALOG.SPECIAL_URL
     @param specialUrl the value for JHCMS.T_CATALOG.SPECIAL_URL
     */
    public void setSpecialUrl(String specialUrl) {
        this.specialUrl = specialUrl == null ? null : specialUrl.trim();
    }

    /**
     * This method returns JHCMS.T_CATALOG.COLUMN_LEVEL
     @return JHCMS.T_CATALOG.COLUMN_LEVEL
     */
    public Long getColumnLevel() {
        return columnLevel;
    }

    /**
     * This method sets JHCMS.T_CATALOG.COLUMN_LEVEL
     @param columnLevel the value for JHCMS.T_CATALOG.COLUMN_LEVEL
     */
    public void setColumnLevel(Long columnLevel) {
        this.columnLevel = columnLevel;
    }

    /**
     * This method returns JHCMS.T_CATALOG.CHILDRENNUM
     @return JHCMS.T_CATALOG.CHILDRENNUM
     */
    public Long getChildrennum() {
        return childrennum;
    }

    /**
     * This method sets JHCMS.T_CATALOG.CHILDRENNUM
     @param childrennum the value for JHCMS.T_CATALOG.CHILDRENNUM
     */
    public void setChildrennum(Long childrennum) {
        this.childrennum = childrennum;
    }

    /**
     * This method returns JHCMS.T_CATALOG.INHERIT_MODE
     @return JHCMS.T_CATALOG.INHERIT_MODE
     */
    public Long getInheritMode() {
        return inheritMode;
    }

    /**
     * This method sets JHCMS.T_CATALOG.INHERIT_MODE
     @param inheritMode the value for JHCMS.T_CATALOG.INHERIT_MODE
     */
    public void setInheritMode(Long inheritMode) {
        this.inheritMode = inheritMode;
    }

    /**
     * This method returns JHCMS.T_CATALOG.INHERIT_FIELD
     @return JHCMS.T_CATALOG.INHERIT_FIELD
     */
    public Long getInheritField() {
        return inheritField;
    }

    /**
     * This method sets JHCMS.T_CATALOG.INHERIT_FIELD
     @param inheritField the value for JHCMS.T_CATALOG.INHERIT_FIELD
     */
    public void setInheritField(Long inheritField) {
        this.inheritField = inheritField;
    }

    /**
     * This method returns JHCMS.T_CATALOG.ATTRIBUTE
     @return JHCMS.T_CATALOG.ATTRIBUTE
     */
    public Long getAttribute() {
        return attribute;
    }

    /**
     * This method sets JHCMS.T_CATALOG.ATTRIBUTE
     @param attribute the value for JHCMS.T_CATALOG.ATTRIBUTE
     */
    public void setAttribute(Long attribute) {
        this.attribute = attribute;
    }

    /**
     * This method returns JHCMS.T_CATALOG.SOURCE_ID
     @return JHCMS.T_CATALOG.SOURCE_ID
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * This method sets JHCMS.T_CATALOG.SOURCE_ID
     @param sourceId the value for JHCMS.T_CATALOG.SOURCE_ID
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * This method returns JHCMS.T_CATALOG.DESCRIPTION
     @return JHCMS.T_CATALOG.DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets JHCMS.T_CATALOG.DESCRIPTION
     @param description the value for JHCMS.T_CATALOG.DESCRIPTION
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}