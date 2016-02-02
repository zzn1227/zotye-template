package com.utils;

public class Constants {

    /**
     * session key
     */
    public static final String USER_AUTHORYIDS     = "zotye_user_authoryIds_987362226888";   // 用户的权限ids
                                                                                              // ，HttpSession以该值作为key
    public static final String USER_AUTHORY_PARAMS = "zotye_user_authoryParams_988882226888"; // 用户的权限的参数值，HttpSession以该值作为key,value为String类型
                                                                                              // 以","作为分隔符

    /**
     * 用户状态
     */
    public static final int    USER_ENABLE         = 0;                                      // 正常
    public static final int    USER_DISABLE        = 1;                                      // 冻结

    public static final String NORMAL              = "0";                                    // 正常
    public static final String DELETED             = "1";                                    // 已删除

}
