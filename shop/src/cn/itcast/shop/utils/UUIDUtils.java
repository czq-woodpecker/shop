package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * 生成激活码
 * @author paperwings
 *
 */
public class UUIDUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
