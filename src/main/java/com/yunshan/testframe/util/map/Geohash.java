package com.yunshan.testframe.util.map;

import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.HashMap;

/**
 * @Title: Geohash.java
 * @Package com.yunshan.testframe.util.map
 * @Description: geohash算法计算经纬度
 * @author <a href="http://www.wanglay.com">Lei Wang</a>
 * @date 2016年6月7日 下午5:08:09
 * @version V1.0 Update Logs: *
 *          **************************************************** Name: Date:
 *          Description: ******************************************************
 */
public class Geohash {

	private static int numbits = 6 * 5;
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();
	static {
		int i = 0;
		for (char c : digits) {
			lookup.put(c, i++);
		}
	}

	public static void main(String[] args) {
		double[] latlon = new Geohash().decode("dj248j248j24");
		System.out.println(latlon[0] + " " + latlon[1]);

		Geohash e = new Geohash();
		String s = e.encode(30, -90.0);
		System.out.println(s);
		latlon = e.decode(s);
		DecimalFormat df = new DecimalFormat("0.00000");
		System.out.println(df.format(latlon[0]) + ", " + df.format(latlon[1]));

	}

	/**
	 * 将Geohash字串解码成经纬值
	 * 
	 * @param geohash
	 *            待解码的Geohash字串
	 * @return 经纬值数组
	 */
	public double[] decode(String geohash) {
		StringBuilder buffer = new StringBuilder();
		for (char c : geohash.toCharArray()) {
			int i = lookup.get(c) + 32;
			buffer.append(Integer.toString(i, 2).substring(1));
		}

		BitSet lonset = new BitSet();
		BitSet latset = new BitSet();

		// even bits
		int j = 0;
		for (int i = 0; i < numbits * 2; i += 2) {
			boolean isSet = false;
			if (i < buffer.length())
				isSet = buffer.charAt(i) == '1';
			lonset.set(j++, isSet);
		}

		// odd bits
		j = 0;
		for (int i = 1; i < numbits * 2; i += 2) {
			boolean isSet = false;
			if (i < buffer.length())
				isSet = buffer.charAt(i) == '1';
			latset.set(j++, isSet);
		}

		double lat = decode(latset, -90, 90);
		double lon = decode(lonset, -180, 180);

		DecimalFormat df = new DecimalFormat("0.00000");
		return new double[] { Double.parseDouble(df.format(lat)),
				Double.parseDouble(df.format(lon)) };
	}

	/**
	 * 根据二进制编码串和指定的数值变化范围，计算得到经/纬值
	 * 
	 * @param bs
	 *            经/纬二进制编码串
	 * @param floor
	 *            下限
	 * @param ceiling
	 *            上限
	 * @return 经/纬值
	 */
	private double decode(BitSet bs, double floor, double ceiling) {
		double mid = 0;
		for (int i = 0; i < bs.length(); i++) {
			mid = (floor + ceiling) / 2;
			if (bs.get(i))
				floor = mid;
			else
				ceiling = mid;
		}
		return mid;
	}

	/**
	 * 根据经纬值得到Geohash字串
	 * 
	 * @param lat
	 *            纬度值
	 * @param lon
	 *            经度值
	 * @return Geohash字串
	 */
	public String encode(double lat, double lon) {
		BitSet latbits = getBits(lat, -90, 90);
		BitSet lonbits = getBits(lon, -180, 180);
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < numbits; i++) {
			buffer.append((lonbits.get(i)) ? '1' : '0');
			buffer.append((latbits.get(i)) ? '1' : '0');
		}
		return base32(Long.parseLong(buffer.toString(), 2));
	}

	/**
	 * 将二进制编码串转换成Geohash字串
	 * 
	 * @param i
	 *            二进制编码串
	 * @return Geohash字串
	 */
	public static String base32(long i) {
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		if (!negative)
			i = -i;
		while (i <= -32) {
			buf[charPos--] = digits[(int) (-(i % 32))];
			i /= 32;
		}
		buf[charPos] = digits[(int) (-i)];

		if (negative)
			buf[--charPos] = '-';
		return new String(buf, charPos, (65 - charPos));
	}

	/**
	 * 得到经/纬度对应的二进制编码
	 * 
	 * @param lat
	 *            经/纬度
	 * @param floor
	 *            下限
	 * @param ceiling
	 *            上限
	 * @return 二进制编码串
	 */
	private BitSet getBits(double lat, double floor, double ceiling) {
		BitSet buffer = new BitSet(numbits);
		for (int i = 0; i < numbits; i++) {
			double mid = (floor + ceiling) / 2;
			if (lat >= mid) {
				buffer.set(i);
				floor = mid;
			} else {
				ceiling = mid;
			}
		}
		return buffer;
	}

}
