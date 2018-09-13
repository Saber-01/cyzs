package com.org.cygs.util.common;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * String Util.
 * 
 * @version 0.3.5
 * @author Xiaobo Liu
 */
public class StringUtil {

	public static List quoteStrList(List list) {
		List tmpList = list;
		list = new ArrayList();
		Iterator i = tmpList.iterator();
		while (i.hasNext()) {
			String str = (String) i.next();
			str = "'" + str + "'";
			list.add(str);
		}
		return list;
	}

	public static String join(List list, String delim) {
		if (list == null || list.size() < 1)
			return null;
		StringBuffer buf = new StringBuffer();
		Iterator i = list.iterator();
		while (i.hasNext()) {
			buf.append((String) i.next());
			if (i.hasNext())
				buf.append(delim);
		}
		return buf.toString();
	}

	public static List split(String str, String delim) {
		List splitList = null;
		StringTokenizer st = null;

		if (str == null)
			return splitList;

		if (delim != null)
			st = new StringTokenizer(str, delim);
		else
			st = new StringTokenizer(str);

		if (st != null && st.hasMoreTokens()) {
			splitList = new ArrayList();

			while (st.hasMoreTokens())
				splitList.add(st.nextToken());
		}
		return splitList;
	}

	public static String createBreaks(String input, int maxLength) {
		char chars[] = input.toCharArray();
		int len = chars.length;
		StringBuffer buf = new StringBuffer(len);
		int count = 0;
		int cur = 0;
		for (int i = 0; i < len; i++) {
			if (Character.isWhitespace(chars[i]))
				count = 0;
			if (count >= maxLength) {
				count = 0;
				buf.append(chars, cur, i - cur).append(" ");
				cur = i;
			}
			count++;
		}
		buf.append(chars, cur, len - cur);
		return buf.toString();
	}

	/**
	 * Escape SQL tags, ' to ''; \ to \\.
	 * 
	 * @param input
	 *            string to replace
	 * @return string
	 */
	public static String escapeSQLTags(String input) {
		if (input == null || input.length() == 0)
			return input;
		StringBuffer buf = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '\\')
				buf.append("\\\\");
			else if (ch == '\'')
				buf.append("\'\'");
			else
				buf.append(ch);
		}
		return buf.toString();
	}

	/**
	 * Escape HTML tags.
	 * 
	 * @param input
	 *            string to replace
	 * @return string
	 */
	public static String escapeHTMLTags(String input) {
		if (input == null || input.length() == 0)
			return input;
		StringBuffer buf = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<')
				buf.append("&lt;");
			else if (ch == '>')
				buf.append("&gt;");
			else if (ch == '&')
				buf.append("&amp;");
			else if (ch == '"')
				buf.append("&quot;");
			else
				buf.append(ch);
		}
		return buf.toString();
	}

	/**
	 * Convert new lines, \n or \r\n to <BR />.
	 * 
	 * @param input
	 *            string to convert
	 * @return string
	 */
	public static String convertNewlines(String input) {
		input = replace(input, "\r\n", "\n");
		input = replace(input, "\n", "<BR />");
		return input;
	}

	public static String replace(String mainString, String oldString,
			String newString) {
		if (mainString == null)
			return null;
		int i = mainString.lastIndexOf(oldString);
		if (i < 0)
			return mainString;
		StringBuffer mainSb = new StringBuffer(mainString);
		while (i >= 0) {
			mainSb.replace(i, i + oldString.length(), newString);
			i = mainString.lastIndexOf(oldString, i - 1);
		}
		return mainSb.toString();
	}

	/**
	 * Check a string null or blank.
	 * 
	 * @param param
	 *            string to check
	 * @return boolean
	 */
	public static boolean nullOrBlank(String param) {
		return (param == null || param.trim().equals("")) ? true : false;
	}

	public static String notNull(String param) {
		return param == null ? "" : param.trim();
	}

	/**
	 * Parse a string to int.
	 * 
	 * @param param
	 *            string to parse
	 * @return int value, on exception return 0.
	 */

	public static int parseInt(String param) {
		int i = 0;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
			i = (int) parseFloat(param);
		}
		return i;
	}

	public static long parseLong(String param) {
		long l = 0;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
			l = (long) parseDouble(param);
		}
		return l;
	}

	public static float parseFloat(String param) {
		float f = 0;
		try {
			f = Float.parseFloat(param);
		} catch (Exception e) {
			//
		}
		return f;
	}

	public static Double[] parseDouble(String[] param) {
		if (param == null)
			return null;
		else {
			Double[] d = new Double[param.length];
			for (int i = 0; i < param.length; i++) {
				d[i] = parseDouble(param[i]);
			}
			return d;
		}
	}

	public static double parseDouble(String param) {
		double d = 0;
		try {
			d = Double.parseDouble(param);
		} catch (Exception e) {
			//
		}
		return d;
	}

	/**
	 * Parse a string to boolean.
	 * 
	 * @param param
	 *            string to parse
	 * @return boolean value, if param begin with(1,y,Y,t,T) return true, on
	 *         exception return false.
	 */
	public static boolean parseBoolean(String param) {
		if (nullOrBlank(param))
			return false;
		switch (param.charAt(0)) {
		case '1':
		case 'y':
		case 'Y':
		case 't':
		case 'T':
			return true;
		}
		return false;
	}

	/**
	 * Convert URL .
	 * 
	 * @param input
	 *            string to convert
	 * @return string
	 */
	public static String convertURL(String input) {
		if (input == null || input.length() == 0)
			return input;
		StringBuffer buf = new StringBuffer(input.length() + 25);
		char chars[] = input.toCharArray();
		int len = input.length();
		int index = -1;
		int i = 0;
		int j = 0;
		int oldend = 0;
		while (++index < len) {
			char cur = chars[i = index];
			j = -1;
			if ((cur == 'f' && index < len - 6 && chars[++i] == 't'
					&& chars[++i] == 'p' || cur == 'h' && (i = index) < len - 7
					&& chars[++i] == 't' && chars[++i] == 't'
					&& chars[++i] == 'p'
					&& (chars[++i] == 's' || chars[--i] == 'p'))
					&& i < len - 4
					&& chars[++i] == ':'
					&& chars[++i] == '/'
					&& chars[++i] == '/')
				j = ++i;
			if (j > 0) {
				if (index == 0 || (cur = chars[index - 1]) != '\''
						&& cur != '"' && cur != '<' && cur != '=') {
					cur = chars[j];
					while (j < len) {
						if (cur == ' ' || cur == '\t' || cur == '\''
								|| cur == '"' || cur == '<' || cur == '['
								|| cur == '\n' || cur == '\r' && j < len - 1
								&& chars[j + 1] == '\n')
							break;
						if (++j < len)
							cur = chars[j];
					}
					cur = chars[j - 1];
					if (cur == '.' || cur == ',' || cur == ')' || cur == ']')
						j--;
					buf.append(chars, oldend, index - oldend);
					buf.append("<a href=\"");
					buf.append(chars, index, j - index);
					buf.append('"');
					buf.append(" target=\"_blank\"");
					buf.append('>');
					buf.append(chars, index, j - index);
					buf.append("</a>");
				} else {
					buf.append(chars, oldend, j - oldend);
				}
				oldend = index = j;
			} else if (cur == '[' && index < len - 6
					&& chars[i = index + 1] == 'u' && chars[++i] == 'r'
					&& chars[++i] == 'l'
					&& (chars[++i] == '=' || chars[i] == ' ')) {
				j = ++i;
				int u2;
				int u1 = u2 = input.indexOf("]", j);
				if (u1 > 0)
					u2 = input.indexOf("[/url]", u1 + 1);
				if (u2 < 0) {
					buf.append(chars, oldend, j - oldend);
					oldend = j;
				} else {
					buf.append(chars, oldend, index - oldend);
					buf.append("<a href =\"");
					String href = input.substring(j, u1).trim();
					if (href.indexOf("javascript:") == -1
							&& href.indexOf("file:") == -1)
						buf.append(href);
					buf.append("\" target=\"_blank");
					buf.append("\">");
					buf.append(input.substring(u1 + 1, u2).trim());
					buf.append("</a>");
					oldend = u2 + 6;
				}
				index = oldend;
			}
		}
		if (oldend < len)
			buf.append(chars, oldend, len - oldend);
		return buf.toString();
	}

	/**
	 * Display a string in html page, call methods: escapeHTMLTags, convertURL,
	 * convertNewlines.
	 * 
	 * @param input
	 *            string to display
	 * @return string
	 */
	public static String dspHtml(String input) {
		String str = input;
		str = createBreaks(str, 80);
		str = escapeHTMLTags(str);
		str = convertURL(str);
		str = convertNewlines(str);
		return str;
	}

	// 中文转换函数
	public static String trans(Object o) {
		if (o == null)
			return null;
		else
			return trans(o.toString());
	}

	public static String trans(String chi) {

		String result = null;
		if (chi == null)
			return null;
		byte tempISO[];
		try {
			tempISO = chi.getBytes("iso-8859-1");
			result = new String(tempISO);

			if (tempISO.length > 0) {
				for (int i = 0; i < tempISO.length; i++) {
					if (tempISO[i] < -1) {

						return result;
					}
				}
			}

			else
				return chi;
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		}
		return chi;
	}

	// 将编号前面带有字母的编号加一
	public static String autoIncrement(String str) {
		String output = null;
		int locate = -1;
		String temp = null;
		String temp2 = null;
		long num;
		// 找到第一个数字的位置
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '1' && str.charAt(i) <= '9') {
				locate = i;
				break;
			}
		}
		// 取子串
		if (locate == -1)
			locate = str.length() - 1;
		temp = str.substring(locate);
		// 转换为数值
		num = StringUtil.parseLong(temp);
		num = num + 1;
		temp2 = "" + num;
		// 判断有进位的情况
		if (temp2.length() > temp.length()) {
			if ((locate - 1) >= 0 && (str.charAt(locate - 1) == '0'))
				locate = locate - 1;
		}

		if (locate > 0)
			output = str.substring(0, locate) + temp2;
		else
			output = temp2;
		return output;
	}

	public static String getStringValue(Object o) {
		return o != null ? o.toString() : null;
	}

	// for some reason....nothing to say....if someone can be more careful....
	// don't waste our time....
	public static String removeSlash(String str) {
		if (str == null)
			return null;
		String sarray[] = str.split("/");
		String temp = "";
		for (int i = 0; i < sarray.length; i++) {
			if (sarray[i].length() > 0)
				temp += "/" + sarray[i];
		}
		return temp;
	}
	/**
	 * 王健 2008-11-18
	 * @param o
	 * @return
	 */
	public static Integer getInteger(Object o) {
		Integer i = null;
		if(o instanceof Integer){
			i = (Integer)o;
		}else if(o instanceof String){
			i = StringUtil.parseInt((String)o);
		}else{
		}
		return i;
	}
	public static Double getDouble(Object o) {
		double i = 0;
		if(o!=null){
			if(o instanceof Double){
				i = (Double)o;
			}else if(o instanceof String){
				i = StringUtil.parseDouble((String)o);
			}
		}
		return i;
	}
	//在数字number前面添加length个0，返回字符串
	public static String addZeroToString(int number,int length){
		return String.format("%0"+length+"d", number);
	}
	
	//
}